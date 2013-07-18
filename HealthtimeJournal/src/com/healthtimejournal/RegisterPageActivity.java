package com.healthtimejournal;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

public class RegisterPageActivity extends Activity{
	
	final Context context = this;
	
	EditText firstnameText;
	EditText lastnameText;
	EditText emailText;
	EditText passText;
	EditText retypePassText;
	
	CheckBox maleCheckBox;
	CheckBox femaleCheckBox;
	
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
		
		/*maleCheckBox = (CheckBox)findViewById(R.id.regMaleCheck);
		maleCheckBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
		    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		        if(buttonView.isChecked()) {
		        	maleCheckBox.setChecked(true);
		            femaleCheckBox.setChecked(false);
		        }
		    }
		});
        femaleCheckBox = (CheckBox)findViewById(R.id.regFemaleCheck);
        femaleCheckBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
		    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		        if(buttonView.isChecked()) {
		        	femaleCheckBox.setChecked(true);
		        	maleCheckBox.setChecked(false);
		        }
		    }
		});*/
        
        bloodType = (Spinner) findViewById(R.id.regBloodTypeSpinner);
		String[] bloods = getResources().getStringArray(R.array.bloodtype);
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, bloods);
		dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		bloodType.setAdapter(dataAdapter);
        
        nextButton = (Button)findViewById(R.id.regNextButton);
        nextButton.setOnClickListener(new OnClickListener() { 
			public void onClick(View arg0) {
				//to delete
				Intent a = new Intent(RegisterPageActivity.this, RegisterPage2Activity.class);
				startActivity(a);
				//to delete ^
				
				/*if(!firstnameText.getText().toString().equals("") && !lastnameText.getText().toString().equals("") && !emailText.getText().toString().equals("") && !passText.getText().toString().equals("") && !retypePassText.getText().toString().equals("") && (maleCheckBox.isChecked() || femaleCheckBox.isChecked())){
					AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
					alertDialogBuilder
							.setMessage("Are you sure you want to go to the next page?")
							.setCancelable(false)
							.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog, int id) {
									Intent a = new Intent(RegisterPageActivity.this, RegisterPage2Activity.class);
									startActivity(a);
								}
							  })
							.setNegativeButton("No", new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog, int id) {
									dialog.cancel();
								}
							});
			 
					AlertDialog alertDialog = alertDialogBuilder.create();
					alertDialog.show();
				}
				else {
					AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
					alertDialogBuilder
							.setMessage("You still have missing fields on the register form.")
							.setCancelable(false)
							.setNeutralButton("Ok", new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog, int id) {
									dialog.cancel();
								}
							});
			 
					AlertDialog alertDialog = alertDialogBuilder.create();
					alertDialog.show();
				}*/
			}
	    }); 
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item){
		
		startActivity(new Intent(RegisterPageActivity.this, MainActivity.class));
		return true;
	}
		 
}
