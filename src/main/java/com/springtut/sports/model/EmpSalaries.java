package com.springtut.sports.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
public class EmpSalaries {
	@Id
	@GeneratedValue
	private int id;
	@NotNull
	@ManyToOne()
	@JoinColumn()
	private Employee emp;
	@NotNull
	@ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn()
	private Transaction transaction;
	public Employee getEmp() {
		return emp;
	}
	public void setEmp(Employee emp) {
		this.emp = emp;
	}
	public Transaction getTransaction() {
		return transaction;
	}
	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
}
