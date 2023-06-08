package com.nit.jdbc3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PsScrollableRsTest {

	private static final String EMP_SELECT_QUERY = "SELECT EMPNO,ENAME,JOB,SAL FROM EMP";
	
	public static void main(String[] args) {
		
		
		try(Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","IMSHYAM","URVIL");
				PreparedStatement ps = con.prepareStatement(EMP_SELECT_QUERY,
																			ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
																									//type										,					//mode
				ResultSet rs = ps.executeQuery();
				) {
			
			if(rs != null) {
				
				System.out.println("RS records top to bottom");
				while(rs.next()) {
					System.out.println(rs.getRow()+"-------->"+rs.getInt(1)+"\t\t"+rs.getString(2)+"\t\t"+rs.getString(3)+"\t\t\t"+rs.getFloat(4));
				}

				System.out.println("=====================================");
				System.out.println("RS record bottom to top");
				rs.afterLast();
				while(rs.previous()) {
					System.out.println(rs.getRow()+"-------->"+rs.getInt(1)+"\t\t"+rs.getString(2)+"\t\t"+rs.getString(3)+"\t\t"+rs.getFloat(4));
				}
				
				System.out.println("=====================================");
				System.out.println("RS First record");
				rs.first();
				System.out.println(rs.getRow()+"-------->"+rs.getInt(1)+"\t\t"+rs.getString(2)+"\t\t"+rs.getString(3)+"\t\t"+rs.getFloat(4));
				
				System.out.println("=====================================");
				System.out.println("RS Last record");
				rs.last();
				System.out.println(rs.getRow()+"-------->"+rs.getInt(1)+"\t\t"+rs.getString(2)+"\t\t"+rs.getString(3)+"\t\t"+rs.getFloat(4));
				
				System.out.println("=====================================");
				System.out.println("RS - From the top 3rd record");
				rs.absolute(3);
				System.out.println(rs.getRow()+"-------->"+rs.getInt(1)+"\t\t"+rs.getString(2)+"\t\t"+rs.getString(3)+"\t\t"+rs.getFloat(4));
				
				System.out.println("=====================================");
				System.out.println("RS - From the bottom 6th record");
				rs.absolute(-6);
				System.out.println(rs.getRow()+"-------->"+rs.getInt(1)+"\t\t"+rs.getString(2)+"\t\t"+rs.getString(3)+"\t\t"+rs.getFloat(4));
				
				System.out.println("=====================================");
				System.out.println("RS - relative(4)");
				rs.relative(4);
				System.out.println(rs.getRow()+"-------->"+rs.getInt(1)+"\t\t"+rs.getString(2)+"\t\t"+rs.getString(3)+"\t\t"+rs.getFloat(4));
				
				System.out.println("=====================================");
				System.out.println("RS - relative(-4)");
				rs.relative(-4);
				System.out.println(rs.getRow()+"-------->"+rs.getInt(1)+"\t\t"+rs.getString(2)+"\t\t"+rs.getString(3)+"\t\t"+rs.getFloat(4));
				
			}//if
			
			
			
		}//try
		catch(SQLException se) {
			se.printStackTrace();
		}
	}//main
}//class
