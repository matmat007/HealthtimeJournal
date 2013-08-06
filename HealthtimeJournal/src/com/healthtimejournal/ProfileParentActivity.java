package com.healthtimejournal;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Menu;

import com.healthtimejournal.customadapter.fragmentadapter.ProfileParentAccountFragmentPageAdapter;
import com.healthtimejournal.model.ParentModel;
import com.healthtimejournal.service.HealthtimeSession;
import com.healthtimejournal.service.HttpClient;
import com.healthtimejournal.service.JSONParser;

public class ProfileParentActivity extends FragmentActivity {
	
	private ViewPager pager;
	private ProgressDialog dialog;
	
	private ParentInfoTask pTask = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.profile_parent_account_main_layout);
		
		retrieve_parent_info();
		
		pager = (ViewPager) findViewById(R.id.profile_parent_pager);
		
	}
	
	private void retrieve_parent_info(){
		if(pTask != null){
			return;
		}
		
		pTask = new ParentInfoTask();
		pTask.execute();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_profile_parent, menu);
		return true;
	}
	
	private class ParentInfoTask extends AsyncTask<Void, Void, String>{
		
		@Override
		protected void onPreExecute(){
			dialog = new ProgressDialog(ProfileParentActivity.this);
			dialog.setMessage("Loading Profile... Please Wait...");
			dialog.setCancelable(false);
			dialog.setIndeterminate(false);
			dialog.show();
			
		}
		
		@Override
		protected String doInBackground(Void... params) {
			// TODO Auto-generated method stub
			HttpClient a = new HttpClient();
			String data = a.retrieve_parent(HealthtimeSession.getParentId(getBaseContext()));
			return data;
		}
		
		@Override
		protected void onPostExecute(String result){
			super.onPostExecute(result);
			String[] items = getResources().getStringArray(R.array.parentItem);
			if(result != null){
				ParentModel model = JSONParser.getOneParent(result);
				ProfileParentAccountFragmentPageAdapter adapter = new ProfileParentAccountFragmentPageAdapter(getSupportFragmentManager(), model, items);
				pager.setAdapter(adapter);
			}
			
			dialog.dismiss();
		}
	}

}
