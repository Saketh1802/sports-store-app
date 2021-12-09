package com.springtut.sports.model.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.springtut.sports.model.WarrantyClaims;

@Repository
public interface WarrantyClaimsDAO extends CrudRepository<WarrantyClaims, Integer>{

}
