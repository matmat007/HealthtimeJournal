package com.healthtimejournal;

import java.util.List;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

import com.healthtimejournal.model.Account;
import com.healthtimejournal.model.ChildModel;
import com.healthtimejournal.service.HealthtimeSession;
import com.healthtimejournal.service.HttpClient;
import com.healthtimejournal.service.JSONParser;

public class SharedAccountActivity extends FragmentActivity{
	
	//variables
	private List<Account> accounts;
	
	//Android elements
	private ViewPager viewpager;
	private ProgressDialog dialog;
	
	//AsyncTask
	private SharedAccountMyChildrenTask smTask = null;
	private SharedAccountSharedByTask sbTask = null;
	private SharedAccountSharedToTask stTask = null;
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.shared_account_main_layout);
		
		retrieve_all_children();
		
		viewpager = (ViewPager) findViewById(R.id.shared_account_pager);
		//viewpager.setAdapter(new SharedAccountFragmentPageAdapter(getSupportFragmentManager()));
	}
	
	private void retrieve_all_children(){
		retrieve_my_children();
		retrieve_shared_by_children();
		retrieve_shared_to_children();
	}
	
	private void retrieve_my_children(){
		if(smTask != null){
			return;
		}
		
		smTask = new SharedAccountMyChildrenTask();
		smTask.execute();
	}
	
	private void retrieve_shared_to_children(){
		if(stTask != null){
			return;
		}
		
		stTask = new SharedAccountSharedToTask();
		stTask.execute();
	}
	
	private void retrieve_shared_by_children(){
		if(sbTask != null){
			return;
		}
		
		sbTask = new SharedAccountSharedByTask();
		sbTask.execute();
	}
	
	private class SharedAccountMyChildrenTask extends AsyncTask<Void, Void, String>{

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			dialog = new ProgressDialog(getBaseContext());
			dialog.setMessage("Loading Children. Please Wait...");
			dialog.setCancelable(false);
			dialog.setIndeterminate(false);
			dialog.show();
		}
		
		@Override
		protected String doInBackground(Void... params) {
			// TODO Auto-generated method stub
			HttpClient a = new HttpClient();
			String data = a.retrieve_child_by_family(HealthtimeSession.getFamilyId(getBaseContext()));
			return data;
		}
		
		@Override
		protected void onPostExecute(String result){
			super.onPostExecute(result);
			
			
			
			if(result != null){
				 
			}
			
			dialog.dismiss();
			
		}
		
	}
	
	private class SharedAccountSharedByTask extends AsyncTask<Void, Void, String>{

		@Override
		protected String doInBackground(Void... params) {
			// TODO Auto-generated method stub
			return null;
		}
		
	}
	
	private class SharedAccountSharedToTask extends AsyncTask<Void, Void, String>{

		@Override
		protected String doInBackground(Void... params) {
			// TODO Auto-generated method stub
			return null;
		}
		
	}
}
