//InsertTestOnStudent.java
package com.sk.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/*
 * 	JDBC App to Insert Data given by the end-user and store to the Student DB Table
 * 	version :: 1.O
 * 	Author :: imshyam
 */

public class InsertTestOnStudent {

	public static void main(String[] args) {
		Scanner scn = null;
		Connection con = null;
		Statement st = null;
		
		try {
			scn = new Scanner(System.in);
			int sno = 0;
			String sname = null;
			String sadd = null;
			double savg = 0.0;
			
			//read inputs from the end-user
			if(scn != null) {
				System.out.print("Enter Student id: ");
				sno = scn.nextInt(); scn.nextLine();
				System.out.print("Enter Student Name: ");
				sname = scn.nextLine().toUpperCase();
				System.out.print("Enter Student Address: ");
				sadd = scn.nextLine().toUpperCase();
				System.out.print("Enter Student Avg Marks: ");
				savg = scn.nextDouble();
			}
			//covert user inputs as required for the SQL Query
			sname = "'"+sname+"'";
			sadd = "'"+sadd+"'";
			
			//Load JDBC driver class
			//Class.forName("oracle.jdbc.OracleDriver");//Optional
			
			//Establish the Connection
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","IMSHYAM","URVIL");
			
			//Create Statement object
			if(con != null)
				st = con.createStatement();
			
			//Prepare SQL Query
			//INSERT INTO STUDENT VALUES(1001,'SHYAM','DELHI',57.69)
			String query = "INSERT INTO STUDENT VALUES("+sno+","+sname+","+sadd+","+savg+")";
			System.out.println(query);
			
			//Send and Execute SQL query
			int count = 0;
			if(st != null)
				count = st.executeUpdate(query);
			
			if(count != 0)
				System.out.println(count+" row created");			
		}//try
		catch(SQLException se) {
			if(se.getErrorCode()>=900 && se.getErrorCode()<=999)
				System.out.println("Invalid Column Names or Table Names or SQL Keywords");
			else if(se.getErrorCode()==1)
				System.out.println("Duplicates can not be Inserted");
			else if(se.getErrorCode()==12899)
				System.out.println("Do not insert more than column size data");
			else if(se.getErrorCode()==1400)
				System.out.println("NULL cannot be Inserted");
			System.out.println("Problem in Record Insertion");
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
