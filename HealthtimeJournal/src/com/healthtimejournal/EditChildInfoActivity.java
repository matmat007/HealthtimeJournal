package com.healthtimejournal;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;


public class EditChildInfoActivity extends Activity {
	
	final Context context = this;
	
	EditText poundsText;
	EditText feetText;
	EditText inchesText;
	
	Button editInfoSubmitButton;
	Button editInfoCancelButton;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.edit_child_info_page);
		
		poundsText = (EditText)findViewById(R.id.poundsEditText);
		feetText = (EditText)findViewById(R.id.feetEditText);
		inchesText = (EditText)findViewById(R.id.inchesEditText);
		
		editInfoSubmitButton = (Button)findViewById(R.id.editInfoSubmitButton);
		editInfoSubmitButton.setOnClickListener(new OnClickListener() { 
			public void onClick(View arg0) {
				if(!poundsText.getText().toString().equals("") && !feetText.getText().toString().equals("") && !inchesText.getText().toString().equals("")){
					AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
					alertDialogBuilder
							.setMessage("Are you sure with your child's edited information?")
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
				else {
					AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
					alertDialogBuilder
							.setMessage("You still have missing fields on this form.")
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
		
		editInfoCancelButton = (Button)findViewById(R.id.editInfoCancelButton);
		editInfoCancelButton.setOnClickListener(new OnClickListener() { 
			public void onClick(View arg0) {
				if(!poundsText.getText().toString().equals("") && !feetText.getText().toString().equals("") && !inchesText.getText().toString().equals("")){
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
				else
					finish();
			}
	    }); 
	}

}
