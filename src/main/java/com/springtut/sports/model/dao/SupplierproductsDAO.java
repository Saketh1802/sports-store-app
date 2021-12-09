package com.springtut.sports.model.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.springtut.sports.model.Supplierproducts;

@Repository
public interface SupplierproductsDAO extends CrudRepository<Supplierproducts, Integer>{

}