//SelectNonSelect.java
package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class SelectNonSelect {

	public static void main(String[] args) {
		
		Scanner scn = null;
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		
		try {
			scn = new Scanner(System.in);
			String query = null;
			//read inputs
			if(scn != null) {
				System.out.print("Enter Select/Non-Select Query: ");
				query = scn.nextLine();
			}
			//Load JDBC Driver class
			//Class.forName("oracle.jdbc.driver.OracleDriver");//optional
			
			//Establish the Connection
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","IMSHYAM","URVIL");
			
			//Create JDBC Statement object
			if(con != null)
				st = con.createStatement();
			
			//Send and Execute SQL query in DB s/w
			if(st != null) {
				boolean flag = st.execute(query);
				if(flag == true) {
					System.out.println("SELECT sql Query executed");
					rs = st.getResultSet();
					//process the ResultSet
					if(rs != null) {
						while(rs.next()) {
							System.out.println(rs.getString(1)+"\t\t"+rs.getString(2)+"\t\t"+rs.getString(3));
						}//while
					}//if
				}//if
				else {
					System.out.println("Non-SELECT SQL query executed");
					int count = st.getUpdateCount(); //long count = st.getLargeUpdateCount();
					System.out.println(count+" row affected");
				}
			}//if
		}//try
		catch(SQLException se) {
			//handle error codes
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			//close JDBC Connections
			try {
				if(rs != null)
					rs.close();
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
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
