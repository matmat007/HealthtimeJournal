package com.healthtimejournal.service;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.healthtimejournal.model.Hashtag;

public class JSONParser {
	
	public static List<Hashtag> getHashtag(String data){
		
		List<Hashtag> result = new ArrayList<Hashtag>();
		
		try {
			JSONObject jObj = new JSONObject(data);
			JSONArray jArray = jObj.getJSONArray("hashtags");
		
		JSONObject oneobj = null;
		
		for(int i = 0; i < jArray.length(); i++){
			Hashtag onehash = new Hashtag();
			oneobj = jArray.getJSONObject(i);
			
			onehash.setHashtag_id(oneobj.getInt("tag_id"));
			onehash.setHashtag_name(oneobj.getString("tag_name"));
			onehash.setHashtag_category(oneobj.getInt("tag_category"));
			
			result.add(onehash);
			
		}
		return result;
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static List<String> getChildren(String data){
		List<String> list = new ArrayList<String>();
		
		try{
			JSONObject jobj = new JSONObject(data);
			JSONArray jarr = jobj.getJSONArray("child");
			
			JSONObject oneObj = null;
			
			for(int i = 0; i < jarr.length(); i++){
				oneObj = jarr.getJSONObject(i);
				list.add(oneObj.getString("first_name") + " " + oneObj.getString("last_name"));
			}
			return list;
		} catch(JSONException e){
			e.printStackTrace();
		}
		
		return null;
	}
}
