package com.healthtimejournal.model;

import android.os.Parcel;
import android.os.Parcelable;

public class ParentModel implements Parcelable{
	
	private int parentId;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private String gender;
	private String bloodType;
	private int familyId;
	private int accountStatus;
	private int parentImageId;
	
	public ParentModel(){
		
	}
	
	public ParentModel(Parcel parcel){
		readParcel(parcel);
	}
	
	//getters and setters
	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
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

	public int getAccountStatus() {
		return accountStatus;
	}

	public void setAccountStatus(int accountStatus) {
		this.accountStatus = accountStatus;
	}

	public int getParentImageId() {
		return parentImageId;
	}

	public void setParentImageId(int parentImageId) {
		this.parentImageId = parentImageId;
	}

	//parcelable
	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub
		dest.writeInt(parentId);
		dest.writeString(firstName);
		dest.writeString(lastName);
		dest.writeString(email);
		dest.writeString(password);
		dest.writeString(gender);
		dest.writeString(bloodType);
		dest.writeInt(familyId);
		dest.writeInt(accountStatus);
		dest.writeInt(parentImageId);
		
	}
	
	private void readParcel(Parcel parcel){
		this.parentId = parcel.readInt();
		this.firstName = parcel.readString();
		this.lastName = parcel.readString();
		this.email = parcel.readString();
		this.password = parcel.readString();
		this.gender = parcel.readString();
		this.bloodType = parcel.readString();
		this.familyId = parcel.readInt();
		this.accountStatus = parcel.readInt();
		this.parentImageId = parcel.readInt();
	}
	
	public static final Parcelable.Creator<ParentModel> CREATOR = new Parcelable.Creator<ParentModel>() {

		@Override
		public ParentModel createFromParcel(Parcel source) {
			// TODO Auto-generated method stub
			return new ParentModel(source);
		}

		@Override
		public ParentModel[] newArray(int size) {
			// TODO Auto-generated method stub
			return new ParentModel[size];
		}
	};
}
