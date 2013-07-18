package com.healthtimejournal;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.healthtimejournal.customView.MyCustomHSV;
import com.healthtimejournal.customadapter.MyCustomAdapterTimeline;
import com.healthtimejournal.customadapter.MyCustomExpandableListAdapter;
import com.healthtimejournal.model.ChildList;
import com.healthtimejournal.model.GroupList;
import com.healthtimejournal.model.TimelineItem;

public class TimelineActivity extends Activity {
	
	private LinearLayout MenuList;
	private LinearLayout SideList;
	private boolean isExpanded;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
        getActionBar().setHomeButtonEnabled(true);
		
		super.onCreate(savedInstanceState);
        setContentView(R.layout.timeline_page);
        
        final ExpandableListView listview = (ExpandableListView)findViewById(R.id.listview);
        final ListView timelinelist = (ListView) findViewById(R.id.timeline_list);
        String[] values = new String[] {"Favorites", "Children", "Option"};
        String[][] values1 = new String[][] { {"News Feed", "Notification", "Album"}, {"Matthew Que", "Martyn Que"}, {"Create Doctor Page", "Log out"} };

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
        
        List<TimelineItem> items = new ArrayList<TimelineItem>();
        TimelineItem item;
        
        for(int i = 0; i < 5; i++){
        	item = new TimelineItem();
	        item.setName("Matthew");
	        item.setDatetime("Wednesday, 10:40 PM");
	        item.setContent("Hi, I am sick today :(");
	        items.add(item);
        }
        
        final MyCustomExpandableListAdapter adapter = new MyCustomExpandableListAdapter(this, list);
        listview.setAdapter(adapter);
        
        final MyCustomAdapterTimeline adapterTimeline = new MyCustomAdapterTimeline(this, items);
        timelinelist.setAdapter(adapterTimeline);
        LayoutParams params = timelinelist.getLayoutParams();
        params.width = width; 
        timelinelist.setLayoutParams(params);
        
        for(int k = 0; k < listview.getExpandableListAdapter().getGroupCount(); k++){
        	listview.expandGroup(k);
        }
        
        SideList = (LinearLayout) findViewById(R.id.sidebar_layout);
        params = SideList.getLayoutParams();
        params.width = width/4*3; 
        SideList.setLayoutParams(params);
        
        MenuList = (LinearLayout) findViewById(R.id.layout_timeline);
        
        MenuList.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(isExpanded){
					isExpanded = false;
					//SideList.setVisibility(View.GONE);
				}
			}
		});
        
        listview.setOnGroupClickListener(new OnGroupClickListener() {
			
			@Override
			public boolean onGroupClick(ExpandableListView parent, View v,
					int groupPosition, long id) {
				// TODO Auto-generated method stub
				return true;
			}
		});
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
}
