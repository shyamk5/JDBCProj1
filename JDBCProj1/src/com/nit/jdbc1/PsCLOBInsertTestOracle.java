package com.nit.jdbc1;

import java.io.FileReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class PsCLOBInsertTestOracle {

	private final static String JOB_SEEKER_INSERT_QUERY = "INSERT INTO JOBSEEKER_INFO VALUES(MAPP_SEQ.NEXTVAL,?,?,?,?)";
	
	public static void main(String[] args) {
		
		try(Scanner scn = new Scanner(System.in)) {
			
			String name = null;
			String addrs = null;
			String resumeLocation = null;
			String dob = null;
			
			//read inputs
			System.out.print("Enter Job Seeker Name:: ");
			name = scn.nextLine().toUpperCase();
			System.out.print("Enter Job Seeker Address:: ");
			addrs = scn.nextLine().toUpperCase();
			System.out.print("Enter Your Resume Location(File path):: ");
			resumeLocation = scn.next().replace("?","");
			System.out.print("Enter Your DOB(YYYY-MM-dd):: ");
			dob = scn.next();
			
			//convert String date format to java.sql.Date class object
			java.sql.Date sqdob = java.sql.Date.valueOf(dob);
			
			try(Reader reader = new FileReader(resumeLocation)) {
				
				try(
						Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","IMSHYAM","URVIL");
						PreparedStatement ps = con.prepareStatement(JOB_SEEKER_INSERT_QUERY);
					) {
							
						//set SQL param values
						if(ps != null) {
							ps.setString(1, name);
							ps.setString(2, addrs);
							ps.setCharacterStream(3, reader);
							ps.setDate(4, sqdob);
						}//if
						
						//execute the query
						int count = 0;
						if(ps != null)
							count = ps.executeUpdate();
						
						if(count == 0)
							System.out.println("Record not Inserted");
						else
							System.out.println("Record Inserted");						
						
					}//try
			}//try
		}//try
		catch(SQLException se) {
			se.printStackTrace();
			System.out.println("Problem in Record Insertion");
		}
		catch(Exception e) {
			e.printStackTrace();
		}

	}//main

}//class

