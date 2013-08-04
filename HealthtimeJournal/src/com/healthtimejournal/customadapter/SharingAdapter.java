package com.healthtimejournal.customadapter;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.healthtimejournal.R;

public class SharingAdapter extends BaseAdapter{

	private Context context;
	private ArrayList<String> names;
	private ArrayList<Integer> privileges;

	public SharingAdapter(Context context, ArrayList<String> names, ArrayList<Integer> privileges){
		this.context = context;
		this.names = names;
		this.privileges = privileges;
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return names.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return names.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) {
		// TODO Auto-generated method stub
		View vi = arg1;
		
		if(vi == null){
			LayoutInflater inflater = (LayoutInflater)	context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			vi = inflater.inflate(R.layout.shared_account_item_layout, null);
		}
		
		TextView label = (TextView) vi.findViewById(R.id.shared_account_item_label);
		TextView desc = (TextView) vi.findViewById(R.id.shared_account_item_description);
		
		label.setText(names.get(arg0));
		switch(privileges.get(arg0)){
			case 1:
				desc.setText("Can View Only");
				break;
			case 2:
				desc.setText("Can Post");
				break;
			case 3:
				desc.setText("Can Create Event");
				break;
		}
		
		return vi;
	}

}
