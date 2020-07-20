package com.thedrinkwholesale.dws;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication
public class DwsApplication {

	public static void main(String[] args) {
		SpringApplication.run(DwsApplication.class, args);
	}

}
