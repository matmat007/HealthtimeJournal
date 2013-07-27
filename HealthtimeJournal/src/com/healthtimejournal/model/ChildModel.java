package com.healthtimejournal.model;

public class ChildModel {
	
	private int childId;
	private String firstName;
	private String lastName;
	private String gender;
	private String birthdate;
	private String bloodType;
	private int familyId;
	private int childImageId;
	private String image;

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
	
	public String getBirthdate() {
		return birthdate;
	}
	
	public void setBirthdate(String string) {
		this.birthdate = string;
	}
	
	public String getBloodType() {
		return bloodType;
	}
	
	public void setBloodType(String bloodType) {
		this.bloodType = bloodType;
	}
	
	public int getFamilyId() {
		return familyId;
	}

	public void setFamilyId(int familyId) {
		this.familyId = familyId;
	}

	public int getChildImageId() {
		return childImageId;
	}

	public void setChildImageId(int childImageId) {
		this.childImageId = childImageId;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
	
}
