package org.itech.test.util;

import java.sql.Connection;
import java.sql.DriverManager;


public class Database {
	public static Connection getConnection(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost/stu_sub", "root", "root" );
			return con;
			
			
		} catch (Exception e) {
			System.out.println("Database.getConnection() Error --> " + e.getMessage());
			return null;
		}
	}
	
	public static void close(Connection con){
		try {
			con.close();
		} catch (Exception ex) {
			System.out.println(ex);
		}
		
	}
	
	
}
