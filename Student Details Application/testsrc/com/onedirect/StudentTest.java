package com.onedirect;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class StudentTest {
	Student s;
	@BeforeEach
	void setUp() throws Exception {
		s=new Student();
	}

	@Test
	void testGetName() {
		s.setName("ashank");
		assertEquals("ashank", s.getName());
	}

	@Test
	void testGetAge() {
		s.setAge(18);
		assertEquals(18, s.getAge());
	}

	@Test
	void testGetAddress() {
		
		s.setAddress("H-204,Sipani Bangalore");
		assertEquals("H-204,Sipani Bangalore", s.getAddress());
	}

	@Test
	void testGetRoll() {
		s.setRoll(1);
		assertEquals(1, s.getRoll());
	}

	@Test
	void testGetEnrolledCourses() {
		ArrayList<Course> courses=new ArrayList<>();
		courses.add(new Course("A"));
		courses.add(new Course("C"));
		courses.add(new Course("B"));
		courses.add(new Course("D"));
		s.setEnrolledCourses(courses);
		assertEquals(courses, s.getEnrolledCourses());
	}

}
