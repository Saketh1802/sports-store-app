package com.springtut.sports.controller;

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

import com.springtut.sports.model.Product;
import com.springtut.sports.model.Supplierproducts;
import com.springtut.sports.model.Transaction;
import com.springtut.sports.model.dao.ProductDAO;
import com.springtut.sports.model.dao.SupplierDAO;
import com.springtut.sports.model.dao.SupplierproductsDAO;
import com.springtut.sports.model.dao.TransactionDAO;
import com.springtut.sports.services.Auth;
import com.springtut.sports.services.DealsForm;
@Controller
@RequestMapping("/deals/")
public class SupplierproductsController {
	private String folder = "deals/";
	@Autowired
	private SupplierproductsDAO deals;
	@Autowired
	private SupplierDAO suppliers;
	@Autowired
	private ProductDAO products;
	@Autowired
	private TransactionDAO transactions;
	@Autowired
	private Auth auth;
	@GetMapping("/")
	public String all(Model model, HttpSession ssn) {
		if(!auth.isManager(ssn)) {
			model.addAttribute("msg", "This page is accessible only to the Admin and Manager");
			return "msg";
		}
		model.addAttribute("deals", deals.findAll());
		return folder + "all";
	}
	@GetMapping("/add")
	public String add(Model model, HttpSession ssn) {
		if(!auth.isManager(ssn)) {
			model.addAttribute("msg", "This page is accessible only to the Admin and Manager");
			return "msg";
		}
		DealsForm form = new DealsForm();
		model.addAttribute("product", products.findAll());
		model.addAttribute("supplier", suppliers.findAll());
		model.addAttribute("deal", form);
		return folder + "add";
	}
	@PostMapping("/add")
	public String add(Model model, @ModelAttribute @Valid DealsForm form, Errors err, HttpSession ssn) {
		if(!auth.isManager(ssn)) {
			model.addAttribute("msg", "This page is accessible only to the Admin and Manager");
			return "msg";
		}
		if(err.hasErrors()) {
			for(ObjectError e:err.getAllErrors()) {
				System.err.println(" --- " + e.getObjectName() + " " + e.getDefaultMessage());
			}
			return add(model, ssn);
		}
		System.err.println("SSSSSSSSSSSSS");
		Supplierproducts deal = form.getDeal();
		Transaction transaction = form.getTransaction();
		transaction.setComments("SUPPLY DEAL WITH " + deal.getSupplier().getName());
		deal.setTransaction(transaction);
		this.transactions.save(transaction);
		deals.save(deal);
		
		//if(form.isUpdate()) {
		Product p = this.products.findById(form.getDeal().getProduct().getId()).get();
		p.setQuantity(p.getQuantity() + form.getDeal().getQuantity());
		this.products.save(p);
		//}
		
		return "redirect:/deals/";
	}
	@GetMapping("/{id}")
	public String one(Model model, @PathVariable int id, HttpSession ssn) {
		if(!auth.isManager(ssn)) {
			model.addAttribute("msg", "This page is accessible only to the Admin and Manager");
			return "msg";
		}
		model.addAttribute("deal", this.deals.findById(id).get());
		return folder + "one";
	}
	@GetMapping("/{id}/edit")
	public String edit(Model model, @PathVariable int id, HttpSession ssn) {
		if(!auth.isManager(ssn)) {
			model.addAttribute("msg", "This page is accessible only to the Admin and Manager");
			return "msg";
		}
		DealsForm form = new DealsForm();
		form.setDeal(this.deals.findById(id).get());
		form.setTransaction(form.getDeal().getTransaction());
		model.addAttribute("product", products.findAll());
		model.addAttribute("supplier", suppliers.findAll());
		model.addAttribute("deal", form);
		return folder + "edit";
	}
	@PostMapping("/{id}/edit")
	public String edit(Model model, @ModelAttribute @Valid DealsForm form, Errors err, @PathVariable int id, HttpSession ssn) {
		if(!auth.isManager(ssn)) {
			model.addAttribute("msg", "This page is accessible only to the Admin and Manager");
			return "msg";
		}
		if(err.hasErrors()) {
			for(ObjectError e:err.getAllErrors()) {
				System.err.println(" --- " + e.getObjectName() + " " + e.getDefaultMessage());
			}
			return edit(model, id, ssn);
		}
		Supplierproducts deal = form.getDeal();
		Transaction transaction = form.getTransaction();
		Supplierproducts o_deal = this.deals.findById(id).get();
		Transaction o_trans = o_deal.getTransaction();
		transaction.setComments("*edited from old transaction (id-" + o_trans.getId() + "\nSUPPLY DEAL WITH " + deal.getSupplier().getName());
		deal.setTransaction(transaction);
		deal.setId(id);		
		Product p = this.products.findById(deal.getProduct().getId()).get();
		p.setQuantity(p.getQuantity()-o_deal.getQuantity() + form.getDeal().getQuantity());
		this.products.save(p);
		
		this.transactions.save(transaction);
		deals.save(deal);
		
		return "redirect:/deals/";
	}
	@GetMapping("/{id}/delete")
	public String delete(Model model, @PathVariable int id, HttpSession ssn) {
		if(!auth.isManager(ssn)) {
			model.addAttribute("msg", "This page is accessible only to the Admin and Manager");
			return "msg";
		}
		this.deals.deleteById(id);
		return "redirect:/deals/";
	}
	
	
}
