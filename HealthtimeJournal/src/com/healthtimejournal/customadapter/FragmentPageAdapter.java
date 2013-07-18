package com.healthtimejournal.customadapter;

import org.json.JSONArray;
import org.json.JSONException;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.healthtimejournal.fragments.TiledEventPageFragment;

public class FragmentPageAdapter extends FragmentPagerAdapter{
	private JSONArray array, array1;
	
    public FragmentPageAdapter(FragmentManager fm, JSONArray array, JSONArray array1) {
        super(fm);
        this.array = array;
        this.array1 = array1;
    }

    @Override
    public int getCount() {
        return array.length();
    }

    @Override
    public Fragment getItem(int position) {
        return TiledEventPageFragment.create(position + 1, array1);
    }
    
    @Override
	public void destroyItem(View collection, int position, Object view) {
		((ViewPager) collection).removeView((View) view);
	}
    
    @Override
    public CharSequence getPageTitle(int position) {
    	String name = null;
		try {
			name = array.getJSONObject(position).getString("first_name") + " " + array.getJSONObject(position).getString("last_name");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
    	return name;
    }
}
