package com.healthtimejournal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class DoctorPageActivity extends Activity {
	
	Button childrenButton;
	Button editInfoButton;
	
	ImageView doctorProfileImage;
	
	TextView doctorProfileName;
	TextView doctorProfileSpecialty;
	TextView doctorProfileHospitals;
	TextView doctorProfileConsulSched;
	TextView doctorProfileContactNum;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.doctor_profile);
		
		doctorProfileImage = (ImageView)findViewById(R.id.doctorProfileImage);
		
		doctorProfileName = (TextView)findViewById(R.id.doctorProfileName);
		doctorProfileSpecialty = (TextView)findViewById(R.id.doctorProfileSpecialty);
		doctorProfileHospitals = (TextView)findViewById(R.id.doctorProfileHospitals);
		doctorProfileConsulSched = (TextView)findViewById(R.id.doctorProfileConsulSched);
		doctorProfileContactNum = (TextView)findViewById(R.id.doctorProfileContactNum);
		
		doctorProfileName.setText("Doctor Name");
		doctorProfileSpecialty.setText("Doctor Specialty");
		doctorProfileHospitals.setText("Hospitals");
		doctorProfileConsulSched.setText("Schedule");
		doctorProfileContactNum.setText("Contacts");
		
		doctorProfileImage.setImageResource(R.drawable.ic_launcher);
		
		childrenButton = (Button)findViewById(R.id.doctorProfileChildrenButton);
		childrenButton.setOnClickListener(new OnClickListener() { 
			public void onClick(View arg0) {
				Intent a = new Intent(DoctorPageActivity.this, MyChildrenActivity.class);
		        startActivity(a);
			}
	    }); 
		
		editInfoButton = (Button)findViewById(R.id.doctorProfileSettingsButton);
		editInfoButton.setOnClickListener(new OnClickListener() { 
			public void onClick(View arg0) {
				Intent a = new Intent(DoctorPageActivity.this, EditDoctorActivity.class);
		        startActivity(a);
			}
	    }); 
		
	}

}
