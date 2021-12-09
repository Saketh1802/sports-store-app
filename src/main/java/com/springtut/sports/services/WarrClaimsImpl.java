package com.springtut.sports.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springtut.sports.model.Sales;
import com.springtut.sports.model.WarrantyClaims;
import com.springtut.sports.model.dao.WarrantyClaimsDAO;

@Service
public class WarrClaimsImpl implements WarrClaims{
	@Autowired
	private WarrantyClaimsDAO claims;
	@Override
	public WarrantyClaims isClaimed(Sales sale) {
		for(WarrantyClaims wc: claims.findAll()) {
			if(wc.getSale().getTransaction().getId() == sale.getTransaction().getId() && wc.getProduct().getId() == sale.getProduct().getId()) {
				return wc;
			}
		}
		
		return null;
	}
	@Override
	public String expiryDate(Sales sale) {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			cal.setTime(sdf.parse(sale.getTransaction().getDate()));
			cal.add(Calendar.MONTH, sale.getProduct().getWarranty().getNum_of_months());
			String ans = "" +
					cal.get(Calendar.YEAR) + "-" +
					((cal.get(Calendar.MONTH) + 1 > 9)?"":"0") + (cal.get(Calendar.MONTH) + 1) + "-" +
					((cal.get(Calendar.DAY_OF_MONTH) + 1 > 9)?"":"0") + cal.get(Calendar.DAY_OF_MONTH);
			return ans;
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return "error occured";
	}
	
}

