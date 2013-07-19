package com.healthtimejournal.service;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.widget.ExpandableListView;

import com.healthtimejournal.customadapter.MyCustomExpandableListAdapter;
import com.healthtimejournal.model.ChildList;
import com.healthtimejournal.model.GroupList;

public class GetEventsDetails extends AsyncTask<String, String, String>{
	
	private static final String url_get_child = "http://192.168.1.4/healthtime/Test/retrieve_child.php";
	private static final String url_get_event = "http://192.168.1.4/healthtime/Test/retrieve_all_post_by_child.php";
	private String urls[] = new String[]{url_get_child, url_get_event};
	
	private static final String TAG_SUCCESS = "success";
	private static final String TAG_NODE_CHILD = "child";
	private static final String TAG_NODE_POST = "posts";
	
	private HttpResponseClient jsonParser = new HttpResponseClient();
	private JSONArray childObj, childObj1;
	
	private ViewPager viewPager;
	private FragmentManager mng;
	private ExpandableListView listview;
	private List<GroupList> list;
	private Context context;
	private ProgressDialog pDialog;
	
	public GetEventsDetails(Context context, List<GroupList> list, ExpandableListView listview, ViewPager viewPager, FragmentManager mng){
		this.viewPager = viewPager;
		this.mng = mng;
		this.listview = listview;
		this.context = context;
		this.list = list;
	}
	
	@Override
    protected void onPreExecute() {
        super.onPreExecute();
        pDialog = new ProgressDialog(context);
        pDialog.setMessage("Loading events. Please wait...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();
    }
	
	@Override
	protected String doInBackground(String... params) {
		// TODO Auto-generated method stub
		/*int success, n = 1;
		
		try{
			for(int i = 0; i < 2; i++){
			List<NameValuePair> pair = new ArrayList<NameValuePair>();
	        pair.add(new BasicNameValuePair("id", String.valueOf(n)));
	
	        JSONObject json = jsonParser.makeHttpRequest(urls[i], "GET", pair);
	
	        Log.d("Results", json.toString());
	        
	        // json success tag
	        success = json.getInt(TAG_SUCCESS);
	        if (success == 1) {
	        	if(i == 0){
	        		childObj = json.getJSONArray(TAG_NODE_CHILD); // JSON Array
	        	}
	        	else{
	        		childObj1 = json.getJSONArray(TAG_NODE_POST);
	        	}
	        }
	        else{
	        	
	        }
			}
	    } catch (JSONException e) {
	        e.printStackTrace();
	    }*/
		
		return null;
	}
	
	@Override
	protected void onPostExecute(String result) {
        super.onPostExecute(result);
        
        pDialog.dismiss();
        //viewPager.setAdapter(new FragmentPageAdapter(mng, childObj, childObj1));
        
        MyCustomExpandableListAdapter adapter = new MyCustomExpandableListAdapter(context, list);
        List<ChildList> chld = new ArrayList<ChildList>();
        for(int i = 0; i < childObj.length(); i++){
        	ChildList ch = new ChildList();
        	ch.setId(i);
        	try {
				ch.setName(childObj.getJSONObject(i).getString("first_name") + " " + childObj.getJSONObject(i).getString("last_name"));
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	chld.add(ch);
        }
        list.get(1).setList(chld);
        listview.setAdapter(adapter);
        for(int k = 0; k < listview.getExpandableListAdapter().getGroupCount(); k++){
        	listview.expandGroup(k);
        }
    }
}
