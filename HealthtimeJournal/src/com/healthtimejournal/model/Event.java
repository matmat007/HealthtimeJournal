package com.healthtimejournal.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Event implements Parcelable{

	private int eventId;
	private int toParentId;
	private int fromParentId;
	private int childId;
	private String eventContent;
	private int eventCategory;
	private String eventDate;
	private int fileId;
	
	public Event(){
		
	}
	
	public Event(Parcel parcel){
		this.eventId = parcel.readInt();
		this.toParentId = parcel.readInt();
		this.fromParentId = parcel.readInt();
		this.childId = parcel.readInt();
		this.eventContent = parcel.readString();
		this.eventCategory = parcel.readInt();
		this.eventDate = parcel.readString();
		
		}
	
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

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel arg0, int arg1) {
		// TODO Auto-generated method stub
		arg0.writeInt(eventId);
		arg0.writeInt(toParentId);
		arg0.writeInt(fromParentId);
		arg0.writeInt(childId);
		arg0.writeString(eventContent);
		arg0.writeString(eventDate);
		arg0.writeInt(eventCategory);
		arg0.writeInt(fileId);
	}
	
	public static final Parcelable.Creator<Event> CREATOR = new Parcelable.Creator<Event>() {

		@Override
		public Event createFromParcel(Parcel source) {
			// TODO Auto-generated method stub
			return new Event(source);
		}

		@Override
		public Event[] newArray(int size) {
			// TODO Auto-generated method stub
			return new Event[size];
		}
	};
}
