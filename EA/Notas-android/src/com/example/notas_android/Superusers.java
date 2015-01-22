package com.example.notas_android;

import java.util.ArrayList;

import com.example.notas_android.model.NotasAPI;
import com.example.notas_android.model.NotasAndroidException;
import com.example.notas_android.model.User;
import com.example.notas_android.model.UserCollection;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import android.view.View;
import android.widget.ListView;

public class Superusers extends ListActivity {

	private class FetchStingsTask extends AsyncTask<Void, Void, UserCollection> {

		private ProgressDialog pd;

		@Override
		protected UserCollection doInBackground(Void... params) {
			UserCollection users = null;
			try {
				users = NotasAPI.getInstance(Superusers.this)
						.getCollectionUsers();
			} catch (NotasAndroidException e) {
				e.printStackTrace();
			}
			System.out.println("antes del post" + users);
			return users;
		}

		@Override
		protected void onPostExecute(UserCollection result) {
			addUser(result);
			if (pd != null) {
				pd.dismiss();
			}
		}

		@Override
		protected void onPreExecute() {
			pd = new ProgressDialog(Superusers.this);
			pd.setTitle("Buscando el servidor...");
			pd.setCancelable(false);
			pd.setIndeterminate(true);
			pd.show();
		}

	}

	private void addUser(UserCollection usuario) {
		userList.addAll(usuario.getUsuarios());
		adapter.notifyDataSetChanged();
	}

	private ArrayList<User> userList;
	private UserAdapter adapter;

	private final static String TAG = Superusers.class.toString();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_superusers);
		userList = new ArrayList<User>();
		adapter = new UserAdapter(this, userList);
		setListAdapter(adapter);

		(new FetchStingsTask()).execute();
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position,
			long videoid) {
		// User usuario = userList.get(position);
		// Log.d(TAG, usuario.getLinks().get("self").getTarget());

		// Intent intent = new Intent(this, Videosreproductor.class);
		// .putExtra("url", video.getLinks().get("self").getTarget());

		// startActivity(intent);

	}

}
