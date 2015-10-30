package com.example.mobilecontact;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class ADD_Data extends Activity {
	EditText et1,et2,et3;
	Button b1;
	Context context=this;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add__data);
		et1=(EditText) findViewById(R.id.addname);
		et2=(EditText) findViewById(R.id.addemail);
		et3=(EditText) findViewById(R.id.addphone);
		b1=(Button) findViewById(R.id.save);
		
		final DatabaseHandler db= new DatabaseHandler(this);
		
		b1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				if (et1.getText().toString().length()>0) {
					db.addContact(new Contact(et1.getText().toString(),et2.getText().toString(),et3.getText().toString()));
					Intent intent= new Intent(ADD_Data.this,MainActivity.class);
					startActivity(intent);
				}
				
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add__data, menu);
		return true;
	}

}
