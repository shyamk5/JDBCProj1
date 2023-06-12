package com.sk.jdbc4;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.sql.SQLException;

import oracle.jdbc.rowset.OracleWebRowSet;

public class WebRowSetTest {

	public static void main(String[] args) {
		
		try(OracleWebRowSet wRowSet = new OracleWebRowSet()) {
			wRowSet.setUrl("jdbc:oracle:thin:@localhost:1521:orcl");
			wRowSet.setUsername("IMSHYAM");
			wRowSet.setPassword("URVIL");
			wRowSet.setCommand("SELECT * FROM STUDENT");
			wRowSet.execute();
			while(wRowSet.next()) {
				System.out.println(wRowSet.getString(1)+"    "+wRowSet.getString(2)+"    "+wRowSet.getString(3)+"    "+wRowSet.getString(4));
			}//while
			System.out.println("----------------------\n");
			OutputStream os = new FileOutputStream("student.xml");
			wRowSet.writeXml(os);
			//wRowSet.writeXml(System.out);
			
		}//try
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}//main
}//class
