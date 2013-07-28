package com.healthtimejournal.fragments;

import android.graphics.Point;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.healthtimejournal.R;

public class ChildAccountPageFragment extends Fragment{

	public static ChildAccountPageFragment create(){
		Bundle bundle = new Bundle();
		ChildAccountPageFragment fragment = new ChildAccountPageFragment();
		fragment.setArguments(bundle);
		return fragment;
	}
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
    	Point size = new Point();
    	getActivity().getWindowManager().getDefaultDisplay().getSize(size);
    	int screenWidth = size.x;
    	int screenHeight = size.y;

    	
    	View view = inflater.inflate(R.layout.profile_child_account_page_layout, container, false);
    	
    	LinearLayout layout = (LinearLayout) view;
    	ImageView profile_img = (ImageView) layout.findViewById(R.id.profile_child_account_profile_img);
    	TextView name = (TextView) layout.findViewById(R.id.profile_child_account_name);
    	
    	profile_img.setImageResource(R.drawable.default_img);
    	profile_img.getLayoutParams().width = screenWidth/2;
    	profile_img.getLayoutParams().height = screenHeight/5;
    	
    	name.setText("Child 1");
    	
    	View graphtileView = inflater.inflate(R.layout.event_item, container, false);
    	
    	FrameLayout flayout = (FrameLayout) graphtileView;
    	ImageView tile_img = (ImageView) flayout.findViewById(R.id.timeline_tile_img);
    	tile_img.setImageResource(R.drawable.default_img);
    	tile_img.getLayoutParams().height = screenHeight/5;
    	
    	layout.addView(flayout, 3);
    	
    	return view;
    }
}
