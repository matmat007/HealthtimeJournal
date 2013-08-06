package com.healthtimejournal.customadapter;

import java.util.List;

import org.json.JSONObject;
import org.json.JSONTokener;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AsyncFacebookRunner;
import com.facebook.BaseRequestListener;
import com.facebook.DialogError;
import com.facebook.Facebook;
import com.facebook.Facebook.DialogListener;
import com.facebook.FacebookError;
import com.facebook.SessionStore;
import com.healthtimejournal.R;
import com.healthtimejournal.model.GalleryModel;
import com.healthtimejournal.model.ParentModel;
import com.healthtimejournal.model.PostModel;
import com.healthtimejournal.service.Base64Decoder;

@SuppressLint("HandlerLeak")
public class MyCustomAdapterTimeline extends BaseAdapter{
	
	private Facebook mFacebook;
    private ProgressDialog mProgress;

    private static final String[] PERMISSIONS = new String[] {"publish_stream", "read_stream", "offline_access"};

    private static final String APP_ID = "460537864017391";
 
    private Handler mRunOnUi = new Handler();

	List<PostModel> items;
	Activity activity;
	
	public MyCustomAdapterTimeline(Activity activity, List<PostModel> items, List<ParentModel> parent, List<GalleryModel> gallery){
		this.activity = activity;
		this.items = items;
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return items.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return items.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) {
		// TODO Auto-generated method stub
		
		Base64Decoder base = new Base64Decoder();
		
		mProgress   = new ProgressDialog(activity);
		 
        mFacebook   = new Facebook(APP_ID);
 
        SessionStore.restore(mFacebook, activity);
        
		View vi = arg1;
		
		if(vi == null){
			LayoutInflater inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			vi = inflater.inflate(R.layout.timeline_item, null);
		}
		
		final ImageView parentImage = (ImageView) vi.findViewById(R.id.timelineParentImage);
	 	
		final TextView text1 = (TextView) vi.findViewById(R.id.timeline_item_username);
		final TextView text2 = (TextView) vi.findViewById(R.id.timeline_item_time);
		final TextView text3 = (TextView) vi.findViewById(R.id.timeline_item_post);
		
		final ImageView postImage = (ImageView) vi.findViewById(R.id.timelinePostImage);
		
		Button shareButton = (Button) vi.findViewById(R.id.timeline_item_button1);
		shareButton.setOnClickListener(new OnClickListener() { 
			public void onClick(View arg0) {
				AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(activity);
				alertDialogBuilder
						.setMessage("Are you sure you want to share this on Facebook?")
						.setCancelable(false)
						.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								if (mFacebook.isSessionValid()) {
						        	postToFacebook(text3.getText().toString());
						        } else {
						            mFacebook.authorize(activity, PERMISSIONS, -1, new FbLoginDialogListener());
						        }
							}
						  })
						.setNegativeButton("No", new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								dialog.cancel();
							}
						});

				AlertDialog alertDialog = alertDialogBuilder.create();
				alertDialog.show();
			}
	    });
		
		TextView deleteButton = (TextView) vi.findViewById(R.id.timeline_item_x);
		deleteButton.setOnClickListener(new OnClickListener() { 
			public void onClick(View arg0) {
				AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(activity);
				alertDialogBuilder
						.setMessage("Are you sure you want to delete this entry?")
						.setCancelable(false)
						.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								dialog.cancel();
							}
						  })
						.setNegativeButton("No", new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								dialog.cancel();
							}
						});

				AlertDialog alertDialog = alertDialogBuilder.create();
				alertDialog.show();
			}
	    });
		
		text1.setText(items.get(arg0).getPostContent());
		text2.setText(items.get(arg0).getPostDate());
		text3.setText(items.get(arg0).getPostContent());
		
		//parentImage.setImageBitmap(base.decodeBase64(items.get(arg0).getFromParentImage()));
		//postImage.setImageBitmap(base.decodeBase64(items.get(arg0).getPostImage()));
		
		return vi;
	}
 
    private final class FbLoginDialogListener implements DialogListener {
        public void onComplete(Bundle values) {
            SessionStore.save(mFacebook, activity);
            getFbDetails();
        }
 
        public void onFacebookError(FacebookError error) {
           Toast.makeText(activity, "Facebook connection failed", Toast.LENGTH_SHORT).show();
        }
 
        public void onError(DialogError error) {
            Toast.makeText(activity, "Facebook connection failed", Toast.LENGTH_SHORT).show();
        }
 
        public void onCancel() {
        }
    }
 
    private void getFbDetails() {
        mProgress.setMessage("Finalizing ...");
        mProgress.show();
 
        new Thread() {
            @Override
            public void run() {
                String name = "";
                int what = 1;
 
                try {
                    String me = mFacebook.request("me");
 
                    JSONObject jsonObj = (JSONObject) new JSONTokener(me).nextValue();
                    name = jsonObj.getString("name");
                    what = 0;
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
 
                mFbHandler.sendMessage(mFbHandler.obtainMessage(what, name));
            }
        }.start();
    }
 
    private Handler mFbHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            mProgress.dismiss();
 
            if (msg.what == 0) {
                String username = (String) msg.obj;
                username = (username.equals("")) ? "No Name" : username;
 
                SessionStore.saveName(username, activity);
 
                Toast.makeText(activity, "Connected to Facebook as " + username, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(activity, "Connected to Facebook", Toast.LENGTH_SHORT).show();
            }
        }
    };
	
	private void postToFacebook(String review) {
        mProgress.setMessage("Posting ...");
        mProgress.show();
 
        AsyncFacebookRunner mAsyncFbRunner = new AsyncFacebookRunner(mFacebook);
 
        Bundle params = new Bundle();
 
        params.putString("message", review);
        params.putString("name", "HealthTime Journal");
        params.putString("caption", "HealthTime Journal");
        params.putString("description", "HealthTime Journal");
        params.putString("link", "https://www.facebook.com/HealthtimeJournal");
        params.putString("picture", "https://raw.github.com/fbsamples/ios-3.x-howtos/master/Images/iossdk_logo.png");
 
        mAsyncFbRunner.request("me/feed", params, "POST", new WallPostListener());
    }
 
    private final class WallPostListener extends BaseRequestListener {
        public void onComplete(final String response) {
            mRunOnUi.post(new Runnable() {
                @Override
                public void run() {
                    mProgress.cancel();
 
                    Toast.makeText(activity, "Posted to Facebook", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

}
