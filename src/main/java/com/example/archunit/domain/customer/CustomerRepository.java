package com.example.archunit.domain.customer;

public interface CustomerRepository {

	Customer findById(Integer customerId);
	Customer save(Customer customer);
	
}
