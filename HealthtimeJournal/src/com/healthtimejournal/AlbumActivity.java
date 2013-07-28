package com.healthtimejournal;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.graphics.Point;
import android.os.Bundle;
import android.widget.GridView;

import com.healthtimejournal.customadapter.MyCustomAdapterAlbum;
import com.healthtimejournal.model.LabeledImage;

public class AlbumActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.album_page_layout);
		
		//for debug purpose
		List<LabeledImage> list = new ArrayList<LabeledImage>();
		for(int i = 0; i < 3; i++){
			LabeledImage one = new LabeledImage();
			one.setId(i);
			one.setTitle("Sample text when text is too long blah blah blah sample text");
			one.setImg("");
			list.add(one);
		}
		
		Point size = new Point();
		getWindowManager().getDefaultDisplay().getSize(size);
		GridView grid = (GridView) findViewById(R.id.album_grid);
		grid.setAdapter(new MyCustomAdapterAlbum(this, list, size.y));
	}

}
