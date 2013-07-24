package com.healthtimejournal;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.healthtimejournal.model.ChildModel;
import com.healthtimejournal.service.HttpClient;

@SuppressLint({ "NewApi", "SimpleDateFormat" })
public class AddChildActivity extends Activity{
	
	final Context context = this;
	
	private AddChildTask mAddTask = null;
	
	EditText firstnameText;
	EditText lastnameText;
	
	RadioButton maleCheckBox;
	RadioButton femaleCheckBox;
	
	Spinner bloodType;
	DatePicker birthdate;
	
	Button addChildButton;
	Button cancelButton;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_child);

		getActionBar().setHomeButtonEnabled(true);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		
		firstnameText = (EditText)findViewById(R.id.addChildFirstNameText);
		lastnameText = (EditText)findViewById(R.id.addChildLastNameText);
		
		maleCheckBox = (RadioButton)findViewById(R.id.addChildMaleCheck);
        femaleCheckBox = (RadioButton)findViewById(R.id.addChildFemaleCheck);

        bloodType = (Spinner)findViewById(R.id.addChildBloodTypeSpinner);
		List<String> list = new ArrayList<String>();
		list.add("A");
		list.add("B");
		list.add("O");
		list.add("AB");
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list);
		dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		bloodType.setAdapter(dataAdapter);
		
		birthdate = (DatePicker)findViewById(R.id.addChildDatePicker);
		birthdate.setCalendarViewShown(false);

        addChildButton = (Button)findViewById(R.id.addChildButton);
        addChildButton.setOnClickListener(new OnClickListener() { 
			public void onClick(View arg0) {
				attemptAddChild();
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

	
	private void attemptAddChild(){
		if(mAddTask != null){
			return;
		}
		
		String mLastName = lastnameText.getText().toString();
		String mFirstName = firstnameText.getText().toString();
		
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
		if(cancel){
			focusView.requestFocus();
		}
		else{
			mAddTask = new AddChildTask(this);
			mAddTask.execute((Void)null);
		}
	}
	
	private class AddChildTask extends AsyncTask<Void,Void,Boolean>{
		
		public AddChildTask(Activity activity){
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
		
		@SuppressWarnings("deprecation")
		protected Boolean doInBackground(Void... arg0) {
			
			int day  = birthdate.getDayOfMonth();
			int month= birthdate.getMonth();
			int year = birthdate.getYear();

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String formatedDate = sdf.format(new Date(year, month, day));
			
			HttpClient a = new HttpClient();
			ChildModel child = new ChildModel();
			
			child.setFirstName(firstnameText.getText().toString());
			child.setLastName(lastnameText.getText().toString());
			if(maleCheckBox.isChecked()){
				child.setGender("Male");
			}
			else
				child.setGender("Female");
			child.setBloodType(bloodType.getSelectedItem().toString());
			child.setBirthdate(formatedDate);
			child.setFamilyId(1);
			a.addChild(child);
			
			return true;
		}
		
		protected void onPostExecute(Boolean value){
			super.onPostExecute(value);
			mAddTask = null;
			pDialog.dismiss();
			if(value){
				Toast.makeText(activity, "Registration Successful", Toast.LENGTH_SHORT).show();
				activity.finish();
			}
			else{
				Toast.makeText(activity, "Registration Failed", Toast.LENGTH_SHORT).show();
			}
			
		}
		
	}
	
}
