package com.healthtimejournal.model;


public class PostModel {
	
	private int postId;
	private int toParentId;
	private int fromParentId;
	private int fromParentImage;
	private int childId;
	private String fromParentName;
	private String postContent;
	private int postCategory;
	private String postDate;
	private int fileId;
	
	public PostModel(){
		
	}
	
	public int getPostId() {
		return postId;
	}

	public void setPostId(int postId) {
		this.postId = postId;
	}
	
	public int getToParentId() {
		return toParentId;
	}

	public void setToParentId(int toParentId) {
		this.toParentId = toParentId;
	}

	public int getFromParentId() {
		return fromParentId;
	}

	public void setFromParentId(int fromParentId) {
		this.fromParentId = fromParentId;
	}

	public int getFromParentImage() {
		return fromParentImage;
	}

	public void setFromParentImage(int fromParentImage) {
		this.fromParentImage = fromParentImage;
	}

	public String getFromParentName() {
		return fromParentName;
	}

	public void setFromParentName(String fromParentName) {
		this.fromParentName = fromParentName;
	}
	
	public int getChildId() {
		return childId;
	}

	public void setChildId(int childId) {
		this.childId = childId;
	}

	public String getPostDate() {
		return postDate;
	}

	public void setPostDate(String postDate) {
		this.postDate = postDate;
	}
	
	public int getPostCategory() {
		return postCategory;
	}

	public void setPostCategory(int postCategory) {
		this.postCategory = postCategory;
	}

	public String getPostContent() {
		return postContent;
	}
	
	public void setPostContent(String postContent) {
		this.postContent = postContent;
	}
	
	public int getFileId() {
		return fileId;
	}
	
	public void setFileId(int fileId) {
		this.fileId = fileId;
	}

}
