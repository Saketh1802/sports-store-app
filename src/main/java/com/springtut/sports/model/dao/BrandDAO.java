package com.springtut.sports.model.dao;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.springtut.sports.model.Brand;
@Repository
public interface BrandDAO extends CrudRepository<Brand, Integer>{
	public boolean existsByName(String name);
}