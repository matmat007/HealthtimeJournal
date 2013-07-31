package com.healthtimejournal;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentValues;
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
import android.widget.RadioButton;
import android.widget.Toast;

import com.healthtimejournal.model.Event;
import com.healthtimejournal.model.GalleryModel;
import com.healthtimejournal.model.Hashtag;
import com.healthtimejournal.service.HealthtimeSession;
import com.healthtimejournal.service.HttpClient;
import com.healthtimejournal.service.JSONParser;
import com.healthtimejournal.service.TagTokenizer;

public class EventActivity extends Activity {

	private static final int REQUEST_CODE = 1;
	private static final int CAMERA_REQUEST = 1888;

	private TagTask mTagTask = null;
	private PostTask mPostTask = null;
	
	private int childId;

	MultiAutoCompleteTextView post;

	List<String> items;

	ImageView cameraButton;
	ImageView attachFileButton;
	ImageView img;
	
	RadioButton radMed;
	RadioButton radNonmed;
	RadioButton radMile;

	Bitmap bm = null;
	String selectedImagePath = null;
	Uri mCapturedImageURI;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		Bundle extras = getIntent().getExtras();
		if(extras != null){
			childId = extras.getInt("id");
		}
		items = new ArrayList<String>();

		getActionBar().setHomeButtonEnabled(true);
		getActionBar().setDisplayHomeAsUpEnabled(true);

		super.onCreate(savedInstanceState);
		setContentView(R.layout.event_activity);

		post = (MultiAutoCompleteTextView)findViewById(R.id.eventEditText);

		//retrieve_all_hashtags();

		/*ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, items);
		post.setAdapter(adapter);
		post.setSelected(true);
		post.setRawInputType(InputType.TYPE_CLASS_TEXT
				|InputType.TYPE_TEXT_FLAG_CAP_SENTENCES
				|InputType.TYPE_TEXT_FLAG_AUTO_CORRECT
				|InputType.TYPE_TEXT_FLAG_MULTI_LINE);
		post.setThreshold(2);
		post.setTokenizer(new TagTokenizer());*/

		img = (ImageView)findViewById(R.id.postPageImage);
		
		radMed = (RadioButton)findViewById(R.id.radioMeds);
		radNonmed = (RadioButton)findViewById(R.id.radioNonmeds);
		radMile = (RadioButton)findViewById(R.id.radioMilestone);

	}

	//Click for Upload
	public void onUploadClick(View view){
		Intent i = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
		startActivityForResult(i, REQUEST_CODE);
	}

	//Click for Camera
	public void onCameraClick(View view){
		try {
			String fileName = "temp.jpg";
			ContentValues values = new ContentValues();
			values.put(MediaStore.Images.Media.TITLE, fileName);
			mCapturedImageURI = getContentResolver()
					.insert(
							MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
							values);
			Intent intent = new Intent(
					MediaStore.ACTION_IMAGE_CAPTURE);
			intent.putExtra(MediaStore.EXTRA_OUTPUT,
					mCapturedImageURI);
			startActivityForResult(intent, CAMERA_REQUEST);
		} catch (Exception e) {
			Log.e("", "", e);
		}
	}

	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode == RESULT_OK) {
			if (requestCode == REQUEST_CODE) {
				mCapturedImageURI = data.getData();
			}
			selectedImagePath = getPath(mCapturedImageURI);
			bm = reduceImageSize(selectedImagePath);
			if(bm != null)
				img.setImageBitmap(bm);
		}
	}

	@SuppressWarnings("deprecation")
	public String getPath(Uri uri) {
		String[] projection = { MediaStore.Images.Media.DATA };
		Cursor cursor = managedQuery(uri, projection, null, null, null);
		int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
		cursor.moveToFirst();
		return cursor.getString(column_index);
	}

	public Bitmap reduceImageSize(String mSelectedImagePath){

		Bitmap m = null;
		try {
			File f = new File(mSelectedImagePath);

			//Decode image size
			BitmapFactory.Options o = new BitmapFactory.Options();
			o.inJustDecodeBounds = true;
			BitmapFactory.decodeStream(new FileInputStream(f),null,o);

			//The new size we want to scale to
			final int REQUIRED_SIZE=150;

			//Find the correct scale value. It should be the power of 2.
			int width_tmp=o.outWidth, height_tmp=o.outHeight;
			int scale=1;
			while(true){
				if(width_tmp/2 < REQUIRED_SIZE || height_tmp/2 < REQUIRED_SIZE)
					break;
				width_tmp/=2;
				height_tmp/=2;
				scale*=2;
			}

			//Decode with inSampleSize
			BitmapFactory.Options o2 = new BitmapFactory.Options();
			o2.inSampleSize=scale;
			m = BitmapFactory.decodeStream(new FileInputStream(f), null, o2);
		} catch (FileNotFoundException e) {
			Toast.makeText(getApplicationContext(), "Image File not found in your phone. Please select another image.", Toast.LENGTH_LONG).show();
		}
		return  m;
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
			finish();
			break;

		case R.id.postAction:
			attemptPost();
			break;
		}
		return true;

	}

	private void retrieve_all_hashtags(){
		if (mTagTask != null) {
			return;
		}

		mTagTask = new TagTask();
		mTagTask.execute((Void) null);
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
			post.setError(getString(R.string.required_not_met));
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

		private ProgressDialog pDialog;

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(EventActivity.this);
			pDialog.setMessage("Please wait...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(false);
			pDialog.show();
		}

		@Override
		protected Boolean doInBackground(Void... params) {
			// TODO Auto-generated method stub
			HttpClient a = new HttpClient();

			GalleryModel onegallery = new GalleryModel();
			if(selectedImagePath != null){
				onegallery.setParentId(HealthtimeSession.getParentId(getBaseContext()));
				onegallery.setFilename(selectedImagePath);
				a.addGallery(onegallery);
				onegallery = null;
				onegallery = JSONParser.getLastGallery(a.retrieve_gallery_last_upload(HealthtimeSession.getParentId(getBaseContext())));
			}
			else
				onegallery.setGalleryId(0);
			
			Event onepost = new Event();
			onepost.setToParentId(HealthtimeSession.getParentId(getBaseContext()));
			onepost.setFromParentId(HealthtimeSession.getParentId(getBaseContext()));
			onepost.setChildId(childId);
			onepost.setEventContent(post.getText().toString());
			onepost.setFileId(onegallery.getGalleryId());
			if(radMed.isChecked()){
				onepost.setEventCategory(1);
			}
			else if(radNonmed.isChecked()){
				onepost.setEventCategory(2);
			}
			else{
				onepost.setEventCategory(3);
			}
			a.addEvent(onepost);

			return true;
		}

		protected void onPostExecute(Boolean value){
			pDialog.dismiss();
			startActivity(new Intent(EventActivity.this, TiledEventsActivity.class));
		}

	}
}
