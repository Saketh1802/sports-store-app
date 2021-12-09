package com.springtut.sports.controller;

import java.util.List;

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

import com.springtut.sports.model.Brand;
import com.springtut.sports.model.dao.BrandDAO;
import com.springtut.sports.services.BrandServ;
import com.springtut.sports.services.SearchForm;

@Controller
@RequestMapping("/brands/")
public class BrandController {
	String folder = "brands/";
	@Autowired
	BrandDAO brands;
	@Autowired
	BrandServ brandServ;
	
	private void init() {
		boolean need = true;
		for(Brand w: brands.findAll()) {
			if(w.getName().compareTo("NO_BRAND") == 0) {
				need = false;
				break;
			}
		}
		if(need) {
			Brand no = new Brand();
			no.setName("NO_BRAND");
			this.brands.save(no);
		}
	}
	@GetMapping("/")
	public String all(Model model) {
		init();
		model.addAttribute("srch", new SearchForm());
		model.addAttribute("brands", brands.findAll());
		model.addAttribute("brand", new Brand());
		return folder + "all";
	}
	@GetMapping("/{id}")
	public String one(Model model, @PathVariable int id) {
		init();
		model.addAttribute("brand", brands.findById(id).get());
		return folder + "one";
	}
	@GetMapping("/add")
	public String add(Model model) {
		init();
		model.addAttribute("brand", new Brand());
		return folder + "add";
	}
	
	@PostMapping("/add") 
	public String add(Model model, @ModelAttribute("brands") @Valid Brand brand, Errors err){
		if(err.hasErrors()) {
			return add(model);
		}

		if(!brands.existsByName(brand.getName()))
			brands.save(brand);
		return "redirect:/brands/";
	}
	@GetMapping("/{id}/edit")
	public String edit(Model model, @PathVariable int id) {
		init();
		model.addAttribute("brand", this.brands.findById(id).get());
		return folder + "edit";
	}
	@PostMapping("/{id}/edit") 
	public String edit(Model model, @ModelAttribute @Valid Brand brand, Errors err, @PathVariable int id){
		init();
		if(err.hasErrors()) {
			return edit(model, id);
		}
		if(!brands.existsByName(brand.getName()))
		{
		Brand original = this.brands.findById(id).get();
		original.setName(brand.getName());
		brands.save(original);
		}
		else
		{
			brands.delete(brand);
		}
		
		return "redirect:/brands/";
	}
	@GetMapping("/{id}/delete")
	public String delete(Model model, @PathVariable int id) {
		init();
		this.brands.deleteById(id);
		return "redirect:/brands/";
	}
	@PostMapping("/search")
	public String search(Model model, @ModelAttribute("srch") SearchForm form) {
		
		List<Brand> br = this.brandServ.search(form.getS());
		model.addAttribute("srch", form);
		model.addAttribute("brands", br);
		model.addAttribute("brand",new Brand());
		return folder + "all";
	}
}
