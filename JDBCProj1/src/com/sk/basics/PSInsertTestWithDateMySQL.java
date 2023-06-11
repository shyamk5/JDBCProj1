package com.sk.basics;

/*
 * 	JDBC App to Insert Data with Date Values in different formats in MySQL
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class PSInsertTestWithDateMySQL {
	
	private static final String INSERT_DATE_QUERY = "INSERT INTO PERSON_INFO_DATES(PNAME,DOB,DOJ,DOM)VALUES(?,?,?,?)";
	public static void main(String[] args) {
		
		Connection con = null;
		PreparedStatement ps = null;
		Scanner scn = null;
		
		try {
			
			//read inputs
			scn = new Scanner(System.in);
			String sname = null,sdob = null,sdoj=null,sdom=null;
			
			if(scn != null) {
				System.out.print("Enter Your Name:: ");
				sname = scn.next().toUpperCase();
				System.out.print("Enter Your DOB(DD-MM-YYYY):: ");
				sdob = scn.next();
				System.out.print("Enter Your DOJ(yyyy-mm-dd):: ");
				sdoj = scn.next();
				System.out.print("Enter Your DOM(mmm-dd-YYYY):: ");
				sdom = scn.next();
			}
			//convert String Date values to java.sql.Date class obj
				//for DOB(dd-mm-yyyy)
					//first convert String date value into java.util.Date class obj
						SimpleDateFormat sdf1 = new SimpleDateFormat("dd-mm-yyyy");
						java.util.Date udob = sdf1.parse(sdob);
						
						//convert java.util.Date class obj to java.sql.Date class obj
						long ms = udob.getTime();
						java.sql.Date sqdob = new java.sql.Date(ms);
			
			//for DOJ(yyyy-mm-dd)
				//convert String date value to java.sql.Date class obj
						java.sql.Date sqdoj = java.sql.Date.valueOf(sdoj);
						
			//for DOM(mmm-dd-yyyy)
					SimpleDateFormat sdf2 = new SimpleDateFormat("MMM-dd-yyyy");
					java.util.Date udom = sdf2.parse(sdom);
					//convert java.util.Date to java.sql.Date class obj
					ms = udom.getTime();
					java.sql.Date sqdom = new java.sql.Date(ms);
			
			//Load the JDBC Driver class
			//Class.forName("com.mysql.cj.oracle.driver");
					
			//Establish the Connection
			con = DriverManager.getConnection("jdbc:mysql:///NTAJ415DB2","root","URVIL");
			
			//Create Preapred Statement obj
			if(con != null)
				ps = con.prepareStatement(INSERT_DATE_QUERY);
			
			//set values to query params
			if(ps != null) {
				ps.setString(1, sname);
				ps.setDate(2, sqdob);
				ps.setDate(3, sqdoj);
				ps.setDate(4, sqdom);
			}
			
			//execute Query
			int count = 0;
			if(ps != null)
				count = ps.executeUpdate();
			
			//process the results
			if(count == 0)
				System.out.println("Record not Inserted");
			else
				System.out.println("Record Inserted");	
		}//try
		catch(SQLException se) {
			se.printStackTrace();
			System.out.println("Error in Record Insertion");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			//close JDBC objects
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
