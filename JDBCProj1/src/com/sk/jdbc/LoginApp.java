package com.sk.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class LoginApp {

	public static void main(String[] args) {
		
		Scanner scn = null;
		Connection con = null;
		Statement st = null;
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
			//prepare inputs as required to SQL query
			uname = "'"+uname+"'";
			pwd = "'"+pwd+"'";
			
			//Load JDBC Driver Class
			//Class.forName("oracle.jdbc.driver.OracleDriver");//optional
			
			//Establish the Connection
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","IMSHYAM","URVIL");
			
			//Create Statement Object
			if(con != null)
				st = con.createStatement();
			
			//Prepare SQL Query
			//SELECT COUNT(*) FROM IRCTC_TAB WHERE UNAME='PAWAN' AND PWD='DEVIL'
			String query = "SELECT COUNT(*) FROM IRCTC_TAB WHERE UNAME="+uname+" AND PWD="+pwd;
			
			//Send and execute the SQL query in DB s/w
			if(st != null)
				rs = st.executeQuery(query);
			
			if(rs != null) {
				rs.next();
				int count = rs.getInt(1);
				if(count == 0)
					System.out.println("INVALID CREDENTIALS");
				else
					System.out.println("VALID CREDENTIALS");
			}//if
			
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
