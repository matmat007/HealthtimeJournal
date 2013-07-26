package com.healthtimejournal.customadapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.healthtimejournal.R;
import com.healthtimejournal.model.CommentModel;
import com.healthtimejournal.service.Base64Decoder;

public class CommentListAdapter extends BaseAdapter {
	
	private static LayoutInflater inflater = null;
	Base64Decoder decoder;
	
	List<CommentModel> items;
	Context context;
	
	public CommentListAdapter(Context context,  List<CommentModel> items) {
		inflater = LayoutInflater.from(context);
		this.context = context;
		this.items = items;
	}

	public View getView(int position, View convertView, ViewGroup parent) {

	    View vi = convertView;
	    
	    final RelativeLayout mainLayout;
		final ImageView commentFriendImage;
		final TextView commentFriendName;
		final TextView commentContent;
		final TextView commentDate;
	    
	    if(convertView == null) {
	        vi = inflater.inflate(R.layout.post_page_comment_one, null);
	        
	        commentFriendImage = (ImageView)vi.findViewById(R.id.commentFriendImage); 
	        commentFriendName = (TextView)vi.findViewById(R.id.commentFriendName); 
	        commentContent = (TextView)vi.findViewById(R.id.commentContent); 
	        commentDate = (TextView)vi.findViewById(R.id.commentDate); 
	        
	        commentFriendImage.setImageBitmap(decoder.decodeBase64(items.get(position).getImage().toString()));
	        commentFriendName.setText(items.get(position).getParentFirstName().toString() + " " + items.get(position).getParentLastName().toString());
	        commentContent.setText(items.get(position).getCommentContent().toString());
	        commentDate.setText(items.get(position).getCommentDate().toString());
	        
	        mainLayout = (RelativeLayout)vi.findViewById(R.id.mainLayout);
	        mainLayout.setOnLongClickListener(new OnLongClickListener() {
	            public boolean onLongClick(View v) {
	            	deleteComment(commentContent.getText().toString());
	                return true;
	            }
	        });
	    }
	    
	    return vi;
	 }

	public int getCount() {
		// TODO Auto-generated method stub
		return items.size();

	}

	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return items.get(position);

	}

	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;

	}
	
	public void deleteComment(String a){
		Toast.makeText(context, a, Toast.LENGTH_SHORT).show();
	}

}
