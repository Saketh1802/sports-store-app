package com.springtut.sports.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

@Entity
public class Brand {
	@Id
	@GeneratedValue
	private int id;
	@NotNull
	private String name;
	@OneToMany(fetch = FetchType.LAZY, mappedBy="brand", cascade=CascadeType.ALL)
	private List<Product> products;
	public Brand() {
		
	}
	public Brand(@NotNull String name) {
		super();
		this.name = name;
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
	public List<Product> getProducts() {
		return products;
	}
	public void setProducts(List<Product> products) {
		this.products = products;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return this.name;
	}
	
}
