//PSInsertTestOnSQL.java
package com.nt.jdbc1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class PSInsertTestOnSQL {
	
	private static final String STUDENT_INSERT_QUERY = "INSERT INTO STUDENT VALUES(?,?,?,?)";

	public static void main(String[] args) {
		Scanner scn = null;
		Connection con = null;
		PreparedStatement ps = null;	
		
		try {
			scn = new Scanner(System.in);
			int count = 0;
			if(scn != null) {
				//read input
				System.out.print("Enter count value: ");
				count = scn.nextInt();
			}
			//load JDBC Driver class
			//Class.forName("oracle.jdbc.driver.OracleDriver");  //optional
			
			//Establish the Connection
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","IMSHYAM","URVIL");
			
			//create PreparedStatement obj
			if(con != null)
				ps = con.prepareStatement(STUDENT_INSERT_QUERY);
			
			//read inputs from the end-user set them to QUERY param values and execute the pre-compiled
			//SQL query for multiple times
			if(ps != null && scn != null) {
				//read inputs from the end-user as Student details
				for(int i=1; i<=count; i++) {
					System.out.println("\nEnter "+i+" Student Details");
					System.out.print("Enter Student id: ");
					int no = scn.nextInt(); scn.nextLine();
					System.out.print("Enter Student Name: ");
					String name = scn.nextLine().toUpperCase();
					System.out.print("Enter Student Address: ");
					String address = scn.nextLine().toUpperCase();
					System.out.print("Enter Student Average Marks: ");
					float avg = scn.nextFloat();
					
					//set each Student details as SQL query params
					ps.setInt(1, no); ps.setString(2, name); ps.setString(3, address); ps.setFloat(4, avg);
					
					//execute SQL query each time
					int result = ps.executeUpdate();
					
					//process execution result
					if(result == 0)
						System.out.println(i+" Student details not Inserted");
					else
						System.out.println(i+" Student details Inserted");
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
			//close JDBC Connections
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
		}

	}//main

}//class
