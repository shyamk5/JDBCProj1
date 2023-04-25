//SelectTestMaxSAL.java
package com.nt.jdbc;

/*
 * 	JDBC app to get Max of SAL from EMP DB Table
 * 	Version :: 1.O
 * 	Author :: imshyam
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SelectTestMaxSAL {

	public static void main(String[] args) {
		
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		
		try {
			
			//Load JDBC Driver Class
			//Class.forName("oracle.jdbc.driver.OracleDriver");
			//Optional Loading the Driver Class
			
			//Establish the Connection b/w Java app and JDBC Driver s/w
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","IMSHYAM","URVIL");
			//Create JDBC Statement object
			if(con != null)
				st = con.createStatement();
			//Prepare SQL Query
			//SELECT EMPNO,ENAME,JOB,SAL FROM EMP WHERE SAL=(SELECT MAX(SAL) FROM EMP)
			String query = "SELECT EMPNO,ENAME,JOB,SAL FROM EMP WHERE SAL=(SELECT MAX(SAL) FROM EMP)";
			System.out.println(query);
			
			//Send and execute the SQL Query
			if(st != null)
				rs = st.executeQuery(query);
			
			//Process the ResultSet
			if(rs != null) {
				boolean flag = false;
				while(rs.next() != false) {
					flag = true;
					System.out.println(rs.getInt(1)+"\t\t"+rs.getString(2)+"\t\t"+rs.getString(3)+"\t\t"+rs.getFloat(4));
				}//while
				if(flag == false)
					System.out.println("no records found");
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
		}//finally
	
	}//main
}//class
