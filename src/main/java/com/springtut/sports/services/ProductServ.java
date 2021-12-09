package com.springtut.sports.services;

import java.util.List;

import com.springtut.sports.model.Product;

public interface ProductServ {
	public List<Product> search(String s);
}
