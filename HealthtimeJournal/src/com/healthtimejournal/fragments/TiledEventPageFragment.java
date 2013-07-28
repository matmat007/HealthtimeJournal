package com.healthtimejournal.fragments;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Point;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.GridLayout.Spec;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.healthtimejournal.R;
import com.healthtimejournal.model.PostModel;


public class TiledEventPageFragment extends Fragment {
    public static final String ARG_PAGE = "ARG_PAGE";
    public static final String ARG_CONTENT = "ARG_CONTENT";

    private int mPage;

    public static TiledEventPageFragment create(int page, List<PostModel> posts, ArrayList<String> contents) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        args.putStringArrayList(ARG_CONTENT, contents);
        TiledEventPageFragment fragment = new TiledEventPageFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("Page", String.valueOf(mPage));
        mPage = getArguments().getInt(ARG_PAGE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
    	View view = inflater.inflate(R.layout.event_grid, container, false);
    	
    	if(getArguments().getStringArrayList(ARG_CONTENT) != null){
    		Point size = new Point();
        	getActivity().getWindowManager().getDefaultDisplay().getSize(size);
        	int screenWidth = size.x;
        	int halfScreenWidth = (int)(screenWidth *0.5);
        	int quarterScreenWidth = (int)(halfScreenWidth * 0.5);
        	
        	
            ScrollView scroll = (ScrollView) view;
        	
        	GridLayout grid = (GridLayout)scroll.findViewById(R.id.timeline_gridlayout);
            grid.setColumnCount(2);
            grid.setRowCount(100);//for debug purpose only (should be dynamic)
            
            int spec_col = 0, spec_row = 0;
            for(int i = 0; i < getArguments().getStringArrayList(ARG_CONTENT).size(); i++){
            	Spec row = null, col = null;
            	int width = 0, height = 0;
            	final FrameLayout layout = (FrameLayout) inflater.inflate(R.layout.event_item, container, false);
            	
            	row = GridLayout.spec(spec_row);
    			col = GridLayout.spec(spec_col, 1);
    			width = quarterScreenWidth * 2;
    			height = quarterScreenWidth;
    			spec_col++;
    			
    			if(spec_col == 2){
    				spec_col = 0;
    				spec_row ++;
    			}
            	//for debug purpose only
            	/*switch(i){
            		case 0:
            			row = GridLayout.spec(0, 2);
            			col = GridLayout.spec(0, 2);
            			width = screenWidth;
            			height = quarterScreenWidth * 2;
            			break;
            		case 1:
            			row = GridLayout.spec(2);
            			col = GridLayout.spec(0, 1);
            			width = quarterScreenWidth * 2;
            			height = quarterScreenWidth;
            			break;
            		case 2:
            			row = GridLayout.spec(2);
            			col = GridLayout.spec(1, 1);
            			width = quarterScreenWidth * 2;
            			height = quarterScreenWidth;
            			break;
            		case 3:
            			row = GridLayout.spec(3, 2);
            			col = GridLayout.spec(0, 1);
            			width = quarterScreenWidth * 2;
            			height = quarterScreenWidth * 2;
            			break;
            		case 4:
            			row = GridLayout.spec(3);
            			col = GridLayout.spec(1, 1);
            			width = quarterScreenWidth * 2;
            			height = quarterScreenWidth;
            			break;
            		case 5:
            			row = GridLayout.spec(4);
            			col = GridLayout.spec(1, 1);
            			width = quarterScreenWidth * 2;
            			height = quarterScreenWidth;
            			break;
            	}//for debug purpose only*/
            	
    			layout.setTag(getArguments().getStringArrayList(ARG_CONTENT).get(i));
            	layout.setOnClickListener(new OnClickListener() {
    				
    				@Override
    				public void onClick(View v) {
    					// TODO Auto-generated method stub
    					Toast.makeText(getActivity(), (CharSequence) layout.getTag(), Toast.LENGTH_SHORT).show();
    				}
    			});
            	
                grid.addView(layout, populateGrid(layout, width, height, row, col, i));
            }
    	}
        
        return view;
    }
    
    private GridLayout.LayoutParams populateGrid(FrameLayout layout, int width, int height, GridLayout.Spec row, GridLayout.Spec col, int count){
    	ImageView img = (ImageView) layout.findViewById(R.id.timeline_tile_img);
    	GridLayout.LayoutParams params = new GridLayout.LayoutParams(row, col);
        params.width = width;
        params.height = height;
        
        TextView text = (TextView) layout.findViewById(R.id.timeline_item_title);
        if(getArguments().getStringArrayList(ARG_CONTENT) != null)
        	text.setText(getArguments().getStringArrayList(ARG_CONTENT).get(count));
        
        img.setImageResource(R.drawable.default_img);//for debug purpose only
        
        return params;
    }
}
