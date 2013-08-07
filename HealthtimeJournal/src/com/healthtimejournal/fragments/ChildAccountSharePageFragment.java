package com.healthtimejournal.fragments;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.healthtimejournal.R;
import com.healthtimejournal.customadapter.SharingAdapter;
import com.healthtimejournal.customcontextmenu.SetPrivilegeContext;

public class ChildAccountSharePageFragment extends Fragment{
	
	public static final String ARG_IDS = "ARG_IDS";
	public static final String ARG_ACCOUNTS = "ARG_ACCOUNTS";
	public static final String ARG_PRIVILEGES = "ARG_PRIVILEGES";
	
	public static ChildAccountSharePageFragment create(ArrayList<Integer> ids,ArrayList<String> arrays, ArrayList<Integer> privileges){
		Bundle bundle = new Bundle();
		bundle.putIntegerArrayList(ARG_IDS, ids);
		bundle.putStringArrayList(ARG_ACCOUNTS, arrays);
		bundle.putIntegerArrayList(ARG_PRIVILEGES, privileges);
		ChildAccountSharePageFragment fragment = new ChildAccountSharePageFragment();
		fragment.setArguments(bundle);
		return fragment;
	}
	
	@Override
	public void onCreate(Bundle savedInstance){
		super.onCreate(savedInstance);
	}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
    	View view = inflater.inflate(R.layout.shared_account_page_layout, container, false);
    	
    	LinearLayout layout = (LinearLayout) view;
    	ListView list = (ListView) layout.findViewById(R.id.shared_account_list);
    	
    	if(getArguments().getStringArrayList(ARG_ACCOUNTS) != null)
    		list.setAdapter(new SharingAdapter(getActivity().getApplicationContext(), getArguments().getStringArrayList(ARG_ACCOUNTS), getArguments().getIntegerArrayList(ARG_PRIVILEGES)));
    	
    	list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				SetPrivilegeContext context = new SetPrivilegeContext();
				context.openDialog(getActivity(), getArguments().getIntegerArrayList(ARG_IDS).get(arg2));
			}
		});
    	
    	return view;
    }
}
