package com.healthtimejournal.model;


public class ParentPrivilegeModel {
	
	private SharingModel share;
	private ParentModel parent;
	
	public SharingModel getShare() {
		return share;
	}
	public void setShare(SharingModel share) {
		this.share = share;
	}
	public ParentModel getParents() {
		return parent;
	}
	public void setParents(ParentModel parent) {
		this.parent = parent;
	}
}
