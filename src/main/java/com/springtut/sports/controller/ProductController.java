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
import com.springtut.sports.model.Product;
import com.springtut.sports.model.Warranty;
import com.springtut.sports.model.dao.BrandDAO;
import com.springtut.sports.model.dao.ProductDAO;
import com.springtut.sports.model.dao.WarrantyDAO;
import com.springtut.sports.services.ProductServ;
import com.springtut.sports.services.SearchForm;

@Controller
@RequestMapping("/products/")
public class ProductController {
	String folder = "products/";
	@Autowired
	private ProductDAO products;
	@Autowired
	private BrandDAO brands;
	@Autowired
	private WarrantyDAO warr;
	@Autowired
	private ProductServ productServ;
	public void init() {
		boolean need = true;
		for(Warranty w: warr.findAll()) {
			if(w.getNum_of_months() == 0) {
				need = false;
				break;
			}
		}
		if(need) {
			Warranty no = new Warranty();
			no.setNum_of_months(0);
			no.setDescription("NO WARRANTY AVAILABLE");
			warr.save(no);
		}
		need = true;
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
		model.addAttribute("products", products.findAll());
		model.addAttribute("warr", warr.findAll());
		model.addAttribute("srch", new SearchForm());
		return folder + "all";
	}
	@GetMapping("/{id}")
	public String one(Model model, @PathVariable int id) {
		model.addAttribute("product", products.findById(id).get());
		return folder + "one";
	}
	@GetMapping("/add")
	public String add(Model model) {
		init();
		model.addAttribute("product", new Product());
		model.addAttribute("brands", brands.findAll());
		model.addAttribute("warr", warr.findAll());
		return folder + "add";
	}
	
	@PostMapping("/add") 
	public String add(Model model, @ModelAttribute @Valid Product product, Errors err){
		if(err.hasErrors()) {
			return add(model);
		}
		if(products.existsByNameAndCostAndBrandAndWarranty(product.getName(), product.getCost(), product.getBrand(), product.getWarranty()))
		{
			Product original=products.findByNameAndCostAndBrandAndWarranty(product.getName(), product.getCost(), product.getBrand(), product.getWarranty());
			original.setQuantity(original.getQuantity()+product.getQuantity());		
		}
		else
			products.save(product);
		return "redirect:/products/";
	}
	@GetMapping("/{id}/edit")
	public String edit(Model model, @PathVariable int id) {
		model.addAttribute("product", this.products.findById(id).get());
		model.addAttribute("brands", brands.findAll());
		model.addAttribute("warr", warr.findAll());
		return folder + "edit";
	}
	@PostMapping("/{id}/edit") 
	public String edit(Model model, @ModelAttribute @Valid Product product, Errors err, @PathVariable int id){
		if(err.hasErrors()) {
			return edit(model, id);
		}
		Product original = this.products.findById(id).get();
		
		if(original.getBrand().equals(product.getBrand()) && (original.getCost()==product.getCost()) && original.getName().equals(product.getName()) && original.getWarranty().equals(product.getWarranty()) )
		{
			original.setQuantity(product.getQuantity());
		}
		else if(products.existsByNameAndCostAndBrandAndWarranty(product.getName(), product.getCost(), product.getBrand(), product.getWarranty()))
		{
			Product originals=products.findByNameAndCostAndBrandAndWarranty(product.getName(), product.getCost(), product.getBrand(), product.getWarranty());
			originals.setQuantity(originals.getQuantity()+product.getQuantity());
			products.delete(original);
			
		}
		else
		{
		original.setBrand(product.getBrand());
		original.setCost(product.getCost());
		original.setName(product.getName());
		original.setQuantity(product.getQuantity());
		original.setSales(product.getSales());
		original.setWarranty(product.getWarranty());
		}
		return "redirect:/products/";
	}
	@GetMapping("/{id}/delete")
	public String delete(Model model, @PathVariable int id) {
		this.products.deleteById(id);
		return "redirect:/products/";
	}
	@PostMapping("/search")
	public String search(Model model, @ModelAttribute SearchForm form) {
		List<Product> prod = this.productServ.search(form.getS());
		model.addAttribute("srch", form);
		model.addAttribute("products", prod);
		model.addAttribute("warr", warr.findAll());
		return folder + "all";
	}
}
