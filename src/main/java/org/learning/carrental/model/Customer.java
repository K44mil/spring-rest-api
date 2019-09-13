package org.learning.carrental.model;

import java.io.Serializable;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Entity
@Table(name = "customers", uniqueConstraints = {
		@UniqueConstraint(columnNames = {
				"pesel"
		}),
		@UniqueConstraint(columnNames = {
				"phoneNumber"
		}),
		@UniqueConstraint(columnNames = {
				"driverLicenseNumber"
		})
})
public class Customer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Embedded
	@JsonSerialize(as = Name.class)
	private Name name;
	
	@Embedded
	@JsonSerialize(as = Address.class)
	private Address address;
	
	@NotBlank
	@Size(max = 20)
	private String pesel;
	
	@NotBlank
	@Size(max = 20)
	private String driverLicenseNumber;
	
	@NotBlank
	@Size(max = 20)
	private String phoneNumber;
	
	@OneToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "user_id", nullable = false)
	private User user;
	
	public Customer() {
		
	}

	public Customer(Name name, Address address, @NotBlank @Size(max = 20) String pesel,
			@NotBlank @Size(max = 20) String driverLicenseNumber, @NotBlank @Size(max = 20) String phoneNumber,
			User user) {
		super();
		this.name = name;
		this.address = address;
		this.pesel = pesel;
		this.driverLicenseNumber = driverLicenseNumber;
		this.phoneNumber = phoneNumber;
		this.user = user;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Name getName() {
		return name;
	}

	public void setName(Name name) {
		this.name = name;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getPesel() {
		return pesel;
	}

	public void setPesel(String pesel) {
		this.pesel = pesel;
	}

	public String getDriverLicenseNumber() {
		return driverLicenseNumber;
	}

	public void setDriverLicenseNumber(String driverLicenseNumber) {
		this.driverLicenseNumber = driverLicenseNumber;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
