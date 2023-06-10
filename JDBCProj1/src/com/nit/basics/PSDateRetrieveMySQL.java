package com.nit.basics;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

public class PSDateRetrieveMySQL {
	
	private static final String RETRIEVE_DATE_QUERY = "SELECT PID,PNAME,DOB,DOJ,DOM FROM PERSON_INFO_DATES";
	public static void main(String[] args) {
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			
			//Load the JDBC Driver class
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			//Establish the Connection
			con = DriverManager.getConnection("jdbc:mysql:///NTAJ415DB2","root","URVIL");
			
			//Create Preapred Statement obj
			if(con != null)
				ps = con.prepareStatement(RETRIEVE_DATE_QUERY);
			
			//execute query
			if(ps != null)
				rs = ps.executeQuery();
			/*
			//process ResultSet obj
			if(rs != null) {
				while(rs.next()) {
					System.out.println(rs.getInt(1)+"\t\t"+rs.getString(2)+"\t\t"+rs.getDate(3)+"\t\t"+rs.getDate(4)+"\t\t"+rs.getDate(5));
				}//while
			}//if
			*/
			
			System.out.println("=======================================================================\n\n");
			
			//process ResultSet obj
			if(rs != null) {
				while(rs.next()) {
					int pid = rs.getInt(1);
					String pname = rs.getString(2);
					java.sql.Date sqldob = rs.getDate(3);
					java.sql.Date sqldoj = rs.getDate(4);
					java.sql.Date sqldom = rs.getDate(5);
					
					//convert  java.sql.Date class obj to String date value
					SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-YYYY");
					String sdob = sdf.format(sqldob);
					String sdoj = sdf.format(sqldoj);
					String sdom = sdf.format(sqldom);
					
					System.out.println(pid+"\t\t"+pname+"\t\t"+sdob+"\t\t"+sdoj+"\t\t"+sdom);
					
				}//while
			}//if
			
			
			
			
		}//try
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			//close JDBC objects
			try {
				if(ps != null)
					ps.close();
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
				if(rs != null)
					rs.close();
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
		}//finally
	}//main
}//class
