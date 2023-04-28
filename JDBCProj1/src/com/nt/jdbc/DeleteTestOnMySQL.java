package com.nt.jdbc;

/*
 * 	JDBC App to Delete Student Records Based on the Given Student id(STID) on MySQL
 * 	Version :: 1.O
 * 	Author :: imshyam
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class DeleteTestOnMySQL {

	public static void main(String[] args) {
		
		Connection con = null;
		Scanner scn = null;
		Statement st = null;
		
		try {
			int sno = 0;
			
			scn = new Scanner(System.in);
			System.out.println("For Deleting Student Records");
			//read inputs from the end-user
			if(scn != null) {
				System.out.print("Enter Student id: ");
				sno = scn.nextInt(); scn.nextLine();
			}
			
			//Load the JDBC Driver class
			//Class.forName("com.mysql.cj.jdbc.Driver");//optional
			
			//Establish the Connection
			con = DriverManager.getConnection("jdbc:mysql:///NTAJ415DB","root","URVIL");
			
			//Create JDBC Statement Object
			if(con != null)
				st = con.createStatement();
			
			//Prepare Query
			//DELETE FROM STUDENT WHERE STID = &0
			String query = "DELETE FROM STUDENT WHERE STID = "+sno;
			System.out.println(query);
			
			//Send and Execute query
			int count = 0;
			if(st != null)
				count = st.executeUpdate(query);
			
			if(count == 0)
				System.out.println(count+" row affected");
			else
				System.out.println(count+" row affected");
		}//try
		catch(SQLException se) {
			if(se.getErrorCode()==1146)
				System.out.println("table doesn't exist");
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			//Close JDBC Connection
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
