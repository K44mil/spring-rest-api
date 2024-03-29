package org.learning.carrental.model.embedded;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Embeddable
public class Address {
	
	@NotNull
	@Size(max = 100)
	private String city;
	
	@NotNull
	@Size(max = 100)
	private String street;
	
	@NotNull
	@Size(max = 20)
	private String houseNumber;
	
	@Size(max = 20)
	private String flatNumber;
	
	@NotNull
	@Size(max = 6)
	private String zipCode;
	
	public Address() {
		
	}

	public Address(@NotNull @Size(max = 100) String city, @NotNull @Size(max = 100) String street,
			@NotNull @Size(max = 100) String houseNumber, @Size(max = 100) String flatNumber,
			@NotNull @Size(max = 6) String zipCode) {
		super();
		this.city = city;
		this.street = street;
		this.houseNumber = houseNumber;
		this.flatNumber = flatNumber;
		this.zipCode = zipCode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getHouseNumber() {
		return houseNumber;
	}

	public void setHouseNumber(String houseNumber) {
		this.houseNumber = houseNumber;
	}

	public String getFlatNumber() {
		return flatNumber;
	}

	public void setFlatNumber(String flatNumber) {
		this.flatNumber = flatNumber;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	
	public boolean isValid() {
		
		if (city.length() > 100 || city.equals(""))
			return false;
		if (street.length() > 100 || street.equals(""))
			return false;
		if (houseNumber.length() > 20 || houseNumber.equals(""))
			return false;
		if (flatNumber.length() > 20)
			return false;
		if (zipCode.length() > 6 || zipCode.equals(""))
			return false;
		
		return true;
	}
}
