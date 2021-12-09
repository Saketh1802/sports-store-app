package com.springtut.sports.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springtut.sports.model.Supplier;
import com.springtut.sports.model.dao.SupplierDAO;

@Service
public class SupplierServImpl implements SupplierServ{
	@Autowired
	private SupplierDAO suppl;
	@Override
	public List<Supplier> search(String s) {
		List<Supplier> ans = new ArrayList<>();
		if(s == null || s == "") {
			for(Supplier p:this.suppl.findAll()) {
				ans.add(p);
			}
			return ans;
		}
		for(Supplier p:this.suppl.findAll()) {
			if(p.getName().strip().toLowerCase().startsWith(s.toLowerCase()) || 
								String.valueOf(p.getId()).toLowerCase().startsWith(s.toLowerCase()) ||
								p.getContact().toLowerCase().strip().startsWith(s.toLowerCase()) ) {
				ans.add(p);
			}
		}
		return ans;
	}
}
