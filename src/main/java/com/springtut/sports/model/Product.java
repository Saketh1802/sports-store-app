package com.springtut.sports.model;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;


@Entity
public class Product {
	@Id
	@GeneratedValue
	private int id;
	@NotNull
	private String name;
	@NotNull
	@ManyToOne()
	@JoinColumn()
	private Brand brand;
	@NotNull
	private int quantity;
	@NotNull
	private float cost;
	@OneToOne()
	@JoinColumn()
	private Warranty warranty;
	@OneToMany(mappedBy="product", cascade=CascadeType.ALL)
	private List<Sales> sales = new ArrayList<>();
	@OneToMany(mappedBy="product", cascade=CascadeType.ALL)
	private List<Supplierproducts> deals = new ArrayList<>();
	
	public Brand getBrand() {
		return brand;
	}
	public void setBrand(Brand brand) {
		this.brand = brand;
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
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public float getCost() {
		return cost;
	}
	public void setCost(float cost) {
		this.cost = cost;
	}
	@Override
	public String toString() {
		return this.name + " (brand : " + this.brand.getName() + ")";
	}
	public Warranty getWarranty() {
		return warranty;
	}
	public void setWarranty(Warranty warranty) {
		this.warranty = warranty;
	}
	public List<Sales> getSales() {
		return sales;
	}
	public void setSales(List<Sales> sales) {
		this.sales = sales;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
}
