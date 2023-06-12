package com.sk.jdbc4;

import java.sql.SQLException;

import oracle.jdbc.rowset.OracleCachedRowSet;

public class CachedRowSetTest {

	public static void main(String[] args) {
		
		try(OracleCachedRowSet cRowSet = new OracleCachedRowSet()) {
			cRowSet.setUrl("jdbc:oracle:thin:@localhost:1521:orcl");
			cRowSet.setUsername("IMSHYAM");
			cRowSet.setPassword("URVIL");
			cRowSet.setCommand("SELECT * FROM STUDENT");
			cRowSet.execute();
			while(cRowSet.next()) {
				System.out.println(cRowSet.getString(1)+"   "+cRowSet.getString(2)+"   "+cRowSet.getString(3)+"   "+cRowSet.getString(4));
			}
			System.out.println("----------------------\n");
			System.out.println("Stop DB s/w from Services.msc");
			Thread.sleep(4000);
			cRowSet.absolute(3);
			cRowSet.updateFloat(4, 45.99f);
			cRowSet.updateRow();
			System.out.println("Offline Modification happened");
			System.out.println("Start DB s/w from services.msc");
			System.out.println("----------------------\n");
			Thread.sleep(6000);
			cRowSet.acceptChanges();
			//after Offline updation
			while(cRowSet.next()) {
				System.out.println(cRowSet.getString(1)+"   "+cRowSet.getString(2)+"   "+cRowSet.getString(3)+"   "+cRowSet.getString(4));
			}
			
		}//try
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}//main
}//class
