package com.healthtimejournal;

import java.util.List;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import com.healthtimejournal.model.ParentModel;
import com.healthtimejournal.service.HttpClient;
import com.healthtimejournal.service.JSONParser;

public class SharingAddSharedActivity extends Activity{
	
	//UI elements
	private AutoCompleteTextView textview;
	
	//adapter
	private ArrayAdapter<String> adapter;
	
	//values
	private NonSharedParentsTask nTask = null;
	private static final String ARGS_ID = "ARG_ID";
	private int child_id;
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sharing_search_page_layout);
		
		child_id = savedInstanceState.getInt(ARGS_ID);
		
		retrieve_non_shared_parents();
		
		textview = new AutoCompleteTextView(this);
	}
	
	private void retrieve_non_shared_parents(){
		if(nTask != null){
			return;
		}
		
		nTask = new NonSharedParentsTask();
		nTask.execute();
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
				List<ParentModel> parents = JSONParser.getParent(result);
				
			}
		}
		
	}

}
