package com.springtut.sports.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springtut.sports.model.User;
import com.springtut.sports.model.dao.UserDAO;
import com.springtut.sports.services.SecurityService;

@Controller
@RequestMapping("/")
public class LoginController {
// 	private String folder = "login/";
	@Autowired
	private UserDAO userDAO;
	@Autowired
    private SecurityService securityService;
//	@Autowired
//	private Auth auth;
	
	
	@GetMapping("/signin")
	public String signin(Model model,String error,String logout)
	{
		 if (securityService.isAuthenticated()) {
	            return "redirect:/";
	        }
	     return "login";
	}
	
	
	
	@GetMapping({"/home/","","/"})
	public String home(Model model, HttpSession ssn, Authentication authentication) {
		
//		if(ssn.getAttribute("user_n") == null)
//			return "redirect:/login/";
		//  System.out.println(authentication.getName());
		
		User user = userDAO.findByName(authentication.getName());
		model.addAttribute("role", user.getRole().getName());
		if(user.getEmp() == null) {
			model.addAttribute("name", "Admin");
		}
		else
			model.addAttribute("name", user.getEmp().getName());
		ssn.setAttribute("user_n", user.getName());
		ssn.setAttribute("user_passwd", user.getPasswd());
		String role = user.getRole().toString().toLowerCase();
		ssn.setAttribute(role, true);
		if(role.equals("employee"))
			return "home2";
		return "home";
	}
/*
	@GetMapping("/login")
	public String login(Model model, HttpSession ssn) {
		model.addAttribute("user", new User());
		return folder + "login";
	}
	@PostMapping("/login")
	public String login(Model model,  HttpServletRequest req, @ModelAttribute @Valid User usr, Errors err) {
		HttpSession ssn = req.getSession();
		if(!auth.isValid(usr)) {
			
			model.addAttribute("msg", "invalid credentials");
			return login(model, ssn);
		}
		ssn.setAttribute("user_n", usr.getName());
		ssn.setAttribute("user_passwd", usr.getPasswd());
		String role = auth.getUser(ssn).getRole().toString().toLowerCase();
		ssn.setAttribute(role, true);
		System.err.println(role);
		return "redirect:/home/";
	}
*/
/*
	@GetMapping("/logout/")
	public String logout(Model model, HttpSession ssn) {
		ssn.setAttribute("user_n", null);
		ssn.setAttribute("user_passwd", null);
		ssn.setAttribute("admin", null);
		ssn.setAttribute("manager", null);
		ssn.setAttribute("employee", null);
		// System.out.println("logout function");
		return "redirect:/signin";
	}
*/
	/*
	@GetMapping("/register")
	public String register(Model model, HttpSession ssn) {
		Employee emp = (Employee)ssn.getAttribute("emp");
		if(emp == null) {
			model.addAttribute("msg", "already registered");
			return "msg";
		}
		User usr = new User();
		usr.setEmp(emp);
		usr.setName(emp.getName() + usr.getId());
		return folder + "register";
	}
	*/
}
