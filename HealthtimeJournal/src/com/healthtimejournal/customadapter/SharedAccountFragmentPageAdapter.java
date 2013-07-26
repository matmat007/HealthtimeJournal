package com.healthtimejournal.customadapter;

import java.util.ArrayList;
import java.util.List;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.healthtimejournal.fragments.SharedAccountPageFragment;
import com.healthtimejournal.model.Account;
import com.healthtimejournal.model.ChildModel;

public class SharedAccountFragmentPageAdapter extends FragmentPagerAdapter{
	
	private List<Account> accounts;
	private List<Fragment> fragments;
	
	public SharedAccountFragmentPageAdapter(FragmentManager fm, List<Account> accounts) {
		// TODO Auto-generated method stub
		super(fm);
		this.accounts = accounts;
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
		return accounts.size();
	}
	
	@Override
	public void destroyItem(View collection, int position, Object view) {
		((ViewPager) collection).removeView((View) view);
	}
    
    @Override
    public CharSequence getPageTitle(int position) {
    	
    	return accounts.get(position).getName();
    }
    
    private void instatiatefragments(){
    	fragments = new ArrayList<Fragment>();
    	
    	for(Account a : accounts){
    		ArrayList<String> names = new ArrayList<String>();
    		for(ChildModel c : a.getAccounts()){
    			names.add(c.getFirstName() + " " + c.getLastName());
    		}
    		if(names.size() > 0)
    			fragments.add(SharedAccountPageFragment.create(a.getName(), names));
    		else
    			fragments.add(SharedAccountPageFragment.create(a.getName(), null));
    	}
    }

}
