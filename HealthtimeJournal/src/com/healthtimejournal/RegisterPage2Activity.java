package com.healthtimejournal;

import java.util.Arrays;
import java.util.List;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.Toast;

import com.healthtimejournal.customadapter.MyCustomArrayAdapterSickness;
import com.healthtimejournal.model.ParentSicknessModel;
import com.healthtimejournal.service.HealthtimeSession;
import com.healthtimejournal.service.HttpClient;

public class RegisterPage2Activity extends Activity{
	
	private HistoryTask mHistTask = null;
	private int parentid = HealthtimeSession.getParentId(this);
	
	CheckBox anemiaCheckBox;
	CheckBox asthmaCheckBox;
	CheckBox bleedingDisCheckBox;
	CheckBox diabetesCheckBox;
	CheckBox epilepsyCheckBox;
	CheckBox heartDisCheckBox;
	CheckBox highBloodCheckBox;
	CheckBox highChoCheckBox;
	CheckBox liverDisCheckBox;
	CheckBox kidneyDisCheckBox;
	CheckBox nasalAllCheckBox;
	CheckBox tuberculosisCheckBox;
	
	MyCustomArrayAdapterSickness adapter;
	
	Button submitButton;
	Button previousButton;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register_page_2);
		
		getActionBar().setHomeButtonEnabled(true);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		
		ListView listview = (ListView)findViewById(R.id.sick_list);
		List<String> data = Arrays.asList(getResources().getStringArray(R.array.sickness));
		
		adapter = new MyCustomArrayAdapterSickness(data, this);
		listview.setAdapter(adapter);
		
		
		
		submitButton = (Button)findViewById(R.id.regSubmitButton);
		submitButton.setOnClickListener(new OnClickListener() { 
			public void onClick(View arg0) {
				attemptAddHistory();
			}
	    });
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item){
		
		startActivity(new Intent(RegisterPage2Activity.this, RegisterPageActivity.class));
		return true;
	}

	private void attemptAddHistory(){
		if(mHistTask != null){
			return;
		}
		mHistTask = new HistoryTask(this);
		mHistTask.execute((Void) null);
	}
	
	private class HistoryTask extends AsyncTask<Void, Void, Boolean>{

		public HistoryTask(Activity activity) {
			// TODO Auto-generated constructor stub
			this.activity = activity;
		}
		private ProgressDialog pDialog;
		private Activity activity;
		
		protected void onPreExecute() {
	        super.onPreExecute();
	        pDialog = new ProgressDialog(activity);
	        pDialog.setMessage("Please wait...");
	        pDialog.setIndeterminate(false);
	        pDialog.setCancelable(false);
	        pDialog.show();
	    }
		
		@Override
		protected Boolean doInBackground(Void... params) {
			HttpClient a = new HttpClient();
			List<Boolean> result = adapter.getResult();
			ParentSicknessModel model = new ParentSicknessModel();
			model.setParentId(parentid);
			for(int i = 0; i<result.size(); i++)
				Log.d(String.valueOf(i), String.valueOf(result.get(i)));
			
			if(result.get(0))
				model.setAnemia(1);
			else
				model.setAnemia(0);
			
			if(result.get(1))
				model.setAsthma(1);
			else
				model.setAsthma(0);
			
			if(result.get(2))
				model.setBleedingDis(1);
			else
				model.setBleedingDis(0);
			
			if(result.get(3))
				model.setDiabetes(1);
			else
				model.setDiabetes(0);
			
			if(result.get(4))
				model.setEpilepsy(1);
			else
				model.setDiabetes(0);
			
			if(result.get(5))
				model.setHeartDis(1);
			else
				model.setHeartDis(0);
			
			if(result.get(6))
				model.setHighBlood(1);
			else
				model.setHighBlood(0);
			
			if(result.get(7))
				model.setHighCho(1);
			else
				model.setHighCho(0);
			
			if(result.get(8))
				model.setLiverDis(1);
			else
				model.setLiverDis(0);
			
			if(result.get(9))
				model.setKidneyDis(1);
			else
				model.setKidneyDis(0);
			
			if(result.get(10))
			
				model.setNasalAll(1);
			else
				model.setNasalAll(0);
			
			if(result.get(11))
				model.setTuberculosis(1);
			else
				model.setTuberculosis(0);
			
			a.addMedicalHistory(model);
			return true;
		}
		
		protected void onPostExecute(Boolean value){
			super.onPostExecute(value);
			mHistTask = null;
			pDialog.dismiss();
			if(value){
				Toast.makeText(activity, "Registration Successful", Toast.LENGTH_SHORT).show();
				startActivity(new Intent(RegisterPage2Activity.this, RegisterPage3Activity.class));
			}
			else{
				Toast.makeText(activity, "Registration Failed", Toast.LENGTH_SHORT).show();
			}
			
		}
	}
}
