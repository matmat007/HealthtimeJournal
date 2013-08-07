package com.healthtimejournal.model;

public class GalleryModel {
	
	private int galleryId;
	private int parentId;
	private String filename;
	
	public int getGalleryId() {
		return galleryId;
	}
	
	public void setGalleryId(int galleryId) {
		this.galleryId = galleryId;
	}
	
	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
	
	public String getFilename() {
		return filename;
	}
	
	public void setFilename(String filename) {
		this.filename = filename;
	}
	
}
