package com.nit.jdbc4;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class ResultSetMetaDataTest {

	public static void main(String[] args) {
		
		try(
				Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","IMSHYAM","URVIL"); //Oracle
				//Connection con = DriverManager.getConnection("jdbc:mysql:///NTAJ415DB2","root","URVIL")  //mySQL
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery("Select * from Student");
				) {			
			
			ResultSetMetaData rsmd = null;
			
			if(rs != null) {
				
				rsmd = rs.getMetaData();
				
				//get Column count
				if(rsmd != null) {
					int colCount = rsmd.getColumnCount();
					
					for(int i=1; i<=colCount; ++i) {
						System.out.print(rsmd.getColumnName(i)+"\t\t");
					}
					System.out.println();
					System.out.println("=====\t\t=====\t\t=====\t\t=====");
					
					for(int i=1; i<=colCount; ++i) {
						System.out.print(rsmd.getColumnTypeName(i)+" \t");
					}
					System.out.println();
					
					while(rs.next()) {
						for(int i=1; i<=colCount; ++i) {
							System.out.print(rs.getString(i)+" \t\t");
						}
						System.out.println();
					}
					
					
					
				}//if
				
				
			}//rs
		}//try
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}

	}//main

}//class
