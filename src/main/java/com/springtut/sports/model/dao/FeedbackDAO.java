package com.springtut.sports.model.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.springtut.sports.model.Feedback;

@Repository
public interface FeedbackDAO extends CrudRepository<Feedback, Integer>{

}
