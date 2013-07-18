package com.healthtimejournal;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class EditParentInfoActivity extends Activity {

	final Context context = this;
	
	Button editParentSubmitButton;
	Button editParentCancelButton;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.edit_parent_info_page);
		
		editParentSubmitButton = (Button)findViewById(R.id.editParentSubmitButton);
		editParentSubmitButton.setOnClickListener(new OnClickListener() { 
			public void onClick(View arg0) {
				AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
				alertDialogBuilder
						.setMessage("Are you sure with your edited information?")
						.setCancelable(false)
						.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								finish();
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
		
		editParentCancelButton = (Button)findViewById(R.id.editParentCancelButton);
		editParentCancelButton.setOnClickListener(new OnClickListener() { 
			public void onClick(View arg0) {
				AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
				alertDialogBuilder
						.setMessage("Are you sure you want to cancel?")
						.setCancelable(false)
						.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								finish();
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
	}
	
}
