package com.example.archunit.domain.customer;

public class CustomerService {

	private final CustomerRepository customerRepository;

	public CustomerService(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}
	
	public Customer getCustomer(Integer customerId) throws CustomerNotFoundException {
		Customer customer = customerRepository.findById(customerId);
		
		if (customer == null) {
			throw new CustomerNotFoundException(customerId);
		}
		
		return customer;
	}
	
	public Customer createCustomer(CustumerCreateData custumerCreateData) throws CustomerNameCannotBeNullOrEmptyException {
		return customerRepository.save(new Customer(custumerCreateData.getName()));
	}
	
}
