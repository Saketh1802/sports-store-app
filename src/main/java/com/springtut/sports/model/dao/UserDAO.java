package com.springtut.sports.model.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.springtut.sports.model.User;

@Repository
public interface UserDAO extends CrudRepository<User, Integer> {

	public User findByName(String username);

}
