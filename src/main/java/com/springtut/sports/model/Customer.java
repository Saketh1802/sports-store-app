package com.springtut.sports.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Entity
public class Customer {
	@Id
	@GeneratedValue
	private int id;
	@NotNull
	private String name;
	@NotNull
	private Gender gender = Gender.MALE;
	private String addr;
	@Email
	private String mail;
	private String phone;
	@OneToMany(mappedBy="customer", cascade=CascadeType.ALL, orphanRemoval=true)
	List<Feedback> feedback = new ArrayList<>();
	@OneToMany(mappedBy="customer", cascade=CascadeType.ALL, orphanRemoval=true)
	List<WarrantyClaims> warrantyclaims = new ArrayList<>();
	@OneToMany(mappedBy="customer", cascade=CascadeType.ALL, orphanRemoval=true)
	private List<Sales> sales = new ArrayList<>();
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
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public List<Feedback> getFeedback() {
		return feedback;
	}
	public void setFeedback(List<Feedback> feedback) {
		this.feedback = feedback;
	}
	public List<WarrantyClaims> getWarrantyclaims() {
		return warrantyclaims;
	}
	public void setWarrantyclaims(List<WarrantyClaims> warrantyclaims) {
		this.warrantyclaims = warrantyclaims;
	}
	public List<Sales> getSales() {
		return sales;
	}
	public void setSales(List<Sales> sales) {
		this.sales = sales;
	}
	public int getId() {
		return id;
	}
	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	@Override
	public String toString() {
		return this.name + " (Ph:" + this.phone +")";
	}
}
