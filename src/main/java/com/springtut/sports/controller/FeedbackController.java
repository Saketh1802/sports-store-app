package com.springtut.sports.controller;

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

import com.springtut.sports.model.Feedback;
import com.springtut.sports.model.dao.CustomerDAO;
import com.springtut.sports.model.dao.FeedbackDAO;

@Controller
@RequestMapping("/feedback/")
public class FeedbackController {
	String folder = "feedback/";
	@Autowired
	CustomerDAO customer;
	@Autowired
	FeedbackDAO feedback;
	@GetMapping("/")
	public String all(Model model) {
		model.addAttribute("customers", this.customer.findAll());
		model.addAttribute("fb", feedback.findAll());
		return folder + "all";
	}
	@GetMapping("/{id}")
	public String one(Model model, @PathVariable int id) {
		model.addAttribute("customers", this.customer.findAll());
		model.addAttribute("fb", feedback.findById(id).get());
		return folder + "one";
	}
	@GetMapping("/add")
	public String add(Model model) {
		model.addAttribute("customers", this.customer.findAll());
		model.addAttribute("fb", new Feedback());
		return folder + "add";
	}
	@PostMapping("/add")
	public String add(Model model, @ModelAttribute @Valid Feedback feedback, Errors err) {
		if(err.hasErrors()) {
			for(ObjectError o:err.getAllErrors())
				System.err.println(" -- " + o.getObjectName() + " " + o.getDefaultMessage());
			return add(model);
		}
		this.feedback.save(feedback);
		
		return "redirect:/feedback/";
	}
}
