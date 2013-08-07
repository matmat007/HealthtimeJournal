package com.healthtimejournal.customadapter.fragmentadapter;

import java.util.ArrayList;
import java.util.List;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.healthtimejournal.fragments.TiledEventPageFragment;
import com.healthtimejournal.model.ChildModel;
import com.healthtimejournal.model.Event;

public class TiledEventFragmentPageAdapter extends FragmentPagerAdapter{
	private List<ChildModel> children;
	private List<List<Event>> events;
	private List<Fragment> fragments;
	
	public TiledEventFragmentPageAdapter(FragmentManager fm, List<ChildModel> children, List<List<Event>> events){
		super(fm);
		this.children = children;
		this.events = events;
		fragments = new ArrayList<Fragment>();
		
		findId();
    }

    @Override
    public int getCount() {
    	return children.size();
    }

    @Override
    public Fragment getItem(int position) {
    	return fragments.get(position);
    }
    
    private void findId(){
    	for(int n = 0; n < children.size(); n++){
    		for(int o = 0; o < events.size(); o++){
    			if(children.get(n).getChildId() == events.get(o).get(0).getChildId()){
        			fragments.add(TiledEventPageFragment.create(n + 1, getIds(events.get(o)), (ArrayList<Event>)events.get(o)));
        		}
    		}
    		
    		if(fragments.size() == n){
    			fragments.add(TiledEventPageFragment.create(n + 1, null, null));
    		}
    	}
    }
    
    private ArrayList<String> getContents(List<Event> post){
    	ArrayList<String> list = new ArrayList<String>();
    	
    	for(int i = 0; i < post.size(); i++){
    		list.add(post.get(i).getEventContent());
    	}
    	return list;
    }
    
    private ArrayList<Integer> getIds(List<Event> post){
    	ArrayList<Integer> list = new ArrayList<Integer>();
    	
    	for(int i = 0; i < post.size(); i++){
    		list.add(post.get(i).getEventId());
    	}
    	return list;
    }
    
    @Override
	public void destroyItem(View collection, int position, Object view) {
		((ViewPager) collection).removeView((View) view);
	}
    
    @Override
    public CharSequence getPageTitle(int position) {
    	String name = children.get(position).getFirstName() + " " + children.get(position).getLastName();
    	return name;
    }
    
    public int getChildId(int position){
    	return children.get(position).getChildId();
    }
}
