package com.healthtimejournal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class CreateDoctorActivity extends Activity {
	
	Button createButton;
	Button cancelButton;
	
	EditText doctorSpecialty;
	EditText doctorHospital;
	EditText doctorHospitalAddress;
	EditText doctorConsultation;
	EditText doctorContact1;
	EditText doctorContact2;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.create_doctor_page);
		
		getActionBar().setHomeButtonEnabled(true);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		
		doctorSpecialty = (EditText)findViewById(R.id.doctorSpecialtyText);
		doctorHospital = (EditText)findViewById(R.id.doctorHospitalText);
		doctorHospitalAddress = (EditText)findViewById(R.id.doctorHospitalAddressText);
		doctorConsultation = (EditText)findViewById(R.id.doctorConsultationText);
		doctorContact1 = (EditText)findViewById(R.id.doctorContact1Text);
		doctorContact2 = (EditText)findViewById(R.id.doctorContact2Text);
		
		createButton = (Button)findViewById(R.id.createDoctorButton);
		createButton.setOnClickListener(new OnClickListener() { 
			public void onClick(View arg0) {
				Intent a = new Intent(CreateDoctorActivity.this, LifeEventsActivity.class);
		        startActivity(a);
			}
	    }); 
		
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item){
		
		switch(item.getItemId()){
		case android.R.id.home:
			finish();
			break;
		}
		return true;
	}

}
