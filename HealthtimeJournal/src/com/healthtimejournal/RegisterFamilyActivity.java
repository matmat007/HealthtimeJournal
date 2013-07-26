package com.healthtimejournal;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.InputType;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.MultiAutoCompleteTextView;

import com.healthtimejournal.service.HttpClient;
import com.healthtimejournal.service.TagTokenizer;

public class RegisterFamilyActivity extends Activity {
	
	private NameTask mNameTask = null;
	private RegisterTask mRegTask = null;
	MultiAutoCompleteTextView name;
	List<String> items;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register_family_activity);
		items = new ArrayList<String>();
		name = (MultiAutoCompleteTextView)findViewById(R.id.nameEditText);
		
		retrieve_names();
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, items);
		name.setAdapter(adapter);
		name.setSelected(true);
		name.setRawInputType(InputType.TYPE_CLASS_TEXT
		          |InputType.TYPE_TEXT_FLAG_CAP_SENTENCES
		          |InputType.TYPE_TEXT_FLAG_AUTO_CORRECT
		          |InputType.TYPE_TEXT_FLAG_MULTI_LINE);
		name.setThreshold(2);
		name.setTokenizer(new TagTokenizer());
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.register_family_activity, menu);
		return true;
	}

	private void retrieve_names(){
		if (mNameTask != null) {
            return;
        }
		
		/*mNameTask = new NameTask();
		mNameTask.execute((Void) null);*/
		
	}
	
	private void attempt_register(){
		if(mRegTask != null){
			return;
		}
		
	}
	private class NameTask extends AsyncTask<Void,Void,String>{

		@Override
		protected String doInBackground(Void... params) {
			HttpClient a = new HttpClient();
			String data = a.retrieve_parent_by_search("conr");
			return data;
		}
		
	}
	
	private class RegisterTask extends AsyncTask<Void,Void,Void>{

		@Override
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub
			return null;
		}
		
	}
}
