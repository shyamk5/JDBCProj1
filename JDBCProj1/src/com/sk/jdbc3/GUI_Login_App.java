package com.sk.jdbc3;

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
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class GUI_Login_App extends JFrame implements ActionListener, WindowListener {

	JLabel uname,pwd,status;
	JTextField userField,updateStatus;
	JPasswordField pwdField;
	JButton login;
	
	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	private static final String IRCTC_LOGIN_QUERY = "SELECT COUNT(*) FROM IRCTC_TAB WHERE UNAME=? AND PWD=?";
	
	//constructor
	public GUI_Login_App() {
		System.out.println("GUI_Login_App constructor start");
		setTitle("GUI Login App");
		setSize(600,500);
		setLayout(new FlowLayout());
		
		//add components
		//for Username
		uname = new JLabel("Username");
		add(uname);
		userField = new JTextField(20);
		add(userField);
		
		//for password
		pwd = new JLabel("Password");
		add(pwd);
		pwdField = new JPasswordField(20);
		add(pwdField);
		
		//login button
		login = new JButton("Login");
		add(login);
		
		//for status
		status = new JLabel("Status");
		add(status);
		updateStatus = new JTextField(25);
		add(updateStatus);
		
		//disable status textfield
		updateStatus.setEditable(false);
		
		//register and activate action listener to Login button
		login.addActionListener(this);
		
		//add window listener to frame
		this.addWindowListener(this);
		
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		initializeJDBC();
		System.out.println("GUI_Login_App constructor end");
	}
	
	private void initializeJDBC() {
		try {
			//Establish the Connection object
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","IMSHYAM","URVIL");
			//Create PreparedSTatement object
			ps = con.prepareStatement(IRCTC_LOGIN_QUERY,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
		}
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}//initializeJDBC
	
	
	public static void main(String[] args) {
		new GUI_Login_App();

	}
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		System.out.println("GUI_Login_App.actionPerformed()");
		
		if(ae.getSource()==login) {
			System.out.println("Login button clicked");
			try {
				//set values to Query param
				String password = String.valueOf(pwdField.getPassword());
				ps.setString(1, userField.getText());
				ps.setString(2, password);
				rs = ps.executeQuery();
				
				if(rs != null) {
					rs.next();
					int count = rs.getInt(1);
					if(count == 0)
						updateStatus.setText("Invalid Credentials");
					else
						updateStatus.setText("Valid Credentials");
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}//if
	}//actionPerformed

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		System.out.println("GUI_Login_App.windowClosing()");
		
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

}
