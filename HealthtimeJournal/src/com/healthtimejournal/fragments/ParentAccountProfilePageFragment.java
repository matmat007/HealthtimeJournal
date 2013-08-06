package com.healthtimejournal.fragments;

import com.healthtimejournal.model.ParentModel;

import android.os.Bundle;
import android.support.v4.app.Fragment;

public class ParentAccountProfilePageFragment extends Fragment{
	
	public static ParentAccountProfilePageFragment create(ParentModel parent){
		Bundle args = new Bundle();
		args.putParcelable("parent", parent);
		
		ParentAccountProfilePageFragment fragment = new ParentAccountProfilePageFragment();
		fragment.setArguments(args);
		
		return fragment;
	}

}
