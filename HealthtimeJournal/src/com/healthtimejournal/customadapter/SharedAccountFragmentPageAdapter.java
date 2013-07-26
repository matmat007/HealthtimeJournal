package com.healthtimejournal.customadapter;

import com.healthtimejournal.fragments.SharedAccountPageFragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

public class SharedAccountFragmentPageAdapter extends FragmentPagerAdapter{

	public SharedAccountFragmentPageAdapter(FragmentManager fm) {
		super(fm);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Fragment getItem(int arg0) {
		// TODO Auto-generated method stub
		return SharedAccountPageFragment.create();
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 1;
	}
	
	@Override
	public void destroyItem(View collection, int position, Object view) {
		((ViewPager) collection).removeView((View) view);
	}
    
    @Override
    public CharSequence getPageTitle(int position) {
    	
    	return "My Children";
    }

}
