package com.healthtimejournal.model;

import java.sql.Date;
import java.sql.Time;

public class CommentModel {
	
	private int commentId;
	private int image;
	private String name;
	private String commentContent;
	private Date dateOfComment;
	private Time timeOfComment;
	
	public CommentModel(){
		
	}
	
	public int getCommentId() {
		return commentId;
	}

	public void setCommentId(int commentId) {
		this.commentId = commentId;
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
	
	public String getCommentContent() {
		return commentContent;
	}
	
	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}
	
	public Date getDateOfComment() {
		return dateOfComment;
	}
	
	public void setDateOfComment(Date dateOfComment) {
		this.dateOfComment = dateOfComment;
	}
	
	public Time getTimeOfComment() {
		return timeOfComment;
	}
	
	public void setTimeOfComment(Time timeOfComment) {
		this.timeOfComment = timeOfComment;
	}

}
