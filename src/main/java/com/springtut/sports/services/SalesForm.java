package com.springtut.sports.services;

import java.util.List;

import com.springtut.sports.model.Sales;
import com.springtut.sports.model.Transaction;

public class SalesForm {
	private Transaction transaction;
	private List<Sales> sale;
	public List<Sales> getSale() {
		return sale;
	}
	public void setSale(List<Sales> sale) {
		this.sale = sale;
	}
	public Transaction getTransaction() {
		return transaction;
	}
	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}
	
}
