package com.healthtimejournal.fragments;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.healthtimejournal.R;

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
    	View view = inflater.inflate(R.layout.shared_account_page_layout, container, false);
    	
    	List<String> temp = new ArrayList<String>();
    	temp.add("Matthew Que");
    	temp.add("Niko Reyno");
    	temp.add("Aldrich Choi");
    	temp.add("Juliard Soriaga");
    	
    	LinearLayout linear = (LinearLayout) view;
    	ListView listview = (ListView) linear.findViewById(R.id.shared_account_list);
    	listview.setAdapter(new ArrayAdapter<String>(getActivity().getBaseContext(), R.layout.shared_account_item_layout, R.id.shared_account_item_label, temp));
    	
        return view;
    }
}
