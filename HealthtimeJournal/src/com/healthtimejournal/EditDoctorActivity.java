package com.healthtimejournal;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.healthtimejournal.model.DoctorModel;
import com.healthtimejournal.service.HealthtimeSession;
import com.healthtimejournal.service.HttpClient;
import com.healthtimejournal.service.JSONParser;

public class EditDoctorActivity extends Activity {
	
	private DoctorTask mDoctorTask = null;
	private RetrieveDoctorTask mRetDoctorTask = null;
	
	private int doctorid;
	
	TextView createDoctorText;
	
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
		
		mRetDoctorTask = new RetrieveDoctorTask();
		mRetDoctorTask.execute();
		
		createDoctorText = (TextView)findViewById(R.id.createDoctorText);
		createDoctorText.setText("Edit Doctor");
		
		doctorSpecialty = (EditText)findViewById(R.id.doctorSpecialtyText);
		doctorHospital = (EditText)findViewById(R.id.doctorHospitalText);
		doctorHospitalAddress = (EditText)findViewById(R.id.doctorHospitalAddressText);
		doctorConsultation = (EditText)findViewById(R.id.doctorConsultationText);
		doctorContact1 = (EditText)findViewById(R.id.doctorContact1Text);
		doctorContact2 = (EditText)findViewById(R.id.doctorContact2Text);
		
		createButton = (Button)findViewById(R.id.createDoctorButton);
		createButton.setText("Edit Doctor");
		createButton.setOnClickListener(new OnClickListener() { 
			public void onClick(View arg0) {
				attemptEditDoctor();
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

	private void attemptEditDoctor(){
		if(mDoctorTask != null){
			return;
		}
		
		String mDoctorSpecialty = doctorSpecialty.getText().toString();
		String mDoctorHospital = doctorHospital.getText().toString();
		String mDoctorHospitalAddress = doctorHospitalAddress.getText().toString();
		String mDoctorConsultation = doctorConsultation.getText().toString();
		String mDoctorContact1 = doctorContact1.getText().toString();
		
		boolean cancel = false;
		View focusView = null;
		
		if(TextUtils.isEmpty(mDoctorSpecialty)){
			doctorHospital.setError(getString(R.string.required_not_met));
			focusView = doctorHospital;
			cancel = true;
		}
		if(TextUtils.isEmpty(mDoctorHospital)){
			doctorHospital.setError(getString(R.string.required_not_met));
			focusView = doctorHospital;
			cancel = true;
		}
		if(TextUtils.isEmpty(mDoctorHospitalAddress)){
			doctorHospitalAddress.setError(getString(R.string.required_not_met));
			focusView = doctorHospitalAddress;
			cancel = true;
		}
		if(TextUtils.isEmpty(mDoctorConsultation)){
			doctorConsultation.setError(getString(R.string.required_not_met));
			focusView = doctorConsultation;
			cancel = true;
		}
		if(TextUtils.isEmpty(mDoctorContact1)){
			doctorContact1.setError(getString(R.string.required_not_met));
			focusView = doctorContact1;
			cancel = true;
		}
		if(cancel){
			focusView.requestFocus();
		}
		else{
			mDoctorTask = new DoctorTask(this);
			mDoctorTask.execute((Void)null);
		}
	}
	
	private class DoctorTask extends AsyncTask<Void,Void,Boolean>{
		
		public DoctorTask(Activity activity){
			this.activity = activity;
		}

		private ProgressDialog pDialog;
		private Activity activity;

		@Override
		protected void onPreExecute() {
	        super.onPreExecute();
	        pDialog = new ProgressDialog(activity);
	        pDialog.setMessage("Please wait...");
	        pDialog.setIndeterminate(false);
	        pDialog.setCancelable(false);
	        pDialog.show();
	    }
		
		protected Boolean doInBackground(Void... arg0) {
			
			HttpClient a = new HttpClient();
			DoctorModel doctor = new DoctorModel();

			doctor.setDoctorId(doctorid);
			doctor.setParentId(HealthtimeSession.getParentId(EditDoctorActivity.this));
			doctor.setSpecialty(doctorSpecialty.getText().toString());
			doctor.setHospital(doctorHospital.getText().toString());
			doctor.setHospitalAddress(doctorHospitalAddress.getText().toString());
			doctor.setConsultation(doctorConsultation.getText().toString());
			doctor.setContact1(doctorContact1.getText().toString());
			doctor.setContact2(doctorContact2.getText().toString());
			
			a.editDoctor(doctor);
			
			return true;
		}
		
		protected void onPostExecute(Boolean value){
			super.onPostExecute(value);
			mDoctorTask = null;
			pDialog.dismiss();
			if(value){
				Toast.makeText(activity, "Registration Successful", Toast.LENGTH_SHORT).show();
				startActivity(new Intent(EditDoctorActivity.this,TiledEventsActivity.class));
			}
			else{
				Toast.makeText(activity, "Registration Failed", Toast.LENGTH_SHORT).show();
			}
			
		}
		
	}
	
	private class RetrieveDoctorTask extends AsyncTask<Void,Void,String>{
		
		private ProgressDialog pDialog;
		
		@Override
		protected void onPreExecute() {
	        super.onPreExecute();
	        pDialog = new ProgressDialog(EditDoctorActivity.this);
	        pDialog.setMessage("Loading details. Please wait...");
	        pDialog.setIndeterminate(false);
	        pDialog.setCancelable(false);
	        pDialog.show();
	    }
		
		protected String doInBackground(Void... params) {
			
			HttpClient a = new HttpClient();
			String data = a.retrieve_doctor_by_parent(HealthtimeSession.getParentId(EditDoctorActivity.this));
			return data;
			
		}
		
		protected void onPostExecute(String value){
			super.onPostExecute(value);
	        pDialog.dismiss();
	        
			mRetDoctorTask = null;
			DoctorModel doctor = new DoctorModel();
			
			doctor = JSONParser.getOneDoctor(value);

			doctorid = doctor.getDoctorId();
			doctorSpecialty.setText(doctor.getSpecialty().toString());
			doctorHospital.setText(doctor.getHospital().toString());
			doctorHospitalAddress.setText(doctor.getHospitalAddress().toString());
			doctorConsultation.setText(doctor.getConsultation().toString());
			doctorContact1.setText(doctor.getContact1().toString());
			doctorContact2.setText(doctor.getContact2().toString());
		}
		
	}

}
