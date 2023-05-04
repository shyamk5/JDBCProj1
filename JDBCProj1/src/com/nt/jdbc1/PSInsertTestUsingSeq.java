//PSInsertTestUsingSeq.java
package com.nt.jdbc1;

/*
 * 	JDBC App to Insert Student details on Student DB table in Oracle with Sequence
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class PSInsertTestUsingSeq {
	
	private static final String STUDENT_INSERT_QUERY = "INSERT INTO STUDENT VALUES(SQ_STID.NEXTVAL,?,?,?)";

	public static void main(String[] args) {
		
		Scanner scn = null;
		Connection con = null;
		PreparedStatement ps = null;
		
		
		try {
			scn = new Scanner(System.in);
			//read input for counting the number of Insert operation
			int count = 0;
			String sname = null;
			String sadd = null;
			float savg = 0.0f;
			if(scn != null) {
				System.out.print("How many Numbers of Student details you want to Insert: ");
				count = scn.nextInt(); scn.nextLine();
			}
			
			//Load JDBC driver class
			//Class.forName("oracle.jdbc.driver.OracleDriver");//optional
			
			//Establish the Connection
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","IMSHYAM","URVIL");
			
			//Create PreparedStatement obj
			if(con != null)
				ps = con.prepareStatement(STUDENT_INSERT_QUERY);
			
			if(ps != null && scn != null) {
				//read inputs for Student details
				for(int i=1; i<=count; i++) {
					System.out.println("Enter "+i+" Student info");
					System.out.print("Enter Student Name: ");
					sname = scn.next().toUpperCase();
					System.out.print("Enter Student Address: ");
					sadd = scn.next().toUpperCase();
					System.out.print("Enter Student Average Marks: ");
					savg = scn.nextFloat();
					
					//set each student details to the Parameter value
					ps.setString(1, sname); ps.setString(2, sadd); ps.setFloat(3, savg);
					
					int result = ps.executeUpdate();
					//process the execution result
					if(result == 0)
						System.out.println(i+" Student details are not Inserted");
					else
						System.out.println(i+" Student details are Inserted");
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
			//close JDBC Connection
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
