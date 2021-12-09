package com.springtut.sports.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springtut.sports.model.Supplier;
import com.springtut.sports.model.Supplierproducts;
import com.springtut.sports.model.dao.SupplierDAO;
import com.springtut.sports.model.dao.SupplierproductsDAO;
import com.springtut.sports.services.Auth;
import com.springtut.sports.services.SearchForm;
import com.springtut.sports.services.SupplierServ;

@Controller
@RequestMapping("/suppliers/")
public class SupplierController {
	String folder = "suppliers/";
	@Autowired
	private SupplierDAO suppliers;
	@Autowired
	private SupplierproductsDAO deals;
	@Autowired
	private SupplierServ supplierServ;
	@Autowired
	private Auth auth;
	@GetMapping("/")
	public String all(Model model, HttpSession ssn) {
		if(!auth.isManager(ssn)) {
			model.addAttribute("msg", "This page is accessible only to the Admin and Manager");
			return "msg";
		}

		model.addAttribute("srch", new SearchForm());
		model.addAttribute("suppliers", suppliers.findAll());
		return folder + "all";
	}
	@GetMapping("/{id}")
	public String one(Model model, @PathVariable int id, HttpSession ssn) {
		if(!auth.isManager(ssn)) {
			model.addAttribute("msg", "This page is accessible only to the Admin and Manager");
			return "msg";
		}
		model.addAttribute("supplier", suppliers.findById(id).get());
		List<Supplierproducts> dealsbyid = new ArrayList<>();
		for(Supplierproducts deal : deals.findAll()) {
			if(deal.getSupplier().getId() == id) {
				dealsbyid.add(deal);
			}
		}
		model.addAttribute("deals", dealsbyid);
		return folder + "one";
	}
	@GetMapping("/add")
	public String add(Model model, HttpSession ssn) {
		if(!auth.isManager(ssn)) {
			model.addAttribute("msg", "This page is accessible only to the Admin and Manager");
			return "msg";
		}
		model.addAttribute("supplier", new Supplier());
		return folder + "add";
	}
	
	@PostMapping("/add") 
	public String add(Model model, @ModelAttribute @Valid Supplier supplier, Errors err, HttpSession ssn){
		if(!auth.isManager(ssn)) {
			model.addAttribute("msg", "This page is accessible only to the Admin and Manager");
			return "msg";
		}
		if(err.hasErrors()) {
			for(ObjectError e:err.getAllErrors()) {
				System.err.println("err  " + e.getObjectName() + " " + e.getDefaultMessage());
			}
			return add(model, ssn);
		}
		
		if(!suppliers.existsByNameAndContactAndAddr(supplier.getName(), supplier.getContact(), supplier.getAddr()))
			suppliers.save(supplier);
		return "redirect:/suppliers/";
	}
	
	@GetMapping("{id}/edit")
	public String edit(Model model, @PathVariable int id, HttpSession ssn) {
		if(!auth.isManager(ssn)) {
			model.addAttribute("msg", "This page is accessible only to the Admin and Manager");
			return "msg";
		}
		model.addAttribute("supplier", this.suppliers.findById(id).get());
		return folder + "edit";
	}
	@PostMapping("{id}/edit") 
	public String edit(Model model, @ModelAttribute @Valid Supplier supplier, Errors err, @PathVariable int id, HttpSession ssn){
		if(!auth.isManager(ssn)) {
			model.addAttribute("msg", "This page is accessible only to the Admin and Manager");
			return "msg";
		}
		if(err.hasErrors()) {
			for(ObjectError e:err.getAllErrors()) {
				System.err.println("err  " + e.getObjectName() + " " + e.getDefaultMessage());
			}
			return add(model, ssn);
		}
		supplier.setId(id);
		suppliers.save(supplier);
		return "redirect:/suppliers/";
	}
	@GetMapping("/{id}/delete")
	public String delete(Model model, @PathVariable int id, HttpSession ssn) {
		if(!auth.isManager(ssn)) {
			model.addAttribute("msg", "This page is accessible only to the Admin and Manager");
			return "msg";
		}
		this.suppliers.deleteById(id);
		return "redirect:/suppliers/";
	}
	@PostMapping("/search")
	public String search(Model model, @ModelAttribute SearchForm form, HttpSession ssn) {
		if(!auth.isManager(ssn)) {
			model.addAttribute("msg", "This page is accessible only to the Admin and Manager");
			return "msg";
		}
		List<Supplier> suppl = this.supplierServ.search(form.getS());
		model.addAttribute("srch", form);
		model.addAttribute("suppliers", suppl);
		return folder + "all";
	}
}