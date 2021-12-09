package com.springtut.sports.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Supplier {
	@Id
	@GeneratedValue
	private int id;
	@NotNull
	private String name;
	@NotNull
	private String addr;
	@NotNull
	private String contact;
	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return this.name + " @ " + this.contact + " @ " + this.addr;
	}
}
