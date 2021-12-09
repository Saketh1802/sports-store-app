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

import com.springtut.sports.model.WarrantyClaims;
import com.springtut.sports.model.dao.WarrantyClaimsDAO;

@Controller
@RequestMapping("/claims/")
public class WarrantyClaimsController {
	String folder = "claims/";
	@Autowired
	private WarrantyClaimsDAO claims;
	@GetMapping("/")
	public String all(Model model) {
		model.addAttribute("claims", claims.findAll());
		return folder + "all";
	}
	@GetMapping("/{id}/edit")
	public String edit(Model model, @PathVariable int id) {
		model.addAttribute("claim", this.claims.findById(id).get());
		return folder + "edit";
	}
	@PostMapping("/{id}/edit")
	public String edit(Model model, @ModelAttribute @Valid WarrantyClaims claim, Errors err,  @PathVariable int id) {
		WarrantyClaims wc = this.claims.findById(id).get();
		wc.setClaim_date(claim.getClaim_date());
		wc.setRemarks(claim.getRemarks());
		this.claims.save(wc);
		return "redirect:/claims/";
	}
	@GetMapping("/{id}/delete")
	public String delete(Model model, @PathVariable int id) {
		this.claims.deleteById(id);
		return "redirect:/claims/";
	}
}
