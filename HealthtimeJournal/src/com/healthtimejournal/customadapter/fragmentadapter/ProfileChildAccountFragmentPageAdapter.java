package com.healthtimejournal.customadapter.fragmentadapter;

import java.util.ArrayList;
import java.util.List;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.healthtimejournal.fragments.ChildAccountAlbumPageFragment;
import com.healthtimejournal.fragments.ChildAccountProfilePageFragment;
import com.healthtimejournal.model.ChildModel;

public class ProfileChildAccountFragmentPageAdapter extends FragmentPagerAdapter{

	List<Fragment> fragments;
	ChildModel child;
	
	public ProfileChildAccountFragmentPageAdapter(FragmentManager fm, ChildModel child) {
		// TODO Auto-generated constructor stub
		super(fm);
		this.child = child;
		
		instantiatefragments();
	}

	@Override
	public Fragment getItem(int arg0) {
		// TODO Auto-generated method stub
		return fragments.get(arg0);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 2;
	}
	
	@Override
	public void destroyItem(View collection, int position, Object view) {
		((ViewPager) collection).removeView((View) view);
	}
    
    @Override
    public CharSequence getPageTitle(int position) {
    	
    	return "Profile";
    }
    
    private void instantiatefragments(){
    	fragments = new ArrayList<Fragment>();
    	
    	for(int i = 0; i < 2; i++){
    		switch(i){
    			case 0:
    				fragments.add(ChildAccountProfilePageFragment.create(child));
    				break;
    			case 1:
    				fragments.add(ChildAccountAlbumPageFragment.create());
    				break;
    		}
    	}
    }

}
