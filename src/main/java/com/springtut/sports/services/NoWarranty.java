package com.springtut.sports.services;

import org.springframework.beans.factory.annotation.Autowired;

import com.springtut.sports.model.Warranty;
import com.springtut.sports.model.dao.WarrantyDAO;


public class NoWarranty {
	@Autowired
	private WarrantyDAO warranties;
	
	public void init() {
		boolean need = true;
		for(Warranty w: warranties.findAll()) {
			if(w.getNum_of_months() == 0) {
				need = false;
				break;
			}
		}
		if(need) {
			Warranty no = new Warranty();
			no.setNum_of_months(0);
			no.setDescription("NO WARRANTY AVAILABLE");
			warranties.save(no);
		}
	}
}
