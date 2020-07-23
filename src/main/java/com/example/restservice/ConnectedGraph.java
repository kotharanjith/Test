package com.example.restservice;
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

@Service
public class ConnectedGraph {
	
	private  Map<Vertex, List<Vertex>> adjVertices;
	private final String fileLocation = "city.txt";
	
	public ConnectedGraph() throws IOException
	
	{
		buildConnectedGraph();
		
	}
	
	private void buildConnectedGraph() throws FileNotFoundException, IOException {
		this.adjVertices= new HashMap<Vertex, List<Vertex>>();
		BufferedReader reader = new BufferedReader(new FileReader(this.fileLocation));
		String currentLine;
		while ((currentLine= reader.readLine()) != null) {
			String[] str = currentLine.trim().split(",");
			String source = str[0].trim();
			String destination = str[1].trim();
			this.addVertex(source);
			this.addVertex(destination);
			this.addEdge(source, destination);

		}
		reader.close();
	}
	void addVertex(String label) {
	    adjVertices.putIfAbsent(new Vertex(label), new ArrayList<>());
	}
	
	void addEdge(String label1, String label2) {
	    Vertex v1 = new Vertex(label1);
	    Vertex v2 = new Vertex(label2);
	    adjVertices.get(v1).add(v2);
	    adjVertices.get(v2).add(v1);
	}
	
	
	List<Vertex> getAdjVertices(String label) {
	    return adjVertices.get(new Vertex(label));
	}
	
	boolean breadthFirstTraversal(String source, String destination) {
	    Set<String> visited = new LinkedHashSet<String>();
	    Queue<String> queue = new LinkedList<String>();
	    queue.add(source);
	    visited.add(source);
	    while (!queue.isEmpty()) {
	        String vertex = queue.poll();
	        Vertex d = new Vertex(destination);
	        for (Vertex v : this.getAdjVertices(vertex)) {        	
	        	if(v.equals(d)) return true;        	
	        	if (!visited.contains(v.label)) {
	                visited.add(v.label);
	                queue.add(v.label);
	            }
	        }
	    }
	    return false;
	}

	public Map<Vertex, List<Vertex>> getAllVertices() {
		return adjVertices;
	}

}
