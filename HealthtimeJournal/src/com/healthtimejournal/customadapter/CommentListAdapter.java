package com.healthtimejournal.customadapter;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.healthtimejournal.R;
import com.healthtimejournal.R.drawable;
import com.healthtimejournal.R.id;
import com.healthtimejournal.R.layout;
import com.healthtimejournal.model.CommentModel;

public class CommentListAdapter extends BaseAdapter {
	
	private static LayoutInflater inflater = null;
	 
	private ArrayList<CommentModel> setComment = init();
	
	public ArrayList<CommentModel> init(){
		
		ArrayList<CommentModel> tempList = new ArrayList<CommentModel>();
		
		CommentModel oneComment = new CommentModel();
		oneComment.setImage(R.drawable.ic_launcher);
		oneComment.setName("Joey Bing");
		oneComment.setCommentContent("Bing Bong");
	   	tempList.add(oneComment);
	   	
	   	oneComment = new CommentModel();
		oneComment.setImage(R.drawable.ic_launcher);
		oneComment.setName("Joey Bing");
		oneComment.setCommentContent("Bing Bong");
	   	tempList.add(oneComment);
	    
	   	oneComment = new CommentModel();
		oneComment.setImage(R.drawable.ic_launcher);
		oneComment.setName("Joey Bing");
		oneComment.setCommentContent("Bing Bong");
	   	tempList.add(oneComment);
	
	   	oneComment = new CommentModel();
		oneComment.setImage(R.drawable.ic_launcher);
		oneComment.setName("Joey Bing");
		oneComment.setCommentContent("Bing Bong");
	   	tempList.add(oneComment);
	
	   	return tempList;
	}
	
	public CommentListAdapter(Context context) {
		inflater = LayoutInflater.from(context);
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		
		ViewHolder holder;

	    View vi = convertView;
	    
	    if(convertView == null) {
	        vi = inflater.inflate(R.layout.post_page_comment_one, null);
	        
	        holder = new ViewHolder();
	        
	        holder.commentFriendImage = (ImageView)vi.findViewById(R.id.commentFriendImage); 
	        holder.commentFriendName = (TextView)vi.findViewById(R.id.commentFriendName); 
	        holder.commentContent = (TextView)vi.findViewById(R.id.commentContent); 
	        holder.commentDate = (TextView)vi.findViewById(R.id.commentDate); 
	        
	        holder.commentFriendImage.setImageResource(setComment.get(position).getImage());
	        holder.commentFriendName.setText(setComment.get(position).getName().toString());
	        holder.commentContent.setText(setComment.get(position).getCommentContent().toString());
	        holder.commentDate.setText("1/1/01");
	    }
	    
	    return vi;
	 }

	public int getCount() {
		// TODO Auto-generated method stub
		return setComment.size();

	}

	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return setComment.get(position);

	}

	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;

	}
	
	static class ViewHolder {            
		ImageView commentFriendImage;
		TextView commentFriendName;
		TextView commentContent;
		TextView commentDate;
	}  

}
