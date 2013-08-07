package com.healthtimejournal.customadapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.healthtimejournal.R;
import com.healthtimejournal.model.ChildModel;
import com.healthtimejournal.service.HttpClient;
import com.healthtimejournal.service.JSONParser;

public class MyChildrenAdapter extends BaseAdapter {

	private static LayoutInflater inflater = null;

	HttpClient client = new HttpClient();
	JSONParser parse = new JSONParser();
	
	private List<ChildModel> setFriend = init();
	
	public List<ChildModel> init() {
		List<ChildModel> temp = parse.getChild(client.retrieve_child_by_family(1));
		return temp; 
	}

	public MyChildrenAdapter(Context context) {
		inflater = LayoutInflater.from(context);
	}

	public View getView(int position, View convertView, ViewGroup parent) {

		ViewHolder holder;

		View vi = convertView;

		if (convertView == null) {
			vi = inflater.inflate(R.layout.friend_list_one, null);

			holder = new ViewHolder();

			holder.friendImage = (ImageView) vi
					.findViewById(R.id.friendListImage);
			holder.friendName = (TextView) vi.findViewById(R.id.friendName);
			holder.friendDesc = (TextView) vi.findViewById(R.id.friendDesc);

//			holder.friendImage.setImageResource(setFriend.get(position)
//					.getFriendImage());
//			holder.friendName.setText(setFriend.get(position).getName()
//					.toString());
//			holder.friendDesc.setText(setFriend.get(position).getDesc()
//					.toString());
		}

		return vi;
	}

	public int getCount() {
		// TODO Auto-generated method stub
		return setFriend.size();

	}

	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return setFriend.get(position);

	}

	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;

	}

	static class ViewHolder {
		ImageView friendImage;
		TextView friendName;
		TextView friendDesc;
	}

}
