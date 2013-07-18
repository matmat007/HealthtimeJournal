package com.healthtimejournal.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class SharedAccountPageFragment extends Fragment{
	
	public static SharedAccountPageFragment create(){
		Bundle args = new Bundle();
		SharedAccountPageFragment fragment = new SharedAccountPageFragment();
		fragment.setArguments(args);
		return fragment;
	}
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
    	
        return null;
    }
}
