package com.example.mobilecontact;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class MainActivity extends Activity {

	Button add;
	ListView ls;
	EditText et1;
	final List <String> name = new ArrayList<String>();
	final List <String> email = new ArrayList<String>();
	final List <String> phone = new ArrayList<String>();
	
	List<Integer> keys = new ArrayList<Integer>();
	ArrayAdapter<String> adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		add=(Button) findViewById(R.id.add);
		
		final DatabaseHandler db= new DatabaseHandler(this);
		
		List<Contact> contacts = db.getAllContacts();
		name.clear();
		email.clear();
		phone.clear();
		keys.clear();
		for (Contact cn : contacts) {
			name.add(cn.getFirst_name());
			phone.add(cn.getPhone_id());
			email.add(cn.getEmail_id());
			keys.add(cn.get_id());		
		}
		
		ls=(ListView) findViewById(R.id.listView1);
		adapter = new ArrayAdapter<String>(this, R.layout.row, R.id.single_row, name);
		ls.setAdapter(adapter);
		adapter.notifyDataSetChanged();
		
		ls.setOnItemClickListener(new OnItemClickListener() {

			String nameString, phoneString, emailString;
			int key;
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long arg3) {
				List<Contact> contacts = db.getAllContacts();
				name.clear();
				email.clear();
				phone.clear();
				keys.clear();
				for (Contact cn : contacts) {
					name.add(cn.getFirst_name());
					phone.add(cn.getPhone_id());
					email.add(cn.getEmail_id());
					keys.add(cn.get_id());		
				}
				nameString = (String) ls.getItemAtPosition(position);
				int index = name.indexOf(nameString);
				phoneString = phone.get(index);
				emailString = email.get(index);
				key = keys.get(index);
				
				Intent intent = new Intent(MainActivity.this,Information.class);
				intent.putExtra("name", adapter.getItem(position));
				intent.putExtra("phone", phone.get(position));
				intent.putExtra("email", email.get(position));
				
				intent.putExtra("keypass", key);
				startActivity(intent);
				
			}
		});
		
		
		et1=(EditText) findViewById(R.id.searchid);
		
		
		
		
		
		add.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(MainActivity.this,ADD_Data.class);
				startActivity(intent);
				
			}
		});
		
	}
	
}
