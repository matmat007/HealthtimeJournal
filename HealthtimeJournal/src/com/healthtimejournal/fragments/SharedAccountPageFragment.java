package com.healthtimejournal.fragments;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.SearchView.OnQueryTextListener;

import com.healthtimejournal.ProfileChildAccountActivity;
import com.healthtimejournal.R;

public class SharedAccountPageFragment extends Fragment {
	
	public static final String ARG_TITLE = "ARG_TITLE";
	public static final String ARG_IDS = "ARG_IDS";
	public static final String ARG_NAMES = "ARG_NAMES";
	public static final String ARGS = "ARGS";
	
	private ListView listview;
	private SearchView searchBar;
	private ArrayAdapter<String> adapter;
	
	public static SharedAccountPageFragment create(String title, ArrayList<Integer> ids, ArrayList<String> names){
		Bundle args = new Bundle();
		args.putString(ARG_TITLE, title);
		args.putIntegerArrayList(ARG_IDS, ids);
		args.putStringArrayList(ARG_NAMES, names);
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
    	
    	LinearLayout linear = (LinearLayout) view;

    	searchBar = (SearchView) linear.findViewById(R.id.shared_account_search_bar);
    	
    	listview = (ListView) linear.findViewById(R.id.shared_account_list);
    	
    	if(getArguments().getStringArrayList(ARG_NAMES) != null)
    		adapter = new ArrayAdapter<String>(getActivity().getBaseContext(), R.layout.shared_account_item_layout, R.id.shared_account_item_label, getArguments().getStringArrayList(ARG_NAMES));

    	listview.setAdapter(adapter);
    	
        listview.setTextFilterEnabled(true);
    	listview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				Intent a = new Intent(getActivity().getBaseContext(), ProfileChildAccountActivity.class);
				Bundle args = new Bundle();
				args.putInt("ARGS_ID", getArguments().getIntegerArrayList(ARG_IDS).get(arg2));
				a.putExtra(ARGS, args);
				startActivity(a);
			}
		});
    	
    	setupSearchView();
    	
    	return view;
    }
    
    private void setupSearchView() {
        searchBar.setIconifiedByDefault(false);
        searchBar.setOnQueryTextListener(new OnQueryTextListener() {
            @Override
            public boolean onQueryTextChange(String newText) {
            	adapter.getFilter().filter(newText);
            	listview.setAdapter(adapter);
                return true;
            }

            @Override
            public boolean onQueryTextSubmit(String query) {
                // Do something
                return true;
            }
        });
        searchBar.setSubmitButtonEnabled(false);
    }
    
    public boolean onQueryTextChange(String newText) {
        if (TextUtils.isEmpty(newText)) {
            listview.clearTextFilter();
        } else {
            listview.setFilterText(newText.toString());
        }
        return true;
    }
 
    public boolean onQueryTextSubmit(String query) {
        return false;
    }
    
}
