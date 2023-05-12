package com.nt.basics;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.Scanner;

public class PersonAgeCalculatorGeneric {
	
	private final static String RETRIEVE_DOB_QUERY = "SELECT DOB FROM PERSON_INFO_DATES WHERE PID=?";
	
	public static void main(String[] args) {
		
		Scanner scn = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			int pid = 0;
			float age = 0;
			//read inputs
			scn = new Scanner(System.in);
			if(scn != null) {
				System.out.print("Enter the Person ID:: ");
				pid = scn.nextInt();
			}//if

			//load the JDBC Driver class
			Class.forName("com.mysql.cj.jdbc.Driver"); //optional
			
			//establish the Connection
			con = DriverManager.getConnection("jdbc:mysql:///NTAJ415DB2","root","URVIL");
			
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
					java.sql.Date sqdob = rs.getDate(1);
					java.util.Date sysDate = new java.util.Date();
					age = (sysDate.getTime()-sqdob.getTime())/(1000.0f*60.0f*60.0f*24.0f*365.25f);
					
					//DecimalFormat df = new DecimalFormat();
					//df.setMaximumFractionDigits(2);
					
					//one more way
					DecimalFormat df = new DecimalFormat("#.##");
					
					System.out.println("Person Age is:: "+df.format(age));
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
