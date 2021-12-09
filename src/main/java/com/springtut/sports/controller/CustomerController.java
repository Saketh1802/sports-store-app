package com.springtut.sports.controller;

import java.util.List;

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

import com.springtut.sports.model.Customer;
import com.springtut.sports.model.dao.CustomerDAO;
import com.springtut.sports.services.CustomerServ;
import com.springtut.sports.services.SearchForm;


@Controller
@RequestMapping("/customers/")
public class CustomerController {
	String folder = "customers/";
	@Autowired
	CustomerDAO customer;
	@Autowired
	CustomerServ customerServ;
	@GetMapping("/")
	public String all(Model model) {
		model.addAttribute("srch", new SearchForm());
		model.addAttribute("customers", customer.findAll());
		return folder + "all";
	}
	@GetMapping("/{id}")
	public String one(Model model, @PathVariable int id) {
		model.addAttribute("cust", customer.findById(id).get());
		return folder + "one";
	}
	@GetMapping("/add")
	public String add(Model model) {
		model.addAttribute("cust", new Customer());
		return folder + "add";
	}
	@PostMapping("/add")
	public String add(Model model, @ModelAttribute @Valid Customer custmer, Errors err) {
		if(err.hasErrors()) {
			for(ObjectError o:err.getAllErrors())
				System.err.println(" -- " + o.getObjectName() + " " + o.getDefaultMessage());
			return add(model);
		}
		
		
		
		if(!customer.existsByNameAndPhone(custmer.getName(), custmer.getPhone()))
				this.customer.save(custmer);
		
		return "redirect:/customers/";
	}
	
	@GetMapping("/{id}/edit")
	public String edit(Model model, @PathVariable int id) {
		model.addAttribute("cust", this.customer.findById(id).get());
		return folder + "edit";
	}
	@PostMapping("/{id}/edit") 
	public String edit(Model model, @ModelAttribute @Valid Customer cust, Errors err, @PathVariable int id){
		if(err.hasErrors()) {
			return edit(model, id);
		}
		Customer original = this.customer.findById(id).get();
		if(customer.existsByNameAndPhone(cust.getName(), cust.getPhone()))
		{
			Customer intable = customer.findByNameAndPhone(cust.getName(), cust.getPhone());
			intable.setAddr(cust.getAddr());
			intable.setGender(cust.getGender());
			intable.setMail(cust.getMail());
			if(intable!=original)
				customer.delete(original);
		}
		else
		{
		original.setAddr(cust.getAddr());
		original.setGender(cust.getGender());
		original.setMail(cust.getMail());
		original.setName(cust.getName());
		original.setPhone(cust.getPhone());
		this.customer.save(original);
		}
		return "redirect:/customers/";
	}
	@GetMapping("/{id}/delete")
	public String delete(Model model, @PathVariable int id) {
		this.customer.deleteById(id);
		return "redirect:/customers/";
	}
	@PostMapping("/search")
	public String search(Model model, @ModelAttribute SearchForm form) {
		List<Customer> cust= this.customerServ.search(form.getS());
		model.addAttribute("srch", form);
		model.addAttribute("customers", cust);
		return folder + "all";
	}
	
}
