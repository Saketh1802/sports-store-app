package com.springtut.sports.model.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.springtut.sports.model.Customer;

@Repository
public interface CustomerDAO extends CrudRepository<Customer, Integer>{
	
	public boolean existsByNameAndPhone(String name,String phone);
	public Customer findByNameAndPhone(String name,String phone);
}
