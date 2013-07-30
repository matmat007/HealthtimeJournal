package com.healthtimejournal;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.facebook.Facebook;
import com.facebook.SessionStore;
import com.healthtimejournal.customView.MyCustomHSV;
import com.healthtimejournal.customadapter.MyCustomExpandableListAdapter;
import com.healthtimejournal.customadapter.fragmentadapter.TiledEventFragmentPageAdapter;
import com.healthtimejournal.function.MenuInstance;
import com.healthtimejournal.model.ChildModel;
import com.healthtimejournal.model.DoctorModel;
import com.healthtimejournal.model.GroupList;
import com.healthtimejournal.model.PostModel;
import com.healthtimejournal.service.HealthtimeSession;
import com.healthtimejournal.service.HttpClient;
import com.healthtimejournal.service.JSONParser;

@SuppressLint("HandlerLeak")
public class TiledEventsActivity extends FragmentActivity {
	
	final Context context = this;

	private ProgressDialog mProgress;
	private LinearLayout SideList;
	private boolean isExpanded = false;
	
	private EventChildNameTask eTask = null;
	private EventDoctorTask eDoctorTask = null;
	private EventTileTask eTileTask = null;
	
	private List<ChildModel> children = null;
	private List<GroupList> list = null;
	
	private DoctorModel onedoctor = null;
	private int isDoctor;
	
	private int width = 0;
	
	private MyCustomExpandableListAdapter adapter;
	private ExpandableListView listview; 
	private MyCustomHSV hsv;
	
	private ViewPager viewPager;

	private Facebook mFacebook;

    private static final String APP_ID = "460537864017391";
    
    private EditText searchbar;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
        mFacebook = new Facebook(APP_ID);
        mProgress = new ProgressDialog(context);
 
        SessionStore.restore(mFacebook, context);
		
		mProgress = new ProgressDialog(context);
        mFacebook = new Facebook(APP_ID);
 
        SessionStore.restore(mFacebook, context);
		
		children = new ArrayList<ChildModel>();
		
