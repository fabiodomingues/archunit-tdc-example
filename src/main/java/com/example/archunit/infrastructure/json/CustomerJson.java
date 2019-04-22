package com.example.archunit.infrastructure.json;

import com.eclipsesource.json.JsonObject;
import com.example.archunit.domain.customer.Customer;

public class CustomerJson {

	public static String jsonFor(Customer customer) {
		return jsonObjectFor(customer).toString();
	}

	private static JsonObject jsonObjectFor(Customer customer) {
		return new JsonObject()
				.add("id", customer.getId())
				.add("name", customer.getName());
	}
	
}
