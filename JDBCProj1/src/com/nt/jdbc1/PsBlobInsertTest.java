package com.nt.jdbc1;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class PsBlobInsertTest {
	private final static String INSERT_ARTIST_QUERY = "INSERT INTO ARTIST_INFO VALUES(AID_SEQ.NEXTVAL,?,?,?)";
	public static void main(String[] args) {
		
		
		try(Scanner scn = new Scanner(System.in)) {
			//read inputs
			String name = null;
			String addrs = null;
			String photoLocation = null;
			
			if(scn != null) {
				System.out.print("Enter Artist Name:: ");
				name = scn.next().toUpperCase();
				System.out.print("Enter Artist Address:: ");
				addrs = scn.next().toUpperCase();
				System.out.print("Enter Artist Image Path:: ");
				photoLocation = scn.next().replace("?", "");
			}
			//create InputStream pointing photo file
			try(InputStream is = new FileInputStream(photoLocation)) {
				//establish the connection and preparedStatement object
				try(Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","IMSHYAM","URVIL");
						PreparedStatement ps = con.prepareStatement(INSERT_ARTIST_QUERY);
						) {
					
					//set values to Query param
					if(ps != null) {
						ps.setString(1, name);
						ps.setString(2, addrs);
						ps.setBinaryStream(3, is);
					}
					//execute the query
					int count = 0;
					if(ps != null)
						count = ps.executeUpdate();
					
					//process the result
					if(count == 0)
						System.out.println("record not inserted");
					else
						System.out.println("record inserted");
					
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
