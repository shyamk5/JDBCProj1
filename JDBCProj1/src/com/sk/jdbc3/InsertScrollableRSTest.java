package com.sk.jdbc3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class InsertScrollableRSTest {

	private static final String STUDENT_SELECT_QUERY = "SELECT STID,SNAME,SADD,SAVG FROM STUDENT";
	
	public static void main(String[] args) {
		
		try(Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","IMSHYAM","URVIL");
				Statement st = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
																									//type										,					//mode
				ResultSet rs = st.executeQuery(STUDENT_SELECT_QUERY);
				) {
			
			if(rs != null) {
				//select operation
				while(rs.next()) {
					System.out.println(rs.getInt(1)+"-----"+rs.getString(2)+"-----"+rs.getString(3)+"--------------"+rs.getFloat(4));
				}
				
				//Insert operation 
				/*
				rs.moveToInsertRow();
				rs.updateInt(1, 4567);
				rs.updateString(2, "PAWAN");
				rs.updateString(3, "NAUTAN");
				rs.updateFloat(4, 82.99f);
				
				rs.insertRow();
				
				//update operation
				rs.absolute(7);
				rs.updateString(2, "ROHIT");
				rs.updateRow();
				
				
				//delete operation
				rs.absolute(7);
				rs.deleteRow();
				*/
				
				
			}
			
			
			
		}//try
		catch(SQLException se) {
			se.printStackTrace();
		}

	}

}
