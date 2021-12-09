package com.springtut.sports.services;

import javax.servlet.http.HttpSession;

import com.springtut.sports.model.User;

public interface Auth {
	public boolean isManager(HttpSession ssn);
	public boolean isAdmin(HttpSession ssn);
	public boolean isEmp(HttpSession ssn);
	public boolean isValid(User usr);
	public boolean exists(String name);
	public User getUser(HttpSession ssn);
}
