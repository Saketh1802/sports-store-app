package com.springtut.sports.services;

import com.springtut.sports.model.Sales;
import com.springtut.sports.model.WarrantyClaims;

public interface WarrClaims {
	public WarrantyClaims isClaimed(Sales sale);
	public String expiryDate(Sales sale);
}
