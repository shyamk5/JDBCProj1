package com.sk.jdbc1;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import org.apache.commons.io.IOUtils;

public class PsRetrieveCLOBTestMySQL {

	private final static String JOB_SEEKER_RETRIEVE_QUERY = "SELECT JSID,JSNAME,JSADDRS,RESUME,PHOTO,JSDOB FROM JOBSEEKER_INFO WHERE JSID=?";
	
	public static void main(String[] args) {
		
try(Scanner scn = new Scanner(System.in)) {
			
			int jsid = 0;
			
			//read input
			if(scn != null) {
				System.out.print("Enter Job Seeker ID:: ");
				jsid = scn.nextInt();
			}
			
				try(
						Connection con = DriverManager.getConnection("jdbc:mysql:///NTAJ415DB2","root","URVIL");
						PreparedStatement ps = con.prepareStatement(JOB_SEEKER_RETRIEVE_QUERY);
					) {
							
						//set SQL param values
						if(ps != null) {
							ps.setInt(1, jsid);
						}//if
						
						//execute the query
						try(ResultSet rs = ps.executeQuery()) {
							
							if(rs != null) {
								if(rs.next()) {
									jsid = rs.getInt(1);
									String name = rs.getString(2);
									String jsaddrs = rs.getString(3);
									
									System.out.println(jsid+"		"+name+"		"+jsaddrs);
									
									try(Reader reader = rs.getCharacterStream(4);
											InputStream is = rs.getBinaryStream(5);
											Writer writer = new FileWriter("retrieve_resume.txt");
											OutputStream os = new FileOutputStream("retrieve_photo.jpg")){
										
										IOUtils.copy(is, os);
										IOUtils.copy(reader, writer);
										System.out.println("BLOB and CLOB files are Retrieved and Stored in the file");
										
									}//try
								}//if
								else {
									System.out.println("Record not Found");
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
