package com.nt.basics;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.protocol.Resultset;

public class OracleToMySQLDataTransfer {
	
		private static final String MYSQL_INSERT_QUERY_STUDENT = "INSERT INTO STUDENT(SNAME,SADD,SAVG)VALUES(?,?,?)";
		private static final String ORACLE_SELECT_QUERY_STUDENT = "SELECT SNAME,SADD,SAVG FROM STUDENT";
	
	public static void main(String[] args) {
		
		Connection oraCon = null;
		Connection mySqlCon = null;
		Statement st = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			//Register JDBC Driver
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			//Establish the Connection
			oraCon = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","IMSHYAM","URVIL");
			mySqlCon = DriverManager.getConnection("jdbc:mysql:///NTAJ415DB","root","URVIL");
			
			//Create Statement Object
			if(oraCon != null)
				st = oraCon.createStatement();
			if(mySqlCon != null)
				ps = mySqlCon.prepareStatement(MYSQL_INSERT_QUERY_STUDENT);
			
			//send and execute SELECT Query in oracle db s/w and get ResultSet obj
			if(st != null)
				rs = st.executeQuery(ORACLE_SELECT_QUERY_STUDENT);
			
			//Gather each record of RS object and Insert into MySQL db Table
			if(rs != null && ps != null) {
				while(rs.next()) {
					//gather each record from RS object
					String sname = rs.getString(1);
					String sadd = rs.getString(2);
					Float savg = rs.getFloat(3);
					
					//set each record values to Insert query Param
					ps.setString(1, sname);
					ps.setString(2, sadd);
					ps.setFloat(3, savg);
					
					//execute the query
					ps.executeUpdate();
				}//while
				System.out.println("Records are Copied from Oracle db Table from MySQL db Table Successfully...");
			}//if
		}//try
		catch(SQLException se) {
			se.printStackTrace();
			System.out.println("Records are not Copied, Some error Occured...!");
		}
		catch(Exception e) {
			e.printStackTrace();
			System.out.println("Problems in App execution...!");
		}
		finally {
			//close JDBC objects
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
				if(ps != null)
					ps.close();
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
			
			try {
				if(oraCon != null)
					oraCon.close();
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
			
			try {
				if(mySqlCon != null)
					mySqlCon.close();
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
		}//finally
	}//main
}//class
