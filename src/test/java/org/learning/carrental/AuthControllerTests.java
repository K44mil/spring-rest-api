package org.learning.carrental;

import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class AuthControllerTests {

	private final String SIGNUP_PATH = "/api/auth/signup";
	private final String SIGNIN_PATH = "/api/auth/signin";
	
	@BeforeEach
	void setUp() {
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = 8080;
	}
	
	@Test
	public void testCreateUser() throws Exception {
		
		Map<String, String> name = new HashMap<>();
		name.put("firstName", "Jan");
		name.put("middleName", "");
		name.put("lastName", "Kowalski");

		Map<String, String> address = new HashMap<>();
		address.put("city", "Rzeszow");
		address.put("street", "Wolnosci");
		address.put("houseNumber", "100");
		address.put("flatNumber", "105");
		address.put("zipCode", "30-100");
		
		Map<String, Object> customer = new HashMap<>();
		customer.put("name", name);
		customer.put("address", address);
		customer.put("pesel", "9900010101");
		customer.put("driverLicenseNumber", "100/10/2003");
		customer.put("phoneNumber", "999000999");
		
		Map<String, Object> request = new HashMap<>();
		request.put("username", "test");
		request.put("password", "password");
		request.put("email", "email@email.pl");
		request.put("isCustomer", true);
		request.put("isActive", false);
		request.put("isBlocked", false);
		request.put("customer", customer);
		
//		given()
//			.contentType("application/json")
//			.accept("application/json")
//			.body(request)
//			.when()
//			.post(SIGNUP_PATH)
//			.then()
//			.statusCode(415)
//			.contentType("application/json");
		
		Map<String, String> signin = new HashMap<>();
		signin.put("usernameOrEmail", "test");
		signin.put("password", "password");
		
		given()
			.contentType("application/json")
			.accept("application/json")
			.body(signin)
			.when()
			.post(SIGNIN_PATH)
			.then()
			.statusCode(200);
	}
	
}
