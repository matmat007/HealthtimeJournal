package com.healthtimejournal;

import java.util.ArrayList;
import java.util.List;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.healthtimejournal.customView.MyCustomHSV;
import com.healthtimejournal.customadapter.MyCustomExpandableListAdapter;
import com.healthtimejournal.function.MenuInstance;
import com.healthtimejournal.model.GroupList;
import com.healthtimejournal.service.HttpClient;
import com.healthtimejournal.service.JSONParser;

public class TiledEventsActivity extends FragmentActivity {
	
	private LinearLayout SideList;
	private boolean isExpanded = false;
	private Context context;
	private EventChildNameTask eTask = null;
	private List<String> items = null;
	private List<GroupList> list = null;
	private int width = 0;
	
	private MyCustomExpandableListAdapter adapter;
	private ExpandableListView listview; 
	private MyCustomHSV hsv;
	//private List<String> headlist = null; //Arrays.asList("Favorites", "Children", "Option");
	//private List<List<String>> sublist = null; //Arrays.asList(Arrays.asList("News Feed", "Notification", "Album"), Arrays.asList(""), Arrays.asList("Create Doctor Page", "Log out"));
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		items = new ArrayList<String>();
		
		getActionBar().setHomeButtonEnabled(true);
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tile_event_layout);
		context = this;
		
		Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        width = size.x;
        
        retrieve_child();
        
        listview = (ExpandableListView)findViewById(R.id.listview);
        
        ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        //viewPager.setAdapter(new FragmentPageAdapter(getSupportFragmentManager()));
        LayoutParams params = viewPager.getLayoutParams();
        params.width = width;
        viewPager.setLayoutParams(params);
        
        SideList = (LinearLayout) findViewById(R.id.sidebar_layout);
        params = SideList.getLayoutParams();
        params.width = width/4*3; 
        SideList.setLayoutParams(params);
        
        hsv = (MyCustomHSV) findViewById(R.id.timeline_scrollview);
        
        listview.setOnGroupClickListener(new OnGroupClickListener() {
			
			@Override
			public boolean onGroupClick(ExpandableListView parent, View v,
					int groupPosition, long id) {
				// TODO Auto-generated method stub
				return true;
			}
		});
        
        listview.setOnChildClickListener(new OnChildClickListener() {
			
			@Override
			public boolean onChildClick(ExpandableListView parent, View v,
					int groupPosition, int childPosition, long id) {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(), String.valueOf(childPosition), Toast.LENGTH_SHORT).show();
				switch(groupPosition){
					case 0:
						if(childPosition == 2){
							startActivity(new Intent(TiledEventsActivity.this, AlbumActivity.class));
						}
						break;
				}
				return false;
			}
		});
        
	}
	
	private void retrieve_child(){
		if (eTask != null) {
			return;
        
		}
		
		eTask = new EventChildNameTask();
		eTask.execute();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item){
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
			startActivity(new Intent(TiledEventsActivity.this, PostActivity.class));
			break;
		}
		return true;
	}
	
	private class EventChildNameTask extends AsyncTask<Void, Void, String>{
		
		private ProgressDialog pDialog;
		
		@Override
		protected void onPreExecute() {
	        super.onPreExecute();
	        pDialog = new ProgressDialog(context);
	        pDialog.setMessage("Loading events. Please wait...");
	        pDialog.setIndeterminate(false);
	        pDialog.setCancelable(false);
	        pDialog.show();
	    }
		
		@Override
		protected String doInBackground(Void... params) {
			// TODO Auto-generated method stub
			HttpClient a = new HttpClient();
			String data = a.retrieve_child(1);
			return data;
		}
		
		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
	        
	        pDialog.dismiss();
			
			if(result != null){
				List<String> chldlist = JSONParser.getChildren(result);
				
				for(String s : chldlist){
					items.add(s);
					Log.d("Child", s);
				}
				
				list = MenuInstance.instatiateGroup(chldlist);
				adapter = new MyCustomExpandableListAdapter(context, list); 
				adapter.notifyDataSetChanged();
				listview.setAdapter(adapter);
				
				for(int k = 0; k < listview.getExpandableListAdapter().getGroupCount(); k++){
		        	listview.expandGroup(k);
		        }
				
				hsv.scrollTo(width/4*3, 0);
				
				Log.d("Size", String.valueOf(items.size()));
			}
		}
		
	}

}
