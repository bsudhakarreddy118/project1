package com.brillio.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.brillio.model.Customer;
import com.brillio.service.CustomerServie;

@RestController

public class CustomerController {
    
	@Autowired 
	private CustomerServie servie;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@PostMapping("saveCustomer")
	public ResponseEntity<Object> saveCustomerDetails(@RequestBody Customer customer){
		Customer cus = servie.saveCustomer(customer);
		if(cus!=null) {
			return new ResponseEntity<>(cus, HttpStatus.CREATED);
		}
		else {
			return new ResponseEntity<>("No Details Saved", HttpStatus.CONFLICT);
		}
		
	}
	
	@GetMapping("/customer/{id}")
	public String getCustomerOrderDetails(@PathVariable("id") Long id) {
		Customer customer = servie.getCustomer(id); 
		//http://localhost:8082/findCustomer/id
		ArrayList list = restTemplate.getForObject("http://localhost:8082/findCustomer/"+id, ArrayList.class);
		
		return customer.getName() + "-->Order Details" + list;
	
	}

}
