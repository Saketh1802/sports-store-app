package com.springtut.sports.services;

import java.util.List;

import com.springtut.sports.model.Attendance;

public class AttendanceForm {
	private List<Attendance> attendance;

	public List<Attendance> getAttendance() {
		return attendance;
	}

	public void setAttendance(List<Attendance> attendance) {
		this.attendance = attendance;
	}
	
}
