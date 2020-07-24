package com.cities.connectedservice.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.cities.connectedservice.controller.ConnectedCitiesController;
import com.cities.connectedservice.model.City;
import com.cities.connectedservice.service.ConnectedCitiesService;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@WebMvcTest(ConnectedCitiesController.class)
public class CityPathControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ConnectedCitiesService service;
	
	Map<City, List<City>> cities;

	
	@BeforeEach
	public void setUp() {
		cities = new HashMap<City, List<City>>();
		
		City v1 = new City("Boston");
		List<City> list1 = new ArrayList<>(); 
		list1.add(new City("New York"));
		cities.put(v1, list1);

		City v2 = new City("Philadelphia");
		List<City> list2 = new ArrayList<>();
		list2.add(new City("Newark"));
		cities.put(v2, list2);

		City v3 = new City("Newark");
		List<City> list3 = new ArrayList<>();
		list3.add(new City("Boston"));
		cities.put(v3, list3);

		City v4 = new City("Trenton");
		List<City> list4 = new ArrayList<>();
		list4.add(new City("Albany"));
		cities.put(v4, list4);

		City v5 = new City("Albany");
		List<City> list5 = new ArrayList<>();
		cities.put(v5, list5);

		City v6 = new City("New York");
		List<City> list6 = new ArrayList<>();
		cities.put(v6, list6);
		
	}

	@Test
	public void yesShouldReturnFromService() throws Exception {

		when(service.findPathBetWeenCities("Boston", "New York")).thenReturn(true);
		when(service.getAllCities()).thenReturn(cities);

		this.mockMvc.perform(get("/connected?origin=Boston&destination=New York")).andDo(print())
			.andExpect(status().isOk()).andExpect(content().string(containsString("YES")));

		when(service.findPathBetWeenCities("Philadelphia", "Newark")).thenReturn(true);

		this.mockMvc.perform(get("/connected?origin=Philadelphia&destination=Newark")).andDo(print())
			.andExpect(status().isOk()).andExpect(content().string(containsString("YES")));
		
		when(service.findPathBetWeenCities("Philadelphia", "Newark")).thenReturn(false);

		this.mockMvc.perform(get("/connected?origin=Philadelphia&destination=Newark")).andDo(print())
			.andExpect(status().isOk()).andExpect(content().string(containsString("NO")));

	}

	@Test
	public void noShouldReturnFromCotrollerForUnknownOrigin() throws Exception {

		when(service.getAllCities()).thenReturn(cities);

		this.mockMvc.perform(get("/connected?origin=Fremont&destination=New York")).andDo(print())
				.andExpect(status().isOk()).andExpect(content().string(containsString("NO")));
	}

	@Test
	public void noShouldReturnFromControllerForUnknownDesitnation() throws Exception {

		when(service.getAllCities()).thenReturn(cities);

		this.mockMvc.perform(get("/connected?origin=New York&destination=Fremont")).andDo(print())
				.andExpect(status().isOk()).andExpect(content().string(containsString("NO")));
	}
}
