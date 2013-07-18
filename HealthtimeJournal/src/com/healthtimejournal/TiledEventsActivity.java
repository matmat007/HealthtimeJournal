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
import com.healthtimejournal.model.ChildList;
import com.healthtimejournal.model.GroupList;
import com.healthtimejournal.service.HttpClient;
import com.healthtimejournal.service.JSONParser;

public class TiledEventsActivity extends FragmentActivity {
	
	private LinearLayout SideList;
	private boolean isExpanded = false;
	private Context context;
	private List<String> items;
	private EventChildNameTask eTask = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		items = new ArrayList<String>();
		
		getActionBar().setHomeButtonEnabled(true);
		
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tile_event_layout);
		context = this;
		
        String[] values = new String[] {"Favorites", "Children", "Option"};
        String[][] values1 = new String[][] { {"News Feed", "Notification", "Album"}, {}, {"Create Doctor Page", "Log out"} };
        
        retrieve_child(values1);
        
        final ExpandableListView listview = (ExpandableListView)findViewById(R.id.listview);
        final ArrayList<GroupList> list = new ArrayList<GroupList>();
        
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        
        for (int i = 0; i < values.length; i++) {
        	GroupList tempList = new GroupList();
            List<ChildList> tempList1 = new ArrayList<ChildList>();
            
        	tempList.setName(values[i]);
        	for(int j = 0; j < values1[i].length; j++){
        		ChildList temp = new ChildList();
        		temp.setName(values1[i][j]);
        		tempList1.add(temp);
        	}
        	tempList.setList(tempList1);
        	list.add(tempList);
        }
        
        final MyCustomExpandableListAdapter adapter = new MyCustomExpandableListAdapter(this, list);
        listview.setAdapter(adapter);
        for(int k = 0; k < listview.getExpandableListAdapter().getGroupCount(); k++){
        	listview.expandGroup(k);
        }
        
        ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        //viewPager.setAdapter(new FragmentPageAdapter(getSupportFragmentManager()));
        LayoutParams params = viewPager.getLayoutParams();
        params.width = width;
        viewPager.setLayoutParams(params);
        
        
        SideList = (LinearLayout) findViewById(R.id.sidebar_layout);
        params = SideList.getLayoutParams();
        params.width = width/4*3; 
        SideList.setLayoutParams(params);
        
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
	
	private void retrieve_child(String[][] values){
		if (eTask != null) {
            return;
        }
		
		eTask = new EventChildNameTask();
		eTask.execute();
		
		for(int i = 0; i < items.size(); i++){
			values[1][i] = items.get(i);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
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
			if(result != null){
				List<String> chldlist = JSONParser.getChildren(result);
				
				for(String s : chldlist){
					items.add(s);
				}
			}
		}
		
	}

}
