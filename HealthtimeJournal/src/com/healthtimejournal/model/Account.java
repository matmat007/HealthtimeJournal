package com.healthtimejournal.model;

import java.util.List;

public class Account {
	private int name;
	private List<LabeledImage> accounts;
	
	public int getName() {
		return name;
	}
	public void setName(int name) {
		this.name = name;
	}
	public List<LabeledImage> getAccounts() {
		return accounts;
	}
	public void setAccounts(List<LabeledImage> accounts) {
		this.accounts = accounts;
	}
}
