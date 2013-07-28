package com.healthtimejournal;

import java.util.List;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.MultiAutoCompleteTextView;
import android.widget.TextView;
import android.widget.Toast;

import com.healthtimejournal.model.FamilyModel;
import com.healthtimejournal.model.ParentModel;
import com.healthtimejournal.service.HealthtimeSession;
import com.healthtimejournal.service.HttpClient;
import com.healthtimejournal.service.JSONParser;

public class RegisterFamilyActivity extends Activity {

	//	private NameTask mNameTask = null;

	private RegisterTask mRegTask = null;
	MultiAutoCompleteTextView name;
	List<String> items;

	TextView email;
	TextView password;
	
	Button register;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register_family_activity);

		email = (TextView)findViewById(R.id.familyEmailText);
		password = (TextView)findViewById(R.id.familyPasswordText);

		register = (Button)findViewById(R.id.regButton);
		register.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				attempt_register();
			}
		});
		
		//		items = new ArrayList<String>();
		//		name = (MultiAutoCompleteTextView)findViewById(R.id.nameEditText);
		//		
		//		retrieve_names();
		//		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, items);
		//		name.setAdapter(adapter);
		//		name.setSelected(true);
		//		name.setRawInputType(InputType.TYPE_CLASS_TEXT
		//		          |InputType.TYPE_TEXT_FLAG_CAP_SENTENCES
		//		          |InputType.TYPE_TEXT_FLAG_AUTO_CORRECT
		//		          |InputType.TYPE_TEXT_FLAG_MULTI_LINE);
		//		name.setThreshold(2);
		//		name.setTokenizer(new TagTokenizer());

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.register_family_activity, menu);
		return true;
	}

	//	private void retrieve_names(){
	//		if (mNameTask != null) {
	//            return;
	//        }
	//		
	//		mNameTask = new NameTask();
	//		mNameTask.execute((Void) null);
	//		
	//	}

	private void attempt_register(){
		if(mRegTask != null){
			return;
		}
		
		String mEmail = email.getText().toString();
		String mPass = password.getText().toString();
		boolean cancel = false;
		View focusView = null;
		if(TextUtils.isEmpty(mEmail)){
			email.setError(getString(R.string.required_not_met));
			focusView = email;
			cancel = true;
		}
		if(TextUtils.isEmpty(mPass)){
			password.setError(getString(R.string.required_not_met));
			focusView = password;
			cancel = true;
		}
		if(cancel){
			focusView.requestFocus();
		}
		else{
			mRegTask = new RegisterTask();
			mRegTask.execute();
		}

	}

	//	private class NameTask extends AsyncTask<Void,Void,String>{
	//
	//		@Override
	//		protected String doInBackground(Void... params) {
	//			HttpClient a = new HttpClient();
	//			String data = a.retrieve_parent_by_search("conr");
	//			return data;
	//		}
	//		
	//	}

	private class RegisterTask extends AsyncTask<Void,Void,Boolean>{

		private ProgressDialog pDialog;

		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(RegisterFamilyActivity.this);
			pDialog.setMessage("Checking.. Please wait...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(false);
			pDialog.show();
		}

		@Override
		protected Boolean doInBackground(Void... params) {
			// TODO Auto-generated method stub
			HttpClient a = new HttpClient();
			String data = a.loginUser(email.getText().toString(), password.getText().toString());
			ParentModel oneparent = JSONParser.getOneParent(data);
			int tempParentId = oneparent.getParentId();
			
			String tempfamily = a.retrieve_family(tempParentId);
			
			FamilyModel onefamily = JSONParser.getOneFamily(tempfamily);
			
			if(onefamily.getFatherId() == 0) {
				onefamily.setFatherId(HealthtimeSession.getParentId(getBaseContext()));
				a.editFamily(onefamily);
			}
			else if(onefamily.getMotherId() == 0) {
				onefamily.setMotherId(HealthtimeSession.getParentId(getBaseContext()));
				a.editFamily(onefamily);
			}
			return true;
		}

		protected void onPostExecute(Boolean data){
			super.onPostExecute(data);
			pDialog.dismiss();
			
			if(data){
				Toast.makeText(RegisterFamilyActivity.this, "Successful", Toast.LENGTH_SHORT).show();
				startActivity(new Intent(RegisterFamilyActivity.this, TiledEventsActivity.class));
				
			}
			else{
				Toast.makeText(RegisterFamilyActivity.this, "Failed", Toast.LENGTH_SHORT).show();
				email.setText("");
				password.setText("");
			}
		}
	}

//	private class RegisterTask extends AsyncTask<Void,Void,Void>{
//
//		@Override
//		protected Void doInBackground(Void... params) {
//			// TODO Auto-generated method stub
//			return null;
//		}
//
//	}
	
}
