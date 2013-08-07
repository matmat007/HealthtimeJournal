package com.healthtimejournal.fragments;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Point;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.healthtimejournal.R;
import com.healthtimejournal.customadapter.MyCustomAdapterAlbum;
import com.healthtimejournal.model.LabeledImage;

public class ChildAccountAlbumPageFragment extends Fragment{
	
	public static ChildAccountAlbumPageFragment create(){
		Bundle bundle = new Bundle();
		ChildAccountAlbumPageFragment fragment = new ChildAccountAlbumPageFragment();
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
    	int screenHeight = size.y;
    	
    	//for debug purpose
    			List<LabeledImage> list = new ArrayList<LabeledImage>();
    			for(int i = 0; i < 3; i++){
    				LabeledImage one = new LabeledImage();
    				one.setId(i);
    				one.setTitle("Sample text when text is too long blah blah blah sample text");
    				one.setImg("");
    				list.add(one);
    			}
    	
    	
    	View view = inflater.inflate(R.layout.album_page_layout, container, false);
    	
    	GridView grid = (GridView) view.findViewById(R.id.album_grid);
    	grid.setAdapter(new MyCustomAdapterAlbum(getActivity().getBaseContext(), list, screenHeight));
    	
    	return view;
    }
}
