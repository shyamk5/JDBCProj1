package com.sk.jdbc2;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class CS_ProcedureCall_MySQL {

	private static final String PROCEDURE_CALL = "{CALL P_GET_PROD_DETAILS_BY_PRICE_RANGE(?,?) }";
	
	public static void main(String[] args) {
		
		try(Scanner scn = new Scanner(System.in);
				Connection con = DriverManager.getConnection("jdbc:mysql:///NTAJ415DB2","root","URVIL");
				CallableStatement cs = con.prepareCall(PROCEDURE_CALL);
				) {
			float startPrice = 0.0f;
			float endPrice = 0.0f;
			//read inputs
			if(scn != null) {
				System.out.print("Enter Start Price:: ");
				startPrice = scn.nextFloat();
				System.out.print("Enter End Price:: ");
				endPrice = scn.nextFloat();
			}
		
			if(cs != null) {
				//set values to IN params
				cs.setFloat(1, startPrice);
				cs.setFloat(2, endPrice);
				
				//call PL/SQL procedure
				cs.execute();
				
				try(ResultSet rs = cs.getResultSet()) {
					//display records
					boolean flag = false;
					while(rs.next()) {
						flag = true;
						System.out.println(rs.getInt(1)+"--------"+rs.getString(2)+"--------"+rs.getFloat(3)+"--------"+rs.getInt(4)+"--------"+rs.getString(5));
					}
					if(flag == false) {
						System.out.println("no records found");
					}
					
				}//try
				
				
			}//if
			
		}//try
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}//main
}//class
