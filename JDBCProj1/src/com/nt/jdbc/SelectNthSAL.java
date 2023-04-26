//SelectNthSAL.java
package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;

/*
 * 	JDBC app to get Employee details whose having Nth highest salary
 * 	Version :: 1.O
 * 	Author :: imshyam
 */

import java.util.Scanner;

public class SelectNthSAL {

	public static void main(String[] args) {
		
		Scanner scn = null;
		Statement st = null;
		Connection con = null;
		ResultSet rs = null;
		
		int nth = 0;
		
		try {
			//Read inputs from end user
			scn = new Scanner(System.in);
			if(scn != null) {
				System.out.print("Enter the position to get Highest Sal: ");
				nth = scn.nextInt();
			}//if
			
			nth = nth-1; //convert input values as required for the SQL queries
			
			//Register JDBC Driver by loading JDBC Driver class
			//Class.forName(oracle.jdbc.driver.OracleDriver); //Optional because of Auto loading
			
			//Establish the Connection between Java and DB s/w
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","IMSHYAM","URVIL");
			
			//Create Statement Object
			if(con != null)
				st = con.createStatement();
			
			//Prepare SQL Query
			//SELECT E1.EMPNO,E1.ENAME,E1.JOB,E1.SAL FROM EMP E1 WHERE 1=(SELECT COUNT(DISTINCT SAL) FROM EMP E2 WHERE E2.SAL>E1.SAL)
			String query = "SELECT E1.EMPNO,E1.ENAME,E1.JOB,E1.SAL FROM EMP E1 WHERE "+nth+"=(SELECT COUNT(DISTINCT SAL) FROM EMP E2 WHERE E2.SAL>E1.SAL)";
			System.out.println(query);
			
			//ResultSet Object Creation
			if(st != null)
				rs = st.executeQuery(query);
			
			//Process the ResultSet object
			if(rs != null) {
				boolean flag = false;
				while(rs.next()) {
					flag = true;
					System.out.println(rs.getInt(1)+"\t\t"+rs.getString(2)+"\t\t"+rs.getString(3)+"\t\t"+rs.getFloat(4));
				}//while
				if(flag == false)
					System.out.println("no records found");
			}//if
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
			//close JDBC connection
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
			catch (SQLException se) {
				se.printStackTrace();
			}
			try {
				if(con != null)
					con.close();
			}
			catch (SQLException se) {
				se.printStackTrace();
			}
			try {
				if(scn != null)
					scn.close();
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}//finally
	}//main
}//class
