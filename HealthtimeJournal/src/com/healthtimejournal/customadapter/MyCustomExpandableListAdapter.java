package com.healthtimejournal.customadapter;

import java.util.List;

import org.json.JSONObject;
import org.json.JSONTokener;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.Facebook;
import com.facebook.SessionStore;
import com.healthtimejournal.R;
import com.healthtimejournal.model.ChildList;
import com.healthtimejournal.model.GroupList;

public class MyCustomExpandableListAdapter extends BaseExpandableListAdapter{
	
	private Context context;
	private List<GroupList> list;
	
	private Facebook mFacebook;
    private ProgressDialog mProgress;

    private static final String APP_ID = "460537864017391";
	
	public MyCustomExpandableListAdapter(Context context, List<GroupList> list){
		this.context = context;
		this.list = list;
	}
	
	@Override
	public Object getChild(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return list.get(arg0).getList().get(arg1);
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return childPosition;
	}

	@Override
	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		
		mProgress = new ProgressDialog(context);
        mFacebook = new Facebook(APP_ID);
 
        SessionStore.restore(mFacebook, context);
		
		ChildList chld = (ChildList) getChild(groupPosition, childPosition);
		
		if(convertView == null){
			LayoutInflater infalInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = infalInflater.inflate(R.layout.sidebar_list_child, null);
		}
		
		TextView tv = (TextView) convertView.findViewById(R.id.sidebar_list_text);
		tv.setText(chld.getName().toString());
		
		/*if(tv.getText().toString().equals("Log out")) {
			tv.setOnClickListener(new OnClickListener() { 
				public void onClick(View arg0) {
					AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
					alertDialogBuilder
							.setMessage("Are you sure you want to log out?")
							.setCancelable(false)
							.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog, int id) {
									fbLogout();
								}
							  })
							.setNegativeButton("No", new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog, int id) {
									dialog.cancel();
								}
							});

					AlertDialog alertDialog = alertDialogBuilder.create();
					alertDialog.show();
				}
		    });
		}
		
		else if(tv.getText().toString().equals("Album")){
			//context.startActivity(new Intent(,));
		}*/
		
		return convertView;
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		// TODO Auto-generated method stub
		return list.get(groupPosition).getList().size();
	}

	@Override
	public Object getGroup(int groupPosition) {
		// TODO Auto-generated method stub
		return list.get(groupPosition);
	}

	@Override
	public int getGroupCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public long getGroupId(int groupPosition) {
		// TODO Auto-generated method stub
		return groupPosition;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		GroupList grp = (GroupList) getGroup(groupPosition);
		
		if(convertView == null){
			LayoutInflater infalInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = infalInflater.inflate(R.layout.sidebar_list_group, null);
		}
		
		TextView tv = (TextView) convertView.findViewById(R.id.sidebar_group_text);
		tv.setText(grp.getName().toString());
		return convertView;
	}

	@Override
	public boolean hasStableIds() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return true;
	}
	
	private void fbLogout() {
        mProgress.setMessage("Disconnecting from Facebook");
        mProgress.show();
 
        new Thread() {
            @Override
            public void run() {
                SessionStore.clear(context);
 
                int what = 1;
 
                try {
                    mFacebook.logout(context);
 
                    what = 0;
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
 
                mHandler.sendMessage(mHandler.obtainMessage(what));
            }
        }.start();
    }
 
    private Handler mFbHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            mProgress.dismiss();
 
            if (msg.what == 0) {
                String username = (String) msg.obj;
                username = (username.equals("")) ? "No Name" : username;
 
                SessionStore.saveName(username, context);
 
                Toast.makeText(context, "Connected to Facebook as " + username, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "Connected to Facebook", Toast.LENGTH_SHORT).show();
            }
        }
    };
 
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            mProgress.dismiss();
 
            if (msg.what == 1) {
                Toast.makeText(context, "Facebook logout failed", Toast.LENGTH_SHORT).show();
            } else {
 
                Toast.makeText(context, "Disconnected from Facebook", Toast.LENGTH_SHORT).show();
            }
        }
    };

}