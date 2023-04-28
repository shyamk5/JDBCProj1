package com.nt.jdbc;

/*
 * 	JDBC App to Insert Data given by the end-user and store to the Student DB Table on MySQL
 * 	Version :: 1.O
 * 	Author :: imshyam
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class InsertTestOnMySQL {

	public static void main(String[] args) {
		
		Connection con = null;
		Scanner scn = null;
		Statement st = null;
		
		try {
			int sno = 0;
			String sname = null;
			String sadd = null;
			float savg = 0.0f;
			
			scn = new Scanner(System.in);
			//read inputs from the end-user
			if(scn != null) {
				System.out.print("Enter Student id: ");
				sno = scn.nextInt(); scn.nextLine();
				System.out.print("Enter Student name: ");
				sname = scn.nextLine().toUpperCase();
				System.out.print("Enter Student Address: ");
				sadd = scn.nextLine().toUpperCase();
				System.out.println("Enter Student Average Marks: ");
				savg = scn.nextFloat();
			}
			//preapre inputs as per required for MySQL query
			sname = "'"+sname+"'";
			sadd = "'"+sadd+"'";
			
			//Load the JDBC Driver class
			//Class.forName("com.mysql.cj.jdbc.Driver");//optional
			
			//Establish the Connection
			con = DriverManager.getConnection("jdbc:mysql:///NTAJ415DB","root","URVIL");
			
			//Create JDBC Statement Object
			if(con != null)
				st = con.createStatement();
			
			//Prepare Query
			//INSERT INTO STUDENT VALUES(105,'PRADEEP','HYD',85.25)
			String query = "INSERT INTO STUDENT VALUES("+sno+","+sname+","+sadd+","+savg+")";
			System.out.println(query);
			
			//Send and Execute query
			int count = 0;
			if(st != null)
				count = st.executeUpdate(query);
			
			if(count != 0)
				System.out.println(count+" row created");		
		}//try
		catch(SQLException se) {
			if(se.getErrorCode()==1062)
				System.out.println("Duplicates can not be Inserted");
			else if(se.getErrorCode()==1406)
				System.out.println("Do not insert more than column size data");
			else if(se.getErrorCode()==1048)
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
