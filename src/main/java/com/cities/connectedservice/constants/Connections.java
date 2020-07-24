package com.cities.connectedservice.constants;

public enum Connections {
	CONNECTED("YES"),
	NOTCONNECTED("NO");
	
	private String path;
	
	Connections(String path){
		this.path = path;
	}
	
	public String getPath() {
		
		return path;
		
	}

}
