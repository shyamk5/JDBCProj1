package com.sk.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SelectTestTWRWithPostgreSQL {

	public static void main(String[] args) {
		
		try {
			//load JDBC driver class
			Class.forName("org.postgresql.Driver");
		}
		catch(ClassNotFoundException cnf) {
			cnf.printStackTrace();
		}
		
		try(
				//Establish the connection between Java app and db s/w
				Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/NTAJ415DB","postgres","URVIL");
				//Connection con = DriverManager.getConnection("jdbc:postgresql:NTAJ415DB","postgres","URVIL");
				//create statement object
				Statement st = con.createStatement();
				//send and execute SQL query in DB s/w
				ResultSet rs = st.executeQuery("SELECT * from product");	) {
			
			//Process the resultset object
			if(rs != null) {
				boolean flag = false;
				int count = 0;
				while(rs.next()) {
					flag=true;
					System.out.println(rs.getInt(1)+"\t\t\t"+rs.getString(2)+"\t\t"+rs.getInt(3)+"\t\t"+rs.getInt(4)+"\t\t"+rs.getString(5));
					count++;
				}
				if(flag == false)
					System.out.println("no records found");
				else
					System.out.println(count+" records found");
			}
			
		}//try
		catch(SQLException se) {
			se.printStackTrace();
		}

	}//main

}//class
