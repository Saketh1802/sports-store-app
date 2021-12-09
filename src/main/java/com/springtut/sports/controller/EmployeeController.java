package com.springtut.sports.controller;

import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springtut.sports.model.Attendance;
import com.springtut.sports.model.Employee;
import com.springtut.sports.model.Roles;
import com.springtut.sports.model.User;
import com.springtut.sports.model.dao.EmployeeDAO;
import com.springtut.sports.model.dao.UserDAO;
import com.springtut.sports.services.Auth;
import com.springtut.sports.services.EmpAttendance;
import com.springtut.sports.services.EmpForm;
import com.springtut.sports.services.EmpServ;
import com.springtut.sports.services.SearchForm;
import com.springtut.sports.services.StringForm;


@Controller
@RequestMapping("/employee")
public class EmployeeController {
	String folder = "employee/";
	@Autowired
	private EmployeeDAO emp;
	@Autowired
	private EmpAttendance att;
	@Autowired
	private UserDAO users;
	@Autowired
	private Auth auth;
	@Autowired
	private EmpServ empServ;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	@GetMapping("/")
	public String all(Model model, HttpSession ssn) {
		if(!auth.isManager(ssn)) {
			model.addAttribute("msg", "This page is accessible only to the Admin and Manager");
			return "msg";
		}
		model.addAttribute("srch", new SearchForm());
		model.addAttribute("employees", emp.findAll());
		return folder + "all";
	}
	@GetMapping("/{id}")
	public String one(Model model, @PathVariable int id,  HttpServletRequest req) {
		 HttpSession ssn = req.getSession();
		if(auth.isManager(ssn)) {
			model.addAttribute("emp", emp.findById(id).get());
			Calendar now = Calendar.getInstance();
			now.add(Calendar.MONTH, -1);
			model.addAttribute("since", new StringForm(EmpAttendance.toString(now)));
			model.addAttribute("presentdays", this.att.getPresent(EmpAttendance.toString(now), id));
			return folder + "one";
		}
		model.addAttribute("msg", "This page is accessible only to the Admin and Manager");
		return "msg";
	}
	@PostMapping("/{id}")
	public String one(Model model, @PathVariable int id, @ModelAttribute StringForm form) {
		
		String since = form.getName();
		model.addAttribute("emp", emp.findById(id).get());
		model.addAttribute("since", new StringForm(since));
		model.addAttribute("presentdays", this.att.getPresent(since, id));
		return folder + "one";
	}
	@GetMapping("/add")
	public String add(Model model, HttpSession ssn) {
		if(!auth.isManager(ssn)) {
			model.addAttribute("msg", "This page is accessible only to the Admin and Manager");
			return "msg";
		}
		model.addAttribute("form", new EmpForm());
		return folder + "add";
	}
	@PostMapping("/add")
	public String add(Model model, @ModelAttribute @Valid EmpForm form, Errors err, HttpSession ssn) {
		if(!auth.isManager(ssn) || (!auth.isAdmin(ssn) && form.getUsr().getRole() == Roles.ADMIN)) {
			model.addAttribute("msg", "This page is accessible only to the Admin and Manager");
			return "msg";
		}
		if(err.hasErrors()) {
			for(ObjectError o:err.getAllErrors())
				System.err.println(" -- " + o.getObjectName() + " " + o.getDefaultMessage());
			return add(model, ssn);
		}
		
		Employee emp = form.getEmp();
		User usr = form.getUsr();
		usr.setEmp(emp);
		usr.setPasswd(passwordEncoder.encode(usr.getPasswd()));
		
		if(this.auth.exists(usr.getName())) {
			model.addAttribute("form", form);
			model.addAttribute("auth_msg", "username already used");
			return folder + "add";
		}
		this.emp.save(emp);
		this.users.save(usr);
		return "redirect:/employee/";
	}
	@GetMapping("/{id}/edit")
	public String edit(Model model, @PathVariable int id, HttpSession ssn) {
		if(!auth.isManager(ssn)) {
			model.addAttribute("msg", "This page is accessible only to the Admin and Manager");
			return "msg";
		}
		model.addAttribute("emp", this.emp.findById(id).get());
		return folder + "edit";
	}
	@PostMapping("/{id}/edit") 
	public String edit(Model model, @ModelAttribute @Valid Employee e, Errors err, @PathVariable int id, HttpSession ssn){
		if(!auth.isManager(ssn)) {
			model.addAttribute("msg", "This page is accessible only to the Admin and Manager");
			return "msg";
		}
		if(err.hasErrors()) {
			return edit(model, id, ssn);
		}
		Employee original = this.emp.findById(id).get();
		e.setId(id);
		e.setAttendance(original.getAttendance());
		e.setSalaries(original.getSalaries());
		this.emp.save(e);
		return "redirect:/employee/";
	}
	@GetMapping("/{id}/delete")
	public String delete(Model model, @PathVariable int id, HttpSession ssn) {
		if(!auth.isManager(ssn)) {
			model.addAttribute("msg", "This page is accessible only to the Admin and Manager");
			return "msg";
		}
		System.err.println("NOT OK" + this.emp.findById(id).isPresent());
		
		users.delete(users.findByName(emp.findById(id).get().getName()));
		emp.findById(id).get().setAttendance(null);
		this.emp.deleteById(id);
		return "redirect:/employee/";
	}
	@PostMapping("/search")
	public String search(Model model, @ModelAttribute SearchForm form, HttpSession ssn) {
		if(!auth.isManager(ssn)) {
			model.addAttribute("msg", "This page is accessible only to the Admin and Manager");
			return "msg";
		}
		List<Employee> emp = this.empServ.search(form.getS());
		model.addAttribute("srch", form);
		model.addAttribute("employees", emp);
		return folder + "all";
	}
}
