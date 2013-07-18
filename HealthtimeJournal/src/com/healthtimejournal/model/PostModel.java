package com.healthtimejournal.model;

import java.sql.Time;
import java.sql.Date;

public class PostModel {
	
	private int postId;
	private int image;
	private String name;
	private String postContent;
	private Date dateOfPost;
	private Time timeOfPost;
	
	public PostModel(){
		
	}
	
	public int getPostId() {
		return postId;
	}

	public void setPostId(int postId) {
		this.postId = postId;
	}

	public int getImage() {
		return image;
	}
	
	public void setImage(int image) {
		this.image = image;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getPostContent() {
		return postContent;
	}
	
	public void setPostContent(String postContent) {
		this.postContent = postContent;
	}
	
	public Date getDateOfPost() {
		return dateOfPost;
	}
	
	public void setDateOfPost(Date dateOfPost) {
		this.dateOfPost = dateOfPost;
	}
	
	public Time getTimeOfPost() {
		return timeOfPost;
	}
	
	public void setTimeOfPost(Time timeOfPost) {
		this.timeOfPost = timeOfPost;
	}

}
