package com.healthtimejournal;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.healthtimejournal.model.ParentModel;
import com.healthtimejournal.service.HealthtimeSession;
import com.healthtimejournal.service.HttpClient;
import com.healthtimejournal.service.JSONParser;

public class MainActivity extends Activity {
	
	private LoginTask mLoginTask = null;
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
		
		String email = HealthtimeSession.getEmail(this);
		String pass = HealthtimeSession.getPassword(this);
		
		if(!(email.isEmpty()&&pass.isEmpty())){
			emailText.setText(email);
			passText.setText(pass);
			attemptLogin();
		}
		loginButton = (Button)findViewById(R.id.mainLoginButton);
		loginButton.setOnClickListener(new OnClickListener() { 
			public void onClick(View arg0) {
				attemptLogin();
				
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
	
	private void attemptLogin(){
		if(mLoginTask != null){
			return;
		}
		
		String mEmail = emailText.getText().toString();
		String mPass = passText.getText().toString();
		boolean cancel = false;
		View focusView = null;
		if(TextUtils.isEmpty(mEmail)){
			emailText.setError(getString(R.string.required_not_met));
			focusView = emailText;
			cancel = true;
		}
		if(TextUtils.isEmpty(mPass)){
			passText.setError(getString(R.string.required_not_met));
			focusView = passText;
			cancel = true;
		}
		if(cancel){
			focusView.requestFocus();
		}
		else{
			mLoginTask = new LoginTask(this);
			mLoginTask.execute((Void)null);
		}
	}
	
	private class LoginTask extends AsyncTask<Void,Void,String>{
		
		public LoginTask(Activity activity){
			this.activity = activity;
		}
		
		private ProgressDialog pDialog;
		private Activity activity;
		
		protected void onPreExecute() {
	        super.onPreExecute();
	        pDialog = new ProgressDialog(activity);
	        pDialog.setMessage("Logging in. Please wait...");
	        pDialog.setIndeterminate(false);
	        pDialog.setCancelable(false);
	        pDialog.show();
	    }

		@Override
		protected String doInBackground(Void... params) {
			// TODO Auto-generated method stub
			HttpClient a = new HttpClient();
			String data = a.loginUser(emailText.getText().toString(), passText.getText().toString());
			return data;
		}
		
		protected void onPostExecute(String data){
			super.onPostExecute(data);
			mLoginTask = null;
			pDialog.dismiss();
			ParentModel oneparent = JSONParser.getOneParent(data);
			if(!(oneparent == null)){
			oneparent.setEmail(emailText.getText().toString());
			oneparent.setPassword(passText.getText().toString());
			HealthtimeSession.save(oneparent, activity);
			Toast.makeText(activity, "Login Successful", Toast.LENGTH_SHORT).show();
			Intent a = new Intent(MainActivity.this, TiledEventsActivity.class);
	        startActivity(a);
			}
			else{
			Toast.makeText(activity, "Login Failed", Toast.LENGTH_SHORT).show();
			emailText.setText("");
			passText.setText("");
			}
		}
	}

}
