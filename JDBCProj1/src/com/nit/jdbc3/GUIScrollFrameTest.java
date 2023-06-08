package com.nit.jdbc3;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class GUIScrollFrameTest extends JFrame implements ActionListener,WindowListener {
	
	JLabel stid,sname,sadd,savg;
	JTextField fStid, fSname, fSadd, fSavg;
	JButton JFirst, JLast, JPrevious, JNext;
	
	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	private static final String SELECT_STUDENT_QUERY = "SELECT STID,SNAME,SADD,SAVG FROM STUDENT ORDER BY STID";
	
	//constructor
	public GUIScrollFrameTest() {
		System.out.println("GUIScrollFrameTest 0-param constructor start");
		setTitle("GUIFrontend-Scroll frame");
		setSize(400,300);
		setLayout(new FlowLayout());
		
		//add comps
		
		//forStudent id
		stid = new JLabel("Student Id");
		add(stid);
		fStid = new JTextField(10);
		add(fStid);
		
		//for Student Name
		sname = new JLabel("Student Name");
		add(sname);
		fSname = new JTextField(16);
		add(fSname);
		
		//for Student Address
		sadd = new JLabel("Student Address");
		add(sadd);
		fSadd = new JTextField(20);
		add(fSadd);
		
		//for Student Marks
		savg = new JLabel("Marks");
		add(savg);
		fSavg = new JTextField(20);
		add(fSavg);
		
		//disable the Textfields
		fStid.setEditable(false);
		fSname.setEditable(false);
		fSadd.setEditable(false);
		fSavg.setEditable(false);
		
		//Buttons
		JFirst = new JButton("First");
		JNext = new JButton("Next");
		JLast = new JButton("Last");
		JPrevious = new JButton("Previous");
		add(JFirst); add(JNext); add(JLast); add(JPrevious);
		
		//register and activate ActionListener for all 4 buttons
		JFirst.addActionListener(this);
		JNext.addActionListener(this);
		JLast.addActionListener(this);
		JPrevious.addActionListener(this);
		
		//add window listener to frame
		this.addWindowListener(this);
		
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		initializeJDBC();
		System.out.println("GUIScrollFrameTest 0-param constructor start");
	}
	
	private void initializeJDBC() {
		try {
			//Establish the Connection object
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","IMSHYAM","URVIL");
			//Create PreparedSTatement object
			ps = con.prepareStatement(SELECT_STUDENT_QUERY,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
			//execute the query
			rs = ps.executeQuery();
		}
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}//initializeJDBC
	
	public static void main(String[] args) {
		System.out.println("GUIScrollFrameTest.main start");
		new GUIScrollFrameTest(); //anonymous object
		System.out.println("GUIScrollFrameTest.main end ");
	}//main

	@Override
	public void actionPerformed(ActionEvent ae) {
		boolean flag = false;
		if(ae.getSource()==JFirst) {
			System.out.println("First button is clicked");
			try {
				rs.first();
				flag = true;
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
		}
		else if(ae.getSource()==JNext) {
			System.out.println("Next Button is clicked");
			try {
				if(!rs.isLast()) {
					rs.next();
					flag = true;
				}
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
		}
		else if(ae.getSource()==JLast) {
			System.out.println("Last Button is Clicked");
			try {
				rs.last();
				flag = true;
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
		}
		else if(ae.getSource()==JPrevious) {
			System.out.println("Previous button is clicked");
			try {
				if(!rs.isFirst()) {
					rs.previous();
					flag = true;
				}
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
		}
		
		if(flag == true) {
			try {
				fStid.setText(rs.getString(1));
				fSname.setText(rs.getString(2));
				fSadd.setText(rs.getString(3));
				fSavg.setText(rs.getString(4));
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
		}//if
		
	}//actionPerformed

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		System.out.println("GUIScrollFrameTest.windowClosing()");
		try {
			if(rs != null)
				rs.close();
		}
		catch(SQLException se) {
			se.printStackTrace();
		}
		try {
			if(ps != null)
				ps.close();
		}
		catch(SQLException se) {
			se.printStackTrace();
		}
		try {
			if(con != null)
				con.close();
		}
		catch(SQLException se) {
			se.printStackTrace();
		}
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}//class
