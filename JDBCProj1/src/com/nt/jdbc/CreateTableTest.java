//CreateTableTest.java
package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateTableTest {

	public static void main(String[] args) {
		
		Connection con = null;
		Statement st = null;
		
		try {
			
			//Load JDBC Driver class
			//Class.forName("oracle.jdbc.driver.OracleDriver");//optional
			
			//Establish the Connection
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","IMSHYAM","URVIL");
			
			//Create JDBC Statement object
			if(con != null)
				st = con.createStatement();
			
			//Prepare SQL Query
			//CREATE TABLE TEMP_STUDENT(SNO NUMBER(5) PRIMARY KEY, SNAME VARCHAR2(15))
			String query = "CREATE TABLE TEMP_STUDENT(SNO NUMBER(5) PRIMARY KEY, SNAME VARCHAR2(15))";
			System.out.println(query);
			
			//Send and Execute SQL Query
			int count =0;
			if(st != null)
				count = st.executeUpdate(query);
			
			if(count == 0)
				System.out.println("DB Table is created");
			else
				System.out.println("DB Table is not Created");
			
		}//try
		catch(SQLException se) {
			if(se.getErrorCode()==955)
				System.out.println("DB Table is already Created");
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			//close JDBC Connections
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
