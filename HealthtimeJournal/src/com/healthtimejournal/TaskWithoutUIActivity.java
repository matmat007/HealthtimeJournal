package com.healthtimejournal;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.healthtimejournal.model.ChildModel;
import com.healthtimejournal.service.HttpClient;

public class TaskWithoutUIActivity extends Activity {
	
	private Button addchild;
	private Button adddoctor;
	private Button addcomment;
	
	private ChildTask mChildTask;
	private DoctorTask mDoctorTask;
	private CommentTask mCommentTask;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.task_without_ui_activity);
		addchild = (Button) findViewById(R.id.childButton);
		addchild.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				attemptAddChild();
				
			}
		});
		adddoctor = (Button) findViewById(R.id.doctorButton);
		adddoctor.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				attemptAddDoctor();
			}
		});
		addcomment = (Button) findViewById(R.id.commentButton);
		addcomment.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				attemptAddComment();
			}
		});
	}

	private void attemptAddChild(){
		if(mChildTask != null){
			return;
		}
		mChildTask = new ChildTask();
		mChildTask.execute((Void)null);
	}
	private void attemptAddDoctor(){
		if(mDoctorTask != null){
			return;
		}
		mDoctorTask = new DoctorTask();
		mDoctorTask.execute((Void)null);
	}
	private void attemptAddComment(){
		if(mCommentTask != null){
			return;
		}
		mCommentTask = new CommentTask();
		mCommentTask.execute((Void)null);
	
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.task_without_ui_activity, menu);
		return true;
	}

	private class ChildTask extends AsyncTask<Void,Void,Void>{
		
		private ProgressDialog pDialog;
		
		protected void onPreExecute(){
			 super.onPreExecute();
		        pDialog = new ProgressDialog(TaskWithoutUIActivity.this);
		        pDialog.setMessage("Please wait...");
		        pDialog.setIndeterminate(false);
		        pDialog.setCancelable(false);
		        pDialog.show();
		}

		@Override
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub
			ChildModel child = new ChildModel();
			child.setFamilyId(1);
			child.setFirstName("Bob");
			child.setLastName("Marley");
			child.setGender("Male");
			child.setBirthdate("1993-12-04");
			child.setBloodType("AB");
			HttpClient a = new HttpClient();
			a.addChild(child);
			return null;
		}
		protected void onPostExecute(Void result){
			super.onPostExecute(result);
			mChildTask = null;
			pDialog.dismiss();
			Toast.makeText(TaskWithoutUIActivity.this, "Check DB if success", Toast.LENGTH_SHORT).show();
		}
		
	}
	
	private class DoctorTask extends AsyncTask<Void,Void,Void>{

		@Override
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub
			return null;
		}
		
	}
	
	private class CommentTask extends AsyncTask<Void,Void,Void>{

		@Override
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub
			return null;
		}
		
	}
}
