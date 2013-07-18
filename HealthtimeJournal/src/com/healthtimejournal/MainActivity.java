package com.healthtimejournal;

import org.json.JSONObject;
import org.json.JSONTokener;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.DialogError;
import com.facebook.Facebook;
import com.facebook.Facebook.DialogListener;
import com.facebook.FacebookError;
import com.facebook.SessionStore;

public class MainActivity extends Activity {
	
	EditText emailText;
	EditText passText;
	
	Button loginButton;
	TextView registerButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		emailText = (EditText)findViewById(R.id.loginEmailText);
		passText = (EditText)findViewById(R.id.loginPasswordText);
		
		loginButton = (Button)findViewById(R.id.mainLoginButton);
		loginButton.setOnClickListener(new OnClickListener() { 
			public void onClick(View arg0) {
				Intent a = new Intent(MainActivity.this, TiledEventsActivity.class);
		        startActivity(a);
			}
	    }); 
		
		registerButton = (TextView)findViewById(R.id.register_link);
		registerButton.setOnClickListener(new OnClickListener() { 
			public void onClick(View arg0) {
				Intent a = new Intent(MainActivity.this, RegisterPageActivity.class);
		        startActivity(a);
			}
	    });
	    
	}

}
