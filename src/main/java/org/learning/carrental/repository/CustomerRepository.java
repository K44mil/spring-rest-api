package org.learning.carrental.repository;

import org.learning.carrental.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

	Boolean existsByPesel(String pesel);
	
	Boolean existsByPhoneNumber(String phoneNumber);
	
	Boolean existsByDriverLicenseNumber(String driverLicenseNumber);
}
