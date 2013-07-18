package com.healthtimejournal.model;

public class FriendModel {
	
	private int friendId;
	private int friendImage;
	private String name;
	private String desc;
	private int privilege;
	
	public FriendModel(){
		
	}
	
	public int getFriendId() {
		return friendId;
	}

	public void setFriendId(int friendId) {
		this.friendId = friendId;
	}

	public int getFriendImage() {
		return friendImage;
	}
	
	public void setFriendImage(int friendImage) {
		this.friendImage = friendImage;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getDesc() {
		return desc;
	}
	
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	public int getPrivilege() {
		return privilege;
	}
	
	public void setPrivilege(int privilege) {
		this.privilege = privilege;
	}

}
