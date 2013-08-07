package com.healthtimejournal.service;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.healthtimejournal.model.ParentModel;

public class HealthtimeSession {
	
	private static final String SESSION = "ht_session";
	
	public static boolean save(ParentModel parent, Context context){
		Editor editor = context.getSharedPreferences(SESSION, Context.MODE_PRIVATE).edit();
		editor.putInt("parent_id", parent.getParentId());
		editor.putString("first_name", parent.getFirstName());
		editor.putString("last_name", parent.getLastName());
		editor.putString("email", parent.getEmail());
		editor.putString("password", parent.getPassword());
		editor.putInt("family_id", parent.getFamilyId());
		return editor.commit();
	}
	
	public static String getName(Context context){
		SharedPreferences savedSession =
	            context.getSharedPreferences(SESSION, Context.MODE_PRIVATE);
	    	
	    	return savedSession.getString("first_name", "Unknown")+" "+savedSession.getString("last_name", "Unknown");
	}
	
	public static int getParentId(Context context){
		SharedPreferences savedSession =
	            context.getSharedPreferences(SESSION, Context.MODE_PRIVATE);
	    	
	    	return savedSession.getInt("parent_id", 0);
	}
	
	public static int getFamilyId(Context context){
		SharedPreferences savedSession =
	            context.getSharedPreferences(SESSION, Context.MODE_PRIVATE);
	    	
	    	return savedSession.getInt("family_id", 0);
	}
	
	public static String getEmail(Context context){
		SharedPreferences savedSession =
	            context.getSharedPreferences(SESSION, Context.MODE_PRIVATE);
	    	
	    	return savedSession.getString("email", "");
	}
	
	public static String getPassword(Context context){
		SharedPreferences savedSession =
	            context.getSharedPreferences(SESSION, Context.MODE_PRIVATE);
	    	
	    	return savedSession.getString("password", "");
	}
	
	public static ParentModel getParentInfo(Context context){
		SharedPreferences savedSession =
	            context.getSharedPreferences(SESSION, Context.MODE_PRIVATE);
		
		ParentModel parent = new ParentModel();
		parent.setParentId(savedSession.getInt("parent_id", 0));
		parent.setFirstName(savedSession.getString("first_name", ""));
		parent.setLastName(savedSession.getString("last_name", ""));
		parent.setEmail(savedSession.getString("email", ""));
		parent.setPassword(savedSession.getString("password", ""));
		parent.setFamilyId(savedSession.getInt("family_id", 0));
		
		return parent;
	}
	
	public static void clear(Context context) {
        Editor editor = context.getSharedPreferences(SESSION, Context.MODE_PRIVATE).edit();
        editor.clear();
        editor.commit();
    }

}
