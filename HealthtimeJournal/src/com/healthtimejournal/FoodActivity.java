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

import com.healthtimejournal.customadapter.NewsFeedAdapter;
import com.healthtimejournal.customcontextmenu.PostSettingsContext;

public class FoodActivity extends Activity {
	
	final Context context = this;
	
	ListView segregateList;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.segregate_page);
		
		segregateList = (ListView)findViewById(R.id.segregatePageList);
		segregateList.setAdapter(new NewsFeedAdapter(this));

		segregateList.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> a, View v, int position, long id) {
				Intent newActivity0 = new Intent(FoodActivity.this, PostPageActivity.class);     
	            startActivity(newActivity0);
			}
		});
		
		segregateList.setOnItemLongClickListener(new OnItemLongClickListener() {
		    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
		    	PostSettingsContext dialog = new PostSettingsContext();
		    	dialog.openDialog(context);
		    	return true;
		    }
		});
	}

}
