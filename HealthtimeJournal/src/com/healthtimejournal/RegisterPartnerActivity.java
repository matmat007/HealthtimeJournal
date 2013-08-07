package com.healthtimejournal;

import java.util.List;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.healthtimejournal.model.FamilyModel;
import com.healthtimejournal.model.ParentModel;
import com.healthtimejournal.service.HealthtimeSession;
import com.healthtimejournal.service.HttpClient;
import com.healthtimejournal.service.JSONParser;

public class RegisterPartnerActivity extends Activity {
	
	private AddFamily mAddTask = null;
	
	private Button yesButton;
	private Button noButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register_partner_activity);
		
		yesButton = (Button)findViewById(R.id.yesButton);
		yesButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent a = new Intent(RegisterPartnerActivity.this,RegisterPartner2Activity.class);
				startActivity(a);
				
				
			}
		});
		
		noButton = (Button)findViewById(R.id.noButton);
		noButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				attemptAddFamily();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.register_partner_activity, menu);
		return true;
	}
	
	public void attemptAddFamily(){
		if(mAddTask != null){
			return;
		}
		
		mAddTask = new AddFamily(this);
		mAddTask.execute((Void) null);
	}
	
	private class AddFamily extends AsyncTask<Void,Void,String>{
		public AddFamily(Activity activity){
			this.activity = activity;
		}
		
		private Activity activity;
		private ProgressDialog pDialog;
		
		protected void onPreExecute() {
	        super.onPreExecute();
	        pDialog = new ProgressDialog(activity);
	        pDialog.setMessage("Finalizing..Please wait...");
	        pDialog.setIndeterminate(false);
	        pDialog.setCancelable(false);
	        pDialog.show();
	    }
		
		@Override
		protected String doInBackground(Void... params) {
			HttpClient a = new HttpClient();
			FamilyModel family = new FamilyModel();
			String data = a.retrieve_parent(HealthtimeSession.getParentId(getBaseContext()));
			List<ParentModel> result = JSONParser.getParent(data);
			int gender = Integer.parseInt(result.get(0).getGender());
			if(gender == 1){
				family.setFatherId(HealthtimeSession.getParentId(activity));
				family.setMotherId(0);
			}
			else{
				family.setFatherId(0);
				family.setMotherId(HealthtimeSession.getParentId(activity));
			}
			Log.d("father", String.valueOf(family.getFatherId()));
			Log.d("mother", String.valueOf(family.getMotherId()));
			data = a.addFamily(family).trim();
			// TODO Auto-generated method stub
			return data;
		}
		
		protected void onPostExecute(String value){
			super.onPostExecute(value);
			mAddTask = null;
			pDialog.dismiss();
			if(value.trim() != "error"){
				ParentModel parent = HealthtimeSession.getParentInfo(getBaseContext());
				parent.setFamilyId(Integer.parseInt(value.trim()));
				HealthtimeSession.save(parent, getBaseContext());
				startActivity(new Intent(RegisterPartnerActivity.this, TiledEventsActivity.class));
			}
			else{
				Toast.makeText(getBaseContext(), "Registration Failed", Toast.LENGTH_SHORT).show();
			}
			
		}
		
	}

}
