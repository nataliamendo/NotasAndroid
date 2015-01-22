package com.example.notas_android;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.notas_android.model.User;

public class UserAdapter extends BaseAdapter {

	private ArrayList<User> data;
	private LayoutInflater inflater;

	private static class ViewHolder {
		
		// Aqui falta lo del list view
		TextView idUser;
		TextView Name;
		TextView Contra;
		// TextView tvDate;
	}

	public UserAdapter(Context context, ArrayList<User> data) {
		super();
		inflater = LayoutInflater.from(context);
		this.data = data;
	}

	@Override
	public int getCount() {// devuelve numero total de filas que habria en la
							// lista , numero de datos q tu muestras
		// TODO Auto-generated method stub
		return data.size();
	}

	@Override
	public Object getItem(int position) {// modelo, cada posicion de la lista
											// tiene un modelo de la cual tiene
											// una serie de datos
		// TODO Auto-generated method stub
		return data.get(position);
	}

	@Override
	public long getItemId(int position) {// devuelve valor unico para una
											// determinada posicion
		// TODO Auto-generated method stub
		
		//esto de aqui hay que modificarlo si o si, bueno en fin eso creo
		//---------------------------------------------------------
		
		//return Long.parseLong(((User) getItem(position)).getUserid());
		return (long) ((User) getItem(position)).getUserid();
		
		
		//----------------------------------------------------
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {// devueve
																			// ese
																			// layout
																			// qe
																			// hemos
																			// creado
																			// cn
																			// datos
		// TODO Auto-generated method stub
		ViewHolder viewHolder = null;
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.list_row_users, null);// listrowsting
																			// que
																			// habiamos
																			// creado
			viewHolder = new ViewHolder();
			viewHolder.idUser = (TextView) convertView
					.findViewById(R.id.tvIdUser);
			viewHolder.Name = (TextView) convertView
					.findViewById(R.id.tvName);
			viewHolder.Contra = (TextView) convertView
					.findViewById(R.id.tvContra);
			// viewHolder.tvDate = (TextView) convertView
			// .findViewById(R.id.tvDate);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		String nombre = data.get(position).getLogin();// recuperando
		String Contra = data.get(position).getContra();													// valores de
	//	String view = data.get(position).getClass();														// esa posicion
		int id = data.get(position).getUserid();
		
		// EN esta linea hay algo raro
		// String date = SimpleDateFormat.getInstance().format(data);
		
		//saldra duplicado pero ahora me da palo pensar , asi que lo dejo
		//asi , ya tu sabe donde esta el fallo.
		viewHolder.idUser.setTextColor(Color.BLACK);
		viewHolder.idUser.setTextSize(25);
		viewHolder.idUser.setTypeface(null, Typeface.BOLD_ITALIC);;
		//viewHolder.idUser.setText(nombre);
		viewHolder.Name.setText(nombre);
		viewHolder.Name.setTextColor(Color.WHITE);
		viewHolder.Name.setTypeface(null, Typeface.ITALIC);
		viewHolder.Name.setTextSize(19);
		viewHolder.Contra.setText(Contra);
		viewHolder.Contra.setTextSize(25);
		viewHolder.Contra.setTextColor(Color.BLACK);
		viewHolder.Contra.setTypeface(null, Typeface.BOLD_ITALIC);
		// viewHolder.tvDate.setText(date);
		return convertView;
	}

}
