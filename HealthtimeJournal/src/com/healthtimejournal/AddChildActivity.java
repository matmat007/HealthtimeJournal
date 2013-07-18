package com.healthtimejournal;

import java.util.ArrayList;
import java.util.List;

import com.healthtimejournal.R;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

@SuppressLint("NewApi")
public class AddChildActivity extends Activity{
	
	final Context context = this;
	
	EditText firstnameText;
	EditText lastnameText;
	
	Spinner bloodType;
	DatePicker birthdate;
	
	Button addChildButton;
	Button cancelButton;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_child);

		getActionBar().setHomeButtonEnabled(true);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		
		firstnameText = (EditText)findViewById(R.id.addChildFirstNameText);
		lastnameText = (EditText)findViewById(R.id.addChildLastNameText);
		
//		maleCheckBox = (CheckBox)findViewById(R.id.addChildMaleCheck);
//		maleCheckBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
//			@Override
//		    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//		        if(maleCheckBox.isChecked()) {
//		        	maleCheckBox.setChecked(true);
//		            femaleCheckBox.setChecked(false);
//		        }
//		    }
//		});
//        femaleCheckBox = (CheckBox)findViewById(R.id.addChildFemaleCheck);
//        femaleCheckBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
//			@Override
//		    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//		        if(femaleCheckBox.isChecked()) {
//		        	femaleCheckBox.setChecked(true);
//		        	maleCheckBox.setChecked(false);
//		        }
//		    }
//		});

        bloodType = (Spinner)findViewById(R.id.addChildBloodTypeSpinner);
		List<String> list = new ArrayList<String>();
		list.add("A");
		list.add("B");
		list.add("O");
		list.add("AB");
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list);
		dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		bloodType.setAdapter(dataAdapter);
		
		birthdate = (DatePicker)findViewById(R.id.addChildDatePicker);
		birthdate.setCalendarViewShown(false);

        addChildButton = (Button)findViewById(R.id.addChildButton);
//        addChildButton.setOnClickListener(new OnClickListener() { 
//			public void onClick(View arg0) {
//				if(!firstnameText.getText().toString().equals("") && !lastnameText.getText().toString().equals("") && (maleCheckBox.isChecked() || femaleCheckBox.isChecked())){
//					AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
//					alertDialogBuilder
//							.setMessage("Are you sure with your child's information?")
//							.setCancelable(false)
//							.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//								public void onClick(DialogInterface dialog, int id) {
//									finish();
//								}
//							  })
//							.setNegativeButton("No", new DialogInterface.OnClickListener() {
//								public void onClick(DialogInterface dialog, int id) {
//									dialog.cancel();
//								}
//							});
//			 
//					AlertDialog alertDialog = alertDialogBuilder.create();
//					alertDialog.show();
//				}
//				else {
//					AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
//					alertDialogBuilder
//							.setMessage("You still have missing fields on the child register form.")
//							.setCancelable(false)
//							.setNeutralButton("Ok", new DialogInterface.OnClickListener() {
//								public void onClick(DialogInterface dialog, int id) {
//									dialog.cancel();
//								}
//							});
//		 
//					AlertDialog alertDialog = alertDialogBuilder.create();
//					alertDialog.show();
//				}
//			}
//			
//	    }); 
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item){
		
		switch(item.getItemId()){
		case android.R.id.home:
			finish();
			break;
		}
		return true;
	}

}
