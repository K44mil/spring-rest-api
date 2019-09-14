package org.learning.carrental.model;

import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.learning.carrental.model.embedded.Name;

@Entity
@Table(name = "employees")
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Embedded
	private Name name;
	
	@NotBlank
	private Double salary;
	
	@NotBlank
	@Temporal(TemporalType.DATE)
	private Date hireDate;
	
	@NotBlank
	@Temporal(TemporalType.DATE)
	private Date birthDate;
	
	@NotBlank
	@Size(max = 20)
	private String phoneNumber;
	
	@NotBlank
	@Enumerated(EnumType.STRING)
	private Gender gender;

	@OneToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "user_id", nullable = false)
	private User user;
	
	public Employee() {
		
	}

	public Employee(Name name, @NotBlank Double salary, @NotBlank Date hireDate, @NotBlank Date birthDate,
			@NotBlank @Size(max = 20) String phoneNumber, @NotBlank Gender gender, User user) {
		super();
		this.name = name;
		this.salary = salary;
		this.hireDate = hireDate;
		this.birthDate = birthDate;
		this.phoneNumber = phoneNumber;
		this.gender = gender;
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

	public Double getSalary() {
		return salary;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}

	public Date getHireDate() {
		return hireDate;
	}

	public void setHireDate(Date hireDate) {
		this.hireDate = hireDate;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public boolean isValid() {
		
		if (!name.isValid())
			return false;
		if (salary.toString().equals(""))
			return false;
		if (hireDate.toString().equals(""))
			return false;
		if (birthDate.toString().equals(""))
			return false;
		if (phoneNumber.length() > 20 || phoneNumber.equals(""))
			return false;
		if (!(gender.equals(Gender.MALE) || gender.equals(Gender.FEMALE)))
			return false;
		
		return true;
	}

}
