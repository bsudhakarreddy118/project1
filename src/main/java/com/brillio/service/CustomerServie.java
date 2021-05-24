package com.brillio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brillio.model.Customer;
import com.brillio.repository.CustomerRepository;

@Service
public class CustomerServie {

	@Autowired
	private CustomerRepository customerRepository;

	public Customer saveCustomer(Customer customer) {
		return customerRepository.save(customer);
	}
     
	public Customer getCustomer(Long id) {
		return customerRepository.findById(id).get();
	}
}
