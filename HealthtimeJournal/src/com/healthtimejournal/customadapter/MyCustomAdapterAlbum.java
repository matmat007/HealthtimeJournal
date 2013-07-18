package com.healthtimejournal.customadapter;

import java.util.List;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.healthtimejournal.R;
import com.healthtimejournal.model.LabeledImage;

public class MyCustomAdapterAlbum extends BaseAdapter{
	
	List<LabeledImage> list;
	Context context;
	int height;
	
	public MyCustomAdapterAlbum(Context context, List<LabeledImage> list, int height){
		this.list = list;
		this.context = context;
		this.height = height/3;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return list.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View vi = convertView;
		
		if(vi == null){
			LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			vi = inflater.inflate(R.layout.album_image_layout, parent, false);
		}
		
		TextView text = (TextView) vi.findViewById(R.id.album_name);
		text.setText(list.get(position).getTitle());
		
		Drawable draw = new BitmapDrawable(vi.getResources(), list.get(position).getImg());
		draw.setBounds(0,0, draw.getMinimumWidth(), draw.getMinimumHeight());
		
		ImageView img = (ImageView) vi.findViewById(R.id.album_img);
		img.setImageDrawable(draw);
		//text.setCompoundDrawables(null, draw, null, null);
		
		return vi;
	}

}
