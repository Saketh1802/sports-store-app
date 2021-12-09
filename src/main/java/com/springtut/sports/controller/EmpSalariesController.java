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

import com.springtut.sports.model.EmpSalaries;
import com.springtut.sports.model.Transaction;
import com.springtut.sports.model.dao.EmpSalariesDAO;
import com.springtut.sports.model.dao.EmployeeDAO;
import com.springtut.sports.model.dao.TransactionDAO;
import com.springtut.sports.services.Auth;
import com.springtut.sports.services.SalaryForm;

@Controller
@RequestMapping("/salaries")
public class EmpSalariesController {
	String folder = "salaries/";
	@Autowired
	EmpSalariesDAO salaries;
	@Autowired
	EmployeeDAO emp;
	@Autowired
	TransactionDAO transactions;
	@Autowired
	private Auth auth;
	
	@GetMapping("/")
	public String all(Model model, HttpSession ssn) {
		if(!auth.isManager(ssn)) {
			model.addAttribute("msg", "This page is accessible only to the Admin and Manager");
			return "msg";
		}
		model.addAttribute("salaries", salaries.findAll());
		return folder + "all";
	}
	@GetMapping("/add")
	public String add(Model model, HttpSession ssn) {
		if(!auth.isManager(ssn)) {
			model.addAttribute("msg", "This page is accessible only to the Admin and Manager");
			return "msg";
		}
		SalaryForm m = new SalaryForm();
		m.setSalary(new EmpSalaries());
		m.setTransaction(new Transaction());
		model.addAttribute("m", m);
		model.addAttribute("employees", emp.findAll());
		return folder + "add";
	}
	@GetMapping("/{id}")
	public String one(Model model, @PathVariable int id, HttpSession ssn) {
		if(!auth.isManager(ssn)) {
			model.addAttribute("msg", "This page is accessible only to the Admin and Manager");
			return "msg";
		}
		model.addAttribute("salary", this.salaries.findById(id).get());
		return folder + "one";
	}
	@PostMapping("/add")
	public String add(Model model, @ModelAttribute @Valid SalaryForm m, Errors err, HttpSession ssn) {
		if(!auth.isManager(ssn)) {
			model.addAttribute("msg", "This page is accessible only to the Admin and Manager");
			return "msg";
		}
		if(err.hasErrors()) {
			for(ObjectError o:err.getAllErrors())
				System.err.println(" -- " + o.getObjectName() + " " + o.getDefaultMessage());
			return add(model, ssn);
		}
		Transaction transaction = m.getTransaction();
		EmpSalaries salary = m.getSalary();
		transaction.setComments(" SALARY PAID TO " + salary.getEmp().getName() + " ( " + transaction.getComments() + " ) ");
		salary.setTransaction(transaction);
		this.transactions.save(transaction);
		this.salaries.save(salary);
		
		return "redirect:/salaries/";
	}
	@GetMapping("/{id}/edit")
	public String edit(Model model, @PathVariable int id, HttpSession ssn) {
		if(!auth.isManager(ssn)) {
			model.addAttribute("msg", "This page is accessible only to the Admin and Manager");
			return "msg";
		}
		SalaryForm m = new SalaryForm();
		m.setSalary(this.salaries.findById(id).get());
		m.setTransaction(this.salaries.findById(id).get().getTransaction());
		model.addAttribute("m", m);
		model.addAttribute("employees", emp.findAll());
		
		
		return folder + "edit";
	}
	@PostMapping("/{id}/edit") 
	public String edit(Model model, @ModelAttribute @Valid SalaryForm m, Errors err, @PathVariable int id, HttpSession ssn){
		if(!auth.isManager(ssn)) {
			model.addAttribute("msg", "This page is accessible only to the Admin and Manager");
			return "msg";
		}
		if(err.hasErrors()) {
			for(ObjectError o:err.getAllErrors())
				System.err.println(" -- " + o.getObjectName() + " " + o.getDefaultMessage());
			return add(model, ssn);
		}
		EmpSalaries original_s = this.salaries.findById(id).get();
		Transaction original_t = original_s.getTransaction();
		Transaction transaction = m.getTransaction();
		EmpSalaries salary = m.getSalary();
		transaction.setComments("SALARY PAID TO " + salary.getEmp().getName());
		salary.setTransaction(transaction);
		
		original_t.setAmount(transaction.getAmount());
		original_s.setEmp(salary.getEmp());
		original_s.setTransaction(salary.getTransaction());
		original_t.setComments(transaction.getComments());
		original_t.setDate(transaction.getDate());
		this.transactions.save(original_t);
		this.salaries.save(original_s);
		
		return "redirect:/salaries/";
	}
	@GetMapping("/{id}/delete")
	public String delete(Model model, @PathVariable int id, HttpSession ssn) {
		if(!auth.isManager(ssn)) {
			model.addAttribute("msg", "This page is accessible only to the Admin and Manager");
			return "msg";
		}
		this.salaries.deleteById(id);
		return "redirect:/salaries/";
	}
}
