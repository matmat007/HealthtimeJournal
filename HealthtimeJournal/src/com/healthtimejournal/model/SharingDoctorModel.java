package com.healthtimejournal.model;

public class SharingDoctorModel {
	
	private int sharingDoctorId;
	private int doctorId;
	private int parentId;
	private int childId;
	
	public SharingDoctorModel(){
		
	}

	public int getSharingDoctorId() {
		return sharingDoctorId;
	}

	public void setSharingDoctorId(int sharingDoctorId) {
		this.sharingDoctorId = sharingDoctorId;
	}

	public int getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(int doctorId) {
		this.doctorId = doctorId;
	}

	public int getParentId() {
		return parentId;
	}
	
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	public int getChildId() {
		return childId;
	}

	public void setChildId(int childId) {
		this.childId = childId;
	}

}
