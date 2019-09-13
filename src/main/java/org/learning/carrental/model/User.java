package org.learning.carrental.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.Type;
import org.learning.carrental.model.audit.DateAudit;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "users", uniqueConstraints = {
		@UniqueConstraint(columnNames = {
				"username"
		}),
		@UniqueConstraint(columnNames = {
				"email"
		})
})
public class User extends DateAudit {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	@Size(max = 15)
	private String username;
	
	@NaturalId
	@NotBlank
	@Size(max = 40)
	@Email
	private String email;
	
	@NotBlank
	@Size(max = 100)
	private String password;
	
	@Column(nullable = false, columnDefinition = "TINYINT(1)")
	private Boolean isCustomer;
	
	@Column(nullable = false, columnDefinition = "TINYINT(1)")
	private Boolean isActive;
	
	@Column(nullable = false, columnDefinition = "TINYINT(1)")
	private Boolean isBlocked;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "user_roles",
			joinColumns = @JoinColumn(name = "user_id"),
			inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles = new HashSet<>();
	
	@OneToOne(fetch = FetchType.LAZY,
			cascade = CascadeType.ALL,
			mappedBy = "user")
	private Customer customer;
	
	public User() {
		
	}

	public User(@NotBlank @Size(max = 15) String username, @NotBlank @Size(max = 40) @Email String email,
			@NotBlank @Size(max = 100) String password, Boolean isCustomer, Boolean isActive, Boolean isBlocked) {
		super();
		this.username = username;
		this.email = email;
		this.password = password;
		this.isCustomer = isCustomer;
		this.isActive = isActive;
		this.isBlocked = isBlocked;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", email=" + email + ", password=" + password
				+ ", isCustomer=" + isCustomer + ", isActive=" + isActive + ", isBlocked=" + isBlocked + ", roles="
				+ roles + ", customer=" + customer + "]";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean isCustomer() {
		return isCustomer;
	}

	public void setCustomer(Boolean isCustomer) {
		this.isCustomer = isCustomer;
	}

	public Boolean isActive() {
		return isActive;
	}

	public void setActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public Boolean isBlocked() {
		return isBlocked;
	}

	public void setBlocked(Boolean isBlocked) {
		this.isBlocked = isBlocked;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	@JsonIgnore
	public Customer getCustomer() {
		return customer;
	}
	
	@JsonIgnore
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
}
