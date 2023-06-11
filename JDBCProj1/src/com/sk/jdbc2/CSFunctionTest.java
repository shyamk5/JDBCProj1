package com.sk.jdbc2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

public class CSFunctionTest {
	
	private final static String CALL_FX_QUERY = "{?=call FX_GET_STUDENT_DETAILS_BY_NO(?,?,?) }";

	public static void main(String[] args) {
		
		try(Scanner scn = new Scanner(System.in)) {
			int num = 0;
			//read inputs
			if(scn != null) {
				System.out.print("Enter Student id:: ");
				num = scn.nextInt();
			}
			
			try(Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","IMSHYAM","URVIL");
					java.sql.CallableStatement cs = con.prepareCall(CALL_FX_QUERY);
					) {
				
				//register return,OUT param with JDBC types
				if(cs != null) {
					cs.registerOutParameter(1, Types.FLOAT);//savg
					cs.registerOutParameter(3, Types.VARCHAR);//name
					cs.registerOutParameter(4, Types.VARCHAR);//sadd
				}
				
				//set IN param value
				if(cs != null)
					cs.setInt(2, num);
				
				//Call execute PL/SQL function
				if(cs != null)
					cs.execute();
				
				//gather results from return, OUT param
				if(cs != null) {
					System.out.println("Student Name: "+cs.getString(3));
					System.out.println("Student Address: "+cs.getString(4));
					System.out.println("Student AVG: "+cs.getFloat(1));
				}
				
				
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
