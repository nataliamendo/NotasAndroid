package com.example.notas_android.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;




public class NotasCollection {
	private Map<String, Link> links = new HashMap<String, Link>();
	private List<Notas> notas = new ArrayList<Notas>();
	
	public Map<String, Link> getLinks() {
		return links;
	}
	public void setLinks(Map<String, Link> links) {
		this.links = links;
	}
	public List<Notas> getNotas() {
		return notas;
	}
	public void setVideos(List<Notas> nota) {
		this.notas = nota;
	};
	
	public void addNotas(Notas nota) {
		notas.add(nota);
	}
}
