package com.cities.connectedservice.test;


import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.cities.connectedservice.ConnectedCitieServiceApplication;
import com.cities.connectedservice.controller.ConnectedCitiesController;

@SpringBootTest(classes = ConnectedCitieServiceApplication.class)
class ConnectedCitiesServiceApplicationTest {
	
	@Autowired
	private ConnectedCitiesController cityPathController;

	@Test
	void contextLoads() {
		
		assertNotNull(cityPathController);

	}

}
