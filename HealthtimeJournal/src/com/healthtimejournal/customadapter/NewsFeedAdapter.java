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
import com.healthtimejournal.model.PostModel;

public class NewsFeedAdapter extends BaseAdapter {

	private static LayoutInflater inflater = null;
	 
	private ArrayList<PostModel> setPost = init();
	
	public ArrayList<PostModel> init(){
		
		ArrayList<PostModel> tempList = new ArrayList<PostModel>();
		
//		PostModel onePost = new PostModel();
//		onePost.setImage(R.drawable.ic_launcher);
//		onePost.setName("Joey Bing");
//		onePost.setPostContent("Bing Bong");
//		onePost.setDateOfPost(null);
//	   	tempList.add(onePost);
//	   	
//	   	onePost = new PostModel();
//		onePost.setImage(R.drawable.ic_launcher);
//		onePost.setName("Joey Bing");
//		onePost.setPostContent("Bing Bong");
//		onePost.setDateOfPost(null);
//	   	tempList.add(onePost);
	
	   	return tempList;
	}
	
	public NewsFeedAdapter(Context context) {
		inflater = LayoutInflater.from(context);
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		
		ViewHolder holder;

	    View vi = convertView;
	    
	    if(convertView == null) {
	        vi = inflater.inflate(R.layout.news_feed_one, null);
	        
	        holder = new ViewHolder();
	        
	        holder.postStatus = (TextView)vi.findViewById(R.id.newsFeedPostStatus);
	        holder.friendImage = (ImageView)vi.findViewById(R.id.newsFeedFriendImage); 
	        holder.friendName = (TextView)vi.findViewById(R.id.newsFeedFriendName); 
	        holder.postContent = (TextView)vi.findViewById(R.id.newsFeedPostContent); 
	        
	        holder.postStatus.setText("blahblah");
//	        holder.friendImage.setImageResource(setPost.get(position).getImage());
//	        holder.friendName.setText(setPost.get(position).getName().toString());
	        holder.postContent.setText(setPost.get(position).getPostContent().toString());
	    }
	    
	    return vi;
	 }

	public int getCount() {
		// TODO Auto-generated method stub
		return setPost.size();

	}

	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return setPost.get(position);

	}

	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;

	}
	
	static class ViewHolder {
		TextView postStatus;
		ImageView friendImage;
		TextView friendName;
		TextView postContent;
	}  
}
