package com.springtut.sports.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
public class WarrantyClaims {
	@Id
	@GeneratedValue
	private int id;
	@ManyToOne
	@JoinColumn()
	private Sales sale;
	@ManyToOne
	@JoinColumn()
	private Product product;
	@ManyToOne
	@JoinColumn()
	private Customer customer;
	@NotNull
	private String claim_date;
	private String remarks;
	
	public Sales getSale() {
		return sale;
	}
	public void setSale(Sales sale) {
		this.sale = sale;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public String getClaim_date() {
		return claim_date;
	}
	public void setClaim_date(String claim_date) {
		this.claim_date = claim_date;
	}
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
}
