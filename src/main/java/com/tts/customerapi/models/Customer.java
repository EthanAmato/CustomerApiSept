package com.tts.customerapi.models;

import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

// A Customer class will be used as a blueprint for storing and retrieving rows / objects
// from a table in the H2 database. Each instance variable will be represented as a 'column' in the Customer
// table and we can also define a series of methods (just like regular Java class) that we can run
// on our Customer whenever we retrieve data from the DB as an object

// To tell Springboot that a class is supposed to be stored inside of our Model/Db, we 
// must give the class the @Entity annotation. This basically tells SB that this class
// will be persisted inside of a relational database

@Entity
public class Customer {

	// Now that we are defining fields for a relational database table, we MUST
	// provide some kind of Primary key. Thanks to the magic of JPA / Springboot, we
	// can
	// use Annotations to allow Spring boot to choose unique ids for each row in our
	// DB
	// that we save. To do this we use the @Id annotation and, for added
	// configurability, we
	// can use the @GeneratedValue to tell SB HOW to generate a unique ID for our
	// row:

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	// When we created this project, we installed a dependency called
	// Validation. Validation allows us to ensure that our rows will
	// follow a certain set of rules of our own creation (User Integrity)
	// The beautiful thing about this is that we can avoid writing boilerplate
	// code inside of controller methods for checking the values and instead just
	// use annotations to ensure a 'Customer' is following all of our rules:

	// E.g. we can use the @Length annotation to ensure a max / min number of
	// characters
	// for an instance variable:
	@Length(max = 30)
	private String name;

	private String favoriteProduct;

	// Can use @Max and @Min to ensure that a number field is inside a certain
	// range:
	@Max(120)
	@Min(1)
	private Integer age;

	public Customer() {
	}

	// Notice that I did NOT include the ID as input for our constructor.
	// This is because we are relying on a fundamental principle of frameworks like
	// SB called inversion of control. We want to LET Springboot have control over
	// creating unique ids for our rows rather than guessing that a certain number
	// is unique (and potentially being wrong). Makes our code more robust when
	// we give ourselves less opportunities to do stuff wrong.
	
	// SB will generate an ID for each Customer object when it is saved inside
	// of the database
	public Customer(String name, String favoriteProduct, Integer age) {
		this.name = name;
		this.favoriteProduct = favoriteProduct;
		this.age = age;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFavoriteProduct() {
		return favoriteProduct;
	}

	public void setFavoriteProduct(String favoriteProduct) {
		this.favoriteProduct = favoriteProduct;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name + ", favoriteProduct=" + favoriteProduct + ", age=" + age + "]";
	}

}
