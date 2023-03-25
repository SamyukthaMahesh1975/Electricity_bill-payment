package com.cg.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

	public Optional<Customer> findByEmail(String email);

	public Optional<Customer> findByAadhaarNumber(String aadhaarNumber);

	public Optional<Customer> findByMobileNumber(String mobileNumber);

	public List<Customer> findByFirstName(String firstName);

	
	//public Optional<Customer> findById(long customerId);

	

}
