package com.healthtimejournal;

import com.healthtimejournal.customadapter.NewsFeedAdapter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class VaccinationActivity extends Activity {
	
	ListView segregateList;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.segregate_page);
		
		segregateList = (ListView)findViewById(R.id.segregatePageList);
		segregateList.setAdapter(new NewsFeedAdapter(this));

		segregateList.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> a, View v, int position, long id) {
				Intent newActivity0 = new Intent(VaccinationActivity.this, PostPageActivity.class);     
	            startActivity(newActivity0);
			}
		});
	}

}


