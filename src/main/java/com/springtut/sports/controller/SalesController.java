package com.springtut.sports.controller;

import java.util.ArrayList;
import java.util.Calendar;
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

import com.springtut.sports.model.Product;
import com.springtut.sports.model.Sales;
import com.springtut.sports.model.Transaction;
import com.springtut.sports.model.WarrantyClaims;
import com.springtut.sports.model.dao.CustomerDAO;
import com.springtut.sports.model.dao.EmployeeDAO;
import com.springtut.sports.model.dao.ProductDAO;
import com.springtut.sports.model.dao.SalesDAO;
import com.springtut.sports.model.dao.TransactionDAO;
import com.springtut.sports.model.dao.WarrantyClaimsDAO;
import com.springtut.sports.services.SalesForm;
import com.springtut.sports.services.WarrClaims;


@Controller
@RequestMapping("/sales/")
public class SalesController {
	String folder = "sales/";
	@Autowired
	private SalesDAO sales;
	@Autowired
	private TransactionDAO transactions;
	@Autowired
	private ProductDAO products;
	@Autowired
	private CustomerDAO customers;
	@Autowired
	private EmployeeDAO emp;
	@Autowired
	private WarrantyClaimsDAO claims;
	@Autowired
	private WarrClaims claimService;
	@GetMapping("/")
	public String all(Model model) {
		model.addAttribute("sales", sales.findAll());
		return folder + "all";
	}
	@GetMapping("/{id}")
	public String one(Model model, @PathVariable int id) {
		Sales s = sales.findById(id).get();
		String exp = claimService.expiryDate(s);
		Calendar today = Calendar.getInstance();
		String cur = "" +
				today.get(Calendar.YEAR) + "-" +
				((today.get(Calendar.MONTH) + 1 > 9)?"":"0") + (today.get(Calendar.MONTH) + 1) + "-" +
				((today.get(Calendar.DAY_OF_MONTH) + 1 > 9)?"":"0") + today.get(Calendar.DAY_OF_MONTH);
		boolean canClaim = (exp.compareTo(cur) > 0);
		System.err.println(canClaim);
		model.addAttribute("sale", s);
		model.addAttribute("claim", claimService.isClaimed(s));
		model.addAttribute("expiry", exp);
		model.addAttribute("canClaim", canClaim);
		return folder + "one";
	}
	@GetMapping("/add")
	public String add(Model model) {
		SalesForm m = new SalesForm();
		m.setSale(new ArrayList<Sales>());
		m.getSale().add(new Sales());
		m.setTransaction(new Transaction());
		model.addAttribute("m", m);
		model.addAttribute("tot", 0);
		model.addAttribute("products", products.findAll());
		model.addAttribute("customers", customers.findAll());
		model.addAttribute("employees", emp.findAll());
		model.addAttribute("productDAO",customers);
		return folder + "add";
	}
	@PostMapping(value="/add", params="addmore")
	public String addMore(Model model, @ModelAttribute @Valid SalesForm m, Errors err) {
		if(err.hasErrors()) {
			for(ObjectError o:err.getAllErrors())
				System.err.println(" -- " + o.getObjectName() + " " + o.getDefaultMessage());
			
			return add(model);
		}
		
		Sales sale=m.getSale().get(m.getSale().size()-1);
		Product product =  products.findByNameAndBrand(sale.getProduct().getName(),sale.getProduct().getBrand());
		if(sale.getQuantity()>product.getQuantity())
			return "redirect:/sales/add";
		
		
		float tot = sum(m.getSale());
		Transaction transaction = m.getTransaction();
		Sales new_sale = new Sales();
		new_sale.setTransaction(m.getTransaction());
		m.getSale().add(new_sale);
		
		model.addAttribute("m", m);
		model.addAttribute("tot", tot);
		model.addAttribute("products", products.findAll());
		model.addAttribute("customers", customers.findAll());
		model.addAttribute("employees", emp.findAll());
		return folder + "add";
	}
	private float sum(List<Sales> sales) {
		float tot = 0;
		for(Sales s: sales) {
			tot += s.getQuantity()*(s.getProduct().getCost());
		}
		return tot;
	}
	@PostMapping(value="/add", params="final")
	public String add(Model model, @ModelAttribute @Valid SalesForm m, Errors err) {
		if(err.hasErrors()) {
			for(ObjectError o:err.getAllErrors())
				System.err.println(" -- " + o.getObjectName() + " " + o.getDefaultMessage());
			
			return add(model);
		}
		
		Sales saler=m.getSale().get(m.getSale().size()-1);
		Product product =  products.findByNameAndBrand(saler.getProduct().getName(),saler.getProduct().getBrand());
		if(saler.getQuantity()>product.getQuantity())
			return "redirect:/sales/add";
		
		List<Sales> sale = m.getSale();
		for(Sales s:sale)
		{
			Product p =  products.findByNameAndBrand(s.getProduct().getName(),s.getProduct().getBrand());
			p.setQuantity(p.getQuantity()-s.getQuantity());
		}
		
		Transaction transaction = m.getTransaction();
		transaction.setComments("SOLD " + sale.size() + " PRODUCTS");
		float tot = sum(m.getSale());
		transaction.setAmount(tot);
		this.transactions.save(transaction);
		for(Sales s : sale) {
			s.setTransaction(transaction);
			s.setPrice(s.getQuantity()*s.getProduct().getCost());
			this.sales.save(s);
		}
		
		return "redirect:/transactions/" + transaction.getId();
	}
	@GetMapping("{id}/claim")
	public String claim(Model model,  @PathVariable int id) {
		if(this.claimService.isClaimed(this.sales.findById(id).get()) != null) {
			model.addAttribute("msg", "already claimed");
			return "msg";
		}
		model.addAttribute("claim", new WarrantyClaims());
		return folder + "claim";
	}
	@PostMapping("{id}/claim")
	public String claim(Model model, @ModelAttribute @Valid WarrantyClaims claim, Errors err,  @PathVariable int id) {
		if(err.hasErrors()) {
			for(ObjectError o:err.getAllErrors())
				System.err.println(" -- " + o.getObjectName() + " " + o.getDefaultMessage());
			return claim(model, id);
		}
		Sales sale = this.sales.findById(id).get();
		claim.setCustomer(sale.getCustomer());
		claim.setProduct(sale.getProduct());
		claim.setSale(sale);
		this.claims.save(claim);
		return "redirect:/sales/" + id;
	}
	/*
	@GetMapping("/{id}/edit")
	public String edit(Model model, @PathVariable int id) {
		SalesForm m = new SalesForm();
		m.setSale(new ArrayList<Sales>());
		m.getSale().add(this.sales.findById(id).get());
		m.setTransaction(this.sales.findById(id).get().getTransaction());
		model.addAttribute("m", m);
		model.addAttribute("products", products.findAll());
		model.addAttribute("customers", customers.findAll());
		model.addAttribute("employees", emp.findAll());
		return folder + "edit";
	}
	@PostMapping("/{id}/edit")
	public String edit(Model model, @ModelAttribute @Valid SalesForm m, Errors err,  @PathVariable int id) {
		if(err.hasErrors()) {
			for(ObjectError o:err.getAllErrors())
				System.err.println(" -- " + o.getObjectName() + " " + o.getDefaultMessage());
			return add(model);
		}
		Transaction transaction = m.getTransaction();
		Sales sale = m.getSale().get(0);
		Sales o_sales = this.sales.findById(id).get();
		Transaction o_trans = o_sales.getTransaction();
		transaction.setComments("*edited from old transaction (id-" + o_trans.getId() + ")\nSOLD PRODUCT " + sale.getProduct().getName());
		sale.setId(id);
		this.transactions.save(transaction);
		sale.setTransaction(transaction);
		this.sales.save(sale);
		this.transactions.save(transaction);
		return "redirect:/sales/";
	}
	
	@GetMapping("/{id}/delete")
	public String delete(Model model, @PathVariable int id) {
		this.sales.deleteById(id);
		return "redirect:/sales/";
	}
	*/
	
	
	
}
