package com.tts.customerapi.controllers;

import java.util.List;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tts.customerapi.models.Customer;
import com.tts.customerapi.repositories.CustomerRepository;

// Just like in our first project, let's specify that this is a RESTController
// so it returns JSON data
// We can also provide a base route using RequestMapping so that we are able to effectively
// version all methods inside of this controller:
@RequestMapping("/api/v1")
@RestController
public class CustomerControllerV1 {

	// Let's rely on that inversion of control concept
	// one more time and allow springboot to create
	// an object representation of our repository.
	// Essentially, using the @Autowired method, we are
	// able to tell Springboot to implement all the necessary
	// code for our CustomerRepository and save it to
	// an instance variable in this controller:

	@Autowired
	CustomerRepository customerRepository;

	// Must send a get request to /api/v1/test in order to call this method:
	@GetMapping("/test")
	public List<String> HelloWorld() {
		List<String> test = new ArrayList<>();
		test.add("Hello");
		return test;
	}

	@GetMapping("/customers")
	public List<Customer> getCustomers() {
//		Customer customerOne = new Customer("Ethan", "Protein Powder", 24);
//		Customer customerTwo = new Customer("Fae", "Pizza", 59);
//		Customer customerThree = new Customer("Jayse", "Celsius", 27);
//
//		Customer dbCustomer = customerRepository.save(customerOne);
//		Customer dbCustomer2 = customerRepository.save(customerTwo);
//		Customer dbCustomer3 = customerRepository.save(customerThree);

		List<Customer> myCustomers = customerRepository.findAll();
		return myCustomers;
	}
	
	// This method is listening in on the /api/v1/customers endpoint
	// specifically for POST HTTP requests:
	
	// Recall that POST requests take in bodies (usually in JSON format)
	// This, in java-speak, would be referred to as the 'input' to the
	// createCustomer method. Another magical thing about SpringBoot
	// is that we can use the @RequestBody to take in the JSON
	// sent by a request and try to coerce it into a Customer object:
	@PostMapping("/customers")
	public void createCustomer(@RequestBody Customer newCustomer) {
		System.out.println("Hi From post method!!!");
		System.out.println(newCustomer);
		customerRepository.save(newCustomer);
	}
	
	
	// Call the custom findByName method we defined inside of 
	// CustomerRepository
	@GetMapping("/ethan")
	public List<Customer> findEthan() {
		return customerRepository.findByName("Ethan");
	}
	

}
