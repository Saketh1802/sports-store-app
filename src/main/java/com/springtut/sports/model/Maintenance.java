package com.springtut.sports.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
public class Maintenance {
	@Id
	@GeneratedValue
	private int id;
	private String towards;
	@NotNull
	@ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "transactionid")
	private Transaction transaction;
}
