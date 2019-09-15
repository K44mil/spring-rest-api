package org.learning.carrental;

import java.util.TimeZone;

import javax.annotation.PostConstruct;

import org.learning.carrental.model.Role;
import org.learning.carrental.model.RoleName;
import org.learning.carrental.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

@SpringBootApplication
@EntityScan(basePackageClasses = {
		CarRentalApplication.class,
		Jsr310JpaConverters.class
})
public class CarRentalApplication {

	@Autowired
	RoleRepository roleRepository;
	
	@PostConstruct
	void init() {
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
		roleRepository.save(new Role(RoleName.ROLE_USER));
		roleRepository.save(new Role(RoleName.ROLE_ADMIN));
		roleRepository.save(new Role(RoleName.ROLE_EMPLOYEE));
	}
	
	public static void main(String[] args) {
		SpringApplication.run(CarRentalApplication.class, args);
	}

}
