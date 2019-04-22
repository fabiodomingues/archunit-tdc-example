package com.example.archunit.domain.customer;

public class Customer {
	
	private Integer id;
	private String name;
	
	public Customer(String name) throws CustomerNameCannotBeNullOrEmptyException {
		if (name == null || name.isEmpty()) {
			throw new CustomerNameCannotBeNullOrEmptyException();
		}
		
		this.name = name;
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}

}
