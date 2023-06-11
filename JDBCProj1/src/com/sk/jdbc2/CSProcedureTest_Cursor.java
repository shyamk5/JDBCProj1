package com.sk.jdbc2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import oracle.jdbc.OracleTypes;

public class CSProcedureTest_Cursor {
	
	private static final String CALL_PROCEDURE = "{CALL P_GET_EMP_DETAILS_BY_CHAR(?,?) }";

	public static void main(String[] args) {
		
		try(Scanner scn = new Scanner(System.in)) {
			String initchar = null;
			//read inputs
			if(scn != null) {
				System.out.print("Enter initial character of Employee name:: ");
				initchar = scn.next().toUpperCase()+"%";
			}
			
			try(Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","IMSHYAM","URVIL");
					java.sql.CallableStatement cs = con.prepareCall(CALL_PROCEDURE); ) {
				
				if(cs != null) {
					//register OUT param with JDBC types
					cs.registerOutParameter(2,OracleTypes.CURSOR);
				}
				
				if(cs != null) {
					//set values to IN params
					cs.setString(1, initchar);
				}
				
				//call PL/SQL procedure
				if(cs != null)
					cs.execute();
				
				//gather result from OUT param
				try(ResultSet rs = (ResultSet)cs.getObject(2)) {
					if(rs != null) {
						boolean flag = false;
						while(rs.next()) {
							flag = true;
							System.out.println(rs.getInt(1)+"\t\t"+rs.getString(2)+"\t\t"+rs.getString(3)+"\t\t"+rs.getFloat(4));
						}
						if(flag == false)
							System.out.println("Records not found");
					}
					
				}//try
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
