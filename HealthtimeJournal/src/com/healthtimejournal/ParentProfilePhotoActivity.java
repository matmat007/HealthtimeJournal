package com.healthtimejournal;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

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
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.Toast;

import com.healthtimejournal.model.GalleryModel;
import com.healthtimejournal.model.ParentModel;
import com.healthtimejournal.service.HealthtimeSession;
import com.healthtimejournal.service.HttpClient;
import com.healthtimejournal.service.JSONParser;

public class ParentProfilePhotoActivity extends Activity {
	
	private static final int REQUEST_CODE = 1;
	private static final int CAMERA_REQUEST = 1888;
	
	private UploadPhotoTask mUploadTask = null;
	
	ImageView editParentCameraButton;
	ImageView editParentAttachButton;
	ImageView img;

	Bitmap bm = null;
	String selectedImagePath = null;
	Uri mCapturedImageURI;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.edit_parent_photo_page);
		
		img = (ImageView)findViewById(R.id.editParentImage);
		
		editParentCameraButton = (ImageView)findViewById(R.id.editParentCameraButton);
		editParentCameraButton.setOnClickListener(new OnClickListener() { 
			public void onClick(View arg0) {
				onCameraClick();
			}
        });
		
		editParentAttachButton = (ImageView)findViewById(R.id.editParentAttachFileButton);
		editParentAttachButton.setOnClickListener(new OnClickListener() { 
			public void onClick(View arg0) {
				onUploadClick();
			}
        });
		
	}
	
	public void attemptUpload(){
		if (mUploadTask != null) {
			return;
		}

		mUploadTask = new UploadPhotoTask();
		mUploadTask.execute((Void) null);
	}
	
	public void onUploadClick(){
		Intent i = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
		startActivityForResult(i, REQUEST_CODE);
	}

	public void onCameraClick(){
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
	
	private class UploadPhotoTask extends AsyncTask<Void, Void, Boolean>{

		private ProgressDialog pDialog;

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(getApplicationContext());
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
			
			ParentModel oneparent = new ParentModel();
			oneparent.setParentId(HealthtimeSession.getParentId(getBaseContext()));
			oneparent.setImage(onegallery.getGalleryId());
			a.editParentPhoto(oneparent);
			
			return true;
		}

		protected void onPostExecute(Boolean value){
			pDialog.dismiss();
			startActivity(new Intent(ParentProfilePhotoActivity.this, TiledEventsActivity.class));
		}

	}
	
}
