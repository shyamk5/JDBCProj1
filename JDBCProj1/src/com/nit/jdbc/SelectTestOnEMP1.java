//SelectTestOnEMP1.java
package com.nit.jdbc;
/*
*		Java App to get the Employee Details According to their First Character name entered by the end user
*		Version :: 1.O
*		Author :: imshyam
*/
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class SelectTestOnEMP1 {

	public static void main(String[] args) {
		System.out.println("SelectTestOnEMP1.main()");
		Scanner scn = null;
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		
		String initChars = null;
		
		try {
			//read inputs from end user
			scn = new Scanner(System.in);
			if(scn != null) {
				System.out.print("Enter Initial Character of Employee Name: ");
				initChars = scn.next().toUpperCase();
			}
			//convert input values as required for the SQL queries
			initChars = "'"+initChars+"%'";
			
			//Register JDBC Driver by loading JDBC Driver class
			//Class.forName(oracle.jdbc.driver.OracleDriver); //Optional because of Auto loading
			
			//Establish the Connection between Java and DB s/w
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","IMSHYAM","URVIL");
			
			//Create Statement Object
			if(con != null)
				st = con.createStatement();
			//Prepare SQL Query
			//SELECT EMPNO,ENAME,JOB,SAL FROM EMP WHERE ENAME LIKE 'S%'
			String query = "SELECT EMPNO,ENAME,JOB,SAL FROM EMP WHERE ENAME LIKE "+initChars;
			
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
			}	
		}//try
		catch (SQLException se) {
			if(se.getErrorCode()>=900 && se.getErrorCode()<=999)
				System.out.println("Invalid Column Names or Table Names or SQL Keywords");
			se.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			//Close JDBC Objects
			try {
				if(rs != null)
					rs.close();
			}
			catch (SQLException se) {
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
		}//finally end

	}//main end

}//class end
