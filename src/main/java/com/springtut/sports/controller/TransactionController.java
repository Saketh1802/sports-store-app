package com.springtut.sports.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springtut.sports.model.Transaction;
import com.springtut.sports.model.dao.TransactionDAO;
import com.springtut.sports.services.Auth;


@Controller
@RequestMapping("/transactions/")
public class TransactionController {
	String folder = "transactions/";
	@Autowired
	private TransactionDAO trans;
	@Autowired
	private Auth auth;
	@GetMapping("/")
	public String all(Model model, HttpSession ssn) {
		/*
		 * if(!auth.isManager(ssn)) { model.addAttribute("msg",
		 * "This page is accessible only to the Admin and Manager"); return "msg"; }
		 */
		model.addAttribute("trans", trans.findAll());
		return folder + "all";
	}
	
	@GetMapping("/add")
	public String add(Model model, HttpSession ssn) {
		/*
		 * if(!auth.isManager(ssn)) { model.addAttribute("msg",
		 * "This page is accessible only to the Admin and Manager"); return "msg"; }
		 */
		model.addAttribute("trans", new Transaction());
		return folder + "add";
	}
	@PostMapping("/add") 
	public String add(Model model, @ModelAttribute @Valid Transaction transaction, Errors err, HttpSession ssn){
		/*
		 * if(!auth.isManager(ssn)) { model.addAttribute("msg",
		 * "This page is accessible only to the Admin and Manager"); return "msg"; }
		 */if(err.hasErrors()) {
			return add(model, ssn);
		}
		this.trans.save(transaction);
		return "redirect:/transactions/";
	}
	@GetMapping("/{id}")
	public String one(Model model, @PathVariable int id, HttpSession ssn) {
		/*
		 * if(!auth.isManager(ssn)) { model.addAttribute("msg",
		 * "This page is accessible only to the Admin and Manager"); return "msg"; }
		 */model.addAttribute("trans", this.trans.findById(id).get());
		return folder + "one";
	}
	@GetMapping("/{id}/edit")
	public String edit(Model model, @PathVariable int id, HttpSession ssn) {
		/*
		 * if(!auth.isManager(ssn)) { model.addAttribute("msg",
		 * "This page is accessible only to the Admin and Manager"); return "msg"; }
		 */model.addAttribute("trans", this.trans.findById(id).get());
		return folder + "edit";
	}
	@PostMapping("/{id}/edit")
	public String edit(Model model, @ModelAttribute @Valid Transaction transaction, Errors err, @PathVariable int id, HttpSession ssn) {
		/*
		 * if(!auth.isManager(ssn)) { model.addAttribute("msg",
		 * "This page is accessible only to the Admin and Manager"); return "msg"; }
		 */if(err.hasErrors()) {
			return edit(model, id, ssn);
		}
		Transaction o_trans = this.trans.findById(id).get();
		transaction.setId(id);
		transaction.setDeals(o_trans.getDeals());
		transaction.setMaintenances(o_trans.getMaintenances());
		transaction.setSalaries(o_trans.getSalaries());
		transaction.setSales(o_trans.getSales());
		this.trans.save(transaction);
		return "redirect:/transactions/";
	}
	@GetMapping("/{id}/delete")
	public String delete(Model model, @PathVariable int id, HttpSession ssn) {
		/*
		 * if(!auth.isManager(ssn)) { model.addAttribute("msg",
		 * "This page is accessible only to the Admin and Manager"); return "msg"; }
		 */this.trans.deleteById(id);
		return "redirect:/transactions/";
	}
}
