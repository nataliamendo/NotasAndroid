package com.example.notas_android;

import java.util.ArrayList;


//import com.example.notas_android.Superusers.FetchStingsTask;
import com.example.notas_android.model.Notas;
import com.example.notas_android.model.NotasAPI;
import com.example.notas_android.model.NotasAndroidException;
import com.example.notas_android.model.NotasCollection;
import com.example.notas_android.model.User;
import com.example.notas_android.model.UserCollection;

import android.support.v7.app.ActionBarActivity;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class SuperNotas extends ListActivity {

	private class FetchStingsTask extends AsyncTask<Void, Void, NotasCollection> {

		private ProgressDialog pd;

		@Override
		protected NotasCollection doInBackground(Void... params) {
			NotasCollection notas = null;
			try {
				notas = NotasAPI.getInstance(SuperNotas.this).getCollectionNotas();
			} catch (NotasAndroidException e) {
				e.printStackTrace();
			}
			System.out.println("antes del post"+notas);
			return notas;
		}
		
		

		@Override
		protected void onPostExecute(NotasCollection result) {
			addNotas(result);
			if (pd != null) {
				pd.dismiss();
			}
		}

		@Override
		protected void onPreExecute() {
			pd = new ProgressDialog(SuperNotas.this);
			pd.setTitle("Buscando el servidor...");
			pd.setCancelable(false);
			pd.setIndeterminate(true);
			pd.show();
		}

	}
	
	private void addNotas(NotasCollection notas){
		notasList.addAll(notas.getNotas());
		adapter.notifyDataSetChanged();
	}
	private ArrayList<Notas> notasList;
	private NotasAdapter adapter;

	private final static String TAG = SuperNotas.class.toString();
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_super_notas);
		
		notasList = new ArrayList<Notas>();
		adapter = new NotasAdapter(this, notasList);
		setListAdapter(adapter);
		
		
		(new FetchStingsTask()).execute();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.super_notas, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
