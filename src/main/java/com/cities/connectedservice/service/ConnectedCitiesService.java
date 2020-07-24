package com.cities.connectedservice.service;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.cities.connectedservice.model.City;

@Service
public class ConnectedCitiesService {
	
	private  Map<City, List<City>> adjCities;
	private final String fileLocation = "city.txt";
	
	public ConnectedCitiesService() throws IOException
	
	{
		buildConnectedCitiesMap();
		
	}
	
	private void buildConnectedCitiesMap() throws FileNotFoundException, IOException {
		this.adjCities= new HashMap<City, List<City>>();
		BufferedReader reader = new BufferedReader(new FileReader(this.fileLocation));
		String currentLine;
		while ((currentLine= reader.readLine()) != null) {
			String[] str = currentLine.trim().split(",");
			String source = str[0].trim();
			String destination = str[1].trim();
			this.addCity(source);
			this.addCity(destination);
			this.addpathBetweenCities(source, destination);
 
		}
		reader.close(); 
	}
	void addCity(String label) {
	    adjCities.putIfAbsent(new City(label), new ArrayList<>());
	}
	
	void addpathBetweenCities(String src, String dest) {
	    City source = new City(src);
	    City destination = new City(dest); 
	    adjCities.get(source).add(destination);
	    // considering path between both directions
	    adjCities.get(destination).add(source);
	}
	
	
	public List<City> getConnectedCities(String label) {
	    return adjCities.get(new City(label));  
	}
	
	public boolean findPathBetWeenCities(String source, String destination) {
		City dest = new City(destination);
		for (City city : this.getConnectedCities(source)) {
			if (city.equals(dest))
				return true;
		}
		return false;
	}

	public Map<City, List<City>> getAllCities() {
		return adjCities;
	}

}
