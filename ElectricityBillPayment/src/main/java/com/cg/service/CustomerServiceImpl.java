package com.cg.service;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.entity.Customer;
import com.cg.exception.DuplicateCustomerException;
import com.cg.exception.NoSuchCustomerException;
import com.cg.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements ICustomerService {

	@Autowired
	private CustomerRepository customerRepository;
	
	/*
	 * New customer can register,If already exists it throw duplicate customer exception
	 */
	@Override
	public Customer registerCustomer(Customer customer) throws DuplicateCustomerException {
		return customerRepository.save(customer);
	}

	/*
	 * We can view Customer profile by taking customer id,
	 * if customer id does not exits throws no such customer exception
	 */
	@Override
	public Customer viewCustomerProfile(long customerId) throws NoSuchCustomerException {
		return searchCustomerByCustomerId(customerId);
	}

	/*
	 * We can modify customer profile by taking customer id and customer,
	 * If customer does not exits throws no such customer exception
	 */
	@Override
	public Customer editCustomerProfile(long customerId, Customer customer) throws NoSuchCustomerException {
		return customerRepository.save(customer);
	}

	/*
	 * We can Search customer by taking customer id,
	 * If customerId does not exits throws no such customer exception
	 */
	@Override
	public Customer searchCustomerByCustomerId(long customerId) throws NoSuchCustomerException {
		return customerRepository.findById(customerId)
				.orElseThrow(() -> new NoSuchCustomerException("No Customer Exist!"));
	}

	/*
	 * We can Search customer by taking email,
	 * If email does not exits throws no such customer exception
	 */
	@Override
	public Customer searchCustomerByEmail(String email) throws NoSuchCustomerException {
		return customerRepository.findByEmail(email)
				.orElseThrow(() -> new NoSuchCustomerException("No Customer Exist!"));
	}

	/*
	 * We can search Customer by taking aadhaar number,
	 * If aadhaar does not exits throws no such customer exception
	 */
	@Override
	public Customer searchCustomerByAadhaar(String aadhaarNumber) throws NoSuchCustomerException {
		return customerRepository.findByAadhaarNumber(aadhaarNumber)
				.orElseThrow(() -> new NoSuchCustomerException("No Customer Exist!"));
	}

	/*
	 * We can search customer by giving the mobile number,
	 * If mobile number does not exits throws no such customer exception
	 */
	@Override
	public Customer searchCustomerByMobile(String mobile) throws NoSuchCustomerException {
		return customerRepository.findByMobileNumber(mobile)
				.orElseThrow(() -> new NoSuchCustomerException("No Customer Exist!"));
	}

	/* 
	 * We can search customer name by giving the first name,
	 * If first name does not exits throws no such customer exception
	 */
	@Override
	public List<Customer> searchCustomerByName(String firstName) throws NoSuchCustomerException {
		try {
			List<Customer> byName = customerRepository.findByFirstName(firstName);
			return byName;
		} catch (Exception e) {
			throw new NoSuchCustomerException("Customer with name:" + firstName + "not present");
		}

	}

	
}
