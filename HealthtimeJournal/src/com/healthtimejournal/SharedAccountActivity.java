package com.healthtimejournal;

import java.util.ArrayList;
import java.util.List;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;

import com.healthtimejournal.customadapter.SharedAccountFragmentPageAdapter;
import com.healthtimejournal.model.Account;
import com.healthtimejournal.service.HealthtimeSession;
import com.healthtimejournal.service.HttpClient;

public class SharedAccountActivity extends FragmentActivity{
	
	//Android elements
	private ViewPager viewpager;
	private ProgressDialog dialog;
	
	//AsyncTask
	private SharedAccountMyChildrenTask sTask = null;
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.shared_account_main_layout);
		
		retrieve_children();
		
		viewpager = (ViewPager) findViewById(R.id.shared_account_pager);
		//viewpager.setAdapter(new SharedAccountFragmentPageAdapter(getSupportFragmentManager()));
	}
	
	private void retrieve_children(){
		if(sTask != null){
			return;
		}
		
		sTask = new SharedAccountMyChildrenTask();
		sTask.execute();
	}
	
	private class SharedAccountMyChildrenTask extends AsyncTask<Void, Void, List<Account>>{

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			Log.d("Before loading", " ");
			dialog = new ProgressDialog(SharedAccountActivity.this);
			dialog.setMessage("Loading Children. Please Wait...");
			dialog.setCancelable(false);
			dialog.setIndeterminate(false);
			Log.d("Show", "");
			dialog.show();
		}
		
		@Override
		protected List<Account> doInBackground(Void... params) {
			// TODO Auto-generated method stub
			List<Account> accounts = new ArrayList<Account>();
			List<ChildModel> temp;
			
			HttpClient a = new HttpClient();
			String data = a.retrieve_child_by_family(HealthtimeSession.getFamilyId(getBaseContext()));
			temp = JSONParser.getChild(data);
			if(temp == null)
				temp = new ArrayList<ChildModel>();
			accounts.add(insertChildren(temp, "My Children"));
			
			data = a.retrieve_shared_from_family_child(HealthtimeSession.getFamilyId(getBaseContext()));
			temp = JSONParser.getChild(data);
			if(temp == null)
				temp = new ArrayList<ChildModel>();
			accounts.add(insertChildren(temp, "From Family"));
			
			data = a.retrieve_shared_to_parent_child(HealthtimeSession.getFamilyId(getBaseContext()), HealthtimeSession.getParentId(getBaseContext()));
			temp = JSONParser.getChild(data);
			if(temp == null)
				temp = new ArrayList<ChildModel>();
			accounts.add(insertChildren(temp, "To Parent"));
			
			return accounts;
		}
		
		@Override
		protected void onPostExecute(List<Account> result){
			super.onPostExecute(result);
			
			if(result != null){
				viewpager.setAdapter(new SharedAccountFragmentPageAdapter(getSupportFragmentManager(), result));
			}
			
			dialog.dismiss();
			
		}
		
	}
	
	private Account insertChildren(List<ChildModel> children, String title){
		Account account = new Account();
		
		account.setName(title);
		account.setAccounts(children);
		
		return account;
	}
}
