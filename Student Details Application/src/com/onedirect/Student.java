package com.onedirect;
import java.io.Serializable;
import java.util.ArrayList;

class Student implements Serializable {
	private static final long serialVersionUID = -3509975826119083103L;
	private String name;
	private int age;
	private String address;
	private int roll;
	private ArrayList<Course> enrolledCourses;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getRoll() {
		return roll;
	}
	public void setRoll(int roll) {
		this.roll = roll;
	}
	public ArrayList<Course> getEnrolledCourses() {
		return enrolledCourses;
	}
	public void setEnrolledCourses(ArrayList<Course> enrolledCourses) {
		this.enrolledCourses = enrolledCourses;
	}
	

}
