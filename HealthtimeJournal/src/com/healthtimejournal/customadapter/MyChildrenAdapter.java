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
import com.healthtimejournal.model.FriendModel;

public class MyChildrenAdapter extends BaseAdapter {

	private static LayoutInflater inflater = null;

	private ArrayList<FriendModel> setFriend = init();

	public ArrayList<FriendModel> init() {

		ArrayList<FriendModel> tempList = new ArrayList<FriendModel>();

		FriendModel oneFriend = new FriendModel();
		oneFriend.setFriendImage(R.drawable.ic_launcher);
		oneFriend.setName("Joey Bing");
		oneFriend.setDesc("Bing Bong");
		oneFriend.setPrivilege(1);
		tempList.add(oneFriend);

		oneFriend = new FriendModel();
		oneFriend.setFriendImage(R.drawable.ic_launcher);
		oneFriend.setName("Joey Bing");
		oneFriend.setDesc("Bing Bong");
		oneFriend.setPrivilege(2);
		tempList.add(oneFriend);

		oneFriend = new FriendModel();
		oneFriend.setFriendImage(R.drawable.ic_launcher);
		oneFriend.setName("Joey Bing");
		oneFriend.setDesc("Bing Bong");
		oneFriend.setPrivilege(3);
		tempList.add(oneFriend);

		oneFriend = new FriendModel();
		oneFriend.setFriendImage(R.drawable.ic_launcher);
		oneFriend.setName("Joey Bing");
		oneFriend.setDesc("Bing Bong");
		oneFriend.setPrivilege(3);
		tempList.add(oneFriend);

		return tempList;
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

			holder.friendImage.setImageResource(setFriend.get(position)
					.getFriendImage());
			holder.friendName.setText(setFriend.get(position).getName()
					.toString());
			holder.friendDesc.setText(setFriend.get(position).getDesc()
					.toString());
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
