package com.springtut.sports.services;

import java.util.List;

import com.springtut.sports.model.Brand;

public interface BrandServ {
	public List<Brand> search(String s);
}
