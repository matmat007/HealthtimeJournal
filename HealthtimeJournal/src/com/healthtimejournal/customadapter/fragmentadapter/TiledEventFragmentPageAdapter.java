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
import com.healthtimejournal.model.PostModel;

public class TiledEventFragmentPageAdapter extends FragmentPagerAdapter{
	private List<ChildModel> children;
	private List<List<PostModel>> posts;
	private List<Fragment> fragments;
	
	public TiledEventFragmentPageAdapter(FragmentManager fm, List<ChildModel> children, List<List<PostModel>> posts){
		super(fm);
		this.children = children;
		this.posts = posts;
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
    		for(int o = 0; o < posts.size(); o++){
    			if(children.get(n).getChildId() == posts.get(o).get(0).getChildId()){
        			fragments.add(TiledEventPageFragment.create(n + 1, posts.get(o), getContents(posts.get(o))));
        		}
    		}
    		
    		if(fragments.size() == n){
    			fragments.add(TiledEventPageFragment.create(n + 1, null, null));
    		}
    	}
    }
    
    private ArrayList<String> getContents(List<PostModel> post){
    	ArrayList<String> list = new ArrayList<String>();
    	
    	for(int i = 0; i < post.size(); i++){
    		list.add(post.get(i).getPostContent());
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
}
