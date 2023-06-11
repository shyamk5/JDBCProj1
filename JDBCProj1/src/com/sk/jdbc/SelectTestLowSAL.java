//SelectTestLowSAL.java
package com.sk.jdbc;

/*
 * 	JDBC app to get Low of SAL from EMP DB Table
 * 	Version :: 1.O
 * 	Author :: imshyam
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SelectTestLowSAL {

	public static void main(String[] args) {
		
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		
		try {
			
			//JDBC Driver class Loading
			Class.forName("oracle.jdbc.OracleDriver"); //Optional because of Autoloading
			
			//Establish JDBC Connection
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","IMSHYAM","URVIL");
			
			//Create Statement object
			if(con != null)
				st = con.createStatement();
			
			//Prepare SQL Query
			//SELECT EMPNO,ENAME,JOB,SAL FROM EMP WHERE SAL=(SELECT MIN(SAL) FROM EMP)
			String query = "SELECT EMPNO,ENAME,JOB,SAL FROM EMP WHERE SAL=(SELECT MIN(SAL) FROM EMP)";
			System.out.println(query);
			
			//Create ResultSet obj
			if(st != null)
				rs = st.executeQuery(query);
			
			//Process the ResultSet
			if(rs != null) {
				boolean flag = false;
				while(rs.next() != false) {
					flag = true;
					System.out.println(rs.getInt(1)+"\t"+rs.getString(2)+"\t"+rs.getString(3)+"\t"+rs.getFloat(4));
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
