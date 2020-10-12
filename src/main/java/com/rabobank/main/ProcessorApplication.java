package com.rabobank.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * The Class ProcessorApplication.
 */
@SpringBootApplication
@ComponentScan(basePackages = "com.rabobank")
public class ProcessorApplication {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(ProcessorApplication.class, args);
	}

}
