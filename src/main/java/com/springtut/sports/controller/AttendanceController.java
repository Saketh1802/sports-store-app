package com.springtut.sports.controller;

import java.util.Iterator;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springtut.sports.model.Attendance;
import com.springtut.sports.model.dao.AttendanceDAO;
import com.springtut.sports.model.dao.EmployeeDAO;
import com.springtut.sports.services.AttendanceForm;
import com.springtut.sports.services.Auth;
import com.springtut.sports.services.EmpAttendance;

@Controller
@RequestMapping("/attendance")
public class AttendanceController {
	String folder = "attendance/";
	@Autowired
	AttendanceDAO attendance;
	@Autowired
	EmployeeDAO emp;
	@Autowired
	EmpAttendance a_service;
	@Autowired
	private Auth auth;
	@GetMapping("/")
	public String all(Model model, HttpSession ssn) {
		
		if(!auth.isManager(ssn)) {
			model.addAttribute("msg", "This page is accessible only to the Admin and Manager");
			return "msg";
		}
		model.addAttribute("emp", emp.findAll());
		return folder + "all";
	}
	@GetMapping("/{id}")
	public String one(Model model, @PathVariable int id, HttpSession ssn) {
		if(auth.getUser(ssn).getEmp().getId() != id) {
			model.addAttribute("msg", "This page is accessible only to the Admin and Manager");	
			return "msg";
		}
		model.addAttribute("emp", emp.findById(id).get());
		return folder + "one";
	}
	@GetMapping("/add")
	public String add(Model model, HttpSession ssn) {
		if(!auth.isManager(ssn)) {
			model.addAttribute("msg", "This page is accessible only to the Admin and Manager");
			return "msg";
		}
		a_service.setToday();
		AttendanceForm a_form = new AttendanceForm();
		a_form.setAttendance(a_service.getToday());
		model.addAttribute("attendance", a_form);
		return folder + "add";
	}
	@PostMapping("/add")
	public String take(Model model, @ModelAttribute AttendanceForm attendance, HttpSession ssn) {
		if(!auth.isManager(ssn)) {
			model.addAttribute("msg", "This page is accessible only to the Admin and Manager");
			return "msg";
		}
		AttendanceForm a_form = new AttendanceForm();
		a_form.setAttendance(a_service.getToday());
		Iterator<Attendance> inp = attendance.getAttendance().iterator();
		Iterator<Attendance> act = a_form.getAttendance().iterator();
		while(inp.hasNext()) {
			act.next().setStatus((inp.next().getStatus()));
		}
		for(Attendance a: a_form.getAttendance()) {
			this.attendance.save(a);
		}
		return "redirect:/attendance/";
	}
}