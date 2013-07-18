package com.healthtimejournal.customcontextmenu;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.healthtimejournal.R;

public class PostSettingsContext extends BaseDialog {
	
	@Override
	public void openDialog(Context context) {
		
		LayoutInflater factory = LayoutInflater.from(context);            
		View promptsView = factory.inflate(R.layout.context_post_settings, null);

		AlertDialog.Builder alert = new AlertDialog.Builder(context); 
		alert.setTitle("Post Settings");  
		alert.setView(promptsView); 
		alert.create();
		
		final Button fbButton = (Button)promptsView.findViewById(R.id.addFbButton);
		final Button deleteButton = (Button)promptsView.findViewById(R.id.deletePostButton);
		
		fbButton.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
			} 
		}); 

		deleteButton.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
			} 
		}); 
		
		

		alert.show();
		
	}
}