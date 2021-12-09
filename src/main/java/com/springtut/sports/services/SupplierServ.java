package com.springtut.sports.services;

import java.util.List;

import com.springtut.sports.model.Supplier;

public interface SupplierServ {
	public List<Supplier> search(String s);
}
