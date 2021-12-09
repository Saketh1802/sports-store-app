package com.springtut.sports.model.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.springtut.sports.model.Sales;

@Repository
public interface SalesDAO extends CrudRepository<Sales, Integer> {

}
