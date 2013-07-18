package com.healthtimejournal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class ParentProfileActivity extends Activity {
	
	ImageView parentImage;
	
	TextView parentName;
	TextView parentBirthdate;
	TextView parentAge;
	TextView parentBloodtype;
	TextView parentGender;
	
	ListView myChildList;
	ListView friendChildList;
	
	Button parentInfoButton;
	Button parentAlbumButton;
	Button parentChildrenButton;
	Button parentSharedAccountButton;
	Button parentFriendChildrenButton;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.parent_profile);
		
		parentImage = (ImageView)findViewById(R.id.parentImage);
		
		parentName = (TextView)findViewById(R.id.parentName);
		parentBirthdate = (TextView)findViewById(R.id.parentBirthdate);
		parentAge = (TextView)findViewById(R.id.parentAge);
		parentBloodtype = (TextView)findViewById(R.id.parentBloodtype);
		parentGender = (TextView)findViewById(R.id.parentGender);
		
		parentImage.setImageResource(R.drawable.ic_launcher);
		parentName.setText("Joey Bing");
		parentBirthdate.setText("1-1-11");
		parentAge.setText("42");
		parentBloodtype.setText("O");
		parentGender.setText("M");
		
		parentInfoButton = (Button)findViewById(R.id.parentInformationButton);
		parentInfoButton.setOnClickListener(new OnClickListener() { 
			public void onClick(View arg0) {
				Intent a = new Intent(ParentProfileActivity.this, EditParentInfoActivity.class);
		        startActivity(a);
			}
	    }); 
		
		parentAlbumButton = (Button)findViewById(R.id.parentAlbumButton);
		parentAlbumButton.setOnClickListener(new OnClickListener() { 
			public void onClick(View arg0) {
				Intent a = new Intent(ParentProfileActivity.this, AlbumActivity.class);
		        startActivity(a);
			}
	    }); 
		
		parentChildrenButton = (Button)findViewById(R.id.parentChildrenButton);
		parentChildrenButton.setOnClickListener(new OnClickListener() { 
			public void onClick(View arg0) {
				Intent a = new Intent(ParentProfileActivity.this, MyChildrenActivity.class);
		        startActivity(a);
			}
	    }); 
		
		parentSharedAccountButton = (Button)findViewById(R.id.parentSharedAccountsButton);
		parentSharedAccountButton.setOnClickListener(new OnClickListener() { 
			public void onClick(View arg0) {
				Intent a = new Intent(ParentProfileActivity.this, MySharedAccountsActivity.class);
		        startActivity(a);
			}
	    }); 
		
		parentFriendChildrenButton = (Button)findViewById(R.id.parentFriendsChildrenButton);
		parentFriendChildrenButton.setOnClickListener(new OnClickListener() { 
			public void onClick(View arg0) {
				Intent a = new Intent(ParentProfileActivity.this, MyFriendChildrenActivity.class);
		        startActivity(a);
			}
	    });
		
	}
	
}
