package com.tts.customerapi.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tts.customerapi.models.Customer;

// A Repository in Spring Boot is an interface responsible for 
// interacting with our database. The connection between SB and our H2 database
// is already done, but this is a space to define a series of methods that will
// interact with the database

// To tell a Repository that it is in fact connected to a particular Table in the DB,
// we can extend the CrudRepository which provides a series of methods that will
// automatically know how to interact with our DB via CRUD methods

// We have to provide this CrudRepository with 2 generics:
// 1. The type of class / model we want the repository to interact with (Customer)
// 2. The type of the primary key of that model (Long)


// Repositories in SpringBoot are actually magic. All of the code responsible
// for the interaction between Customers and our Database is implemented by Springboot
// under the hood. All we have to do here is define a series of abstract methods
// with a particular naming convention and spring boot will parse through them and generate
// the necessary code and casting to make it work:
public interface CustomerRepository extends CrudRepository<Customer, Long>{

	@Override
	Optional<Customer> findById(Long id);
		
	@Override
	List<Customer> findAll();
	
	
	// This findByName method does NOT exist in CrudRepository, but
	// springboot recognizes the way I wrote the name of the method and
	// will implement the proper SQL and code under the hood such that I can
	// run this method and it will find and return all customers with a given name
	// in a list
	List<Customer> findByName(String name);
	

}
