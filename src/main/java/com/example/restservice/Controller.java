package com.example.restservice;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;


@RestController
@Slf4j
public class Controller {
	
	@Autowired
	ConnectedGraph graph;
   // private static final Logger log = LoggerFactory.getLogger(Controller.class);
	
	@GetMapping("/connected")
	public String greeting(@RequestParam(value = "origin") String origin,
			@RequestParam(value = "destination") String destination) {
		
		Map<Vertex, List<Vertex>> vertices = graph.getAllVertices();
		
		if(!vertices.containsKey(new Vertex(origin))) {
			return "no";
			
		}
		
		if(!vertices.containsKey(new Vertex(destination))) {
			return "no";
			
		}
				
		boolean check = graph.breadthFirstTraversal(origin, destination);
		return check ? "yest" : "no";
	}

}
