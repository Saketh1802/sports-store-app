package com.springtut.sports.controller;

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

import com.springtut.sports.model.Warranty;
import com.springtut.sports.model.dao.WarrantyDAO;

@Controller
@RequestMapping("/warranty/")
public class WarrantyController {
	String folder = "warranty/";
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
	@GetMapping("/")
	public String all(Model model) {
		init();
		model.addAttribute("warr", warranties.findAll());
		model.addAttribute("warr2", new Warranty());
		return folder + "all";
	}
	@GetMapping("/{id}")
	public String one(Model model, @PathVariable int id) {
		init();
		model.addAttribute("warr", warranties.findById(id).get());
		return folder + "one";
	}
	@GetMapping("/add")
	public String add(Model model) {
		init();
		model.addAttribute("warr", new Warranty());
		return folder + "add";
	}
	@PostMapping("/add") 
	public String add(Model model, @ModelAttribute @Valid Warranty warranty, Errors err){
		if(err.hasErrors()) {
			return add(model);
		}
		// if(!warranties.existsByNum_of_months(warranty.getNum_of_months()))
			warranties.save(warranty);
		return "redirect:/warranty/";
	}
	@GetMapping("/{id}/edit")
	public String edit(Model model, @PathVariable int id) {
		init();
		model.addAttribute("warr", this.warranties.findById(id).get());
		return folder + "edit";
	}
	@PostMapping("/{id}/edit") 
	public String edit(Model model, @ModelAttribute @Valid Warranty warranty, Errors err, @PathVariable int id){
		if(err.hasErrors()) {
			return add(model);
		}
		warranty.setId(id);
		warranty.setProducts(this.warranties.findById(id).get().getProducts());
		warranties.save(warranty);
		return "redirect:/warranty/";
	}
	@GetMapping("/{id}/delete")
	public String delete(Model model, @PathVariable int id) {
		Warranty w = this.warranties.findById(id).get();
		if(w.getProducts().size() > 0) {
			model.addAttribute("msg", "there are some products with this warranty, cannot delete such warranty");
			return "msg";
		}
		this.warranties.deleteById(id);
		return "redirect:/warranty/";
	}
}
