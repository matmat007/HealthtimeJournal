package com.healthtimejournal;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;

public class LifeEventsActivity extends TabActivity {

	TabHost tabHost;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.life_events_page);
		
		tabHost = getTabHost();
	    TabHost.TabSpec spec;
	    Intent intent;
	    
//	    intent = new Intent().setClass(this, FirstsActivity.class);
//	    spec = tabHost.newTabSpec("Firsts").setIndicator("Firsts").setContent(intent);
//	    tabHost.addTab(spec);
//
//	    intent = new Intent().setClass(this, MilestonesActivity.class);
//	    spec = tabHost.newTabSpec("Milestones").setIndicator("Milestones").setContent(intent);
//	    tabHost.addTab(spec);
//
//	    intent = new Intent().setClass(this, FoodActivity.class);
//	    spec = tabHost.newTabSpec("Food").setIndicator("Food").setContent(intent);
//	    tabHost.addTab(spec);
	    
    }
    
    @Override
    public void onResume(){
    	super.onResume();
    	tabHost = getTabHost();
    }
	
}
