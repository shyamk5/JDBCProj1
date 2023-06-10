//SelectCountOnStudentMysql.java
package com.nit.jdbc;

/*
 * 	JDBC app to get count of records from STUDENT DB Table in MySQL
 * 	Version :: 1.O
 * 	Author :: imshyam
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SelectCountOnStudentMysql {

	public static void main(String[] args) {
		
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		
		try {
			
			//Load JDBC Driver Class
			//Class.forName("com.mysql.cj.jdbc.Driver");
			//Optional Loading the Driver Class
			
			//Establish the Connection b/w Java app and JDBC Driver s/w
			con = DriverManager.getConnection("jdbc:mysql:///NTAJ415DB","root","URVIL");
			//Create JDBC Statement object
			if(con != null)
				st = con.createStatement();
			//Prepare SQL Query
			//SELECT COUNT(*) FROM STUDENT
			String query = "SELECT COUNT(*) FROM STUDENT";
			System.out.println(query);
			
			//Send and execute the SQL Query
			if(st != null)
				rs = st.executeQuery(query);
			
			//Process the ResultSet (0 or 1 record)
			if(rs != null) {
				rs.next();
				//int count = rs.getInt(1);
				int count = rs.getInt("count(*)");
				System.out.println("Records Count in Student DB Table: "+count);
			}//if
		}//try
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			//close JDBC Connection
			try {
				if(rs != null)
					rs.close();
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
			try {
				if(st != null)
					st.close();
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
			try {
				if(con != null)
					con.close();
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
		}//finally
	
	}//main
}//class
