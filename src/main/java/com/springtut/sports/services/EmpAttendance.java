package com.springtut.sports.services;

import java.util.Calendar;
import java.util.List;

import com.springtut.sports.model.Attendance;

public interface EmpAttendance {
	public void setToday();
	public List<Attendance> getToday();
	public int getPresent(String start, int id);
	public int getPresent(int id);
	public static String toString(Calendar today) {
		String cur = "" +
				today.get(Calendar.YEAR) + "-" +
				((today.get(Calendar.MONTH) + 1 > 9)?"":"0") + (today.get(Calendar.MONTH) + 1) + "-" +
				((today.get(Calendar.DAY_OF_MONTH) + 1 > 9)?"":"0") + today.get(Calendar.DAY_OF_MONTH);
		return cur;
	}
}
