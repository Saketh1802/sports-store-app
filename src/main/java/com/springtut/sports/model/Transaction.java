package com.springtut.sports.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

@Entity
public class Transaction {
	@Id
	@GeneratedValue
	private int id;
	@NotNull
	private String date;
	@NotNull
	private float amount;
	private String comments;
	@OneToMany(mappedBy="transaction", cascade=CascadeType.ALL)
	private List<Supplierproducts> deals = new ArrayList<>();
	@OneToMany(mappedBy="transaction", cascade=CascadeType.ALL)
	private List<Maintenance> maintenances = new ArrayList<>();
	@OneToMany(mappedBy="transaction", cascade=CascadeType.ALL)
	private List<EmpSalaries> salaries = new ArrayList<>();
	@OneToMany(mappedBy="transaction", cascade=CascadeType.ALL)
	private List<Sales> sales = new ArrayList<>();
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public float getAmount() {
		return amount;
	}
	public void setAmount(float amount) {
		this.amount = amount;
	}
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	public List<Supplierproducts> getDeals() {
		return deals;
	}
	public void setDeals(List<Supplierproducts> deals) {
		this.deals = deals;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public List<Maintenance> getMaintenances() {
		return maintenances;
	}
	public void setMaintenances(List<Maintenance> maintenances) {
		this.maintenances = maintenances;
	}
	public List<EmpSalaries> getSalaries() {
		return salaries;
	}
	public void setSalaries(List<EmpSalaries> salaries) {
		this.salaries = salaries;
	}
	public List<Sales> getSales() {
		return sales;
	}
	public void setSales(List<Sales> sales) {
		this.sales = sales;
	}
	
}
