package com.nit.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class InsertTestTWRWithPostgreSQL {
	
	private final static String INSERT_STUDENT_QUERY = "INSERT INTO STUDENT_INFO VALUES(NEXTVAL('stid_seq'),?,?,?)";

	public static void main(String[] args) {
		
		try(Scanner scn = new Scanner(System.in)) {
			
			String name = null;
			String course = null;
			String doa = null;
			
			//read inputs
			System.out.print("Enter Student Name: ");
			name = scn.nextLine().toUpperCase();
			System.out.print("Enter Course Name: ");
			course = scn.nextLine().toUpperCase();
			System.out.print("Enter Date of Admission(YYYY-mm-dd): ");
			doa = scn.next();
			
			//convert String date values to java.sql.Date class object
			java.sql.Date sqdoa = java.sql.Date.valueOf(doa);
			
			try(//create connection object
					Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/NTAJ415DB","postgres","URVIL");
					//create prepared statement object
					PreparedStatement ps = con.prepareStatement(INSERT_STUDENT_QUERY);		) {
				
				//set insert query param values
				if(ps != null) {
					ps.setString(1, name);
					ps.setString(2, course);
					ps.setDate(3, sqdoa);
				}
				
				//execute the query
				int count = 0;
				if(ps != null) {
					count = ps.executeUpdate();
					count++;
				}
				
				if(count == 0)
					System.out.println("Record not inserted");
				else
					System.out.println(count+" Record inserted");
				
			}//try
		}//try
		catch(SQLException se) {
			se.printStackTrace();
			System.out.println("Problem in record insertion");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}//main

}//class
