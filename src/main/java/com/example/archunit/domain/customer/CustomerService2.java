package com.example.archunit.domain.customer;

public class CustomerService2 {

	private CustomerService customerService;
	
	public CustomerService2() {
		this.customerService = new CustomerService(new CustomerRepository() {
			
			@Override
			public Customer save(Customer customer) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public Customer findById(Integer customerId) {
				// TODO Auto-generated method stub
				return null;
			}
		});
	}
}
