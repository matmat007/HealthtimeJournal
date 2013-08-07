package com.healthtimejournal.model;

import java.util.List;

public class Account {
	private String title;
	private List<ChildModel> accounts;
	
	public String getName() {
		return title;
	}
	public void setName(String title) {
		this.title = title;
	}
	public List<ChildModel> getAccounts() {
		return accounts;
	}
	public void setAccounts(List<ChildModel> accounts) {
		this.accounts = accounts;
	}
}
