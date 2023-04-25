//SelectTestOnDEPT.java
package com.nt.jdbc;

/*
 * 	JDBC app that gives department info from DEPT table based on the given DEPTNO
 * 	Version :: 1.O
 * 	Author :: imshyam
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class SelectTestOnDEPT {

	public static void main(String[] args) {
		
		Scanner scn = null;
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		
		try {
			//read inputs
			scn = new Scanner(System.in);
			int dno = 0;
			if(scn != null) {
				System.out.print("Enter DEPT No: ");
				dno = scn.nextInt();//gives an Integer Number
			}
			//Load JDBC Driver Class
			//Class.forName("oracle.jdbc.driver.OracleDriver");
			//Optional Loading the Driver Class
			
			//Establish the Connection b/w Java app and JDBC Driver s/w
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","IMSHYAM","URVIL");
			//Create JDBC Statement object
			if(con != null)
				st = con.createStatement();
			//Prepare SQL Query
			//SELECT * FROM DEPT WHERE DEPTNO = 10
			String query = "SELECT * FROM DEPT WHERE DEPTNO = "+dno;
			System.out.println(query);
			
			//Send and execute the SQL Query
			if(st != null)
				rs = st.executeQuery(query);
			
			//Process the ResultSet (0 or 1 record)
			if(rs != null) {
				if(rs.next())
					System.out.println(rs.getInt(1)+"\t\t"+rs.getString(2)+"\t\t"+rs.getString(3));
				else
					System.out.println("no records found");
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
			try {
				if(scn != null)
					scn.close();
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}//finally
	
	}//main
}//class
