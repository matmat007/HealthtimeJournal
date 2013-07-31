package com.healthtimejournal.model;


public class PostModel {
	
	private int postId;
	private int eventId;
	private int fromParentId;
	private String postContent;
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

	public int getFromParentId() {
		return fromParentId;
	}

	public void setFromParentId(int fromParentId) {
		this.fromParentId = fromParentId;
	}

	public String getPostDate() {
		return postDate;
	}

	public void setPostDate(String postDate) {
		this.postDate = postDate;
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

	public int getEventId() {
		return eventId;
	}

	public void setEventId(int eventId) {
		this.eventId = eventId;
	}

}
