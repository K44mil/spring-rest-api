package org.learning.carrental.repository;

import java.util.Optional;

import org.learning.carrental.model.Role;
import org.learning.carrental.model.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
	
	Optional<Role> findByName(RoleName name);
	
}
