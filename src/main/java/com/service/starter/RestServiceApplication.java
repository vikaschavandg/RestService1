package com.service.starter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.service")

public class RestServiceApplication {


	public static void main(String[] args) {
		SpringApplication.run(RestServiceApplication.class, args);
	}
}	