package com.springtut.sports.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springtut.sports.model.Employee;
import com.springtut.sports.model.dao.EmployeeDAO;

@Service
public class EmpServImpl implements EmpServ{
	@Autowired
	private EmployeeDAO emp;
	@Override
	public List<Employee> search(String s) {
		List<Employee> ans = new ArrayList<>();
		if(s == null || s == "") {
			for(Employee p:this.emp.findAll()) {
				ans.add(p);
			}
			return ans;
		}
		for(Employee p:this.emp.findAll()) {
			if(p.getName().strip().toLowerCase().startsWith(s.toLowerCase()) || 
								String.valueOf(p.getId()).toLowerCase().startsWith(s.toLowerCase()) ||
								p.getPhone().toLowerCase().strip().startsWith(s.toLowerCase()) ) {
				ans.add(p);
			}
		}
		return ans;
	}
}
