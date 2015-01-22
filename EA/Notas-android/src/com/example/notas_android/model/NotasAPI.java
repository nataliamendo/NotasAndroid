package com.example.notas_android.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.Properties;

import org.apache.http.client.ResponseHandler;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

public class NotasAPI {
	private final static String TAG = NotasAPI.class.getName();
	private static NotasAPI instance = null;
	private URL url;
 
	public int useridp = 0;
	
	private NotasRootAPI rootAPI = null;
	private String variable;
	private NotasAPI(Context context) throws IOException,
			NotasAndroidException {
		super();
 
		AssetManager assetManager = context.getAssets();
		Properties config = new Properties();
		config.load(assetManager.open("config.properties"));//carga fichero configuracion 
		String serverAddress = config.getProperty("server.address");//obtiene los valores de es fichero
		String serverPort = config.getProperty("server.port");
		url = new URL("http://" + serverAddress + ":" + serverPort
				+ "/notas/ioperation/"+variable); //se qeda cn la base url esta si utilizamos hateoas nunca cambia
 
		Log.d("LINKS", url.toString());
		getRootAPI();
	}
	
	public final static NotasAPI getInstance(Context context)
			throws NotasAndroidException {
		if (instance == null)
			try {
				instance = new NotasAPI(context);//context es la actividad, para recuperar valores del fichero conf.
			} catch (IOException e) {
				throw new NotasAndroidException(
						"Can't load configuration file");
			}
		return instance;
	}
	
	
	
