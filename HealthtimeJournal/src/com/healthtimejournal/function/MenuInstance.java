package com.healthtimejournal.function;

import java.util.ArrayList;
import java.util.List;

import com.healthtimejournal.model.ChildList;
import com.healthtimejournal.model.ChildModel;
import com.healthtimejournal.model.GroupList;

import android.util.Log;

public class MenuInstance {
	
	public static List<GroupList> instatiateGroup(List<ChildModel> child){
		List<String> headlist = instatiateMenuHeadList();
		List<List<String>> sublist = instatiateMenuSubList(child);
		List<GroupList> list = new ArrayList<GroupList>();
		
		for (int i = 0; i < headlist.size(); i++) {
        	GroupList tempList = new GroupList();
            List<ChildList> tempList1 = new ArrayList<ChildList>();
            
        	tempList.setName(headlist.get(i));
        	for(int j = 0; j < sublist.get(i).size(); j++){
        		ChildList temp = new ChildList();
        		temp.setName(sublist.get(i).get(j));
        		Log.d("Item", sublist.get(i).get(j));
        		tempList1.add(temp);
        	}
        	tempList.setList(tempList1);
			list.add(tempList);
        }
		
		return list;
	}
	
	private static List<String> instatiateMenuHeadList(){
		List<String> list = new ArrayList<String>();
		
		list.add("Favorites");
		list.add("Children");
		list.add("Options");
		
		return list;
	}
	
	private static List<List<String>> instatiateMenuSubList(List<ChildModel> child){
		List<List<String>> list = new ArrayList<List<String>>();
		List<String> temp = new ArrayList<String>();
		
		temp.add("Social");
		temp.add("Notification");
		temp.add("Album");
		list.add(temp);

        Log.d("List", list.get(0).get(0));
		
		temp = new ArrayList<String>();
		
		if(child != null){
			for(ChildModel s : child){
				temp.add(s.getFirstName() + " " + s.getLastName());
			}
		}
		temp.add("Add Child");
		list.add(temp);
		
		temp = new ArrayList<String>();
		temp.add("Create Doctor Page");
		temp.add("Log out");
		list.add(temp);
		
		Log.d("List", list.get(2).get(0));
		
		
		return list;
	}
}
