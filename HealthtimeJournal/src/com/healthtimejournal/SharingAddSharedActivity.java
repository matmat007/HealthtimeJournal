package com.healthtimejournal;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;

import com.healthtimejournal.customadapter.MyCustomSearchListAdapter;
import com.healthtimejournal.model.ParentModel;
import com.healthtimejournal.model.SharingModel;
import com.healthtimejournal.service.HealthtimeSession;
import com.healthtimejournal.service.HttpClient;
import com.healthtimejournal.service.JSONParser;

public class SharingAddSharedActivity extends Activity{
	
	//UI elements
	private AutoCompleteTextView textview;
	private ListView listview;
	private ProgressDialog dialog;
	
	//adapter
	private MyCustomSearchListAdapter adapter, adapter1;
	
	//values
	private NonSharedParentsTask nTask = null;
	private SaveSharedParentsTask sTask = null;
	private static final String ARGS_ID = "ARGS_ID";
	private static final String ARGS = "ARGS";
	private int child_id;
	private List<ParentModel> parents, included;
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sharing_search_page_layout);
		
		child_id = getIntent().getBundleExtra(ARGS).getInt(ARGS_ID);
		Log.d("child_id", child_id+"");
		included = new ArrayList<ParentModel>();
		
		retrieve_non_shared_parents();
		
		listview = (ListView) findViewById(R.id.search_sharing_list);
		adapter1 = new MyCustomSearchListAdapter(this, included);
		listview.setAdapter(adapter1);
		
		textview = (AutoCompleteTextView) findViewById(R.id.search_sharing_searchbar);
		textview.setThreshold(1);
		textview.setAdapter(adapter);
		
		textview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				textview.setText("");
				getParentInfo(Integer.valueOf(arg1.getTag().toString()));
				Log.d("Size", included.size()+"");
				adapter.notifyDataSetChanged();
			}
		});
		
		textview.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub
				adapter.getFilter().filter(s);
				textview.setAdapter(adapter);
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				
			}
		});
		
		listview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				included.remove(arg2);
				adapter1.notifyDataSetChanged();
			}
			
		});
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.activity_sharing, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item){

		switch(item.getItemId()){
		case android.R.id.home:
			finish();
			break;

		case R.id.saveAction:
			save_shared_parents();
			break;
		}
		return true;

	}
	
	private void save_shared_parents(){
		if(sTask != null){
			return;
		}
		
		sTask = new SaveSharedParentsTask();
		sTask.execute();
		
	}
	
	private void retrieve_non_shared_parents(){
		if(nTask != null){
			return;
		}
		
		nTask = new NonSharedParentsTask();
		nTask.execute();
	}
	
	private void getParentInfo(int tag){
		for(ParentModel p : parents){
			if(p.getParentId() == tag && !included.contains(p)){
				included.add(p);
				return;
			}
		}
	}
	
	private class NonSharedParentsTask extends AsyncTask<Void, Void, String>{
		
		@Override
		protected String doInBackground(Void... params) {
			// TODO Auto-generated method stub
			HttpClient a = new HttpClient();
			String data = a.retrieve_non_shared_parents_by_child(child_id);
			return data;
		}
		
		@Override
		protected void onPostExecute(String result){
			if(result != null){
				parents = JSONParser.getParent(result);
				adapter = new MyCustomSearchListAdapter(getApplicationContext(), parents);
			}
		}
		
	}
	
	private class SaveSharedParentsTask extends AsyncTask<Void, Void, Boolean>{

		@Override
		protected void onPreExecute(){
			dialog = new ProgressDialog(SharingAddSharedActivity.this);
			dialog.setMessage("Saving... Please Wait...");
			dialog.setCancelable(false);
			dialog.setIndeterminate(false);
			dialog.show();
		}
		
		@Override
		protected Boolean doInBackground(Void... arg0) {
			// TODO Auto-generated method stub
			HttpClient a = new HttpClient();
			SharingModel model;
			
			for(ParentModel parent : included){
				model = new SharingModel();
				model.setChildId(child_id);
				model.setFromFamilyId(HealthtimeSession.getFamilyId(getBaseContext()));
				model.setToParentId(parent.getParentId());
				a.addSharing(model);
			}
			
			return true;
		}
		
		@Override
		protected void onPostExecute(Boolean bool){
			dialog.dismiss();
			Intent a = new Intent(SharingAddSharedActivity.this, ProfileChildAccountActivity.class);
			Bundle args = new Bundle();
			args.putInt(ARGS_ID, getIntent().getBundleExtra(ARGS).getInt(ARGS_ID));
			a.putExtra(ARGS, args);
			startActivity(a);
		}
		
	}

}
