package com.springtut.sports.services;

import com.springtut.sports.model.EmpSalaries;
import com.springtut.sports.model.Transaction;

public class SalaryForm {
	private EmpSalaries salary;
	private Transaction transaction;
	public EmpSalaries getSalary() {
		return salary;
	}
	public void setSalary(EmpSalaries salary) {
		this.salary = salary;
	}
	public Transaction getTransaction() {
		return transaction;
	}
	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}
	
}
