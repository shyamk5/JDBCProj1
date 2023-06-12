package com.sk.jdbc4;

import java.sql.SQLException;
import java.util.function.Predicate;

import javax.sql.RowSet;

import oracle.jdbc.rowset.OracleFilteredRowSet;

public class FilteredRowSetTest {
	
	private static class Filter1 implements javax.sql.rowset.Predicate {
		private String cond;

		public Filter1(String cond) {
			this.cond = cond;
		}

		@Override
		public boolean evaluate(RowSet rs) {
			try {
				if(rs.getString("ENAME").startsWith(cond)) {
					return true;
				}
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
			return false;
		}

		@Override
		public boolean evaluate(Object value, int column) throws SQLException {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean evaluate(Object value, String columnName) throws SQLException {
			// TODO Auto-generated method stub
			return false;
		}
		
	}

	public static void main(String[] args) {
		
		try(OracleFilteredRowSet frs = new OracleFilteredRowSet()) {
			frs.setUrl("jdbc:oracle:thin:@localhost:1521:orcl");
			frs.setUsername("IMSHYAM");
			frs.setPassword("URVIL");
			frs.setCommand("SELECT * FROM EMP");
			frs.setFilter(new Filter1("A"));
			frs.execute();
			
			while(frs.next()) {
				System.out.println(frs.getString(1)+"    "+frs.getString(2)+"    "+frs.getString(3)+"    "+frs.getString(4));
			}//while
		}
		catch(SQLException se) {
			se.printStackTrace();
		}

	}

}
