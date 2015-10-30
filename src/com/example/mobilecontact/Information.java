package com.example.mobilecontact;

import java.net.URI;

import android.net.Uri;
import android.os.Bundle;
import android.R.integer;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Information extends Activity {
	Context context=this;
	TextView tt1,tt2,tt3,tt4,tt5,tt6;
	Button bb1,bb2,bb3,bb4;
	private String receive_name, receive_phone, receive_email;
	private int receive_keyid;
	
	DatabaseHandler databaseHandler;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_information);
		tt1=(TextView) findViewById(R.id.contact_details);
		tt2=(TextView) findViewById(R.id.namedetail);
		tt3=(TextView) findViewById(R.id.phonedetail);
		tt4=(TextView) findViewById(R.id.emaildetail);
		tt5=(TextView) findViewById(R.id.nothing);
		tt6=(TextView) findViewById(R.id.moreoption);
		bb1=(Button) findViewById(R.id.deleteinfo);
		bb2=(Button) findViewById(R.id.editinfo);
		bb3=(Button) findViewById(R.id.callinfo);
		bb4=(Button) findViewById(R.id.messageinfo);
		Intent intent= getIntent();
		
		receive_name = intent.getStringExtra("name");
		receive_phone = intent.getStringExtra("phone");
		receive_email = intent.getStringExtra("email");
		receive_keyid = intent.getIntExtra("keypass", 0);
		
		tt2.setText(receive_name);
		tt3.setText(receive_phone);
		tt4.setText(receive_email);
		bb2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				final Dialog dialog= new Dialog(context);
				dialog.setContentView(R.layout.edit);
				dialog.setTitle("Edit Entry");
				dialog.setCanceledOnTouchOutside(false);
				final EditText editname= (EditText) dialog.findViewById(R.id.editname);
				final EditText editphone= (EditText) dialog.findViewById(R.id.editphone);
				final EditText editemail= (EditText) dialog.findViewById(R.id.editemail);
				final Button cancel = (Button) dialog.findViewById(R.id.kancel);
				final Button ok = (Button) dialog.findViewById(R.id.oki);
				
				editname.setText(tt2.getText());
				editphone.setText(tt3.getText());
				editemail.setText(tt4.getText());
				
				cancel.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View arg0) {
						dialog.cancel();
					}
				});
				ok.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						String newname= editname.getText().toString();
						String newphone = editphone.getText().toString();
						String newemail = editemail.getText().toString();
						if (receive_name.equals(newname) && (receive_phone.equals(newphone) && (receive_email.equals(newemail)))) {
							Toast.makeText(context, "Nothing Changed", Toast.LENGTH_LONG).show();
						}
						else {
							tt2.setText(newname);
							tt3.setText(newphone);
							tt4.setText(newemail);
							databaseHandler = new DatabaseHandler(context);
							Contact contact = new Contact(receive_keyid,newname,newphone,newemail);
							databaseHandler.updateContact(contact);
							dialog.cancel();
						}
						
					}
				});
				dialog.show();
			}
		});
		
		bb1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				databaseHandler = new DatabaseHandler(context);
				Contact contact = new Contact(receive_keyid,receive_name,receive_phone,receive_email);
				databaseHandler.deleteContact(contact);
				Intent intent = new Intent(Information.this,MainActivity.class);
				startActivity(intent);
				
			}
		});
		
		
		bb3.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
			String numberString=((TextView) arg0).getText().toString();
			Intent callIntent=new Intent(Intent.ACTION_CALL,Uri.parse(numberString));
			startActivity(callIntent);
				
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.information, menu);
		return true;
	}

}
