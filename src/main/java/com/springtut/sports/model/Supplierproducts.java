package com.springtut.sports.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Supplierproducts {
	@Id
	@GeneratedValue
	private int id;
	@ManyToOne()
    @JoinColumn(name = "supplierid")
	private Supplier supplier;
	@ManyToOne()
    @JoinColumn(name = "productid")
	private Product product;
	private int quantity;
	private String invoice;
	private float price;
	@ManyToOne()
    @JoinColumn(name = "transactionid")
	private Transaction transaction;
	private String recieved;
	public Supplierproducts() {
		
	}
	public Supplierproducts(Supplier supplier, Product product, int quantity) {
		super();
		this.supplier = supplier;
		this.product = product;
		this.quantity = quantity;
	}
	
	public int getId() {
		return id;
	}
	public Supplier getSupplier() {
		return supplier;
	}
	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public float getPrice() {
		return price;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public String getRecieved() {
		return recieved;
	}
	public void setRecieved(String recieved) throws Exception{
		this.recieved = recieved;
				//(new SimpleDateFormat("dd-MM-yyyy").parse(recieved));
	}
	public String getInvoice() {
		return invoice;
	}
	public void setInvoice(String invoice) {
		this.invoice = invoice;
	}
	public Transaction getTransaction() {
		return transaction;
	}
	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}
	
	
}
