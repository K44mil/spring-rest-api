package org.learning.carrental.security;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.learning.carrental.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public class UserPrincipal implements UserDetails {

	private Long id;
	
	private String username;
	
	@JsonIgnoreProperties
	private String email;
	
	@JsonIgnoreProperties
	private String password;
	
	private boolean isCustomer;
	
	private boolean isActive;
	
	private boolean isBlocked;
	
	private Collection<? extends GrantedAuthority> authorities;

	public UserPrincipal(Long id, String username, String email, String password, boolean isCustomer, boolean isActive,
			boolean isBlocked, Collection<? extends GrantedAuthority> authorities) {
		super();
		this.id = id;
		this.username = username;
		this.email = email;
		this.password = password;
		this.isCustomer = isCustomer;
		this.isActive = isActive;
		this.isBlocked = isBlocked;
		this.authorities = authorities;
	}
	
	public static UserPrincipal create(User user) {
		List<GrantedAuthority> authorities = user.getRoles().stream().map(role -> 
				new SimpleGrantedAuthority(role.getName().name())
		).collect(Collectors.toList());
		
		return new UserPrincipal(
				user.getId(),
				user.getUsername(),
				user.getEmail(),
				user.getPassword(),
				user.isCustomer(),
				user.isActive(),
				user.isBlocked(),
				authorities
		);
	}
	
	public Long getId() {
		return id;
	}
	
	public String getEmail() {
		return email;
	}
	
	public boolean isActive() {
		return isActive;
	}
	
	public boolean isCustomer() {
		return isCustomer;
	}
	
	public boolean isBlocked() {
		return isBlocked;
	}
	
	@Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }
    
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserPrincipal that = (UserPrincipal) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
