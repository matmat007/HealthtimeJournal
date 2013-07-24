package com.healthtimejournal.customcontextmenu;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Toast;

import com.healthtimejournal.R;
import com.healthtimejournal.model.SharingModel;
import com.healthtimejournal.service.HealthtimeSession;
import com.healthtimejournal.service.HttpClient;

public class SetPrivilegeContext extends BaseDialogPrivilege {
	
	Context context;
	
	private SharingTask mSharingTask = null;

	private int parentid = HealthtimeSession.getParentId(context);
	
	private int sharingId;
	private int privilege;
	
	@Override
	public void openDialog(final Context context, final int sharingId) {
		this.context = context;
		this.sharingId = sharingId;
		
		LayoutInflater factory = LayoutInflater.from(context);            
		View promptsView = factory.inflate(R.layout.context_set_privilege, null);

		AlertDialog.Builder alert = new AlertDialog.Builder(context); 
		alert.setTitle("Sharing Privilege Options");  
		alert.setView(promptsView); 
		alert.create();
		
		final CheckBox checkLevel1 = (CheckBox)promptsView.findViewById(R.id.level1Check);
		final CheckBox checkLevel2 = (CheckBox)promptsView.findViewById(R.id.level2Check);
		final CheckBox checkLevel3 = (CheckBox)promptsView.findViewById(R.id.level3Check);
		
		checkLevel1.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
		    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		        if(checkLevel1.isChecked()) {
		        	checkLevel1.setChecked(true);
		        	checkLevel2.setChecked(false);
		        	checkLevel3.setChecked(false);
		        	privilege = 1;
		        }
		    }
		});
		checkLevel2.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
		    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		        if(checkLevel2.isChecked()) {
		        	checkLevel2.setChecked(true);
		        	checkLevel1.setChecked(false);
		        	checkLevel3.setChecked(false);
		        	privilege = 2;
		        }
		    }
		});
		checkLevel3.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
		    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		        if(checkLevel3.isChecked()) {
		        	checkLevel3.setChecked(true);
		        	checkLevel1.setChecked(false);
		        	checkLevel2.setChecked(false);
		        	privilege = 3;
		        }
		    }
		});
		
		alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {
				String levelSel = "";
				if(checkLevel1.isChecked() || checkLevel2.isChecked() || checkLevel3.isChecked()){
					
					if(checkLevel1.isChecked())
						levelSel = "level 1";
					else if(checkLevel2.isChecked())
						levelSel = "level 2";
					else if(checkLevel3.isChecked())
						levelSel = "level 3";
					
					AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
					alertDialogBuilder
							.setMessage("Are you sure with privilege " + levelSel + "?")
							.setCancelable(false)
							.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog, int id) {
									editSharing();
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
				else{
					AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
					alertDialogBuilder
							.setMessage("You did not select any privilege level.")
							.setCancelable(false)
							.setNeutralButton("Ok", new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog, int id) {
									dialog.cancel();
								}
							});
			 
					AlertDialog alertDialog = alertDialogBuilder.create();
					alertDialog.show();
				}
			} 
		}); 

		alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() { 
			public void onClick(DialogInterface dialog, int whichButton) {
				dialog.cancel();
			} 
		}); 

		alert.show();
		
	}
	
	public void editSharing(){
		if(mSharingTask != null){
			return;
		}
		
		mSharingTask = new SharingTask(context);
		mSharingTask.execute((Void)null);
	}
	
	private class SharingTask extends AsyncTask<Void,Void,Boolean>{
		
		public SharingTask(Context context){
			this.context = context;
		}

		private ProgressDialog pDialog;
		private Context context;

		@Override
		protected void onPreExecute() {
	        super.onPreExecute();
	        pDialog = new ProgressDialog(context);
	        pDialog.setMessage("Please wait...");
	        pDialog.setIndeterminate(false);
	        pDialog.setCancelable(false);
	        pDialog.show();
	    }
		
		protected Boolean doInBackground(Void... arg0) {
			
			HttpClient a = new HttpClient();
			SharingModel sharing = new SharingModel();

			sharing.setSharingId(sharingId);
			sharing.setPrivilege(privilege);
			
			a.editSharing(sharing);
			
			return true;
		}
		
		protected void onPostExecute(Boolean value){
			super.onPostExecute(value);
			mSharingTask = null;
			pDialog.dismiss();
			if(value){
				Toast.makeText(context, "Registration Successful", Toast.LENGTH_SHORT).show();
			}
			else{
				Toast.makeText(context, "Registration Failed", Toast.LENGTH_SHORT).show();
			}
			
		}
		
	}

}
