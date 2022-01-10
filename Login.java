package hotel.management.system;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

import javax.swing.*;

//HMS = hospital management system

/*
	Allows the user to logn by taking their username and password
	If match in database, login. Else, fail with error message
*/

public class Login extends JFrame implements ActionListener{
	
	//variables
	JLabel I1, I2;
	JTextField t1;
	JPasswordField t2;
	JButton b1,b2;
	
	
	//constructor
	Login() {
		//username static text
		I1= new JLabel("Username");
		I1.setBounds(40,20,100,30);
		add(I1);
		
		//password static text
		I2= new JLabel("Password");
		I2.setBounds(40,60,100,30);
		add(I2);
	
		//username field for user
		t1 = new JTextField();
		t1.setBounds(150,20,150,30);
		add(t1);
		
		//password field for user
		t2 = new JPasswordField();
		t2.setBounds(150,60,150,30);
		add(t2);
		
		
		//Login button
		b1 = new JButton("Login");
		b1.setBackground(Color.BLACK);
		b1.setForeground(Color.white);
		b1.setBounds(40, 100,120,30);
		b1.addActionListener(this);
		add(b1);
		
		//Cancel button
		b2 = new JButton("Cancel");
		b2.setBackground(Color.BLACK);
		b2.setForeground(Color.white);
		b2.setBounds(160, 100,120,30);
		b2.addActionListener(this);
		add(b2);
		
		ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("hotel/management/system/icons/loginAvatar.jpg"));
		Image i2 = i1.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
		ImageIcon i3 = new ImageIcon(i2);
		
		JLabel l3 = new JLabel(i3);
		l3.setBounds(280,1,150,100);
		add(l3);
		
		
		getContentPane().setBackground(Color.white);
		
		setLayout(null);
		setBounds(300,300,600,200);
		setVisible(true);
	}
	
	
	@Override
	//Listens for action events. If B2 selcted, exit
	// If b1 selected proceed 
	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == b1) {
			String username = t1.getText();
			String password = t2.getText();
			
			//queries database for exiting login credentials. If match allow login
			conn c =new conn();
			String str = "select * from login where username = '" + username + "' and password = '"+password + "'";
			try {
				ResultSet rs = c.s.executeQuery(str);
				
				if (rs.next()) {
					
					new Dashboard().setVisible(true);
					System.out.println(rs.toString());
					//this.setVisible(false);
					
				}else {
					JOptionPane.showMessageDialog(null, "Incorrect username and password");
					this.setVisible(false);
				}
			} catch (Exception e) {
				
			}
			
		} else if (ae.getSource()==b2) {
			System.exit(0);
		}
		
	}
	
	public static void main (String[] args) {
		new Login();
		
	}



}
