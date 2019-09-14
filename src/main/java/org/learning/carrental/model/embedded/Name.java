package org.learning.carrental.model.embedded;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Embeddable
public class Name{
	
	@NotNull
	@Size(max = 40)
	private String firstName;
	
	@Size(max = 40)
	private String middleName;
	
	@NotNull
	@Size(max = 40)
	private String lastName;
	
	public Name() {
		
	}

	public Name(String firstName, String middleName, String lastName) {
		super();
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public boolean isValid() {
		
		if (firstName.length() > 40 || firstName.equals(""))
			return false;
		if (lastName.length() > 40 || lastName.equals(""))
			return false;
		
		return true;
	}
}
