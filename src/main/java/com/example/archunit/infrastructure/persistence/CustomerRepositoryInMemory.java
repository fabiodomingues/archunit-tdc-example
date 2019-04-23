package com.example.archunit.infrastructure.persistence;

import java.util.ArrayList;
import java.util.List;

import com.example.archunit.domain.customer.Customer;
import com.example.archunit.domain.customer.CustomerRepository;

public class CustomerRepositoryInMemory implements CustomerRepository {

	private List<Customer> customers;
	
	public CustomerRepositoryInMemory() {
		this.customers = new ArrayList<>();
	}
	
	public Customer findById(Integer customerId) {
		return customers
				.stream()
				.filter(customer -> customer.getId().equals(customerId))
				.findFirst()
				.orElse(null);
	}

	public Customer save(Customer customer) {
		customer.setId(getNextId());
		customers.add(customer);
		return customer;
	}
	
	private Integer getNextId() {
	    Integer maxId = customers
	    		.stream()
	    		.mapToInt(Customer::getId)
	    		.max()
	    		.orElse(0);
	    
	    return ++maxId;
		
	}

}
