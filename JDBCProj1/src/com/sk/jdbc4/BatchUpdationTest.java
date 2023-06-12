package com.sk.jdbc4;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class BatchUpdationTest {

	public static void main(String[] args) {
		
		try(Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","IMSHYAM","URVIL");
				Statement st = con.createStatement();
				) {
			st.addBatch("INSERT INTO TEST1 VALUES(1,'A')");
			st.addBatch("UPDATE TEST1 SET SNAME='PRADEEP' WHERE SNO=2");
			st.addBatch("DELETE FROM TEST1 WHERE SNO=1");
			
			//execute the batch 
			int result[] = st.executeBatch();
			int sum = 0;
			//get the updation status
			for(int i =0; i<result.length; i++) {
				sum+=result[i];
			}
			System.out.println("Action performed:: "+sum+" times");
			
			
		}//try
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}//main
}//class
