package com.springtut.sports.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springtut.sports.model.Product;
import com.springtut.sports.model.dao.ProductDAO;

@Service
public class ProductServImpl implements ProductServ{
	
	@Autowired
	private ProductDAO products;
	@Override
	public List<Product> search(String s) {
		List<Product> ans = new ArrayList<>();
		if(s == null || s == "") {
			for(Product p:this.products.findAll()) {
				ans.add(p);
			}
			return ans;
		}
		for(Product p:this.products.findAll()) {
			if(p.getName().strip().toLowerCase().startsWith(s.toLowerCase()) || 
								String.valueOf(p.getId()).toLowerCase().startsWith(s.toLowerCase()) ) {
				ans.add(p);
			}
		}
		return ans;
	}
	
}
