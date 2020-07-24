package com.cities.connectedservice.test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.cities.connectedservice.model.City;
import com.cities.connectedservice.service.ConnectedCitiesService;


public class ConnectedCitiesServiceTest {
	private ConnectedCitiesService connectedService;

	@BeforeEach
	void init() throws IOException {
		connectedService = new ConnectedCitiesService();
	}

	@Test
	public void testGetAdjCitiess() {
		List<City> list = connectedService.getConnectedCities("Boston");
		assertTrue(list.contains(new City("New York"))); 
		assertFalse(list.contains(new City("Philadelphia")));

	}
	
	@Test
	public void testGetAllCities() {
		Map<City, List<City>> map = connectedService.getAllCities(); 
		assertTrue(map.containsKey(new City("New York")));
		assertFalse(map.containsKey(new City("Texas")));
 
	}

	@Test
	public void testfindPathBetWeenCities() {
		connectedService.getConnectedCities("Boston");
		assertTrue(connectedService.findPathBetWeenCities("Boston", "New York"));
		assertTrue(connectedService.findPathBetWeenCities("Philadelphia", "Newark"));
		assertTrue(connectedService.findPathBetWeenCities("Newark", "Boston"));

	}
	
	@Test
	public void testfindPathBetWeenCitiesNotConnected() {
		connectedService.getConnectedCities("Boston");
		assertFalse(connectedService.findPathBetWeenCities("Boston", "Philadelphia"));
		assertFalse(connectedService.findPathBetWeenCities("Philadelphia", "Boston"));

	
	}

}
