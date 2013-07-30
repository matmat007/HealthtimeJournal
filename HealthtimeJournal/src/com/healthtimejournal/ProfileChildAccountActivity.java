package com.healthtimejournal;

import java.util.List;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Menu;

import com.healthtimejournal.customadapter.fragmentadapter.ProfileChildAccountFragmentPageAdapter;
import com.healthtimejournal.model.ChildModel;
import com.healthtimejournal.model.ParentPrivilegeModel;
import com.healthtimejournal.service.HttpClient;
import com.healthtimejournal.service.JSONParser;

public class ProfileChildAccountActivity extends FragmentActivity {
	
	private ViewPager viewpager;
	private int child_id = 0;
	private ChildTask cTask = null;
	private ChildModel child = null;
	private List<ParentPrivilegeModel> parent = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.profile_child_account_main_layout);
		
		child_id = getIntent().getBundleExtra("ARGS").getInt("child_id");
		retrieve_child();
		
		viewpager = (ViewPager) findViewById(R.id.profile_child_pager);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.profile_child_main_activity, menu);
		return true;
	}
	
	private void retrieve_child(){
		if (cTask != null) {
			return;
		}
		
		cTask = new ChildTask();
		cTask.execute();
	}
	
	private class ChildTask extends AsyncTask<Void, Void, Boolean>{
		
		private ProgressDialog pDialog;
		
		@Override
		protected void onPreExecute() {
	        super.onPreExecute();
	        pDialog = new ProgressDialog(ProfileChildAccountActivity.this);
	        pDialog.setMessage("Loading profile. Please wait...");
	        pDialog.setIndeterminate(false);
	        pDialog.setCancelable(false);
	        pDialog.show();
	    }
		
		@Override
		protected Boolean doInBackground(Void... params) {
			// TODO Auto-generated method stub
			HttpClient a = new HttpClient();
			String data = a.retrieve_child(child_id);
			child = JSONParser.getOneChild(data);
			data = a.retrieve_sharing_by_child(child_id);
			parent = JSONParser.getParentPrivilege(data);
			
			return true;
		}
		
		@Override
		protected void onPostExecute(Boolean value) {
			// TODO Auto-generated method stub
			super.onPostExecute(value);
			
			if(value){
				ProfileChildAccountFragmentPageAdapter adapter = new ProfileChildAccountFragmentPageAdapter(getSupportFragmentManager(), child, parent,getResources().getStringArray(R.array.childItem));
				
				viewpager.setAdapter(adapter);
			}
			
			pDialog.dismiss();
		}
		
	}

}
