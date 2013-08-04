package com.healthtimejournal.model;

public class Event {

	private int eventId;
	private int toParentId;
	private int fromParentId;
	private int childId;
	private String eventContent;
	private int eventCategory;
	private String eventDate;
	private int fileId;
	
	public int getEventId() {
		return eventId;
	}
	
	public void setEventId(int eventId) {
		this.eventId = eventId;
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
	
	public int getChildId() {
		return childId;
	}
	
	public void setChildId(int childId) {
		this.childId = childId;
	}
	
	public String getEventContent() {
		return eventContent;
	}
	
	public void setEventContent(String eventContent) {
		this.eventContent = eventContent;
	}
	
	public int getEventCategory() {
		return eventCategory;
	}
	
	public void setEventCategory(int eventCategory) {
		this.eventCategory = eventCategory;
	}
	
	public String getEventDate() {
		return eventDate;
	}
	
	public void setEventDate(String eventDate) {
		this.eventDate = eventDate;
	}
	
	public int getFileId() {
		return fileId;
	}
	
	public void setFileId(int fileId) {
		this.fileId = fileId;
	}
	
}
