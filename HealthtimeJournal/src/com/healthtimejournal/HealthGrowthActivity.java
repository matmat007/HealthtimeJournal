package com.healthtimejournal;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;

public class HealthGrowthActivity extends TabActivity {

	TabHost tabHost;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.health_growth_page);
		
		tabHost = getTabHost();
	    TabHost.TabSpec spec;
	    Intent intent;
	    
	    intent = new Intent().setClass(this, WeightGraphActivity.class);
	    spec = tabHost.newTabSpec("Weight").setIndicator("Weight").setContent(intent);
	    tabHost.addTab(spec);

	    intent = new Intent().setClass(this, HeightGraphActivity.class);
	    spec = tabHost.newTabSpec("Height").setIndicator("Height").setContent(intent);
	    tabHost.addTab(spec);

//	    intent = new Intent().setClass(this, MedicineActivity.class);
//	    spec = tabHost.newTabSpec("Medicine").setIndicator("Medicine").setContent(intent);
//	    tabHost.addTab(spec);
//	    
//	    intent = new Intent().setClass(this, VaccinationActivity.class);
//	    spec = tabHost.newTabSpec("Vaccine").setIndicator("Vaccine").setContent(intent);
//	    tabHost.addTab(spec);
	    
    }
    
    @Override
    public void onResume(){
    	super.onResume();
    	tabHost = getTabHost();
    }
	
}
