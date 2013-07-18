package com.healthtimejournal;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.EditText;
import android.widget.ListView;

import com.healthtimejournal.customadapter.NewsFeedAdapter;
import com.healthtimejournal.customcontextmenu.PostSettingsContext;

public class NewsFeedActivity extends Activity {
	
	final Context context = this;
	
	EditText newsFeed;
	
	ListView newsFeedList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.news_feed_complete);
		
		newsFeed = (EditText)findViewById(R.id.newsFeedEditText);
		newsFeed.setOnClickListener(new OnClickListener() { 
			public void onClick(View arg0) {
				Intent a = new Intent(NewsFeedActivity.this, PostActivity.class);
		        startActivity(a);
			}
	    }); 
		
		newsFeedList = (ListView)findViewById(R.id.newsFeedList);
		newsFeedList.setAdapter(new NewsFeedAdapter(this));

		newsFeedList.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> a, View v, int position, long id) {
				Intent newActivity0 = new Intent(NewsFeedActivity.this, PostPageActivity.class);     
	            startActivity(newActivity0);
			}
		});
		
		newsFeedList.setOnItemLongClickListener(new OnItemLongClickListener() {
		    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
		    	PostSettingsContext dialog = new PostSettingsContext();
		    	dialog.openDialog(context);
		    	return true;
		    }
		});
	}
	
}
