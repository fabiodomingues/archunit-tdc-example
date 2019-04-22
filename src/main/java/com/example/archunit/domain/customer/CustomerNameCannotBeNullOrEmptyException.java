package com.example.archunit.domain.customer;

import com.example.archunit.domain.shared.DomainException;

public class CustomerNameCannotBeNullOrEmptyException extends DomainException {

	public CustomerNameCannotBeNullOrEmptyException() {
		super("Customer name cannot be null or empty.");
	}
	
}
