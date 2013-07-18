package com.healthtimejournal;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.MultiAutoCompleteTextView;
import android.widget.Toast;

import com.healthtimejournal.model.Hashtag;
import com.healthtimejournal.service.HttpClient;
import com.healthtimejournal.service.JSONParser;
import com.healthtimejournal.service.TagTokenizer;

public class PostActivity extends Activity {

	private static final int REQUEST_CODE = 1;
	private static final int CAMERA_REQUEST = 1337;
	
	private TagTask mTagTask = null;
	private PostTask mPostTask = null;
	
	MultiAutoCompleteTextView post;
	
	List<String> items;
	
	ImageView cameraButton;
	ImageView attachFileButton;
	ImageView img;
	
	Bitmap bm;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		items = new ArrayList<String>();
		
		getActionBar().setHomeButtonEnabled(true);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.post);
		
		post = (MultiAutoCompleteTextView)findViewById(R.id.postEditText);
		
		retrieve_all_hashtags();
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, items);
		post.setAdapter(adapter);
		post.setSelected(true);
		post.setRawInputType(InputType.TYPE_CLASS_TEXT
		          |InputType.TYPE_TEXT_FLAG_CAP_SENTENCES
		          |InputType.TYPE_TEXT_FLAG_AUTO_CORRECT
		          |InputType.TYPE_TEXT_FLAG_MULTI_LINE);
		post.setThreshold(2);
		post.setTokenizer(new TagTokenizer());
		
		
		
		/*
		attachFileButton = (ImageView)findViewById(R.id.postAttachFileButton);
		attachFileButton.setOnClickListener(new OnClickListener() { 
			public void onClick(View arg0) {
				Intent a = new Intent(PostActivity.this, AddChildActivity.class);
		        startActivity(a);
			}
	    }); */
		
	}
	
	private void retrieve_all_hashtags(){
		if (mTagTask != null) {
            return;
        }
		
		mTagTask = new TagTask();
		mTagTask.execute((Void) null);
	}
	//Click for Upload
	public void onUploadClick(View view){
		Intent intent = new Intent();
	    intent.setType("image/*");
	    intent.setAction(Intent.ACTION_GET_CONTENT);
	    intent.addCategory(Intent.CATEGORY_OPENABLE);
	    startActivityForResult(intent, REQUEST_CODE);
	}
	
	//Click for Camera
	public void onCameraClick(View view){
		Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
	    startActivityForResult(intent, CAMERA_REQUEST);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.activity_main, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item){
		
		switch(item.getItemId()){
		case android.R.id.home:
			startActivity(new Intent(PostActivity.this, TimelineActivity.class));
			break;
			
		case R.id.postAction:
			attemptPost();
		}
		return true;
		
	}
	
	private void attemptPost(){
		if (mPostTask != null) {
            return;
        }
		String mPost = post.getText().toString();


        boolean cancel = false;
        View focusView = null;

        // Check for a valid password.
        if (TextUtils.isEmpty(mPost)) {
            post.setError("This field is required");
            focusView = post;
            cancel = true;
        }

        if (cancel) {
            focusView.requestFocus();
        } else {
            mPostTask = new PostTask();
            mPostTask.execute((Void) null);
        }
		
	}
	
	protected void onActivityResult(int requestCode, int resultCode, Intent data){
		super.onActivityResult(requestCode, resultCode, data);
		
		switch(requestCode){
		case REQUEST_CODE: 
			if(resultCode == Activity.RESULT_OK){
				Uri selectedImage = data.getData();
				String[] filePathColumn = { MediaStore.Images.Media.DATA };

	            Cursor cursor = getContentResolver().query(selectedImage,
	                    filePathColumn, null, null, null);
	            cursor.moveToFirst();

	            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
	            String picturePath = cursor.getString(columnIndex);
	            cursor.close();

	            if (bm != null && bm.isRecycled()) {
	            	bm = null;
	            }
	            bm = BitmapFactory.decodeFile(picturePath);
	            img.setImageBitmap(bm);
			}
		}
	}
	
	private class TagTask extends AsyncTask<Void, Void, String>{

		@Override
		protected String doInBackground(Void... arg0) {
			// TODO Auto-generated method stub
			
			HttpClient a = new HttpClient();
			String data = a.retrieve_all_hashtags();
			return data;
		}
		
		protected void onPostExecute(String data){
			mTagTask = null;
			
			if(data != null){
			List<Hashtag> result = JSONParser.getHashtag(data);
			
			Log.d("hashtags", String.valueOf(result.size()));
			
			Iterator<Hashtag> i = result.iterator();
			
			Hashtag onehash = null;
			
			while(i.hasNext()){
				onehash = i.next();
				items.add(onehash.getHashtag_name());
			}
			}
			else{
				Toast.makeText(getApplicationContext(), "Connection to net unsuccessful", Toast.LENGTH_SHORT).show();
			}
		}
		
	}
	
	private class PostTask extends AsyncTask<Void, Void, Boolean>{

		@Override
		protected Boolean doInBackground(Void... params) {
			// TODO Auto-generated method stub
			HttpClient a = new HttpClient();
			a.addPost(post.getText().toString());
			return true;
		}
		
		protected void onPostExecute(Boolean value){
			startActivity(new Intent(PostActivity.this, TiledEventsActivity.class));
		}
		
	}
}
