package com.healthtimejournal.customadapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

import com.healthtimejournal.R;
import com.healthtimejournal.model.ParentModel;

public class MyCustomSearchListAdapter extends ArrayAdapter<ParentModel> {

	Context context;
	
    private List<ParentModel> origParentList;
    private List<ParentModel> newParentList;

	public MyCustomSearchListAdapter(Context context, List<ParentModel> parent) {
		super(context, R.layout.shared_account_item_layout, parent);
		
		this.context = context;
		origParentList = parent;
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
		
		label.setText(origParentList.get(arg0).getFirstName().toString() + " " + origParentList.get(arg0).getLastName().toString());
		desc.setText("aaaaa");
		
		return vi;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return origParentList.size();
	}

	@Override
	public ParentModel getItem(int arg0) {
		// TODO Auto-generated method stub
		return origParentList.get(arg0);
	}

	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return origParentList.indexOf(arg0);
	}
		
	@Override
    public Filter getFilter() {
        return new Filter() {
            @SuppressWarnings("unchecked")
            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                origParentList = (List<ParentModel>) results.values;
                if (results.count > 0) {
                    notifyDataSetChanged();
                }
                else {
                    notifyDataSetInvalidated();
                }
            }

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                if (newParentList == null)
                    newParentList = new ArrayList<ParentModel>(origParentList);
                List<ParentModel> result;
                FilterResults r = new FilterResults();
                if (constraint == null || constraint.length() <= 0)
                    result = new ArrayList<ParentModel>(newParentList);
                else {
                    result = new ArrayList<ParentModel>();
                    for (int i = 0; i < newParentList.size(); i++)
                        if (constraint.length() > 0 && (newParentList.get(i).getFirstName().toLowerCase().contains(constraint.toString().toLowerCase()) || newParentList.get(i).getLastName().toLowerCase().contains(constraint.toString().toLowerCase())))
                            result.add(newParentList.get(i));
                }
                r.values = result;
                r.count = result.size();
                return r;
            }
        };
    }

}
