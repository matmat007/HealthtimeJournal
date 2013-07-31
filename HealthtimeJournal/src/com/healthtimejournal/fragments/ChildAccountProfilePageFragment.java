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
import android.widget.ScrollView;
import android.widget.TextView;

import com.healthtimejournal.R;
import com.healthtimejournal.model.ChildModel;

public class ChildAccountProfilePageFragment extends Fragment{
	
	private static final String ARGS_NAME = "ARGS_NAME";
	private static final String ARGS_GENDER = "ARGS_GENDER";
	private static final String ARGS_BLOODTYPE = "ARGS_BLOODTYPE";
	
	public static ChildAccountProfilePageFragment create(ChildModel child){
		Bundle bundle = new Bundle();
		bundle.putString(ARGS_NAME, child.getFirstName() + " " + child.getLastName());
		bundle.putString(ARGS_GENDER, child.getGender());
		bundle.putString(ARGS_BLOODTYPE, child.getBloodType());
		ChildAccountProfilePageFragment fragment = new ChildAccountProfilePageFragment();
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
    	
    	ScrollView layout = (ScrollView) view;
    	ImageView profile_img = (ImageView) layout.findViewById(R.id.profile_child_account_profile_img);
    	TextView name = (TextView) layout.findViewById(R.id.profile_child_account_name);
    	
    	profile_img.setImageResource(R.drawable.default_img);
    	profile_img.getLayoutParams().width = screenWidth/2;
    	profile_img.getLayoutParams().height = screenHeight/5;
    	
    	name.setText(getArguments().getString(ARGS_NAME));
    	
    	instatiateAbout(layout, inflater, container);
    	for(int i = 0; i < getActivity().getResources().getStringArray(R.array.graph).length; i++)
    	instantiateGraph(layout, inflater, container, screenHeight, getActivity().getResources().getStringArray(R.array.graph)[i]);
    	instantiateMilestone(layout, inflater, container);
    	
    	return view;
    }
    
    private void instatiateAbout(ScrollView scroll, LayoutInflater inflater, ViewGroup container){
    	LinearLayout layout = (LinearLayout) scroll.findViewById(R.id.profile_child_account_about);
    	
    	TextView gender = (TextView) layout.findViewById(R.id.profile_child_account_about_gender);
    	TextView bloodtype = (TextView) layout.findViewById(R.id.profile_child_account_about_bloodtype);
    	
    	gender.setText("Gender: " + getArguments().getString(ARGS_GENDER));
    	bloodtype.setText("Blood Type: " + getArguments().getString(ARGS_BLOODTYPE));

    }
    
    private void instantiateGraph(ScrollView scroll, LayoutInflater inflater, ViewGroup container, int screenHeight, String label){
    	LinearLayout layout = (LinearLayout) scroll.findViewById(R.id.profile_child_account_graph);

    	View graphtileView = inflater.inflate(R.layout.album_image_layout, container, false);
    	
    	FrameLayout flayout = (FrameLayout) graphtileView;
    	TextView textlabel = (TextView) flayout.findViewById(R.id.album_name);
    	ImageView tile_img = (ImageView) flayout.findViewById(R.id.album_img);
    	
    	textlabel.setText(label);
    	tile_img.setImageResource(R.drawable.default_img);
    	tile_img.getLayoutParams().height = screenHeight/5;
    	flayout.setPadding(2, 10, 2, 5);
    	
    	layout.addView(flayout, 1);
    } 
    
    private void instantiateMilestone(ScrollView scroll, LayoutInflater inflater, ViewGroup container){
    	LinearLayout layout = (LinearLayout) scroll.findViewById(R.id.profile_child_account_milestone);
    	View milestoneView = inflater.inflate(R.layout.profile_child_account_milestone_layout, container, false);
    	
    	LinearLayout llayout = (LinearLayout) milestoneView;
    	TextView label = (TextView) llayout.findViewById(R.id.profile_child_account_milestone_label);
    	TextView date = (TextView) llayout.findViewById(R.id.profile_child_account_milestone_date);
    	
    	label.setText("First Word");
    	date.setText("Achievement unlocked on 07/29/2013");
    	
    	layout.addView(llayout, 1);
    }
}
