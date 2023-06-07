package com.nit.jdbc2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

public class CSProcedureTest_Auth {
	
	private static final String CALL_PROCEDURE = "{CALL P_AUTHENTICATE(?,?,?) }";

	public static void main(String[] args) {
		
		try(Scanner scn = new Scanner(System.in)) {
			String username = null, password = null;
			//read inputs
			if(scn != null) {
				System.out.print("Enter Username:: ");
				username = scn.next();
				System.out.print("Enter Password:: ");
				password = scn.next();
			}
			
			try(Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","IMSHYAM","URVIL");
					java.sql.CallableStatement cs = con.prepareCall(CALL_PROCEDURE); ) {
				
				if(cs != null) {
					//register OUT param with JDBC types
					cs.registerOutParameter(3, Types.VARCHAR);
				}
				
				if(cs != null) {
					//set values to IN params
					cs.setString(1, username);
					cs.setString(2, password);
				}
				
				//call PL/SQL procedure
				if(cs != null)
					cs.execute();
				
				//gather result from OUT param
				String result = null;
				if(cs != null) {
					result = cs.getString(3);
					System.out.println(result);
				}//if
				
			}//try
		}//try
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}//main

}//class
