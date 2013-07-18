package com.healthtimejournal.model;

public class SharingModel {
	
	private int sharingId;
	private int toParentId;
	private int fromParentId;
	private int toChildId;
	private int privilege;
	
	public SharingModel(){
		
	}

	public int getSharingId() {
		return sharingId;
	}

	public void setSharingId(int sharingId) {
		this.sharingId = sharingId;
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

	public int getToChildId() {
		return toChildId;
	}

	public void setToChildId(int toChildId) {
		this.toChildId = toChildId;
	}

	public int getPrivilege() {
		return privilege;
	}
	
	public void setPrivilege(int privilege) {
		this.privilege = privilege;
	}

}
