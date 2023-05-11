package com.nt.basics;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class PSDateRetrieveByDateRange {
	
	private static final String RETRIEVE_DATE_QUERY = "SELECT PID,PNAME,DOB,DOJ,DOM FROM PERSON_INFO_DATES WHERE DOB>=? AND DOB<=?";
	public static void main(String[] args) {
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Scanner scn = null;
		
		try {
			
			//read inputs
			String sdob = null, edob = null;
			scn = new Scanner(System.in);	
			if(scn != null) {
				System.out.print("Enter Start Range of DOB(dd-MM-YYYY):: ");
				sdob = scn.next();
				System.out.print("Enter End Range of DOB(dd-MM-YYYY):: ");
				edob = scn.next();
			}
			//convert String date value to java.util.Date class obj
			SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-YYYY");
			java.sql.Date sqsdob = new java.sql.Date(sdf1.parse(sdob).getTime());
			java.sql.Date sqedob = new java.sql.Date(sdf1.parse(edob).getTime());
			
			//Load the JDBC Driver class
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//Establish the Connection
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","IMSHYAM","URVIL");
			
			//Create Preapred Statement obj
			if(con != null)
				ps = con.prepareStatement(RETRIEVE_DATE_QUERY);
			
			//set date to Query params
			if(ps != null) {
				ps.setDate(1, sqsdob);
				ps.setDate(2, sqedob);
			}
			
			
			//execute query
			if(ps != null)
				rs = ps.executeQuery();
			/*
			//process ResultSet obj
			if(rs != null) {
				while(rs.next()) {
					System.out.println(rs.getInt(1)+"\t\t"+rs.getString(2)+"\t\t"+rs.getDate(3)+"\t\t"+rs.getDate(4)+"\t\t"+rs.getDate(5));
				}//while
			}//if
			*/
			
			System.out.println("=======================================================================\n\n");
			
			//process ResultSet obj
			if(rs != null) {
				boolean flag = false;
				while(rs.next()) {
					flag = true;
					int pid = rs.getInt(1);
					String pname = rs.getString(2);
					java.sql.Date sqldob = rs.getDate(3);
					java.sql.Date sqldoj = rs.getDate(4);
					java.sql.Date sqldom = rs.getDate(5);
					
					//convert  java.sql.Date class obj to String date value
					SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-YYYY");
					String dob = sdf.format(sqldob);
					String doj = sdf.format(sqldoj);
					String dom = sdf.format(sqldom);
					
					System.out.println(pid+"\t\t"+pname+"\t\t"+dob+"\t\t"+doj+"\t\t"+dom);
					
				}//while
				if(flag == false)
					System.out.println("Records not Found");
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
				if(rs != null)
					rs.close();
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
