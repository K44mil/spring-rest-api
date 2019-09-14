package org.learning.carrental.controller;

import java.net.URI;
import java.util.Collections;

import javax.validation.Valid;

import org.learning.carrental.exception.AppException;
import org.learning.carrental.model.Customer;
import org.learning.carrental.model.Employee;
import org.learning.carrental.model.Role;
import org.learning.carrental.model.RoleName;
import org.learning.carrental.model.User;
import org.learning.carrental.model.embedded.Address;
import org.learning.carrental.model.embedded.Name;
import org.learning.carrental.payload.ApiResponse;
import org.learning.carrental.payload.JwtAuthenticationResponse;
import org.learning.carrental.payload.LoginRequest;
import org.learning.carrental.payload.SignUpRequest;
import org.learning.carrental.repository.CustomerRepository;
import org.learning.carrental.repository.EmployeeRepository;
import org.learning.carrental.repository.RoleRepository;
import org.learning.carrental.repository.UserRepository;
import org.learning.carrental.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	CustomerRepository customerRepository;
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	JwtTokenProvider tokenProvider;
	
	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
		
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
						loginRequest.getUsernameOrEmail(),
						loginRequest.getPassword()
				)
		);
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		String jwt = tokenProvider.generateToken(authentication);
		return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
	}
	
	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
		// Validate user data
		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			return new ResponseEntity<ApiResponse>(new ApiResponse(false, "Username is already taken!"),
					HttpStatus.BAD_REQUEST);	
		}
		
		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return new ResponseEntity<ApiResponse>(new ApiResponse(false, "Email Address already in use!"),
					HttpStatus.BAD_REQUEST);
		}
		// Validate customer data
		if (signUpRequest.isCustomer()) {		
			if (customerRepository.existsByPesel(signUpRequest.getCustomer().getPesel())) {
				return new ResponseEntity<ApiResponse>(new ApiResponse(false, "PESEL is already in db."),
						HttpStatus.BAD_REQUEST);
			}		
			if (customerRepository.existsByPhoneNumber(signUpRequest.getCustomer().getPhoneNumber())) {
				return new ResponseEntity<ApiResponse>(new ApiResponse(false, "Phone number is already in db."),
						HttpStatus.BAD_REQUEST);
			}	
			if (customerRepository.existsByDriverLicenseNumber(signUpRequest.getCustomer().getDriverLicenseNumber())) {
				return new ResponseEntity<ApiResponse>(new ApiResponse(false, "Driver's license number is already in db."),
						HttpStatus.BAD_REQUEST);
			}	
			if (!validateCustomer(signUpRequest.getCustomer())) {
				return new ResponseEntity<ApiResponse>(new ApiResponse(false, "Customer data is invalid."),
						HttpStatus.BAD_REQUEST);
			}		
		} else {
			// Validate employee data
			if (!validateEmployee(signUpRequest.getEmployee())) {
				return new ResponseEntity<ApiResponse>(new ApiResponse(false, "Employee data is invalid."),
						HttpStatus.BAD_REQUEST);
			}
		}

		// Creating user's account
		User user = new User(signUpRequest.getUsername(), signUpRequest.getEmail(), signUpRequest.getPassword(),
							signUpRequest.isCustomer(), signUpRequest.isActive(), signUpRequest.isBlocked());
		
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		
		Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
				.orElseThrow(() -> new AppException("User Role not set."));
		
		user.setRoles(Collections.singleton(userRole));
		
		User result = userRepository.save(user);
		
		if (result.isCustomer()) {
			// Create Customer Profile
			Customer customerToSave = createCustomer(signUpRequest.getCustomer(), result);
			customerRepository.save(customerToSave);
		} else {
			// Create Employee Profile
			Employee employeeToSave = createEmployee(signUpRequest.getEmployee(), result);
			employeeRepository.save(employeeToSave);
		}
		
		URI location = ServletUriComponentsBuilder
				.fromCurrentContextPath().path("/api/users/{username}")
				.buildAndExpand(result.getUsername()).toUri();
		
		return ResponseEntity.created(location).body(new ApiResponse(true, "User registered successfully"));
	}
	
	private Customer createCustomer(Customer customer, User user) {	
		Name name = new Name(customer.getName().getFirstName(),
							 customer.getName().getMiddleName(),
							 customer.getName().getLastName());
		
		Address address = new Address(customer.getAddress().getCity(),
									  customer.getAddress().getStreet(),
									  customer.getAddress().getHouseNumber(),
									  customer.getAddress().getFlatNumber(),
									  customer.getAddress().getZipCode());
		
		return new Customer(name, address, customer.getPesel(), customer.getDriverLicenseNumber(), customer.getPhoneNumber(), user);
	}
	
	private Employee createEmployee(Employee employee, User user) {
		Name name = new Name(employee.getName().getFirstName(),
							 employee.getName().getMiddleName(),
							 employee.getName().getLastName());
		
		return new Employee(name, employee.getSalary(), employee.getHireDate(), employee.getBirthDate(),
							employee.getPhoneNumber(), employee.getGender(), user);
	}
	
	private boolean validateCustomer(Customer customer) {
		if (customer.isValid()) {
			return true;
		}
		return false;
	}
	
	private boolean validateEmployee(Employee employee) {
		if (employee.isValid()) {
			return true;
		}
		return false;
	}
	
}
