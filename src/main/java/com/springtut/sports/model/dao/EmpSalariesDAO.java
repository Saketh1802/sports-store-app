package com.springtut.sports.model.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.springtut.sports.model.EmpSalaries;

@Repository
public interface EmpSalariesDAO extends CrudRepository<EmpSalaries, Integer>{

}
