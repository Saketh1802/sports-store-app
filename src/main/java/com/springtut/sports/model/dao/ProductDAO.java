package com.springtut.sports.model.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.springtut.sports.model.Brand;
import com.springtut.sports.model.Product;
import com.springtut.sports.model.Warranty;

@Repository
public interface ProductDAO extends CrudRepository<Product, Integer>{
	
	public boolean existsByNameAndCostAndBrandAndWarranty(String name , float cost,Brand brand,Warranty warranty);
	public Product findByNameAndCostAndBrandAndWarranty(String name , float cost,Brand brand,Warranty warranty);
	public Product findByNameAndBrand(String name,Brand brand);
	
}
