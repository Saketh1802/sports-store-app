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
public class Warranty {
	@Id
	@GeneratedValue
	private int id;
	@NotNull
	private int num_of_months;
	private String description;
	@OneToMany(mappedBy="warranty", cascade=CascadeType.ALL, orphanRemoval=true)
	List<Product> products = new ArrayList<>();
	public int getNum_of_months() {
		return num_of_months;
	}
	public void setNum_of_months(int num_of_months) {
		this.num_of_months = num_of_months;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String desc) {
		this.description = desc;
	}
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	public List<Product> getProducts() {
		return products;
	}
	public void setProducts(List<Product> products) {
		this.products = products;
	}
	@Override
	public String toString() {
		return this.num_of_months + " months " + this.description;
	}
	
}
