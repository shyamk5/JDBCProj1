//SalaryHikeOnEMP.java
package com.sk.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/*
 * 	JDBC App to hike Employee Salary by given percentage by the end-user for the Employee
 * 	Whose salary is in the given range(Start Range and End Range)
 * 	Version :: 1.O
 * 	Author :: imshyam
 */


public class SalaryHikeOnEMP {

	public static void main(String[] args) {
		
		Scanner scn = null;
		Connection con = null;
		Statement st = null;
		
		int startRange = 0;
		int endRange = 0;
		double hikeSal = 0;
		
		try {
			scn = new Scanner(System.in);
			//read inputs from the end user
			if(scn != null) {
				System.out.print("Enter the Percentage for Hike in salary: ");
				hikeSal = scn.nextDouble();
				System.out.print("Enter the Start Range: ");
				startRange = scn.nextInt();
				System.out.print("Enter the End Range: ");
				endRange = scn.nextInt();
			}
			//Prepare the inputs according to the SQL Query
			hikeSal = hikeSal/100;
			
			//Load the JDBC Driver Class
			//Class.forName("oracle.jdbc.driver.OracleDriver"); //Optional because of Autoloading
			
			//Establish the Connection b/w Java App and DB s/w
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","IMSHYAM","URVIL");
			
			//Create Statement Object
			if(con != null)
				st = con.createStatement();
			
			//Prepare SQL Query
			//UPDATE EMP SET SAL=SAL+(SAL*&percentage) WHERE SAL>=&START AND SAL<=&END
			String query = "UPDATE EMP SET SAL=SAL+(SAL*"+hikeSal+") WHERE SAL>="+startRange+" AND SAL<="+endRange;
			System.out.println(query);
			
			//Send and Execute SQL query
			int count = 0;
			if(st != null)
				count = st.executeUpdate(query);
			
			if(count == 0) {
				System.out.println("No records found to update");
			}
			else {
				System.out.println(count+" row updated");
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
