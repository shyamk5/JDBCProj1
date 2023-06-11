package com.sk.jdbc2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

public class CSProcedureTestOnEMP {
	
	private final static String CALL_PROCEDURE = "{CALL P_GET_EMP_DETAILS_BY_ID(?,?,?,?) }";
	
	public static void main(String[] args) {
		
		try(Scanner scn = new Scanner(System.in)) {
			int empnum = 0;
			
			//read inputs
			if(scn != null) {
				System.out.print("Enter Employee id: ");
				empnum = scn.nextInt();
			}
			
			//establish the COnnection
			try(Connection con =  DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","IMSHYAM","URVIL");
					java.sql.CallableStatement cs = con.prepareCall(CALL_PROCEDURE);	) {
				
				if(cs != null) {
					//register OUT param with JDBC data type
					cs.registerOutParameter(2, Types.VARCHAR);
					cs.registerOutParameter(3, Types.VARCHAR);
					cs.registerOutParameter(4, Types.FLOAT);
					
					//set values to IN param
					cs.setInt(1, empnum);
					
					//execute call
					cs.execute();
					
					//gather results from OUT param
					String name = cs.getString(2);
					String desg = cs.getString(3);
					Float sal = cs.getFloat(4);
					System.out.println(name+"\t\t"+desg+"\t\t"+sal);
					
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
