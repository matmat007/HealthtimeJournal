package com.healthtimejournal.model;

public class DiseaseDictionaryModel {
	
	private int diseaseDictionaryId;
	private String name;
	private String description;
	private String symptom;
	private String treatment;
	
	public int getDiseaseDictionaryId() {
		return diseaseDictionaryId;
	}
	public void setDiseaseDictionaryId(int diseaseDictionaryId) {
		this.diseaseDictionaryId = diseaseDictionaryId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getSymptom() {
		return symptom;
	}
	public void setSymptom(String symptom) {
		this.symptom = symptom;
	}
	public String getTreatment() {
		return treatment;
	}
	public void setTreatment(String treatment) {
		this.treatment = treatment;
	}
}
