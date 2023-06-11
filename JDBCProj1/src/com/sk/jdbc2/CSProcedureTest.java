package com.sk.jdbc2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

public class CSProcedureTest {
	
	private final static String CALL_PROCEDURE = "{CALL FIRST_PRO(?,?,?) }";
	
	public static void main(String[] args) {
		
		try(Scanner scn = new Scanner(System.in)) {
			int firstNum = 0;
			int secondNum = 0;
			
			//read inputs
			if(scn != null) {
				System.out.print("Enter First value: ");
				firstNum = scn.nextInt();
				System.out.print("Enter Second value: ");
				secondNum = scn.nextInt();
			}
			
			//establish the COnnection
			try(Connection con =  DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","IMSHYAM","URVIL");
					java.sql.CallableStatement cs = con.prepareCall(CALL_PROCEDURE);	) {
				
				if(cs != null) {
					//register OUT param with JDBC data type
					cs.registerOutParameter(3, Types.INTEGER);
					
					//set values to IN param
					cs.setInt(1, firstNum);
					cs.setInt(2, secondNum);
					
					//execute call
					cs.execute();
					
					//gather results from OUT param
					int result = 0;
					result = cs.getInt(3);
					System.out.println("Sum is: "+result);
				}//if
			}//try-2
		}//try-1
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}

	}//main

}//class
