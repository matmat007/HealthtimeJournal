package com.healthtimejournal;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.healthtimejournal.model.ChildModel;
import com.healthtimejournal.model.DoctorModel;
import com.healthtimejournal.model.ParentModel;
import com.healthtimejournal.model.SearchUserModel;
import com.healthtimejournal.service.HealthtimeSession;
import com.healthtimejournal.service.HttpClient;
import com.healthtimejournal.service.JSONParser;

public class SearchBarActivity extends Activity {
	
	private RetrieveUserTask mRetUser = null;
	
	private List<String> searchNames = new ArrayList<String>();
	
	EditText searchbar;
	
	ListView autoList;
	
	ArrayAdapter<String> adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.search_activity);
		
		searchbar = (EditText)findViewById(R.id.search_searchbar);
		searchbar.addTextChangedListener(filterTextWatcher);
		
		retrieve_users();
		
		autoList = (ListView)findViewById(R.id.search_listview);
		adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, searchNames);
		
		autoList.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> a, View v, int position, long id) {
			}
		});
		
	}
	
	private TextWatcher filterTextWatcher = new TextWatcher() {

	    public void afterTextChanged(Editable s) {
	    }

	    public void beforeTextChanged(CharSequence s, int start, int count,
	            int after) {
	    }

	    public void onTextChanged(CharSequence s, int start, int before,
	            int count) {
	        adapter.getFilter().filter(s);
	        if(searchbar.getText().toString().equals(""))
	        	autoList.setAdapter(null);
	        else
	        	autoList.setAdapter(adapter);
	    }

	};

	@Override
	protected void onDestroy() {
	    super.onDestroy();
	    searchbar.removeTextChangedListener(filterTextWatcher);
	}
	
	public void retrieve_users(){
		mRetUser = new RetrieveUserTask();
		mRetUser.execute();
		
	}
	
	private class RetrieveUserTask extends AsyncTask<Void, Void, SearchUserModel>{
		
		private ProgressDialog pDialog;
		
		@Override
		protected void onPreExecute() {
	        super.onPreExecute();
	        pDialog = new ProgressDialog(SearchBarActivity.this);
	        pDialog.setMessage("Loading people.. Please wait...");
	        pDialog.setIndeterminate(false);
	        pDialog.setCancelable(false);
	        pDialog.show();
	    }
		
		@Override
		protected SearchUserModel doInBackground(Void... params) {
			// TODO Auto-generated method stub
			HttpClient a = new HttpClient();
			
			String datadoctor = a.retrieve_all_doctor();
			String datachild = a.retrieve_all_child();
			String dataparent = a.retrieve_all_parent();
			
			List<DoctorModel> doctor = JSONParser.getDoctor(datadoctor);
			List<ChildModel> child = JSONParser.getChild(datachild);
			List<ParentModel> parent = JSONParser.getParent(dataparent);
			
			SearchUserModel searchmodel = new SearchUserModel();
			searchmodel.setDoctorList(doctor);
			searchmodel.setChildList(child);
			searchmodel.setParentList(parent);
			
			return searchmodel;
		}
		
		@Override
		protected void onPostExecute(SearchUserModel result) {
			super.onPostExecute(result);
		
			for(int i = 0; i < result.getParentList().size(); i++){
				if(HealthtimeSession.getParentId(getBaseContext()) != result.getParentList().get(i).getParentId()){
					searchNames.add(result.getParentList().get(i).getFirstName().toString() + " " + result.getParentList().get(i).getLastName().toString() + " (parent)");
				}
			}
			
			for(int j = 0; j < result.getDoctorList().size(); j++){
				if(HealthtimeSession.getParentId(getBaseContext()) != result.getDoctorList().get(j).getParentId())
					searchNames.add(result.getParentList().get(j).getFirstName().toString() + " " + result.getParentList().get(j).getLastName().toString() + " (doctor)");
			}
			
			for(int k = 0; k < result.getChildList().size(); k++){
				if(HealthtimeSession.getFamilyId(getBaseContext()) != result.getChildList().get(k).getFamilyId())
					searchNames.add(result.getChildList().get(k).getFirstName().toString() + " " + result.getChildList().get(k).getLastName().toString() + " (child)");
			}
			
			
			pDialog.dismiss();
			
		}
		
	}

}
