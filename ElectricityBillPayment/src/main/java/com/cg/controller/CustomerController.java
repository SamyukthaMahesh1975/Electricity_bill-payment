package com.cg.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.entity.Customer;
import com.cg.exception.NoSuchCustomerException;
import com.cg.service.ICustomerService;


@RestController
@RequestMapping(value = "/customer")
public class CustomerController {

	@Autowired
	private ICustomerService customerService;

	@PutMapping(value = "/edit/{customerId}")
	public ResponseEntity<Customer> editCustomerProfile(@PathVariable long customerId, @RequestBody Customer customer)
			throws NoSuchCustomerException {
		Customer existingCustomer = customerService.editCustomerProfile(customerId,customer);
		return new ResponseEntity<Customer>(existingCustomer, HttpStatus.OK);
	}

	@GetMapping(value = "/customerId/{customerId}")
	public ResponseEntity<Customer> viewCustomerProfile(@PathVariable long customerId) throws NoSuchCustomerException {
		Customer customer = customerService.viewCustomerProfile(customerId);
		return new ResponseEntity<Customer>(customer, HttpStatus.OK);
	}

	@GetMapping(value = "/email/{email}")
	public ResponseEntity<Customer> searchCustomerByEmail(@PathVariable String email) throws NoSuchCustomerException {
		Customer customer = customerService.searchCustomerByEmail(email);
		return new ResponseEntity<Customer>(customer, HttpStatus.OK);
	}

	@GetMapping(value = "/aadhaar/{aadhaar}")
	public ResponseEntity<Customer> searchCustomerByAadhaar(@Valid @PathVariable String aadhaarNumber) throws NoSuchCustomerException {
		Customer customer = customerService.searchCustomerByAadhaar(aadhaarNumber);
		return new ResponseEntity<Customer>(customer, HttpStatus.OK);
	}

	@GetMapping(value = "/mobile/{mobileNumber}")
	public ResponseEntity<Customer> searchCustomerByMobile( String mobileNumber) throws NoSuchCustomerException {
		Customer customer = customerService.searchCustomerByMobile(mobileNumber);
		return new ResponseEntity<Customer>(customer, HttpStatus.OK);
	}

	/*@GetMapping(value = "/byid/{customerId}")
	public ResponseEntity<Customer> searchCustomerByCustomerId(@PathVariable @Valid long customerId)
			throws NoSuchCustomerException {

		Customer byId = customerService.searchCustomerByAadhaar(customerId);
		return new ResponseEntity<Customer>(byId, HttpStatus.OK);

	}*/

	@GetMapping(value = "/customerbyfn/{firstName}")
	public ResponseEntity<List<Customer>> searchCustomerByFirstName(@PathVariable String firstName)
			throws NoSuchCustomerException {

		List<Customer> byName = customerService.searchCustomerByName(firstName);
		return new ResponseEntity<List<Customer>>(byName, HttpStatus.OK);

	}
	
	@PostMapping(value = "customer/register")
	public ResponseEntity<Customer> registerCustomer(@Valid @RequestBody Customer customer) {
		Customer registeredCustomer = customerService.registerCustomer(customer);
		return new ResponseEntity<Customer>(registeredCustomer, HttpStatus.CREATED);
	}

}
