package com.healthtimejournal.customcontextmenu;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;

import com.healthtimejournal.R;

public class SetPrivilegeContext extends BaseDialog {
	
	@Override
	public void openDialog(final Context context) {
		
		LayoutInflater factory = LayoutInflater.from(context);            
		View promptsView = factory.inflate(R.layout.context_set_privilege, null);

		AlertDialog.Builder alert = new AlertDialog.Builder(context); 
		alert.setTitle("Friend Privilege Options");  
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

}
