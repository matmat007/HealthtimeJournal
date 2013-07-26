package com.healthtimejournal.customcontextmenu;

import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.healthtimejournal.R;
import com.healthtimejournal.customadapter.CommentListAdapter;
import com.healthtimejournal.model.CommentModel;
import com.healthtimejournal.service.HttpClient;
import com.healthtimejournal.service.JSONParser;

public class CommentListContext extends BaseDialog {
	
	private CommentTask mAddCommentTask = null;
	private GetCommentTask mCommentTask = null;
	
	Context context = null;
	
	EditText commentBox;
	
	ImageView commentFriendImage;
	
	TextView commentFriendName;
	TextView commentContent;
	TextView commentDate;
	
	Button commentButton;
	
	ListView commentList;
	
	List<CommentModel> comment = null;

	@Override
	public void openDialog(final Context context) {
		this.context = context;
		
		LayoutInflater factory = LayoutInflater.from(context);            
		View promptsView = factory.inflate(R.layout.context_comment_page, null);

		AlertDialog.Builder alert = new AlertDialog.Builder(context); 
		alert.setView(promptsView); 
		alert.create();
		
		commentList = (ListView)promptsView.findViewById(R.id.commentList);

		commentBox = (EditText)promptsView.findViewById(R.id.commentBox);
		
		commentButton = (Button)promptsView.findViewById(R.id.commentButton);
		commentButton.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				addComment();
			} 
	    }); 
		
		retrieve_comment();
		
		alert.show();
	}
	
	public void addComment(){
		if(mAddCommentTask != null){
			return;
		}
		
		String mComment = commentBox.getText().toString();
		
		boolean cancel = false;
		View focusView = null;
		
		if(TextUtils.isEmpty(mComment)){
			commentBox.setError(getErrorString(R.string.required_not_met));
			focusView = commentBox;
			cancel = true;
		}
		if(cancel){
			focusView.requestFocus();
		}
		else{
			mAddCommentTask = new CommentTask(context);
			mAddCommentTask.execute((Void)null);
		}
	}
	
	private CharSequence getErrorString(int requiredNotMet) {
		return "This is a required field!";
	}

	public void retrieve_comment(){
		if(mCommentTask != null){
			return;
		}
		
		mCommentTask = new GetCommentTask();
		mCommentTask.execute();
	}
	
	private class CommentTask extends AsyncTask<Void,Void,Boolean>{
		
		public CommentTask(Context context){
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
			CommentModel comment = new CommentModel();
			
			comment.setParentId(1);
			comment.setPostId(1);
			comment.setCommentContent(commentBox.getText().toString());
			
			a.addComment(comment);
			
			return true;
		}
		
		protected void onPostExecute(Boolean value){
			super.onPostExecute(value);
			mAddCommentTask = null;
			pDialog.dismiss();
			if(value){
				Toast.makeText(context, "Registration Successful", Toast.LENGTH_SHORT).show();
			}
			else{
				Toast.makeText(context, "Registration Failed", Toast.LENGTH_SHORT).show();
			}
			
		}
		
	}
	
	private class GetCommentTask extends AsyncTask<Void, Void, String>{
		@Override
		protected String doInBackground(Void... params) {
			// TODO Auto-generated method stub
			HttpClient a = new HttpClient();
			String data = a.retrieve_all_comment(1);
			return data;
		}
		
		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
	        comment = new ArrayList<CommentModel>();
	        comment = JSONParser.getComment(result);
	        
	        commentList.setAdapter(new CommentListAdapter(context, comment));
		}
		
	}

}
