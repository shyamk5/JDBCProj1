package com.nt.jdbc1;

/*
 * 	JDBC App to Insert Student details given by the end-user for n times using PreparedStatement
 * 	Version :: 1.O
 * 	Author :: imshyam
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class PSInsertTest {
	
	private static final String STUDENT_INSERT_QUERY = "INSERT INTO STUDENT VALUES(?,?,?,?)";
	
	public static void main(String[] args) {
		Scanner scn = null;
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			scn = new Scanner(System.in);
			//read inputs
			int count = 0;
			if(scn != null) {
				System.out.print("Enter Student Count: ");
				count = scn.nextInt();
			}
			
			//Register JDBC Driver
			//Class.forName("com.mysql.cj.jdbc.driver"); //optional
			
			//Establish the connection
			con = DriverManager.getConnection("jdbc:mysql:///NTAJ415DB","root","URVIL");
			
			//Create PreparedStatement obj having pre-compiled sql query
			if(con != null)
				ps = con.prepareStatement(STUDENT_INSERT_QUERY);
			
			//read input from end-user, set them to query param values and execute the pre-compiled
			//SQL query for multiple times
			
			if(scn != null && ps != null) {
				for(int i=1; i<=count; ++i) {
					System.out.println("\nEnter "+i+" Student Info");
					System.out.print("Enter Student Number: ");
					int no = scn.nextInt(); scn.nextLine();
					System.out.print("Enter Student Name: ");
					String name = scn.nextLine().toUpperCase();
					System.out.print("Enter Student Address: ");
					String address = scn.nextLine().toUpperCase();
					System.out.print("Enter Student Average Marks: ");
					float avg = scn.nextFloat();
					
					//set each student details as pre-compiled SQL query params
					ps.setInt(1, no); ps.setString(2, name); ps.setString(3, address); ps.setFloat(4, avg);
					
					//execute pre-compiled SQL query each time
					int result = ps.executeUpdate();
					
					//process execution result of pre-compiled SQL query
					if(result ==0)
						System.out.println(i+" Student details are not inserted");
					else
						System.out.println(i+" Student details are inserted");
				}//for
			}//if			
		}//try
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			//close JDBC object
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
