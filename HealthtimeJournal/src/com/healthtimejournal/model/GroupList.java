package com.healthtimejournal.model;

import java.util.List;

public class GroupList {
	private String name;
	private List<ChildList> list;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<ChildList> getList() {
		return list;
	}
	public void setList(List<ChildList> list) {
		this.list = list;
	}
	
	
}
