package com.springtut.sports.model.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.springtut.sports.model.Transaction;

@Repository
public interface TransactionDAO extends CrudRepository<Transaction, Integer>{

}
