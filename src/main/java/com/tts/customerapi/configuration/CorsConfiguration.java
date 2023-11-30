package com.tts.customerapi.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// A configuration file is responsible for defining things called 'Beans' in Springboot
// Beans are essentially objects that we can define and leave it to Springboot to create them 
// at the necessary parts of our project

// To tell Springboot that this file is responsible for defining how our app
// will handle CORS policies via a particular object setup process, we 
// MUST give it the @Configuration annotation

// Essentially, we use configuration classes to 'configure' or provide blueprints that Springboot
// will take and use to create objects on its own:

@Configuration
public class CorsConfiguration implements WebMvcConfigurer {

	@Override 
	public void addCorsMappings(CorsRegistry corsRegistry) {
		corsRegistry
			.addMapping("/**") // Applying the following settings to a certain set of URLS (All of them in this case)
			.allowedOrigins("*") // Allow all origins to access / send requests to our project
			.allowedMethods("GET", "POST") // ONLY Allow GET and POST requests to our project
			.allowedHeaders("*"); // Can choose what types of headers are allowed in the request
	}
	
	
}
