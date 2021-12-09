package com.springtut.sports.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
public class Attendance {
	@Id
	@GeneratedValue
	private int id;
	@NotNull
	private String date;
	@ManyToOne
	@JoinColumn()
	private Employee emp;
	private boolean status;
	public Attendance() {
		
	}
	
	public Attendance(String date, Employee emp, boolean status) {
		super();
		this.date = date;
		this.emp = emp;
		this.status = status;
	}

	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public Employee getEmp() {
		return emp;
	}
	public void setEmp(Employee emp) {
		this.emp = emp;
	}
	public boolean getStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	
}
