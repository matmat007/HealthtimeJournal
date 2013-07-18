package com.healthtimejournal.model;

import android.graphics.Bitmap;



public class LabeledImage {
	
	private int id;
	private String title;
	private Bitmap img;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Bitmap getImg() {
		return img;
	}
	public void setImg(Bitmap img) {
		this.img = img;
	}
	
}