		getActionBar().setHomeButtonEnabled(true);
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tile_event_layout);
		
		Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        width = size.x;
        
        retrieve_child();
        retrieve_post_by_child();
        retrieve_doctor_by_parent();
        
        searchbar = (EditText)findViewById(R.id.searchbar);
        searchbar.setOnClickListener(new OnClickListener() { 
			public void onClick(View arg0) {
				startActivity(new Intent(TiledEventsActivity.this, SearchBarActivity.class));
			}
        });
        
        listview = (ExpandableListView)findViewById(R.id.listview);
        
        viewPager = (ViewPager) findViewById(R.id.pager);
        LayoutParams params = viewPager.getLayoutParams();
        params.width = width;
        viewPager.setLayoutParams(params);
        
        SideList = (LinearLayout) findViewById(R.id.sidebar_layout);
        params = SideList.getLayoutParams();
        params.width = width/4*3; 
        SideList.setLayoutParams(params);
        
        hsv = (MyCustomHSV) findViewById(R.id.timeline_scrollview);
        
        listview.setOnGroupClickListener(new OnGroupClickListener() {
			
			@Override
			public boolean onGroupClick(ExpandableListView parent, View v,
					int groupPosition, long id) {
				// TODO Auto-generated method stub
				return true;
			}
		});
        
        listview.setOnChildClickListener(new OnChildClickListener() {
			
			@Override
			public boolean onChildClick(ExpandableListView parent, View v,
					int groupPosition, int childPosition, long id) {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(), String.valueOf(childPosition), Toast.LENGTH_SHORT).show();
				switch(groupPosition){
					case 0:
						if(childPosition == 0){
							startActivity(new Intent(TiledEventsActivity.this, SharedAccountActivity.class));
						}
						else if(childPosition == 2){
							startActivity(new Intent(TiledEventsActivity.this, AlbumActivity.class));
						}
						break;
					case 1:
						if(childPosition == children.size()){
							startActivity(new Intent(TiledEventsActivity.this, AddChildActivity.class));
						}
						break;
					case 2:
						if(childPosition == 0 && isDoctor == 0){
							startActivity(new Intent(TiledEventsActivity.this, CreateDoctorActivity.class));
						}
						else if(childPosition == 0 && isDoctor == 1){
							Toast.makeText(getApplicationContext(), "You are already a doctor.", Toast.LENGTH_SHORT).show();
						}
						else if(childPosition == 1 && isDoctor == 1){
							startActivity(new Intent(TiledEventsActivity.this, EditDoctorActivity.class));
						}
						else if(childPosition == 1 && isDoctor == 0){
							Toast.makeText(getApplicationContext(), "You don't have a doctor account yet.", Toast.LENGTH_SHORT).show();
						}
						else if(childPosition == 2){
							startActivity(new Intent(TiledEventsActivity.this, ParentProfilePhotoActivity.class));
						}
						else if(childPosition == 3){
							logout();
						}
						break;

				}
				return false;
			}
		});
        
	}
	
	private void retrieve_child(){
		if (eTask != null) {
			return;
		}
		
		eTask = new EventChildNameTask();
		eTask.execute();
	}
	
	private void retrieve_post_by_child(){
		if(eTileTask != null){
			return;
		}
		
		eTileTask = new EventTileTask();
		eTileTask.execute();
	}
	
	private void retrieve_doctor_by_parent(){
		if(eDoctorTask != null){
			return;
		}
		
		eDoctorTask = new EventDoctorTask();
		eDoctorTask.execute();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.tiled_event_activity, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item){
		switch(item.getItemId()){
		case android.R.id.home:
			if (!isExpanded){
				isExpanded = true;
		        hsv.scrollTo(0, 0);
				getActionBar().setDisplayHomeAsUpEnabled(true);
			}
			else{
				isExpanded = false;
		        hsv.scrollTo(width/4*3, 0);
				getActionBar().setDisplayHomeAsUpEnabled(false);
			}
			break;
		case R.id.postAction:
			if(children.size() > 0)
			startActivity(new Intent(TiledEventsActivity.this, PostActivity.class));
			else
			Toast.makeText(this, "You have no child. Please add a child first", Toast.LENGTH_SHORT ).show();
			break;
		}
		return true;
	}
	
	private class EventDoctorTask extends AsyncTask<Void, Void, String>{
		@Override
		protected String doInBackground(Void... params) {
			// TODO Auto-generated method stub
			HttpClient a = new HttpClient();
			String data = a.retrieve_doctor_by_parent(HealthtimeSession.getParentId(getBaseContext()));
			return data;
		}
		
		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
	        
			onedoctor = JSONParser.getOneDoctor(result);
			
			if(onedoctor != null)
				isDoctor = 1;
			else
				isDoctor = 0;
			
		}
		
	}
	
	private class EventChildNameTask extends AsyncTask<Void, Void, String>{
		@Override
		protected String doInBackground(Void... params) {
			// TODO Auto-generated method stub
			HttpClient a = new HttpClient();
			String data = a.retrieve_child_by_family(HealthtimeSession.getFamilyId(getBaseContext()));
			return data;
		}
		
		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
	        
			hsv.scrollTo(width/4*3, 0);
			
			if(result != null){
				children = JSONParser.getChild(result);
				if(children != null){
					list = MenuInstance.instatiateGroup(children);
				}
				else{
					list = MenuInstance.instatiateGroup(null);
					children = new ArrayList<ChildModel>();
				}
					adapter = new MyCustomExpandableListAdapter(getApplicationContext(), list); 
					adapter.notifyDataSetChanged();
					listview.setAdapter(adapter);
					
					for(int k = 0; k < listview.getExpandableListAdapter().getGroupCount(); k++){
			        	listview.expandGroup(k);
			        }
				
			}
			
			else{
			}
		}
		
	}
	
	private class EventTileTask extends AsyncTask<Void, Void, String>{
		
		private ProgressDialog pDialog;
		
		@Override
		protected void onPreExecute() {
	        super.onPreExecute();
	        pDialog = new ProgressDialog(TiledEventsActivity.this);
	        pDialog.setMessage("Loading events. Please wait...");
	        pDialog.setIndeterminate(false);
	        pDialog.setCancelable(false);
	        pDialog.show();
	    }
		
		@Override
		protected String doInBackground(Void... params) {
			// TODO Auto-generated method stub
			HttpClient a = new HttpClient();
			Log.d("parent_id",String.valueOf(HealthtimeSession.getParentId(getBaseContext())));
			String data = a.retrieve_all_post(HealthtimeSession.getParentId(getBaseContext()));
			return data;
		}
		
		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			
	        if(result != null){
	        	List<PostModel> chldlist = JSONParser.getPost(result);
	        	List<List<PostModel>> arrangeEvents = new ArrayList<List<PostModel>>();
	        	if(chldlist != null){
		        	int n = chldlist.get(0).getChildId();
		        	List<PostModel> model = new ArrayList<PostModel>();
		        	
		        	for(PostModel p : chldlist){
		        		if(n != p.getChildId()){
		        			n = p.getChildId();
		        			arrangeEvents.add(model);
		        			model = new ArrayList<PostModel>();
		        		}
		        		model.add(p);
	        	}
	        	arrangeEvents.add(model);
	        	}
	        	Log.d("logggg", ""+arrangeEvents.size());
	        	viewPager.setAdapter(new TiledEventFragmentPageAdapter(getSupportFragmentManager(), children, arrangeEvents));
	        }
	        
	        pDialog.dismiss();
		}
		
	}
	
	private void logout() {
        mProgress.setMessage("Disconnecting");
        mProgress.show();
 
        new Thread() {
            @Override
            public void run() {
            	HealthtimeSession.clear(context);
                SessionStore.clear(context);
                int what = 1;
 
                try {
                    mFacebook.logout(context);
                    finish();
                    what = 0;
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
 
                mHandler.sendMessage(mHandler.obtainMessage(what));
            }
        }.start();
    }
	
	private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            mProgress.dismiss();
            if (msg.what == 1) {
                Toast.makeText(context, "Logout failed", Toast.LENGTH_SHORT).show();
            } 
            else {
                Toast.makeText(context, "Disconnected", Toast.LENGTH_SHORT).show();
            }
        }
    };
}
