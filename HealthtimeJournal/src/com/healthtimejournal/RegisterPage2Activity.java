package com.healthtimejournal;

import java.util.Arrays;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;

import com.healthtimejournal.customadapter.MyCustomArrayAdapterSickness;

public class RegisterPage2Activity extends Activity{
	
	private HistoryTask mHistTask = null;
	
	CheckBox anemiaCheckBox;
	CheckBox asthmaCheckBox;
	CheckBox bleedingDisCheckBox;
	CheckBox diabetesCheckBox;
	CheckBox epilepsyCheckBox;
	CheckBox heartDisCheckBox;
	CheckBox highBloodCheckBox;
	CheckBox highChoCheckBox;
	CheckBox liverDisCheckBox;
	CheckBox kidneyDisCheckBox;
	CheckBox nasalAllCheckBox;
	CheckBox tuberculosisCheckBox;
	
	Button submitButton;
	Button previousButton;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register_page_2);
		
		getActionBar().setHomeButtonEnabled(true);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		
		ListView listview = (ListView)findViewById(R.id.sick_list);
		List<String> data = Arrays.asList(getResources().getStringArray(R.array.sickness));
		
		
		MyCustomArrayAdapterSickness adapter = new MyCustomArrayAdapterSickness(data, this);
		listview.setAdapter(adapter);
		
		
		
		submitButton = (Button)findViewById(R.id.regSubmitButton);
		submitButton.setOnClickListener(new OnClickListener() { 
			public void onClick(View arg0) {
				attemptAddHistory();
				startActivity(new Intent(RegisterPage2Activity.this, TimelineActivity.class));
			}
	    });
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item){
		
		startActivity(new Intent(RegisterPage2Activity.this, RegisterPageActivity.class));
		return true;
	}

	private void attemptAddHistory(){
		if(mHistTask != null){
			return;
		}
		mHistTask = new HistoryTask();
		mHistTask.execute((Void) null);
	}
	
	private class HistoryTask extends AsyncTask<Void, Void, Boolean>{

		@Override
		protected Boolean doInBackground(Void... params) {
			
			// TODO Auto-generated method stub
			
			return null;
		}
		
	}
}
