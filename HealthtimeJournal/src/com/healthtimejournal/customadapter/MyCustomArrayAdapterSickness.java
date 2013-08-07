package com.healthtimejournal.customadapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;

import com.healthtimejournal.R;

public class MyCustomArrayAdapterSickness extends BaseAdapter{

	private List<String> list;
	private Context context;
	private ArrayList<Boolean> positionArray;
	
	public MyCustomArrayAdapterSickness(List<String> list, Context context) {
		
		this.context = context;
		this.list = list;
		
		positionArray = new ArrayList<Boolean>(list.size());
	    for(int i =0;i<list.size();i++){
	        positionArray.add(false);
	    }
		// TODO Auto-generated constructor stub
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return list.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}
	
	public ArrayList<Boolean> getResult(){
		return positionArray;
	}

	@Override
	public View getView(final int arg0, final View arg1, ViewGroup arg2) {
		// TODO Auto-generated method stub
		View vi = arg1;
		
		if(vi == null){
			LayoutInflater inflate = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			vi = inflate.inflate(R.layout.register_sickness_layout, null);
		}
		
		CheckBox checkbox = (CheckBox)vi.findViewById(R.id.sickness_checkbox);
        checkbox.setText(list.get(arg0));
		checkbox.setChecked(positionArray.get(arg0));

        //final LinearLayout rowLayout = (LinearLayout) v.findViewById(R.id.individualRow);

        checkbox.setOnCheckedChangeListener(new OnCheckedChangeListener(){

            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    //rowLayout.setBackgroundColor(Color.GRAY);                       
                    positionArray.set(arg0, true);                     
                }
                else
                {   
                    //rowLayout.setBackgroundColor(Color.DKGRAY);
                	positionArray.set(arg0, false);
                }
            }               
        });
		
		return vi;
	}

}
