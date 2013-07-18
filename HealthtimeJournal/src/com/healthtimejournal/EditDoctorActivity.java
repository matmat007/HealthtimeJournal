package com.healthtimejournal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class EditDoctorActivity extends Activity {
	
	Button createButton;
	Button cancelButton;
	
	EditText doctorHospital;
	EditText doctorConsultation;
	EditText doctorContact1;
	EditText doctorContact2;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.edit_doctor_page);
		
		doctorHospital = (EditText)findViewById(R.id.editDoctorHospitalText);
		doctorConsultation = (EditText)findViewById(R.id.editDoctorConsultationText);
		doctorContact1 = (EditText)findViewById(R.id.editDoctorContact1Text);
		doctorContact2 = (EditText)findViewById(R.id.editDoctorContact2Text);
		
		createButton = (Button)findViewById(R.id.editDoctorPageButton);
		createButton.setOnClickListener(new OnClickListener() { 
			public void onClick(View arg0) {
				Intent a = new Intent(EditDoctorActivity.this, LifeEventsActivity.class);
		        startActivity(a);
			}
	    }); 
		
		cancelButton = (Button)findViewById(R.id.editDoctorCancelButton);
		cancelButton.setOnClickListener(new OnClickListener() { 
			public void onClick(View arg0) {
				Intent a = new Intent(EditDoctorActivity.this, HealthGrowthActivity.class);
		        startActivity(a);
			}
	    }); 
		
	}

}
