package com.healthtimejournal.model;

public class ParentSicknessModel {
	
	private int parentSicknessId;
	private Boolean anemia;
	private Boolean asthma;
	private Boolean bleedingDis;
	private Boolean diabetes;
	private Boolean epilepsy;
	private Boolean heartDis;
	private Boolean highBlood;
	private Boolean highCho;
	private Boolean liverDis;
	private Boolean kidneyDis;
	private Boolean nasalAll;
	private Boolean tuberculosis;
	
	public ParentSicknessModel(){
		
	}
		
	public int getParentSicknessId() {
		return parentSicknessId;
	}

	public void setParentSicknessId(int parentSicknessId) {
		this.parentSicknessId = parentSicknessId;
	}

	public Boolean getAnemia() {
		return anemia;
	}
	
	public void setAnemia(Boolean anemia) {
		this.anemia = anemia;
	}
	
	public Boolean getAsthma() {
		return asthma;
	}
	
	public void setAsthma(Boolean asthma) {
		this.asthma = asthma;
	}
	
	public Boolean getBleedingDis() {
		return bleedingDis;
	}
	
	public void setBleedingDis(Boolean bleedingDis) {
		this.bleedingDis = bleedingDis;
	}
	
	public Boolean getDiabetes() {
		return diabetes;
	}
	
	public void setDiabetes(Boolean diabetes) {
		this.diabetes = diabetes;
	}
	
	public Boolean getEpilepsy() {
		return epilepsy;
	}
	
	public void setEpilepsy(Boolean epilepsy) {
		this.epilepsy = epilepsy;
	}
	
	public Boolean getHeartDis() {
		return heartDis;
	}
	
	public void setHeartDis(Boolean heartDis) {
		this.heartDis = heartDis;
	}
	
	public Boolean getHighBlood() {
		return highBlood;
	}
	
	public void setHighBlood(Boolean highBlood) {
		this.highBlood = highBlood;
	}
	
	public Boolean getHighCho() {
		return highCho;
	}
	
	public void setHighCho(Boolean highCho) {
		this.highCho = highCho;
	}
	
	public Boolean getLiverDis() {
		return liverDis;
	}
	
	public void setLiverDis(Boolean liverDis) {
		this.liverDis = liverDis;
	}
	
	public Boolean getKidneyDis() {
		return kidneyDis;
	}
	
	public void setKidneyDis(Boolean kidneyDis) {
		this.kidneyDis = kidneyDis;
	}
	
	public Boolean getNasalAll() {
		return nasalAll;
	}
	
	public void setNasalAll(Boolean nasalAll) {
		this.nasalAll = nasalAll;
	}
	
	public Boolean getTuberculosis() {
		return tuberculosis;
	}
	
	public void setTuberculosis(Boolean tuberculosis) {
		this.tuberculosis = tuberculosis;
	}

}
