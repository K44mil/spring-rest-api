package org.learning.carrental;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.runners.MethodSorters;
import org.learning.carrental.model.SignUpCustomerRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.HashMap;
import java.util.Map;

import org.junit.FixMethodOrder;
import org.junit.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AuthControllerTests {

	private static final Logger logger = LoggerFactory.getLogger(AuthControllerTests.class);
	
	private final String SIGNUP_PATH = "/api/auth/signup";
	private final String SIGNIN_PATH = "/api/auth/signin";
	
	@BeforeEach
	void setUp() {
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = 8080;
	}
	
	@Test
	@Order(1)
	public void testCreateUserCustomer() throws Exception {
		
		Map<String, Object> request = SignUpCustomerRequest.builder()
				.username("test")
				.password("zaq1@@WSX")
				.email("test@test.pl")
				.isActive(false)
				.isBlocked(false)
				.isCustomer(true)
				.firstName("Jan")
				.middleName("Mariusz")
				.lastName("Kowalski")
				.city("Rzeszow")
				.street("Zielona")
				.houseNumber("14")
				.flatNumber("104")
				.zipCode("30-120")
				.pesel("9910009099")
				.driverLicenseNumber("100/1020/132")
				.phoneNumber("999888000")
				.toHashMap();
						
		
		Response response = RestAssured.given()
			.contentType("application/json")
			.accept("application/json")
			.body(request)
			.when()
			.post(SIGNUP_PATH)
			.then()
			.statusCode(201)
			.extract()
			.response();
	
	}
	
	@Test
	@Order(2)
	public void testCreateUserCustomer_UsernameExists() throws Exception {
	
		Map<String, Object> request = SignUpCustomerRequest.builder()
				.username("test")
				.password("zaq1@@WSX")
				.email("test@test.pl")
				.isActive(false)
				.isBlocked(false)
				.isCustomer(true)
				.firstName("Jan")
				.middleName("Mariusz")
				.lastName("Kowalski")
				.city("Rzeszow")
				.street("Zielona")
				.houseNumber("14")
				.flatNumber("104")
				.zipCode("30-120")
				.pesel("9910009099")
				.driverLicenseNumber("100/1020/132")
				.phoneNumber("999888000")
				.toHashMap();
		
		Response response = RestAssured.given()
			.contentType("application/json")
			.accept("application/json")
			.body(request)
			.when()
			.post(SIGNUP_PATH)
			.then()
			.statusCode(400)
			.extract()
			.response();
	}
	
	@Test
	@Order(3)
	public void testCreateUserCustomer_EmailExists() throws Exception {
		
		Map<String, Object> request = SignUpCustomerRequest.builder()
				.username("test1")
				.password("zaq1@@WSX")
				.email("test@test.pl")
				.isActive(false)
				.isBlocked(false)
				.isCustomer(true)
				.firstName("Jan")
				.middleName("Mariusz")
				.lastName("Kowalski")
				.city("Rzeszow")
				.street("Zielona")
				.houseNumber("14")
				.flatNumber("104")
				.zipCode("30-120")
				.pesel("9910009099")
				.driverLicenseNumber("100/1020/132")
				.phoneNumber("999888000")
				.toHashMap();
		
		Response response = RestAssured.given()
			.contentType("application/json")
			.accept("application/json")
			.body(request)
			.when()
			.post(SIGNUP_PATH)
			.then()
			.statusCode(400)
			.extract()
			.response();
		
	}
	
}
