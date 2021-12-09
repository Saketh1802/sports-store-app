package com.springtut.sports.services;

import com.springtut.sports.model.Supplierproducts;
import com.springtut.sports.model.Transaction;
public class DealsForm {
	private Transaction transaction;
	private Supplierproducts deal;
	private boolean update = true;
	public DealsForm() {
		
	}
	
	public DealsForm(Transaction transaction, Supplierproducts deal) {
		super();
		this.transaction = transaction;
		this.deal = deal;
	}

	public Transaction getTransaction() {
		return transaction;
	}
	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}
	public Supplierproducts getDeal() {
		return deal;
	}
	public void setDeal(Supplierproducts deal) {
		this.deal = deal;
	}

	public boolean isUpdate() {
		return update;
	}

	public void setUpdate(boolean update) {
		this.update = update;
	}
	
}
