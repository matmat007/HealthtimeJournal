package com.healthtimejournal;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Menu;

import com.healthtimejournal.customadapter.fragmentadapter.ProfileChildAccountFragmentPageAdapter;

public class ProfileChildAccountActivity extends FragmentActivity {
	
	private ViewPager viewpager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.profile_child_account_main_layout);
		
		viewpager = (ViewPager) findViewById(R.id.profile_child_pager);
		viewpager.setAdapter(new ProfileChildAccountFragmentPageAdapter(getSupportFragmentManager()));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.profile_child_main_activity, menu);
		return true;
	}

}
