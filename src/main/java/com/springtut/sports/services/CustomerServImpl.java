package com.springtut.sports.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springtut.sports.model.Customer;
import com.springtut.sports.model.dao.CustomerDAO;

@Service
public class CustomerServImpl implements CustomerServ {
	@Autowired
	private CustomerDAO cust;
	@Override
	public List<Customer> search(String s) {
		List<Customer> ans = new ArrayList<>();
		if(s == null || s == "") {
			for(Customer p:this.cust.findAll()) {
				ans.add(p);
			}
			return ans;
		}
		for(Customer p:this.cust.findAll()) {
			if(p.getName().strip().toLowerCase().startsWith(s.toLowerCase()) || 
								String.valueOf(p.getId()).toLowerCase().startsWith(s.toLowerCase()) ||
								p.getPhone().toLowerCase().strip().startsWith(s.toLowerCase())) {
				ans.add(p);
			}
		}
		return ans;
	}
}
