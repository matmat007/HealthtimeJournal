package com.healthtimejournal;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.healthtimejournal.model.ParentModel;
import com.healthtimejournal.service.HealthtimeSession;
import com.healthtimejournal.service.HttpClient;

public class RegisterPageActivity extends Activity{
	
	
	private RegisterTask mRegTask = null;
	
	EditText firstnameText;
	EditText lastnameText;
	EditText emailText;
	EditText passText;
	EditText retypePassText;
	
	RadioButton maleCheckBox;
	RadioButton femaleCheckBox;
	
	Spinner bloodType;
	
	Button nextButton;
	Button cancelButton;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register_page);
		
		getActionBar().setHomeButtonEnabled(true);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		
		firstnameText = (EditText)findViewById(R.id.regFirstNameText);
		lastnameText = (EditText)findViewById(R.id.regLastNameText);
		emailText = (EditText)findViewById(R.id.regEmailText);
		passText = (EditText)findViewById(R.id.regPasswordText);
		retypePassText = (EditText)findViewById(R.id.regRetypePasswordText);
		
		maleCheckBox = (RadioButton)findViewById(R.id.radioMale);
		femaleCheckBox = (RadioButton)findViewById(R.id.radioFemale);
        
        bloodType = (Spinner) findViewById(R.id.regBloodTypeSpinner);
		String[] bloods = getResources().getStringArray(R.array.bloodtype);
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, bloods);
		dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		bloodType.setAdapter(dataAdapter);
        
        nextButton = (Button)findViewById(R.id.regNextButton);
        nextButton.setOnClickListener(new OnClickListener() { 
			public void onClick(View arg0) {
				//to delete
				/*Intent a = new Intent(RegisterPageActivity.this, RegisterPage2Activity.class);
				startActivity(a);*/
				attemptRegister();
				//to delete ^
			}
        });
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item){
		
		startActivity(new Intent(RegisterPageActivity.this, MainActivity.class));
		return true;
	}
	
	private void attemptRegister(){
		if(mRegTask != null){
			return;
		}
		
		String mLastName = lastnameText.getText().toString();
		String mFirstName = firstnameText.getText().toString();
		String mEmail = emailText.getText().toString();
		String mPass = passText.getText().toString();
		String mPassRep = retypePassText.getText().toString();
		
		boolean cancel = false;
		View focusView = null;
		
		if(TextUtils.isEmpty(mLastName)){
			lastnameText.setError(getString(R.string.required_not_met));
			focusView = lastnameText;
			cancel = true;
		}
		if(TextUtils.isEmpty(mFirstName)){
			firstnameText.setError(getString(R.string.required_not_met));
			focusView = firstnameText;
			cancel = true;
		}
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
		if(TextUtils.isEmpty(mPassRep)){
			retypePassText.setError(getString(R.string.required_not_met));
			focusView = retypePassText;
			cancel = true;
		}
		
		if(!mPass.equals(mPassRep)){
			passText.setError(getString(R.string.not_match));
			retypePassText.setError(getString(R.string.not_match));
			focusView = retypePassText;
			cancel = true;
		}
		if(cancel){
			focusView.requestFocus();
		}
		else{
			mRegTask = new RegisterTask(this);
			mRegTask.execute((Void)null);
		}
	}
	
	private class RegisterTask extends AsyncTask<Void,Void,Boolean>{
		
		public RegisterTask(Activity activity){
			this.activity = activity;
		}

		private ProgressDialog pDialog;
		private Activity activity;
		@Override
		protected void onPreExecute() {
	        super.onPreExecute();
	        pDialog = new ProgressDialog(activity);
	        pDialog.setMessage("Loading events. Please wait...");
	        pDialog.setIndeterminate(false);
	        pDialog.setCancelable(false);
	        pDialog.show();
	    }
		
		protected Boolean doInBackground(Void... arg0) {
			// TODO Auto-generated method stub
			HttpClient a = new HttpClient();
			ParentModel parent = new ParentModel();
			parent.setFirstName(firstnameText.getText().toString());
			parent.setLastName(lastnameText.getText().toString());
			if(maleCheckBox.isChecked()){
				parent.setGender("Male");
			}
			else
				parent.setGender("Female");
			parent.setBloodType(bloodType.getSelectedItem().toString());
			parent.setEmail(emailText.getText().toString());
			parent.setPassword(passText.getText().toString());
			a.registerUser(parent);
			return true;
		}
		
		protected void onPostExecute(Boolean value){
			super.onPostExecute(value);
			mRegTask = null;
			pDialog.dismiss();
			if(value){
				ParentModel parent = new ParentModel();
				parent.setFirstName(firstnameText.getText().toString());
				parent.setLastName(lastnameText.getText().toString());
				parent.setEmail(emailText.getText().toString());
				parent.setPassword(passText.getText().toString());
				HealthtimeSession.save(parent, activity);
				Toast.makeText(activity, "Registration Successful", Toast.LENGTH_SHORT).show();
				startActivity(new Intent(RegisterPageActivity.this, RegisterPage2Activity.class));
			}
			else{
				Toast.makeText(activity, "Registration Failed", Toast.LENGTH_SHORT).show();
			}
			
		}
		
	}
		 
}
