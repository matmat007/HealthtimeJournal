package com.healthtimejournal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ChildProfileActivity extends Activity {
	
	Button informationButton;
	Button albumButton;
	Button lifeEventsButton;
	Button healthGrowthButton;
	Button timelineButton;
	
	ImageView profileImage;
	
	TextView profileName;
	TextView profileBirthdate;
	TextView profileAge;
	TextView profileBloodtype;
	TextView profileGender;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.child_profile);
		
		profileName = (TextView)findViewById(R.id.profileName);
		profileBirthdate = (TextView)findViewById(R.id.profileBirthdate);
		profileAge = (TextView)findViewById(R.id.profileAge);
		profileBloodtype = (TextView)findViewById(R.id.profileBloodtype);
		profileGender = (TextView)findViewById(R.id.profileGender);
		
		profileName.setText("Joey Bing");
		profileBirthdate.setText("1-1-11");
		profileAge.setText("2");
		profileBloodtype.setText("O");
		profileGender.setText("M");
		
		profileImage = (ImageView)findViewById(R.id.profileImage);
		profileImage.setImageResource(R.drawable.ic_launcher);
		
		informationButton = (Button)findViewById(R.id.informationButton);
		informationButton.setOnClickListener(new OnClickListener() { 
			public void onClick(View arg0) {
				Intent a = new Intent(ChildProfileActivity.this, EditChildInfoActivity.class);
		        startActivity(a);
			}
	    }); 
		
		albumButton = (Button)findViewById(R.id.albumButton);
		albumButton.setOnClickListener(new OnClickListener() { 
			public void onClick(View arg0) {
				Intent a = new Intent(ChildProfileActivity.this, PhotoViewerActivity.class);
		        startActivity(a);
			}
	    }); 
		
		lifeEventsButton = (Button)findViewById(R.id.lifeEventsButton);
		lifeEventsButton.setOnClickListener(new OnClickListener() { 
			public void onClick(View arg0) {
				Intent a = new Intent(ChildProfileActivity.this, LifeEventsActivity.class);
		        startActivity(a);
			}
	    }); 
		
		healthGrowthButton = (Button)findViewById(R.id.healthGrowthButton);
		healthGrowthButton.setOnClickListener(new OnClickListener() { 
			public void onClick(View arg0) {
				Intent a = new Intent(ChildProfileActivity.this, HealthGrowthActivity.class);
		        startActivity(a);
			}
	    }); 
		
		timelineButton = (Button)findViewById(R.id.timelineButton);
		timelineButton.setOnClickListener(new OnClickListener() { 
			public void onClick(View arg0) {
				Intent a = new Intent(ChildProfileActivity.this, TimelineActivity.class);
		        startActivity(a);
			}
	    }); 
		
	}

}
