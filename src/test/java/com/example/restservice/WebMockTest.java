package com.example.restservice;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
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


@WebMvcTest(Controller.class)
public class WebMockTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ConnectedGraph service;

	@Test
	public void yesShouldReturnFromService() throws Exception {

		Map<Vertex, List<Vertex>> vertices = new HashMap<Vertex, List<Vertex>>();

		Vertex v1 = new Vertex("Boston");
		List<Vertex> list1 = new ArrayList<>(); 
		list1.add(new Vertex("New York"));
		vertices.put(v1, list1);

		Vertex v2 = new Vertex("Philadelphia");
		List<Vertex> list2 = new ArrayList<>();
		list2.add(new Vertex("Newark"));
		vertices.put(v2, list2);

		Vertex v3 = new Vertex("Newark");
		List<Vertex> list3 = new ArrayList<>();
		list3.add(new Vertex("Boston"));
		vertices.put(v3, list3);

		Vertex v4 = new Vertex("Trenton");
		List<Vertex> list4 = new ArrayList<>();
		list4.add(new Vertex("Albany"));
		vertices.put(v4, list4);

		Vertex v5 = new Vertex("Albany");
		List<Vertex> list5 = new ArrayList<>();
		vertices.put(v5, list5);

		Vertex v6 = new Vertex("New York");
		List<Vertex> list6 = new ArrayList<>();
		vertices.put(v6, list6);

		when(service.breadthFirstTraversal("Boston", "New York")).thenReturn(true);
		when(service.getAllVertices()).thenReturn(vertices);

		this.mockMvc.perform(get("/connected?origin=Boston&destination=New York")).andDo(print())
				.andExpect(status().isOk()).andExpect(content().string(containsString("yes")));

		when(service.breadthFirstTraversal("Philadelphia", "Newark")).thenReturn(true);

		this.mockMvc.perform(get("/connected?origin=Philadelphia&destination=Newark")).andDo(print())
				.andExpect(status().isOk()).andExpect(content().string(containsString("yes")));

	}

	@Test
	public void noShouldReturnFromCotrollerForUnknownOrigin() throws Exception {

		Map<Vertex, List<Vertex>> vertices = new HashMap<Vertex, List<Vertex>>();
		when(service.getAllVertices()).thenReturn(vertices);

		this.mockMvc.perform(get("/connected?origin=Fremont&destination=New York")).andDo(print())
				.andExpect(status().isOk()).andExpect(content().string(containsString("no")));
	}

	@Test
	public void noShouldReturnFromControllerForUnknownDesitnation() throws Exception {

		Map<Vertex, List<Vertex>> vertices = new HashMap<Vertex, List<Vertex>>();
		when(service.getAllVertices()).thenReturn(vertices);

		this.mockMvc.perform(get("/connected?origin=New York&destination=Fremont")).andDo(print())
				.andExpect(status().isOk()).andExpect(content().string(containsString("no")));
	}
}
