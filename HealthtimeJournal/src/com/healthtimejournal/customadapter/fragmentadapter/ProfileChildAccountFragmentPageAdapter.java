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
import com.healthtimejournal.fragments.ChildAccountSharePageFragment;
import com.healthtimejournal.model.ChildModel;
import com.healthtimejournal.model.ParentPrivilegeModel;

public class ProfileChildAccountFragmentPageAdapter extends FragmentPagerAdapter{

	List<Fragment> fragments;
	ChildModel child;
	List<ParentPrivilegeModel>  parent;
	String[] arrays;
	
	public ProfileChildAccountFragmentPageAdapter(FragmentManager fm, ChildModel child, List<ParentPrivilegeModel> parent, String[] arrays) {
		// TODO Auto-generated constructor stub
		super(fm);
		this.child = child;
		this.arrays = arrays;
		this.parent = parent;
		
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
		return arrays.length;
	}
	
	@Override
	public void destroyItem(View collection, int position, Object view) {
		((ViewPager) collection).removeView((View) view);
	}
    
    @Override
    public CharSequence getPageTitle(int position) {
    	
    	return arrays[position];
    }
    
    private void instantiatefragments(){
    	fragments = new ArrayList<Fragment>();
    	
    	for(int i = 0; i < arrays.length; i++){
    		switch(i){
    			case 0:
    				fragments.add(ChildAccountProfilePageFragment.create(child));
    				break;
    			case 1:
    				fragments.add(ChildAccountAlbumPageFragment.create());
    				break;
    			case 2:
    				ArrayList<String> accounts = new ArrayList<String>();
    				ArrayList<Integer> ids = new ArrayList<Integer>();
    				ArrayList<Integer> privileges = new ArrayList<Integer>();
    				
    				for(ParentPrivilegeModel p : parent){
    					ids.add(p.getShare().getSharingId());
    					accounts.add(p.getParents().getFirstName() + " " + p.getParents().getLastName());
    					privileges.add(p.getShare().getPrivilege());
    				}
    				
    				fragments.add(ChildAccountSharePageFragment.create(ids, accounts, privileges));
    				break;
    		}
    	}
    }

}
