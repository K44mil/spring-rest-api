package org.learning.carrental.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SignUpCustomerRequest implements Serializable {
	
	//# User
	@JsonProperty
	private String username;
	@JsonProperty
	private String password;
	@JsonProperty
	private String email;
	@JsonProperty
	private Boolean isCustomer;
	@JsonProperty
	private Boolean isActive;
	@JsonProperty
	private Boolean isBlocked;
	
	//# Customer
	// name
	@JsonProperty
	private String firstName;
	@JsonProperty
	private String middleName;
	@JsonProperty
	private String lastName;
	// address
	@JsonProperty
	private String city;
	@JsonProperty
	private String street;
	@JsonProperty
	private String houseNumber;
	@JsonProperty
	private String flatNumber;
	@JsonProperty
	private String zipCode;
	
	@JsonProperty
	private String pesel;
	@JsonProperty
	private String driverLicenseNumber;
	@JsonProperty
	private String phoneNumber;
	
	public static Builder builder() {
		return new Builder();
	}
	
	public static final class Builder {
		
		//# User
		private String username = "";
		private String password = "";
		private String email = "";
		private Boolean isCustomer = true;
		private Boolean isActive = false;
		private Boolean isBlocked = false;
		
		//# Customer
		// name
		private String firstName = "";
		private String middleName = "";
		private String lastName = ""; 
		// address
		private String city = "";
		private String street = "";
		private String houseNumber = "";
		private String flatNumber = "";
		private String zipCode = "";
		
		private String pesel = "";
		private String driverLicenseNumber = "";
		private String phoneNumber = "";
	
		public Builder username(String username) {
			this.username = username;
			return this;
		}
		
		public Builder password(String password) {
			this.password = password;
			return this;
		}
		
		public Builder email(String email) {
			this.email = email;
			return this;
		}
		
		public Builder isCustomer(Boolean isCustomer) {
			this.isCustomer = isCustomer;
			return this;
		}
		
		public Builder isActive(Boolean isActive) {
			this.isActive = isActive;
			return this;
		}
		
		public Builder isBlocked(Boolean isBlocked) {
			this.isBlocked = isBlocked;
			return this;
		}
		
		public Builder firstName(String firstName) {
			this.firstName = firstName;
			return this;
		}
		
		public Builder middleName(String middleName) {
			this.middleName = middleName;
			return this;
		}
		
		public Builder lastName(String lastName) {
			this.lastName = lastName;
			return this;
		}
		
		public Builder city(String city) {
			this.city = city;
			return this;
		}
		
		public Builder street(String street) {
			this.street = street;
			return this;
		}
		
		public Builder houseNumber(String houseNumber) {
			this.houseNumber = houseNumber;
			return this;
		}
		
		public Builder flatNumber(String flatNumber) {
			this.flatNumber = flatNumber;
			return this;
		}
		
		public Builder zipCode(String zipCode) {
			this.zipCode = zipCode;
			return this;
		}
		
		public Builder pesel(String pesel) {
			this.pesel = pesel;
			return this;
		}
		
		public Builder driverLicenseNumber(String driverLicenseNumber) {
			this.driverLicenseNumber = driverLicenseNumber;
			return this;
		}
		
		public Builder phoneNumber(String phoneNumber) {
			this.phoneNumber = phoneNumber;
			return this;
		}
		
		public SignUpCustomerRequest build() {
			
			SignUpCustomerRequest request = new SignUpCustomerRequest();
			// User
			request.username = this.username;
			request.password = this.password;
			request.email = this.email;
			request.isActive = this.isActive;
			request.isBlocked = this.isBlocked;
			request.isCustomer = this.isCustomer;
			//# Customer
			request.city = this.city;
			request.street =  this.street;
			request.houseNumber = this.houseNumber;
			request.flatNumber = this.flatNumber;
			request.zipCode = this.zipCode;
			
			request.firstName = this.firstName;
			request.middleName = this.middleName;
			request.lastName = this.lastName;
			
			request.pesel = this.pesel;
			request.driverLicenseNumber = this.driverLicenseNumber;
			request.phoneNumber = this.phoneNumber;
			
			return request;
		}
		
		public Map<String, Object> toHashMap() {
			
			Map<String, String> name = new HashMap<>();
			name.put("firstName", this.firstName);
			name.put("middleName", this.middleName);
			name.put("lastName", this.lastName);

			Map<String, String> address = new HashMap<>();
			address.put("city", this.city);
			address.put("street", this.street);
			address.put("houseNumber", this.houseNumber);
			address.put("flatNumber", this.flatNumber);
			address.put("zipCode", this.zipCode);
			
			Map<String, Object> customer = new HashMap<>();
			customer.put("name", name);
			customer.put("address", address);
			customer.put("pesel", this.pesel);
			customer.put("driverLicenseNumber", this.driverLicenseNumber);
			customer.put("phoneNumber", this.phoneNumber);
			
			Map<String, Object> request = new HashMap<>();
			request.put("username", this.username);
			request.put("password", this.password);
			request.put("email", this.email);
			request.put("isCustomer", this.isCustomer);
			request.put("isActive", this.isActive);
			request.put("isBlocked", this.isBlocked);
			request.put("customer", customer);
			
			return request;
		}
		
	}
}
