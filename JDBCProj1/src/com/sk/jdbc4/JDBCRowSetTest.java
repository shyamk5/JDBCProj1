package com.sk.jdbc4;

import java.sql.SQLException;

import oracle.jdbc.rowset.OracleJDBCRowSet;

public class JDBCRowSetTest {

	public static void main(String[] args) {
		
		try(OracleJDBCRowSet jRowSet = new OracleJDBCRowSet()) {
			jRowSet.setUrl("jdbc:oracle:thin:@localhost:1521:orcl");
			jRowSet.setUsername("IMSHYAM");
			jRowSet.setPassword("URVIL");
			jRowSet.setCommand("SELECT * FROM STUDENT");
			jRowSet.execute();
			while(jRowSet.next()) {
				System.out.println(jRowSet.getString(1)+"    "+jRowSet.getString(2)+"    "+jRowSet.getString(3)+"    "+jRowSet.getString(4));
			}
		}
		catch(SQLException se) {
			se.printStackTrace();
		}
	}

}
