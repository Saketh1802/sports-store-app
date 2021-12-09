package com.springtut.sports.services;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springtut.sports.model.Roles;
import com.springtut.sports.model.User;
import com.springtut.sports.model.dao.UserDAO;

@Service
public class AuthImpl implements Auth{
	@Autowired
	private UserDAO users;

	@Override
	public boolean isManager(HttpSession ssn) {
		User u = getUser(ssn);
		System.out.println(u.getRole());
		return (u.getRole() == Roles.ADMIN) || (u.getRole() == Roles.MANAGER);
	}

	@Override
	public boolean isAdmin(HttpSession ssn) {
		User usr = getUser(ssn);
		return usr.getRole() == Roles.ADMIN;
	}

	@Override
	public boolean isEmp(HttpSession ssn) {
		
		return ssn.getAttribute("user_n") != null;
	}

	@Override
	public boolean isValid(User usr) {
		for(User u: this.users.findAll()) {
			if(u.getName().compareTo(usr.getName()) == 0 && usr.getPasswd().compareTo(u.getPasswd()) == 0) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean exists(String name) {
		for(User u: this.users.findAll()) {
			if(u.getName() == null) {
				System.err.println("FK");
			}
			if(u.getName().compareTo(name) == 0)
				return true;
		}
		return false;
	}
	@Override
	public User getUser(HttpSession ssn) {
		String name = (String) ssn.getAttribute("user_n");
		String passwd = (String) ssn.getAttribute("user_passwd");
		
		for(User u: this.users.findAll()) {
			if(u.getName().compareTo(name) == 0)
				return u;
		}
		System.err.println("OHH NO");
		return null;
	}
	

}
