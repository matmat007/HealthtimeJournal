package com.healthtimejournal;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;
import android.widget.TextView;

import com.healthtimejournal.customadapter.MySharedAccountsAdapter;
import com.healthtimejournal.customcontextmenu.SetPrivilegeContext;

public class MyFriendChildrenActivity extends Activity {

	final Context context = this;
	
	TextView pageLabel;
	
	ListView friendList;
	
	@Override
	public void onCreate(Bundle savedInstanceState){

		super.onCreate(savedInstanceState);
		setContentView(R.layout.friend_list_complete);

		friendList = (ListView)findViewById(R.id.friendList);
		friendList.setAdapter(new MySharedAccountsAdapter(this));

		friendList.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> a, View v, int position, long id) {
				Intent newActivity0 = new Intent(MyFriendChildrenActivity.this, ChildProfileActivity.class);     
	            startActivity(newActivity0);
			}
		});
		
		friendList.setOnItemLongClickListener(new OnItemLongClickListener() {
		    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
		    	SetPrivilegeContext dialog = new SetPrivilegeContext();
		    	dialog.openDialog(context, position);
		    	return true;
		    }
		});

	}

}
