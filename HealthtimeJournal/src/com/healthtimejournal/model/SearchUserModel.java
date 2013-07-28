package com.healthtimejournal.model;

import java.util.List;

public class SearchUserModel {
	
	private List<ParentModel> parentList;
	private List<ChildModel> childList;
	private List<DoctorModel> doctorList;
	
	public List<ParentModel> getParentList() {
		return parentList;
	}
	
	public void setParentList(List<ParentModel> parentList) {
		this.parentList = parentList;
	}
	
	public List<ChildModel> getChildList() {
		return childList;
	}
	
	public void setChildList(List<ChildModel> childList) {
		this.childList = childList;
	}
	
	public List<DoctorModel> getDoctorList() {
		return doctorList;
	}
	
	public void setDoctorList(List<DoctorModel> doctorList) {
		this.doctorList = doctorList;
	}
	
}
