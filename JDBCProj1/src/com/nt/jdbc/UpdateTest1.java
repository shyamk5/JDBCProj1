//UpdateTest1.java
package com.nt.jdbc;

/*
 * 	JDBC App to Update Student Records Based on the Given Details
 * 	Version :: 1.O
 * 	Author :: imshyam
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Scanner;
import java.sql.SQLException;

public class UpdateTest1 {

	public static void main(String[] args) {
		
		Connection con = null;
		Statement st = null;
		Scanner scn = null;
		
		int newSno = 0;
		String sname = null;
		String sadd = null;
		double avg = 0.0;
		int oldSno = 0;
		
		try {
			scn = new Scanner(System.in);
			if(scn != null) {
				//read inputs from the user
				System.out.print("Enter Student No for Updation.: ");
				oldSno = scn.nextInt(); scn.nextLine();
				System.out.print("Enter Student Name: ");
				sname = scn.nextLine().toUpperCase();
				System.out.print("Enter Student Address: ");
				sadd = scn.nextLine().toUpperCase();
				System.out.print("Enter Average Value: ");
				avg = scn.nextDouble();
				System.out.print("Enter New Student No.: ");
				newSno = scn.nextInt();
			}
			//covert the input as required for the SQL Query
			sname = "'"+sname+"'";
			sadd = "'"+sadd+"'";
			
			//Load the JDBC Driver Class
			//Class.forName("oracle.jdbc.driver.OracleDriver"); //Optional because of Autoloading
			
			//Establish the Connection b/w Java App and DB s/w
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","IMSHYAM","URVIL");
			
			//Create Statement Object
			if(con != null)
				st = con.createStatement();
			
			//Prepare SQL Query
			//UPDATE STUDENT SET STID=101,SNAME='SHYAM',SADD='HYD',SAVG=99.99 WHERE STID=104
			
			String query = "UPDATE STUDENT SET STID="+newSno+",SNAME="+sname+",SADD="+sadd+",SAVG="+avg+" WHERE STID="+oldSno;
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
			else if(se.getErrorCode()==12899)
				System.out.println("value can't be large for column SNAME and SADD");
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
