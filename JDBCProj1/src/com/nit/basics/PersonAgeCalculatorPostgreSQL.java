package com.nit.basics;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class PersonAgeCalculatorPostgreSQL {
	
	private final static String RETRIEVE_AGE_QUERY = "SELECT AGE(DOB,CURRENT_DATE) FROM person_info_dates WHERE PID=?";
	
	public static void main(String[] args) {
		
		try(Scanner scn = new Scanner(System.in)) {
			int pid = 0;
			String age = null;
			//read inputs
			
			if(scn != null) {
				System.out.print("Enter the Person ID:: ");
				pid = scn.nextInt();
			}
			
			try(
					Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/NTAJ415DB","postgres","URVIL");
					PreparedStatement ps = con.prepareStatement(RETRIEVE_AGE_QUERY);		) {
				
				//set query param
				if(ps != null)
					ps.setInt(1, pid);
				
				try(ResultSet rs = ps.executeQuery()) {
					//process the result set Object
					if(rs != null) {
						if(rs.next()) {
							age = rs.getString(1);
							age = age.replace("-", "");
							System.out.println("Person Age:: "+age);
						}
						else {
							System.out.println("Person not Found");
						}
					}//if
					
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
