package com.example.notas_android.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class UserCollection {
	private Map<String, Link> links = new HashMap<String, Link>();
	private List<User> Usuarios = new ArrayList<User>();
	
	
	public Map<String, Link> getLinks() {
		return links;
	}
	public void setLinks(Map<String, Link> links) {
		this.links = links;
	}
	public List<User> getUsuarios() {
		return Usuarios;
	}
	public void setUsuarios(List<User> usuarios) {
		this.Usuarios = usuarios;
	}
	
	public void addUser(User user)
	{
		Usuarios.add(user);
	}
	
	
}
