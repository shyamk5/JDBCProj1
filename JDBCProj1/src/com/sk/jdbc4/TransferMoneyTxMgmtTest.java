package com.sk.jdbc4;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class TransferMoneyTxMgmtTest {
	private static final String TXN_MGMT_QUERY = "SELECT BALANCE FROM BANK WHERE ACNO=?";
	public static void main(String[] args) {
		
		long srcAcn = 0, destAcn = 0;
		double amt = 0.0;
			
			try(Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","IMSHYAM","URVIL");
					Statement st = con.createStatement();
					PreparedStatement ps = con.prepareStatement(TXN_MGMT_QUERY);
					Scanner scn = new Scanner(System.in);
					) {
				
				//read inputs
				if(scn != null) {
					System.out.print("Enter Source Account Number:: ");
					srcAcn = scn.nextLong();
					System.out.print("Enter Destination Account Number:: ");
					destAcn = scn.nextLong();
					System.out.print("Enter Balance:: ");
					amt = scn.nextDouble();
				}
				
				//set values to the query param
				if(ps != null)
					ps.setLong(1, srcAcn);
					
				try(ResultSet rs = ps.executeQuery()) {
					float bal = 0.0f;
					if(rs.next()) {
						bal = rs.getFloat(1);
						if(amt>bal) {
							System.out.println("Insufficient Balance");
							return;
						}
					}
					else {
						System.out.println("Source Account not found");
					}
				}//try

				//begin the txMgmt
				if(con != null)
					con.setAutoCommit(false);
				
				if(st != null) {
					//for deducting the amount from source account
					st.addBatch("UPDATE BANK SET BALANCE=BALANCE-"+amt+"WHERE ACNO="+srcAcn);
					//for crediting the amount to the destination account
					st.addBatch("UPDATE BANK SET BALANCE=BALANCE+"+amt+"WHERE ACNO="+destAcn);
					
					//execute the batch
					int result[] = st.executeBatch();
					
					//process the results from txnMgmt
					boolean flag = true;
					for(int i=0; i<result.length; ++i) {
						if(result[i] == 0) {
							flag=false;
							break;
						}
					}
					
					if(flag) {
						con.commit();
						System.out.println("Money Transferred");
					} 
					else {
						con.rollback();
						System.out.println("Money not Transferred(Rolled Back)");
					}
					
			}//if
		}//try
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}

	}

}