	private void getRootAPI() throws NotasAndroidException { //rea un modelo y ataka al servicio
		Log.d(TAG, "getRootAPI()");
		rootAPI = new NotasRootAPI();
		HttpURLConnection urlConnection = null;
		try {
			urlConnection = (HttpURLConnection) url.openConnection();
			urlConnection.setRequestMethod("GET");
			urlConnection.setDoInput(true);// true por defecto, significa que qiero leer
			urlConnection.connect();
			System.out.println(urlConnection.getResponseCode());
			
		} catch (IOException e) {
			System.out.println("explision de la api");
			throw new NotasAndroidException("Can't connect to Videoshare API Web Service");
		}
 
		BufferedReader reader;
		try {//lee json que le devuelve htps://localhost:8080/beeterapi
			
			reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
			StringBuilder sb = new StringBuilder();
			String line = null;
			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}
 /*
			JSONObject jsonObject = new JSONObject(sb.toString());// aparti de un string y objeto json lo convierte
			JSONArray jsonLinks = jsonObject.getJSONArray("links");//asi poder manipular y obtener get, arrays.. 
			parseLinks(jsonLinks, rootAPI.getLinks());//lo proceso con el metodo priado de esta clase y lo guardas en el modelo rootAPI
		
		*/
		} catch (IOException e) {
			throw new NotasAndroidException(
					"Can't get response from Videoshare API Web Service");
		}
 
	}
	
	public UserCollection getCollectionUsers() throws NotasAndroidException
	{	System.out.println("a entrado en getCollectionUsers");
		System.out.println(url);
		Log.d(TAG, "getCollectionUser");
		variable = "getlistusu";
		UserCollection usus = new UserCollection();
 
		HttpURLConnection urlConnection = null;
		try {
//			urlConnection = (HttpURLConnection) new URL(rootAPI.getLinks()
//					.get("create").getTarget()).openConnection();
			
			urlConnection = (HttpURLConnection) url.openConnection();
			urlConnection.setRequestMethod("GET");
			urlConnection.setDoInput(true);// true por defecto, significa que qiero leer
			urlConnection.connect();
			System.out.println(urlConnection.getResponseCode());
			//System.out.println(rootAPI.getLinks()
			//		.get("create").getTarget());
			
			
			
		} catch (IOException e) {
			throw new NotasAndroidException(
					"Can't connect to Notas API Web Service");
		}
 
		BufferedReader reader;
		try {
			reader = new BufferedReader(new InputStreamReader(
					urlConnection.getInputStream()));
			StringBuilder sb = new StringBuilder();
			String line = null;
			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}
 
//			JSONObject jsonObject = new JSONObject(sb.toString());
//			JSONArray jsonLinks = jsonObject.getJSONArray("links");//atributoss
//			parseLinks(jsonLinks, usus.getLinks());
 
			
			JSONArray jsonUser = new JSONArray(sb.toString());
			for (int i = 0; i < jsonUser.length(); i++) {
				User user = new User();
				JSONObject jsonusu = jsonUser.getJSONObject(i);// le doy valor a traves del array y lo a�ado a la coleccion qe es lo qe lo devuelves
				user.setContra(jsonusu.getString("contra"));
				user.setLogin(jsonusu.getString("login"));
				//jsonLinks = jsonSting.getJSONArray("links");
				//parseLinks(jsonLinks, nota.getLinks());
				usus.getUsuarios().add(user);
				//videos.getVideos().add(video);
			}
		} catch (IOException e) {
			throw new NotasAndroidException(
					"Can't get response from Notas API Web Service");
		} catch (JSONException e) {
			throw new NotasAndroidException("Error parsing Notas Root API");
		}
 
		return usus;
	}
	
	
	
	public NotasCollection getCollectionNotas() throws NotasAndroidException {
		Log.d(TAG, "getCollectionNotas");
		NotasCollection notas = new NotasCollection();
		variable = "getlistnota/1";
		
		HttpURLConnection urlConnection = null;
		try {
			urlConnection = (HttpURLConnection) url.openConnection();
			urlConnection.setRequestMethod("GET");
			urlConnection.setDoInput(true);// true por defecto, significa que qiero leer
			urlConnection.connect();
			System.out.println(urlConnection.getResponseCode());
		} catch (IOException e) {
			throw new NotasAndroidException(
					"Can't connect to Notas API Web Service");
		}
 
		BufferedReader reader;
		try {
			reader = new BufferedReader(new InputStreamReader(
					urlConnection.getInputStream()));
			StringBuilder sb = new StringBuilder();
			String line = null;
			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}
 
//			JSONObject jsonObject = new JSONObject(sb.toString());
//			JSONArray jsonLinks = jsonObject.getJSONArray("links");//atributoss
//			parseLinks(jsonLinks, notas.getLinks());
 

			JSONArray jsonNotas= new JSONArray(sb.toString());
			for (int i = 0; i < jsonNotas.length(); i++) {
				Notas nota = new Notas();
				JSONObject jsonnota = jsonNotas.getJSONObject(i);// le doy valor a traves del array y lo a�ado a la coleccion qe es lo qe lo devuelves
				nota.setIdNotes(jsonnota .getString("idNota"));
				nota.setNota(jsonnota.getString("nota"));
				//jsonLinks = jsonSting.getJSONArray("links");
				//parseLinks(jsonLinks, nota.getLinks());
				notas.getNotas().add(nota);
				//videos.getVideos().add(video);
			}
		} catch (IOException e) {
			throw new NotasAndroidException(
					"Can't get response from Notas API Web Service");
		} catch (JSONException e) {
			throw new NotasAndroidException("Error parsing Notas Root API");
		}
 
		return notas;
	}
	
	private void parseLinks(JSONArray jsonLinks, Map<String, Link> map)
			throws NotasAndroidException, JSONException {
		for (int i = 0; i < jsonLinks.length(); i++) {
			Link link = SimpleLinkHeaderParser
					.parseLink(jsonLinks.getString(i));
			//REL PODIA ser multiple rel=" home boomark self" -> 3 enlaces qe obtienes a traves del mapa
			String rel = link.getParameters().get("rel");//tb podria obteet el title i el target(?) pRA QITARME LOS ESPACIOS BLANCOS DE ENCIAM
			String rels[] = rel.split("\\s");
			for (String s : rels)
				map.put(s, link);
		}
	
	}
	
	
	
	
}	
