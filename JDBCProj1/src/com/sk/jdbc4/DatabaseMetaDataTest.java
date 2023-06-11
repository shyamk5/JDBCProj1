package com.sk.jdbc4;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseMetaDataTest {

	public static void main(String[] args) {
		
		try(
				//Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","IMSHYAM","URVIL"); //Oracle
				Connection con = DriverManager.getConnection("jdbc:mysql:///NTAJ415DB2","root","URVIL")  //mySQL
				) {
			
			//create DatabaseMetaData object
			DatabaseMetaData dbmd = con.getMetaData();
			
			if(dbmd != null) {
				System.out.println("DB s/w name:: "+dbmd.getDatabaseProductName());
				System.out.println("DB s/w version:: "+dbmd.getDatabaseProductVersion());
				System.out.println("JDBC Driver Name:: "+dbmd.getDriverName());
				System.out.println("JDBC Driver Version:: "+dbmd.getDriverVersion());
				System.out.println("All SQL Keywords:: "+dbmd.getSQLKeywords());
				System.out.println("All Numeric Functions:: "+dbmd.getNumericFunctions());
				System.out.println("All System Functions:: "+dbmd.getSystemFunctions());
				System.out.println("All String Functions:: "+dbmd.getStringFunctions());
				System.out.println("Max Chars in Table Name:: "+dbmd.getMaxTableNameLength());
				System.out.println("Max Tables in a Select Query:: "+dbmd.getMaxTablesInSelect());
				System.out.println("Max Row Size:: "+dbmd.getMaxRowSize());
				System.out.println("Supports PL/SQL Procedured ? ::  "+dbmd.supportsStoredProcedures());
			}
			
		}
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}

	}//main

}//class
