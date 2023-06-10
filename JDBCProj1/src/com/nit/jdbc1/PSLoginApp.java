//PSLoginApp.java

/*
 * 	JDBC Login App using PreparedStatement 
 */
package com.nit.jdbc1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class PSLoginApp {
	
	private static final String IRCTC_LOGIN_QUERY = "SELECT COUNT(*) FROM IRCTC_TAB WHERE UNAME=? AND PWD=?";

	public static void main(String[] args) {
		
		Scanner scn = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			String uname = null, pwd = null;
			//read inputs
			scn = new Scanner(System.in);
			if(scn != null) {
				System.out.print("Enter Username: ");
				uname = scn.next().toUpperCase(); scn.nextLine();
				System.out.print("Enter Password: ");
				pwd = scn.nextLine().toUpperCase();
			}
			
			//Load JDBC Driver Class
			//Class.forName("oracle.jdbc.driver.OracleDriver");//optional
			
			//Establish the Connection
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","IMSHYAM","URVIL");
			
			//Create Statement Object
			if(con != null)
				ps = con.prepareStatement(IRCTC_LOGIN_QUERY);
			
			if(ps != null) {
				ps.setString(1, uname);
				ps.setString(2, pwd);
				rs = ps.executeQuery();
			}
			
			if(rs != null)
				rs.next();
				
				int count = rs.getInt(1);
				if(count == 0)
					System.out.println("INVALID CREDENTIALS");
				else
					System.out.println("VALID CREDENTIALS");
			
			
		}//try
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			//close JDBC Connections
			try {
				if(rs != null)
					rs.close();
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
			try {
				if(ps != null)
					ps.close();
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
