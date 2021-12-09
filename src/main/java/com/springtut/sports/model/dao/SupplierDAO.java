package com.springtut.sports.model.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.springtut.sports.model.Supplier;
@Repository
public interface SupplierDAO extends CrudRepository<Supplier, Integer>{
	public boolean existsByNameAndContactAndAddr(String name,String contact,String addr);
}
