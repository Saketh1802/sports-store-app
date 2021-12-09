package com.springtut.sports.model;

public enum Roles {
	EMPLOYEE ("EMPLOYEE"),
	MANAGER ("MANAGER"),
	ADMIN ("ADMIN"),
	NA ("NA");
	
	public String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	Roles(String name) {
		this.name = name;
	}
}
