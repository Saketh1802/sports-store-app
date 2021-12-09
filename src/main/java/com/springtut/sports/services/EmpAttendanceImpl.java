package com.springtut.sports.services;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springtut.sports.model.Attendance;
import com.springtut.sports.model.Employee;
import com.springtut.sports.model.dao.AttendanceDAO;
import com.springtut.sports.model.dao.EmployeeDAO;

@Service
public class EmpAttendanceImpl implements EmpAttendance{
	@Autowired
	private AttendanceDAO att;
	@Autowired
	private EmployeeDAO emp;
	private String cur;
	EmpAttendanceImpl(){		
		Calendar today = Calendar.getInstance();
		cur = "" +
				today.get(Calendar.YEAR) + "-" +
				((today.get(Calendar.MONTH) + 1 > 9)?"":"0") + (today.get(Calendar.MONTH) + 1) + "-" +
				((today.get(Calendar.DAY_OF_MONTH) + 1 > 9)?"":"0") + today.get(Calendar.DAY_OF_MONTH);
	}
	public static String toString(Calendar today) {
		String cur = "" +
				today.get(Calendar.YEAR) + "-" +
				((today.get(Calendar.MONTH) + 1 > 9)?"":"0") + (today.get(Calendar.MONTH) + 1) + "-" +
				((today.get(Calendar.DAY_OF_MONTH) + 1 > 9)?"":"0") + today.get(Calendar.DAY_OF_MONTH);
		return cur;
	}
	@Override
	public void setToday() {
			
		for(Employee e: emp.findAll()) {
			boolean need = true;
			for(Attendance a:e.getAttendance()) {
				if(a.getDate().compareTo(cur) >= 0) {
					need = false;
					break;
				}
			}
			if(need) {
				att.save(new Attendance(cur, e, false));
			}
			
		}
		
		
	}
	@Override
	public List<Attendance> getToday() {
		List<Attendance> ans = new ArrayList<>();
		for(Attendance a:att.findAll()) {
			if(a.getDate().compareTo(cur) >= 0) {
				ans.add(a);
			}
		}
		return ans;
	}
	@Override
	public int getPresent(String start, int id) {
		int tot = 0;
		for(Attendance a: att.findAll()) {
			if(a.getEmp().getId() == id && a.getDate().compareTo(start) >= 0 && a.getStatus()) {
				tot++;
			}
		}
		return tot;
	}
	@Override
	public int getPresent(int id) {
		Calendar today = Calendar.getInstance();
		today.add(Calendar.MONTH, -1);
		String start = toString(today);
		return getPresent(start, id);
		
	}
	
	
}
