package com.springtut.sports.model.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.springtut.sports.model.Employee;

@Repository
public interface EmployeeDAO extends CrudRepository<Employee, Integer>{

}

