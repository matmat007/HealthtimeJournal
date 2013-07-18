package com.healthtimejournal;

import java.util.Arrays;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;

import com.healthtimejournal.customadapter.MyCustomArrayAdapterSickness;

public class RegisterPage2Activity extends Activity{
	
	final Context context = this;
	
	CheckBox anemiaCheckBox;
	CheckBox asthmaCheckBox;
	CheckBox bleedingDisCheckBox;
	CheckBox diabetesCheckBox;
	CheckBox epilepsyCheckBox;
	CheckBox heartDisCheckBox;
	CheckBox highBloodCheckBox;
	CheckBox highChoCheckBox;
	CheckBox liverDisCheckBox;
	CheckBox kidneyDisCheckBox;
	CheckBox nasalAllCheckBox;
	CheckBox tuberculosisCheckBox;
	
	Button submitButton;
	Button previousButton;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register_page_2);
		
		getActionBar().setHomeButtonEnabled(true);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		
		ListView listview = (ListView)findViewById(R.id.sick_list);
		List<String> data = Arrays.asList(getResources().getStringArray(R.array.sickness));
		
		
		MyCustomArrayAdapterSickness adapter = new MyCustomArrayAdapterSickness(data, context);
		listview.setAdapter(adapter);
		
		/*
		anemiaCheckBox = (CheckBox)findViewById(R.id.regAnemiaCheck);
		anemiaCheckBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
		    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		        if(buttonView.isChecked()) {
		        }
		    }
		});
		asthmaCheckBox = (CheckBox)findViewById(R.id.regAsthmaCheck);
		asthmaCheckBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
		    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		        if(buttonView.isChecked()) {
		        }
		    }
		});
		bleedingDisCheckBox = (CheckBox)findViewById(R.id.regBleedingDisCheck);
		bleedingDisCheckBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
		    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		        if(buttonView.isChecked()) {
		        }
		    }
		});
		diabetesCheckBox = (CheckBox)findViewById(R.id.regBleedingDisCheck);
		diabetesCheckBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
		    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		        if(buttonView.isChecked()) {
		        }
		    }
		});
		epilepsyCheckBox = (CheckBox)findViewById(R.id.regEpilepsyCheck);
		epilepsyCheckBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
		    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		        if(buttonView.isChecked()) {
		        }
		    }
		});
		heartDisCheckBox = (CheckBox)findViewById(R.id.regHeartDisCheck);
		heartDisCheckBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
		    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		        if(buttonView.isChecked()) {
		        }
		    }
		});
		highBloodCheckBox = (CheckBox)findViewById(R.id.regHighBloodCheck);
		highBloodCheckBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
		    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		        if(buttonView.isChecked()) {
		        }
		    }
		});
		highChoCheckBox = (CheckBox)findViewById(R.id.regHighChoCheck);
		highChoCheckBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
		    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		        if(buttonView.isChecked()) {
		        }
		    }
		});
		liverDisCheckBox = (CheckBox)findViewById(R.id.regLiverDisCheck);
		liverDisCheckBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
		    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		        if(buttonView.isChecked()) {
		        }
		    }
		});
		kidneyDisCheckBox = (CheckBox)findViewById(R.id.regKidneyDisCheck);
		kidneyDisCheckBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
		    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		        if(buttonView.isChecked()) {
		        }
		    }
		});
		nasalAllCheckBox = (CheckBox)findViewById(R.id.regNasalAllCheck);
		nasalAllCheckBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
		    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		        if(buttonView.isChecked()) {
		        }
		    }
		});
		tuberculosisCheckBox = (CheckBox)findViewById(R.id.regTuberculosisCheck);
		tuberculosisCheckBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
		    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		        if(buttonView.isChecked()) {
		        }
		    }
		});
        
		submitButton = (Button)findViewById(R.id.regSubmitButton);
		submitButton.setOnClickListener(new OnClickListener() { 
			public void onClick(View arg0) {
				AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
				alertDialogBuilder
						.setMessage("Are you sure with your information?")
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
		
		previousButton = (Button)findViewById(R.id.regPreviousButton);
		previousButton.setOnClickListener(new OnClickListener() { 
			public void onClick(View arg0) {
				if(anemiaCheckBox.isChecked() || asthmaCheckBox.isChecked() || bleedingDisCheckBox.isChecked() || diabetesCheckBox.isChecked() || epilepsyCheckBox.isChecked() || heartDisCheckBox.isChecked() || 
						highBloodCheckBox.isChecked() || highChoCheckBox.isChecked() || liverDisCheckBox.isChecked() || kidneyDisCheckBox.isChecked() || nasalAllCheckBox.isChecked() || tuberculosisCheckBox.isChecked()){
					AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
					alertDialogBuilder
							.setMessage("Are you sure you want to go back to the previous page?")
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
	    }); */
		
		submitButton = (Button)findViewById(R.id.regSubmitButton);
		submitButton.setOnClickListener(new OnClickListener() { 
			public void onClick(View arg0) {
				startActivity(new Intent(RegisterPage2Activity.this, TimelineActivity.class));
			}
	    });
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item){
		
		startActivity(new Intent(RegisterPage2Activity.this, RegisterPageActivity.class));
		return true;
	}

}
