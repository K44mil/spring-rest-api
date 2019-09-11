package org.learning.carrental.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.annotations.NaturalId;
import org.learning.carrental.model.audit.DateAudit;

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
	
	@NotBlank
	private boolean isCustomer;
	
	@NotBlank
	private boolean isActive;
	
	@NotBlank
	private boolean isBlocked;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "user_roles",
			joinColumns = @JoinColumn(name = "user_id"),
			inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles = new HashSet<>();
	
	public User() {
		
	}

	public User(@NotBlank @Size(max = 15) String username, @NotBlank @Size(max = 40) @Email String email,
			@NotBlank @Size(max = 100) String password, @NotBlank boolean isCustomer, @NotBlank boolean isActive,
			@NotBlank boolean isBlocked) {
		super();
		this.username = username;
		this.email = email;
		this.password = password;
		this.isCustomer = isCustomer;
		this.isActive = isActive;
		this.isBlocked = isBlocked;
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

	public boolean isCustomer() {
		return isCustomer;
	}

	public void setCustomer(boolean isCustomer) {
		this.isCustomer = isCustomer;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public boolean isBlocked() {
		return isBlocked;
	}

	public void setBlocked(boolean isBlocked) {
		this.isBlocked = isBlocked;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

}
