package com.cities.connectedservice.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cities.connectedservice.constants.Connections;
import com.cities.connectedservice.model.City;
import com.cities.connectedservice.service.ConnectedCitiesService;


@RestController
public class ConnectedCitiesController {
	
	@Autowired
	ConnectedCitiesService cityService;
   
	private static final Logger log = LoggerFactory.getLogger(ConnectedCitiesController.class);
	
	@GetMapping("/connected")
	public String findPathBetweenCities(@RequestParam(value = "origin") String origin,
			@RequestParam(value = "destination") String destination) {
		
		Map<City, List<City>> vertices = cityService.getAllCities();
		
		if(!vertices.containsKey(new City(origin))) {
			log.info("origin is not valid");
			return Connections.NOTCONNECTED.getPath();
			
		}
		
		if(!vertices.containsKey(new City(destination))) {
			log.info("destination is not valid");
			return Connections.NOTCONNECTED.getPath();
			
		}
				
		boolean existsPath = cityService.findPathBetWeenCities(origin, destination);
		log.info("Path between" + origin + "and" + destination + "exists" + existsPath);
		return existsPath ? Connections.CONNECTED.getPath() : Connections.NOTCONNECTED.getPath(); 
	}

}
