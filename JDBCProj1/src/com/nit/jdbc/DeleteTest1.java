//DeleteTest1.java
package com.nit.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Scanner;
import java.sql.SQLException;

/*
 * 	JDBC App to Delete Student Records Based on the Given City name(SADD)
 * 	Version :: 1.O
 * 	Author :: imshyam
 */


public class DeleteTest1 {

	public static void main(String[] args) {
		
		Scanner scn =null;
		Connection con = null;
		Statement st = null;
		
		String cityName = null;
		
		try {
			scn = new Scanner(System.in);
			if(scn != null) {
				//Read inputs for the City Name from end user
				System.out.print("Enter the City name: ");
				cityName = scn.nextLine().toUpperCase();
			}
			//covert the input as required for the SQL Query
			cityName = "'"+cityName+"'";
			
			//Load the JDBC Driver Class
			//Class.forName("oracle.jdbc.driver.OracleDriver"); //Optional because of Autoloading
			
			//Establish the Connection b/w Java App and DB s/w
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","IMSHYAM","URVIL");
			
			//Create Statement Object
			if(con != null)
				st = con.createStatement();
			
			//Prepare SQL Query
			//delete from student where sadd=&cityName
			String query = "DELETE FROM STUDENT WHERE SADD="+cityName;
			System.out.println(query);
			
			//Send and Execute SQL query
			int count = 0;
			if(st != null)
				count = st.executeUpdate(query);
			
			if(count == 0) {
				System.out.println("No records found to delete");
			}
			else {
				System.out.println(count+" row deleted");
			}
			
		}//try
		catch(SQLException se) {
			if(se.getErrorCode()>=900 && se.getErrorCode()<=999)
				System.out.println("Invalid Column Names or Table Names or SQL Keywords");
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
