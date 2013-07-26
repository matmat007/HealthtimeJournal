package com.healthtimejournal;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.healthtimejournal.model.DoctorModel;
import com.healthtimejournal.service.Base64Decoder;
import com.healthtimejournal.service.HttpClient;
import com.healthtimejournal.service.JSONParser;

public class DoctorPageActivity extends Activity {
	
	private RetrieveDoctorTask mRetDoctorTask = null;
	
	int doctorid;
	
	ImageView doctorProfileImage;
	
	TextView doctorProfileName;
	TextView doctorProfileSpecialty;
	TextView doctorProfileHospitals;
	TextView doctorProfileHospitalAddress;
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
		doctorProfileHospitalAddress = (TextView)findViewById(R.id.doctorProfileHospitalAddress);
		doctorProfileConsulSched = (TextView)findViewById(R.id.doctorProfileConsulSched);
		doctorProfileContactNum = (TextView)findViewById(R.id.doctorProfileContactNum);
		
		mRetDoctorTask = new RetrieveDoctorTask();
		mRetDoctorTask.execute();
		
	}
	
	public boolean onOptionsItemSelected(MenuItem item){
		switch(item.getItemId()){
		case android.R.id.home:
			finish();
			break;
		case R.id.postAction:
			
			break;
		}
		return true;
	}
	
	private class RetrieveDoctorTask extends AsyncTask<Void,Void,String>{
		
		private ProgressDialog pDialog;
		Base64Decoder decoder;
		
		@Override
		protected void onPreExecute() {
	        super.onPreExecute();
	        pDialog = new ProgressDialog(getBaseContext());
	        pDialog.setMessage("Loading details. Please wait...");
	        pDialog.setIndeterminate(false);
	        pDialog.setCancelable(false);
	        pDialog.show();
	    }
		
		protected String doInBackground(Void... params) {
			
			HttpClient a = new HttpClient();
			String data = a.retrieve_doctor(1);
			return data;
			
		}
		
		protected void onPostExecute(String value){
			super.onPostExecute(value);
	        pDialog.dismiss();
	        
			DoctorModel doctor = new DoctorModel();
			
			doctor = JSONParser.getOneDoctor(value);

			doctorid = doctor.getDoctorId();
			doctorProfileImage.setImageBitmap(decoder.decodeBase64(doctor.getImage().toString()));
			doctorProfileName.setText("Doctor Name:\n" + doctor.getFirstName().toString() + " " + doctor.getLastName().toString());
			doctorProfileSpecialty.setText("Doctor Specialty:\n" + doctor.getSpecialty().toString());
			doctorProfileHospitals.setText("Doctor Hospital:\n" + doctor.getHospital().toString());
			doctorProfileHospitalAddress.setText("Doctor Hospital Address:\n" + doctor.getHospitalAddress().toString());
			doctorProfileConsulSched.setText("Doctor Consultation Schedule:\n" + doctor.getConsultation().toString());
			
			if(doctor.getContact2().toString().equals(null))
				doctor.setContact2("");
			
			doctorProfileContactNum.setText("Doctor Contact Details:\n" + doctor.getContact1().toString() + "\n" + doctor.getContact2().toString());
			
		}
		
	}

}
