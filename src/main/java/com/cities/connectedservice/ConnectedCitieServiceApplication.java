package com.cities.connectedservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@EnableAutoConfiguration 
public class ConnectedCitieServiceApplication { 

	public static void main(String[] args) {
		SpringApplication.run(ConnectedCitieServiceApplication.class, args);
	}

}
