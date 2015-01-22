package com.example.notas_android.model;
import java.util.HashMap;
import java.util.Map;

 
public class NotasRootAPI {
	private Map<String, Link> links;
	 
	public NotasRootAPI() {
		links = new HashMap<String, Link>();
	}
 
	public Map<String, Link> getLinks() {
		return links;
	}
 
}
