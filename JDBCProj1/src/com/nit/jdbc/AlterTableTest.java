package com.nit.jdbc;

/*
 * 	JDBC App to perform ALTER Operation and Rename the Column name on DEMO_TABLE
 * 	Version :: 1.O
 * 	Author :: imshyam
 */


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;


public class AlterTableTest {

	public static void main(String[] args) {
		
		Connection con = null;
		Statement st = null;
		Scanner scn = null;
		System.out.println("This Program is meant for Performing ALTER Operation on DEMO_TABLE");
		System.out.println("If you want to change Column Name then Please Enter required details...");
		try {
			scn = new Scanner(System.in);
			String oldCol = null;
			String newCol = null;
			//read inputs
			if(scn != null) {
				System.out.print("\n\nEnter Column Name which you want to Change: ");
				oldCol = scn.next().toUpperCase();
				System.out.print("Enter New Column Name: ");
				newCol = scn.next().toUpperCase();
			}
			
			//Load JDBC Driver class
			//Class.forName("oracle.jdbc.driver.OracleDriver");//optional
			
			//Establish the Connection
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","IMSHYAM","URVIL");
			
			//Create JDBC Statement object
			if(con != null)
				st = con.createStatement();
			
			//Prepare SQL Query
			//ALTER TABLE DEMO_TABLE RENAME COLUMN TSAL TO EMPSAL
			String query = "ALTER TABLE DEMO_TABLE RENAME COLUMN "+oldCol+" TO "+newCol;
			
			//Send and execute SQL query
			int count = 0;
			if(st != null)
				count = st.executeUpdate(query);
			
			if(count == 0)
				System.out.println("Table altered");
			else
				System.out.println("Table not altered");	
		}//try
		catch(SQLException se) {
			if(se.getErrorCode()==957)
				System.out.println("duplicate column name");
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			//close JDBC Connections
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
