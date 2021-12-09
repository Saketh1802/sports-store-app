package com.springtut.sports.model;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;


@Entity
public class Employee implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private int id;
	@NotNull
	private String name;
	@NotNull
	public int age = 18;
	@NotNull
	private Gender gender = Gender.MALE;
	private String aadhar = "";
	private String addr = "";
	private String phone = "";
	@NotNull
	private String joinDate;
	private int wage;
	private int workHours = 8;
	@OneToMany(mappedBy="emp", cascade=CascadeType.ALL)
	List<Attendance> attendance = new ArrayList<Attendance>();
	@OneToMany(mappedBy="emp", cascade=CascadeType.ALL)
	List<EmpSalaries> salaries = new ArrayList<>();
	public Employee(){
		
	}
	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	public String getAadhar() {
		return aadhar;
	}
	public void setAadhar(String aadhar) {
		this.aadhar = aadhar;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getJoinDate() {
		return joinDate;
	}
	public void setJoinDate(String joinDate) {
		this.joinDate = joinDate;
	}
	public int getWage() {
		return wage;
	}
	public void setWage(int wage) {
		this.wage = wage;
	}
	public int getWorkHours() {
		return workHours;
	}
	public void setWorkHours(int workHours) {
		this.workHours = workHours;
	}
	public List<Attendance> getAttendance() {
		return attendance;
	}
	public void setAttendance(List<Attendance> attendance) {
		this.attendance = attendance;
	}
	
	public List<EmpSalaries> getSalaries() {
		return salaries;
	}
	public void setSalaries(List<EmpSalaries> salaries) {
		this.salaries = salaries;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	@Override
	public String toString() {
		return this.id + " - " + this.name;
	}
}

