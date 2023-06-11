//SelectTestTWR.java
package com.sk.basics;

/*
 * 	JDBC SELECT Test using Try With Resource
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SelectTestTWR {
		private static final String SELECT_QUERY = "SELECT ENAME,JOB,SAL FROM EMP";
	public static void main(String[] args) {
		
		
		try( Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","IMSHYAM","URVIL");
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery(SELECT_QUERY);
				) {
			
			//process the ResultSet object
			if(rs != null) {
				boolean flag = false;
				System.out.println("ENAME\t\tJOB\t\t\tSAL");
				System.out.println("======\t\t=====\t\t=====");
				while(rs.next()) {
					flag = true;
					System.out.println(rs.getString(1)+"\t\t"+rs.getString(2)+"\t\t"+rs.getFloat(3));
				}//while
				if(flag == false)
					System.out.println("No records found");
			}//if
		}//try
		catch (SQLException se) {
			se.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}

	}//main

}//class
