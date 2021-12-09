package com.springtut.sports.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springtut.sports.model.Brand;
import com.springtut.sports.model.dao.BrandDAO;

@Service
public class BrandServImpl implements BrandServ{
	@Autowired
	private BrandDAO brands;
	@Override
	public List<Brand> search(String s) {
		List<Brand> ans = new ArrayList<>();
		if(s == null || s == "") {
			for(Brand p:this.brands.findAll()) {
				ans.add(p);
			}
			return ans;
		}
		for(Brand p:this.brands.findAll()) {
			if(p.getName().strip().toLowerCase().startsWith(s.toLowerCase()) || 
								String.valueOf(p.getId()).toLowerCase().startsWith(s.toLowerCase()) ) {
				ans.add(p);
			}
		}
		return ans;
	}
}
