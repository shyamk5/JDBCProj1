package com.sk.jdbc4;

import java.sql.SQLException;

import oracle.jdbc.rowset.OracleCachedRowSet;
import oracle.jdbc.rowset.OracleJoinRowSet;
import oracle.jdbc.rowset.OracleWebRowSet;

public class JoinRowSetTest {

	public static void main(String[] args) {
		
		try(OracleJoinRowSet jRowSet = new OracleJoinRowSet();
				OracleCachedRowSet crs1 = new OracleCachedRowSet();
				OracleCachedRowSet crs2 = new OracleCachedRowSet();				
				) {
			
			crs1.setUrl("jdbc:oracle:thin:@localhost:1521:orcl");
			crs1.setUsername("IMSHYAM");
			crs1.setPassword("URVIL");
			crs1.setMatchColumn(4);
			crs1.setCommand("SELECT EMPNO,ENAME,SAL,DEPTNO FROM EMP");
			crs1.execute();
			
			crs2.setUrl("jdbc:oracle:thin:@localhost:1521:orcl");
			crs2.setUsername("IMSHYAM");
			crs2.setPassword("URVIL");
			crs2.setMatchColumn(1);
			crs2.setCommand("SELECT DEPTNO,DNAME,LOC FROM DEPT");
			crs2.execute();
			
			//add multiple cached rowset to join row set
			jRowSet.addRowSet(crs2);
			jRowSet.addRowSet(crs1);
	
			while(jRowSet.next()) {
				System.out.println(jRowSet.getString(1)+"    "+jRowSet.getString(2)+"    "+jRowSet.getString(3)+"    "+jRowSet.getString(4)+"    "+jRowSet.getString(5)+"   "+jRowSet.getString(6));
			}//while
			
			
		}//try
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}//main
}//class
