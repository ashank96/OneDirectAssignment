package com.onedirect;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DbTaskTest {
	DbTask dbTask;
	@BeforeEach
	void setUp() throws Exception {
		dbTask=new DbTask();
	}


	@Test
	void testConnect() {
		Connection conn=null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("JDBC Driver loading error");
		}
		
		try {
			conn=DriverManager.getConnection("jdbc:mysql://localhost/inventory","root", "");
			System.out.println("Db connected");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Error in getting connection");
		}

		assertEquals(conn,dbTask.connect());
	}



}
