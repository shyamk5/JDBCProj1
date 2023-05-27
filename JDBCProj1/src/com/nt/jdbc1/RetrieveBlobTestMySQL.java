package com.nt.jdbc1;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import org.apache.commons.io.IOUtils;

public class RetrieveBlobTestMySQL {
	
	private final static String RETRIEVE_BLOB_QUERY = "SELECT AID,NAME,ADDRS,PHOTO FROM ARTIST_INFO WHERE AID = ?";

	public static void main(String[] args) {
		
		try(Scanner scn = new Scanner(System.in)) {
			//read inputs
			int aid = 0;
			if(scn != null) {
				System.out.print("Enter Artist Id: ");
				aid = scn.nextInt();
			}
			//create Connection and PreparedStatement object
			try(Connection con = DriverManager.getConnection("jdbc:mysql:///NTAJ415DB2","root","URVIL");
					PreparedStatement ps = con.prepareStatement(RETRIEVE_BLOB_QUERY);
					) {
				//set query param values
				if(ps != null)
					ps.setInt(1, aid);
				
				//execute query
				try(ResultSet rs = ps.executeQuery()) {
					//process the results
					if(rs != null) {
						if(rs.next()) {
							int a_aid = rs.getInt(1);
							String name = rs.getString(2);
							String addrs = rs.getString(3);
							System.out.println(a_aid+"\t"+name+"\t"+addrs);
							//get InputStream pointing to BLOB col values
							try(InputStream is = rs.getBinaryStream(4);
									OutputStream os = new FileOutputStream("image_retrieve_mysql.jpg");
									) {
								//copy BLOB col value to destination file
								IOUtils.copy(is,os);
								System.out.println("BLOB value is retrieved and stored in the file");
							}//try
						}//if
						else {
							System.out.println("Record not found");
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
