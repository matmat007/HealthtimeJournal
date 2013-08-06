package com.healthtimejournal;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;

import com.healthtimejournal.customadapter.MyCustomAdapterTimeline;
import com.healthtimejournal.model.GalleryModel;
import com.healthtimejournal.model.ParentModel;
import com.healthtimejournal.model.PostModel;
import com.healthtimejournal.service.HttpClient;

public class TimelineActivity extends Activity {
	
	private RetrievePostTask mRetTask = null;

	ListView timelinelist;
	List<PostModel> postItem;
	List<GalleryModel> galleryItem;
	List<ParentModel> parentItem;
	MyCustomAdapterTimeline adapterTimeline;

	private int id;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
        getActionBar().setHomeButtonEnabled(true);

		postItem = new ArrayList<PostModel>();
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.timeline_page);
		
		Bundle a  = getIntent().getExtras();
		if(a != null){
			id = a.getInt("id");
			Log.d("id",String.valueOf(a.getInt("id")));
		}
        
        
        timelinelist = (ListView)findViewById(R.id.timeline_list);

        
        retrieve_post();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.activity_main, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item){
		
		switch(item.getItemId()){
		case android.R.id.home:
			startActivity(new Intent(TimelineActivity.this, TiledEventsActivity.class));
			break;
		case R.id.postAction:
			Intent a = new Intent(TimelineActivity.this, PostActivity.class);
			a.putExtra("id", id);
			startActivity(a);
			break;
		}
		return true;
		
	}
	
	public void retrieve_post(){
		mRetTask = new RetrievePostTask();
		mRetTask.execute();
	}
	
	private class RetrievePostTask extends AsyncTask<Void, Void, String>{

		private ProgressDialog pDialog;
		

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(TimelineActivity.this);
			pDialog.setMessage("Loading posts. Please wait...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(false);
			pDialog.show();
		}

		@Override
		protected String doInBackground(Void... params) {
			// TODO Auto-generated method stub
			HttpClient a = new HttpClient();
			String data = a.retrieve_all_post_by_event(id);
			return data;
		}

		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			
			//adapterTimeline = new MyCustomAdapterTimeline(TimelineActivity.this, postItem);
	        timelinelist.setAdapter(adapterTimeline);

			pDialog.dismiss();
		}

	}
	
}
