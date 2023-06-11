package com.sk.basics;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class PersonAgeFinder {
	
	private final static String RETRIEVE_DOB_QUERY = "SELECT ROUND((SYSDATE-DOB)/365.25,2) FROM PERSON_INFO_DATES WHERE PID=?";
	
	public static void main(String[] args) {
		
		Scanner scn = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			int pid = 0;
			Float age = 0.0f;
			//read inputs
			scn = new Scanner(System.in);
			if(scn != null) {
				System.out.print("Enter the Person ID:: ");
				pid = scn.nextInt();
			}//if

			//load the JDBC Driver class
			Class.forName("oracle.jdbc.driver.OracleDriver"); //optional
			
			//establish the Connection
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","IMSHYAM","URVIL");
			
			//Create the Prepared Statement object
			if(con != null)
				ps = con.prepareStatement(RETRIEVE_DOB_QUERY);
			
			//set values to Query Parameter and Execute the Query
			if(ps != null) {
				ps.setInt(1, pid);
				rs = ps.executeQuery();
			}
			
			//process the result set Object
			if(rs != null) {
				if(rs.next()) {
					age = rs.getFloat(1);
					System.out.println("Person Age is (in years):: "+age);
				}
				else {
					System.out.println("Person not Found");
				}
			}//if
		}//try
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			//close JDBC objects
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
