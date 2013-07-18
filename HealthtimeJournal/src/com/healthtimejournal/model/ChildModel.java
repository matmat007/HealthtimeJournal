package com.healthtimejournal.model;

import java.sql.Date;

public class ChildModel {
	
	private int childId;
	private String firstName;
	private String lastName;
	private String gender;
	private Date birthdate;
	private String bloodType;
	
	public ChildModel(){
		
	}
	
	public int getChildId() {
		return childId;
	}
	
	public void setChildId(int childId) {
		this.childId = childId;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getGender() {
		return gender;
	}
	
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	public Date getBirthdate() {
		return birthdate;
	}
	
	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}
	
	public String getBloodType() {
		return bloodType;
	}
	
	public void setBloodType(String bloodType) {
		this.bloodType = bloodType;
	}
	
}
