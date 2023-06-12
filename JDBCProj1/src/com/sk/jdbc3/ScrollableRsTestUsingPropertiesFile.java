package com.sk.jdbc3;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class ScrollableRsTestUsingPropertiesFile {

	private static final String EMP_SELECT_QUERY = "SELECT EMPNO,ENAME,JOB,SAL FROM EMP";
	
	public static void main(String[] args) {
		
		Properties props = null;
		try(InputStream is = new FileInputStream("jdbc.properties")) {
			props = new Properties();
			props.load(is);
		}
		catch(FileNotFoundException fnfe) {
			fnfe.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		try(Connection con = DriverManager.getConnection(props.getProperty("jdbc.url"),
																									props.getProperty("jdbc.username"),
																									props.getProperty("jdbc.pwd"));
				Statement st = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
																									//type										,					//mode
				ResultSet rs = st.executeQuery(EMP_SELECT_QUERY);
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
