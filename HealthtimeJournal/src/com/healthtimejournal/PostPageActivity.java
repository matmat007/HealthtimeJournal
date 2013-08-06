package com.healthtimejournal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class PostPageActivity extends Activity {
	
	ImageView friendPostImage;
	ImageView contentPostImage;
	
	TextView friendName;
	TextView postDate;
	TextView postContent;
	
	EditText commentBox;
	
	ListView commentList;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.post_page);
		
		friendPostImage = (ImageView)findViewById(R.id.friendPostImage);
		friendPostImage.setImageResource(R.drawable.ic_launcher);
		
		friendName = (TextView)findViewById(R.id.postFriendName);
		postDate = (TextView)findViewById(R.id.postDate);
		postContent = (TextView)findViewById(R.id.postContent);
		
		friendName.setText("Joey Bing");
		postDate.setText("1/1/11");
		postContent.setText("Bing Bang Boom!");
		
		contentPostImage = (ImageView)findViewById(R.id.postContentImage);
		contentPostImage.setImageResource(R.drawable.ic_launcher);
		
		commentBox = (EditText)findViewById(R.id.commentBox);
		commentBox.setOnClickListener(new OnClickListener() { 
			public void onClick(View arg0) {
				Intent a = new Intent(PostPageActivity.this, PostActivity.class);
		        startActivity(a);
			}
	    }); 
		
		commentList = (ListView)findViewById(R.id.commentList);
		//commentList.setAdapter(new CommentListAdapter(this));
		
	}

}
