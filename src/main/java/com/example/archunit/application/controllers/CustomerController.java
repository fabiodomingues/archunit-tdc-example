package com.example.archunit.application.controllers;

import static com.example.archunit.infrastructure.json.CustomerJson.jsonFor;
import static org.eclipse.jetty.http.HttpStatus.CREATED_201;
import static org.eclipse.jetty.http.HttpStatus.OK_200;

import com.eclipsesource.json.Json;
import com.eclipsesource.json.JsonObject;
import com.example.archunit.domain.customer.Customer;
import com.example.archunit.domain.customer.CustomerNameCannotBeNullOrEmptyException;
import com.example.archunit.domain.customer.CustomerNotFoundException;
import com.example.archunit.domain.customer.CustomerService;
import com.example.archunit.domain.customer.CustumerCreateData;

import spark.Request;
import spark.Response;

public class CustomerController {

	private CustomerService customerService;

	public CustomerController(CustomerService customerService) {
		this.customerService = customerService;
	}

	public String getCustomer(Request request, Response response) throws NumberFormatException, CustomerNotFoundException {
		Customer customer = customerService.getCustomer(Integer.valueOf(request.params(":id")));
        
		response.status(OK_200);
        response.type("application/json");
        
		return jsonFor(customer);
	}

	public String createCustomer(Request request, Response response) throws CustomerNameCannotBeNullOrEmptyException {
		Customer customer = customerService.createCustomer(custumerCreateDataFrom(request));

        response.status(CREATED_201);
        response.type("application/json");
        
		return jsonFor(customer);
	}
	
    private CustumerCreateData custumerCreateDataFrom(Request request) {
        JsonObject json = Json.parse(request.body()).asObject();

        return new CustumerCreateData(json.getString("name", ""));
    }

}
