package com.example.notas_android.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;





public class User {
	private String contra = null;
	private String login = null;
	private List<Notas> notas = new ArrayList<Notas>();
	private Map<String, Link> links = new HashMap<String, Link>();
	private int userid;
	
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public Map<String, Link> getLinks() {
		return links;
	}
	public void setLinks(Map<String, Link> links) {
		this.links = links;
	}
	public void addNota(Notas nota)
	{
		notas.add(nota);
	}
	public List<Notas> getNotas() {
		return notas;
	}
	public void setNotas(List<Notas> notas) {
		this.notas = notas;
	}
	public String getContra() {
		return contra;
	}
	public void setContra(String contra) {
		this.contra = contra;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	
}
