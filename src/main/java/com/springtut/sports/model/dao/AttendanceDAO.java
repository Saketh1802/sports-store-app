package com.springtut.sports.model.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.springtut.sports.model.Attendance;

@Repository
public interface AttendanceDAO extends CrudRepository<Attendance, Integer>{
	
}
