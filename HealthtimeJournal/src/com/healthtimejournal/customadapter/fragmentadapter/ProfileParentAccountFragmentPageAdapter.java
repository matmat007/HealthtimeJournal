package com.healthtimejournal.customadapter.fragmentadapter;

import java.util.List;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.healthtimejournal.fragments.ParentAccountProfilePageFragment;
import com.healthtimejournal.model.ParentModel;

public class ProfileParentAccountFragmentPageAdapter extends FragmentPagerAdapter{

	List<Fragment> fragments;
	ParentModel parent;
	String[] items;
	
	public ProfileParentAccountFragmentPageAdapter(FragmentManager fm, ParentModel parent, String[] items) {
		super(fm);
		this.parent = parent;
		this.items = items;
		// TODO Auto-generated constructor stub
		instatiatefragments();
	}

	@Override
	public Fragment getItem(int arg0) {
		// TODO Auto-generated method stub
		return fragments.get(arg0);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return items.length;
	}
	
	@Override
	public void destroyItem(View collection, int position, Object view) {
		((ViewPager) collection).removeView((View) view);
	}
    
    @Override
    public CharSequence getPageTitle(int position) {
    	return items[position];
    }
    
    private void instatiatefragments(){
    	for(int i = 0; i < 3; i++){
    		switch(i){
    			case 0:
    				fragments.add(ParentAccountProfilePageFragment.create(parent));
    				break;
    		}
    	}
    	
    }

}
