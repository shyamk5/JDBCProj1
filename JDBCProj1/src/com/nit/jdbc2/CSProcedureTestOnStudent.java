package com.nit.jdbc2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

public class CSProcedureTestOnStudent {
	
	private final static String CALL_PROCEDURE = "{CALL P_GET_STUDENT_DETIALS_BY_NO(?,?,?,?) }";
	
	public static void main(String[] args) {
		
		try(Scanner scn = new Scanner(System.in)) {
			int sno = 0;
			
			//read inputs
			if(scn != null) {
				System.out.print("Enter Student id: ");
				sno = scn.nextInt();
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
					cs.setInt(1, sno);
					
					//execute call
					cs.execute();
					
					//gather results from OUT param
					String name = cs.getString(2);
					String sadd = cs.getString(3);
					Float marks = cs.getFloat(4);					
					System.out.println("Name\t\tAddress\t\tMarks");
					System.out.println("=====\t\t======\t\t=====");
					System.out.println(name+"\t\t"+sadd+"\t\t"+marks);
					
				}//if
			}//try-2
		}//try-1
		catch(SQLException se) {
			System.err.println("No Data Found for this Student id");
			System.err.println("Error:: "+se.getErrorCode());
		}
		catch(Exception e) {
			System.err.println("Error:: "+e.toString());
		}

	}//main

}//class
