package com.sk.jdbc1;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class MatrimonyAppOracle {

	private final static String MAPP_INSERT_QUERY = "INSERT INTO MATRIMONY_APP VALUES(MAPP_SEQ.NEXTVAL,?,?,?,?,?,?,?,?)";
	
	public static void main(String[] args) {
		
		try(Scanner scn = new Scanner(System.in)) {
			
			String cname = null;
			String gender = null;
			String dob = null;
			String photoLocation = null;
			String resumeLocation = null;
			String biodataLocation = null;
			String audioInfo = null;
			String videoInfo = null;
			
			//read inputs
			System.out.print("Enter Your Name: ");
			cname = scn.next().toUpperCase();
			System.out.print("Enter Your Gender(M/F): ");
			gender = scn.next().toUpperCase();
			System.out.print("Enter Your Date of Birth(dd-MM-YYYY): ");
			dob = scn.next();
			System.out.print("Enter Your Photo Location (file path): ");
			photoLocation = scn.next().replace("?", "");
			System.out.print("Enter Your Resume Location (file path): ");
			resumeLocation = scn.next().replace("?", "");
			System.out.print("Enter Your Biodata Location (file path): ");
			biodataLocation = scn.next().replace("?", "");
			System.out.print("Enter Your Fav. Audio Location (file path): ");
			audioInfo = scn.next().replace("?", "");
			System.out.print("Enter Your Fav. Video Location (file path): ");
			videoInfo = scn.next().replace("?", "");
			
			//convert String date values to java.sql.Date class obj
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-YYYY");
			java.util.Date udob = sdf.parse(dob);
			
			//convert java.util.Date class obj to java.sql.Date class object
			long ms = udob.getTime();
			java.sql.Date sdob = new java.sql.Date(ms);
			
			try(InputStream isp = new FileInputStream(photoLocation);
					Reader resumeReader = new FileReader(resumeLocation);
					Reader biodataReader = new FileReader(biodataLocation);
					Reader audioReader = new FileReader(audioInfo);
					Reader videoReader = new FileReader(videoInfo);
					) {
				
				try(Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","IMSHYAM","URVIL");
						PreparedStatement ps = con.prepareStatement(MAPP_INSERT_QUERY);
						) {
					//set Query param values
					if(ps != null) {
						ps.setString(1, cname);
						ps.setString(2, gender);
						ps.setDate(3, sdob);
						ps.setBinaryStream(4, isp);
						ps.setCharacterStream(5, resumeReader);
						ps.setCharacterStream(6, biodataReader);
						ps.setCharacterStream(7, audioReader);
						ps.setCharacterStream(8, videoReader);
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
