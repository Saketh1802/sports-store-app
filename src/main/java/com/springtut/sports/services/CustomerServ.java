package com.springtut.sports.services;

import java.util.List;

import com.springtut.sports.model.Customer;

public interface CustomerServ {
	public List<Customer> search(String s);
}
