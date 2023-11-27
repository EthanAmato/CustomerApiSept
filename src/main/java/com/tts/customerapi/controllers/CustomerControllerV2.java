package com.tts.customerapi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tts.customerapi.models.Customer;
import com.tts.customerapi.repositories.CustomerRepository;

import jakarta.validation.Valid;

// Just like in V1, let's define a RequestMapping that will make sure all
// methods here will be prepended by /api/v2 instead of /api/v1
// This way, users don't have to worry about have the backend of their project automatically
// being switched and they can instead deliberately choose the more robust version when they're
// ready to switch

@RequestMapping("/api/v2")
@RestController
public class CustomerControllerV2 {

	// Let's make sure to get another copy of our customerRepository so that we can
	// interact with the Database:
	@Autowired
	CustomerRepository customerRepository;

	// In this version of the API, we want to return HTTP statuses
	// alongside the data we retrieve (or fail to retrieve) from the database
	// To do that, we can use the ResponseEntity object
	// The response entity object takes a Generic - this generic will be the return
	// type
	// of whatever you want to send back as a response (List<Customer>)

	// Let's add some additional functionality so that
	// the user can filter by name instead of having to
	// call that findEthan method from before.
	// We can access filterParams via the
	// @RequestParam annotation - this will check
	// the url for different key-value pairs
	// and store them in an input variable:
	@GetMapping("/customers")
	public ResponseEntity<List<Customer>> getCustomers(@RequestParam(value = "name", required = false) String name) {
		List<Customer> myCustomers;

		System.out.println(name);
		
		if(name != null) {
			myCustomers = customerRepository.findByName(name);
		} else {
			myCustomers = customerRepository.findAll();
		}
		
		// We have to provide ReponseEntity with:
		// 1. (optionally) the Data we want to send back
		// 2. an HTTP status code from an enum:
		return new ResponseEntity<>(myCustomers, HttpStatus.OK);
	}

	// We can enforce the validation rules we defined
	// inside of our Customer class with the help of
	// the @Valid annotation
	
	// If there are any errors associated with our Customer,
	// they will stored inside of the Errors input variable:
	
	// Since we know we have to return some kind of HTTP status code, but we don't really want to provide
	// a response body, we can still return a type of ResponseEntity (to provide the code) but have its generic
	// be a Void type (note that this is the wrapper class) so that we don't have to provide any real data to send
	// with the code:
	@PostMapping("/customers")
	public ResponseEntity<Void> createCustomer(@Valid @RequestBody Customer newCustomer, Errors errors) {
		System.out.println(newCustomer);
		System.out.println(errors);
		
		if(errors.hasErrors()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		// If we made it to this part of the method, we know that the user has provided
		// valid data for a customer and we are therefore safe to save that customer to the DB
		// without messing anything up:
		customerRepository.save(newCustomer);
		// We will also send back a 201 created status code to let user know everything went well
		return new ResponseEntity<>(HttpStatus.CREATED); 
	}

}
