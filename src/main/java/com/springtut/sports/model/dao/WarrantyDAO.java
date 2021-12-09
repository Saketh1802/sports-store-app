package com.springtut.sports.model.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.springtut.sports.model.Warranty;

@Repository
public interface WarrantyDAO extends CrudRepository<Warranty, Integer>{
		// public boolean existsByNum_of_monthsByDescription(@Param("num_of_months")int num_of_months);
}
