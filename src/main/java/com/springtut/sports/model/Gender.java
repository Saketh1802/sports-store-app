package com.springtut.sports.model;

public enum Gender {
	MALE ("Male"),
	FEMALE ("Female"),
	NA ("NA");
	
	public String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	Gender(String name) {
		this.name = name;
	}
}
