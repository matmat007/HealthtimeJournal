package com.healthtimejournal.model;

public class SharingModel {
	
	private int sharingId;
	private int toParentId;
	private int fromFamilyId;
	private int ChildId;
	private int privilege;
	
	public SharingModel(){
		
	}

	public int getSharingId() {
		return sharingId;
	}

	public void setSharingId(int sharingId) {
		this.sharingId = sharingId;
	}

	public int getPrivilege() {
		return privilege;
	}
	
	public void setPrivilege(int privilege) {
		this.privilege = privilege;
	}

	public int getChildId() {
		return ChildId;
	}

	public void setChildId(int childId) {
		ChildId = childId;
	}

	public int getFromFamilyId() {
		return fromFamilyId;
	}

	public void setFromFamilyId(int fromFamilyId) {
		this.fromFamilyId = fromFamilyId;
	}

	public int getToParentId() {
		return toParentId;
	}

	public void setToParentId(int toParentId) {
		this.toParentId = toParentId;
	}

}
