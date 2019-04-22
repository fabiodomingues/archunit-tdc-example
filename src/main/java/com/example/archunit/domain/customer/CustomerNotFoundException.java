package com.example.archunit.domain.customer;

import com.example.archunit.domain.shared.DomainException;

public class CustomerNotFoundException extends DomainException {

	public CustomerNotFoundException(Integer customerId) {
		super(String.format("Customer with id '%d' was not found.", customerId));
	}

}
