package com.healthtimejournal.customadapter;

import java.util.List;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.healthtimejournal.R;
import com.healthtimejournal.model.CommentModel;
import com.healthtimejournal.service.Base64Decoder;
import com.healthtimejournal.service.HttpClient;

public class CommentListAdapter extends BaseAdapter {
	
	private DeleteCommentTask mDeleteTask = null;
	
	private static LayoutInflater inflater = null;
	Base64Decoder decoder;
	
	List<CommentModel> comment;
	Context context;
	
	public CommentListAdapter(Context context,  List<CommentModel> comment) {
		inflater = LayoutInflater.from(context);
		this.context = context;
		this.comment = comment;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		
		ViewHolder holder;

	    View vi = convertView;
	    
	    if(convertView == null) {
	    	holder = new ViewHolder();
	    	
	        vi = inflater.inflate(R.layout.post_page_comment_one, null);
	        
	        holder.commentFriendImage = (ImageView)vi.findViewById(R.id.commentFriendImage); 
	        holder.commentFriendName = (TextView)vi.findViewById(R.id.commentFriendName); 
	        holder.commentContent = (TextView)vi.findViewById(R.id.commentContent); 
	        holder.commentDate = (TextView)vi.findViewById(R.id.commentDate); 
	        
//	        holder.commentFriendImage.setImageBitmap(decoder.decodeBase64(comment.get(position).getImage().toString()));
//	        holder.commentFriendName.setText(comment.get(position).getParentFirstName().toString() + " " + comment.get(position).getParentLastName().toString());
	        holder.commentContent.setText(comment.get(position).getCommentContent().toString());
	        holder.commentDate.setText(comment.get(position).getCommentDate().toString());
	        
	        holder.mainLayout = (RelativeLayout)vi.findViewById(R.id.mainLayout);
	        holder.mainLayout.setOnLongClickListener(new OnLongClickListener() {
	            public boolean onLongClick(View v) {
	            	deleteComment();
	                return true;
	            }
	        });
	    }
	    
	    return vi;
	 }

	public int getCount() {
		// TODO Auto-generated method stub
		return comment.size();

	}

	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return comment.get(position);

	}

	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;

	}
	
	static class ViewHolder {
		RelativeLayout mainLayout;
		ImageView commentFriendImage;
		TextView commentFriendName;
		TextView commentContent;
		TextView commentDate;
	}

	public void deleteComment(){
		mDeleteTask = new DeleteCommentTask();
		mDeleteTask.execute();
	}
	
	private class DeleteCommentTask extends AsyncTask<Void, Void, Boolean>{

		private ProgressDialog pDialog;

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(context);
			pDialog.setMessage("Please wait...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(false);
			pDialog.show();
		}

		@Override
		protected Boolean doInBackground(Void... params) {
			// TODO Auto-generated method stub
			HttpClient a = new HttpClient();
			
			CommentModel onecomment = new CommentModel();
			onecomment.setCommentId(1);
			onecomment.setPostId(1);
			onecomment.setParentId(1);
			onecomment.setCommentContent("");
			onecomment.setCommentDate("");
			a.deleteComment(onecomment);

			return true;
		}

		protected void onPostExecute(Boolean value){
			pDialog.dismiss();
			
		}

	}
	
}
