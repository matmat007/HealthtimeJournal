package com.healthtimejournal;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Point;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;

import com.healthtimejournal.customView.MyCustomHSV;
import com.healthtimejournal.customadapter.MyCustomAdapterTimeline;
import com.healthtimejournal.model.PostModel;
import com.healthtimejournal.service.HttpClient;
import com.healthtimejournal.service.JSONParser;

public class TimelineActivity extends Activity {
	
	private RetrievePostTask mRetTask = null;
	
	private boolean isExpanded;

	ListView timelinelist;
	List<PostModel> timelineItem;
	MyCustomAdapterTimeline adapterTimeline;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
        getActionBar().setHomeButtonEnabled(true);

		timelineItem = new ArrayList<PostModel>();
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.timeline_page);
		
		Bundle a  = getIntent().getExtras();
		if(a != null){
			
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
		Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        MyCustomHSV hsv = (MyCustomHSV)findViewById(R.id.timeline_scrollview);
        
		switch(item.getItemId()){
		case android.R.id.home:
			if (!isExpanded){
				isExpanded = true;
		        hsv.scrollTo(0, 0);
				getActionBar().setDisplayHomeAsUpEnabled(true);
			}
			else{
				isExpanded = false;
		        hsv.scrollTo(width/4*3, 0);
				getActionBar().setDisplayHomeAsUpEnabled(false);
			}
			break;
		case R.id.postAction:
			startActivity(new Intent(TimelineActivity.this, PostActivity.class));
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
			String data = a.retrieve_all_post_by_event(1);
			return data;
		}

		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			timelineItem = JSONParser.getPost(result);

			adapterTimeline = new MyCustomAdapterTimeline(TimelineActivity.this, timelineItem);
	        timelinelist.setAdapter(adapterTimeline);

			pDialog.dismiss();
		}

	}
	
}
