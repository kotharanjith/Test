package com.example.restservice;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ConnectedGraphTest {
	private ConnectedGraph connectedGraph;

	@BeforeEach
	void init() throws IOException {
		connectedGraph = new ConnectedGraph();
	}

	@Test
	public void testGetAdjVertices() {
		List<Vertex> list = connectedGraph.getAdjVertices("Boston");
		assertTrue(list.contains(new Vertex("New York"))); 
		assertFalse(list.contains(new Vertex("Philadelphia")));

	}
	
	@Test
	public void testGetAllVertices() {
		Map<Vertex, List<Vertex>> map = connectedGraph.getAllVertices(); 
		assertTrue(map.containsKey(new Vertex("New York")));
		assertFalse(map.containsKey(new Vertex("Texas")));
 
	}

	@Test
	public void testbreadthFirstTraversalsCitiesConnected() {
		connectedGraph.getAdjVertices("Boston");
		assertTrue(connectedGraph.breadthFirstTraversal("Boston", "New York"));
		assertTrue(connectedGraph.breadthFirstTraversal("Philadelphia", "Newark"));
		assertTrue(connectedGraph.breadthFirstTraversal("Newark", "Boston"));

	}
	
	@Test
	public void testbreadthFirstTraversalsCitiesNotConnected() {
		connectedGraph.getAdjVertices("Boston");
		assertFalse(connectedGraph.breadthFirstTraversal("Boston", "Philadelphia"));
		assertFalse(connectedGraph.breadthFirstTraversal("Philadelphia", "Boston"));

	
	}

}
