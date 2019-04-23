package com.example.archunit.application;

import static org.eclipse.jetty.http.HttpStatus.BAD_REQUEST_400;
import static org.eclipse.jetty.http.HttpStatus.NOT_IMPLEMENTED_501;
import static spark.Spark.before;
import static spark.Spark.exception;
import static spark.Spark.get;
import static spark.Spark.internalServerError;
import static spark.Spark.notFound;
import static spark.Spark.port;
import static spark.Spark.post;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.eclipsesource.json.JsonObject;
import com.example.archunit.application.controllers.CustomerController;
import com.example.archunit.domain.customer.CustomerService;
import com.example.archunit.domain.shared.DomainException;
import com.example.archunit.infrastructure.persistence.CustomerRepositoryInMemory;

import spark.Spark;

public class Application {
	
    private static final Logger logger = LoggerFactory.getLogger(Application.class);
    
    private static final String API_NOT_IMPLEMENTED = "API not implemented.";
    private static final String INTERNAL_SERVER_ERROR = "Internal server error.";

	public void start() {
		port(8080);
		
		enableCORS();
		setLog();

		createRoute();

        configureInternalServerError();
        configureNotImplemented();
        configureDomainException();
	}

    public void stop() {
        Spark.stop();
    }

    public void awaitInitialization() {
        Spark.awaitInitialization();
    }

	private void enableCORS() {
        before((request, response) -> {
            response.header("Access-Control-Allow-Origin", "*");
            response.header("Access-Control-Allow-Headers", "*");
            response.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT, PATCH, OPTIONS");
        });
    }

    private void setLog() {
        before((request, response) -> {
            logger.info("URL request: " + request.requestMethod() + " " + request.uri() + " - headers: " + request.headers());
        });
    }

    private void createRoute() {
		CustomerRepositoryInMemory customerRepository = new CustomerRepositoryInMemory();
		CustomerService customerService = new CustomerService(customerRepository);
		
		CustomerController customerController = new CustomerController(customerService);
		
		get("customers/:id", (req, res) -> customerController.getCustomer(req, res));
		post("customers", (req, res) -> customerController.createCustomer(req, res));
	}

    private void configureInternalServerError() {
        internalServerError((req, res) -> {
            res.status(NOT_IMPLEMENTED_501);
            logger.error(INTERNAL_SERVER_ERROR + ": " + req.pathInfo());
            return INTERNAL_SERVER_ERROR;
        });
    }

    private void configureNotImplemented() {
        notFound((req, res) -> {
            res.status(NOT_IMPLEMENTED_501);
            logger.error(API_NOT_IMPLEMENTED + ": " + req.pathInfo());
            return API_NOT_IMPLEMENTED;
        });
    }
    
    private void configureDomainException() {
    	exception(DomainException.class, (exception, req, res) -> {
            res.status(BAD_REQUEST_400);
            res.type("application/json");
            
            res.body(new JsonObject()
            		.add("message", exception.getMessage())
                    .toString());
    	});
    }

}
