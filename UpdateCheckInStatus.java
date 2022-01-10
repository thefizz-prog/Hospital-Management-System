package hotel.management.system;

import java.awt.Choice;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

import javax.swing.*;

import com.mysql.cj.xdevapi.Result;

public class UpdateCheckInStatus extends JFrame implements ActionListener{

	//variable declaration
	JLabel l1,l2,l3,l4,l5,l6,l7; 
	Choice c1;
	JTextField t1,t2,t3,t4,t5;
	JButton b1,b2,b3;
	
	//Constructor
	UpdateCheckInStatus() {
		
		
		// Relative Co-ords for fields and labels
		int xposLabel = 20;
		int xposField =  xposLabel +200;
		int yposLabel = 30;
		
		l1 = new JLabel("Check-In Details");
		l1.setFont(new Font("Calibri", Font.PLAIN, 22));
		l1.setForeground(Color.BLUE);
		l1.setBounds(xposLabel, yposLabel, 200,40);
		add(l1);
		
		l2 = new JLabel("Customer ID ");
		l2.setFont(new Font("Calibri", Font.PLAIN, 20));
		l2.setForeground(Color.BLACK);
		l2.setBounds(xposLabel, yposLabel+=40, 200,30);
		add(l2);
		
		c1 = new Choice();
		try {
			conn c = new conn();
			ResultSet rs = c.s.executeQuery("select * from customer");
			
			while (rs.next()) {
				c1.add(rs.getString("number"));
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		c1.setBounds(xposField, yposLabel, 150,30);
		add(c1);
		
		
		l3 = new JLabel("Room Number");
		l3.setFont(new Font("Calibri", Font.PLAIN, 20));
		l3.setForeground(Color.BLACK);
		l3.setBounds(xposLabel, yposLabel+=40, 200,30);
		add(l3);
		
		t1 = new JTextField();
		t1.setBounds(xposField, yposLabel, 150,25);
		add(t1);
		
		
		l4 = new JLabel("Name");
		l4.setFont(new Font("Calibri", Font.PLAIN, 20));
		l4.setForeground(Color.BLACK);
		l4.setBounds(xposLabel, yposLabel+=40, 200,30);
		add(l4);
		
		t2 = new JTextField();
		t2.setBounds(xposField, yposLabel, 150,25);
		add(t2);
		
		l5 = new JLabel("Check In");
		l5.setFont(new Font("Calibri", Font.PLAIN, 20));
		l5.setForeground(Color.BLACK);
		l5.setBounds(xposLabel, yposLabel+=40, 200,30);
		add(l5);
		
		t3 = new JTextField();
		t3.setBounds(xposField, yposLabel, 150,25);
		add(t3);
		
		l6 = new JLabel("Amount Paid");
		l6.setFont(new Font("Calibri", Font.PLAIN, 20));
		l6.setForeground(Color.BLACK);
		l6.setBounds(xposLabel, yposLabel+=40, 200,30);
		add(l6);
		
		t4 = new JTextField();
		t4.setBounds(xposField, yposLabel, 150,25);
		add(t4);
		
		l7 = new JLabel("Balance Outstanding");
		l7.setFont(new Font("Calibri", Font.PLAIN, 20));
		l7.setForeground(Color.BLACK);
		l7.setBounds(xposLabel, yposLabel+=40, 200,30);
		add(l7);
		
		t5 = new JTextField();
		t5.setBounds(xposField, yposLabel, 150,25);
		add(t5);
		
		//Relative Co-ordinates for buttons
		int yposButton =  yposLabel+40;
		int xposButton = xposLabel;
		
		
		// Buttons 
		b1 = new JButton("Check");
		b1.setBackground(Color.black);
		b1.setForeground(Color.white);
		b1.setBounds(xposButton, yposButton, 150,30);
		b1.addActionListener(this);
		add(b1);
			
		b3 = new JButton("Back");
		b3.setBackground(Color.black);
		b3.setForeground(Color.white);
		b3.setBounds(xposButton+=150, yposButton, 150,30);
		b3.addActionListener(this);
		add(b3);
		
		
		setLayout(null);
		setBounds(500,200,1000,500);
		setVisible(true);
	}
	public static void main(String[] args) {
		new UpdateCheckInStatus().setVisible(true);
		
	}
	@Override
	public void actionPerformed(ActionEvent ae) {
		
		// pulls in room number, name, checkin , amount paid and calculates 
		// balance outstanding the cost of the room for the user
		if (ae.getSource() == b1) {
			
			try {
				conn c = new conn();
				String ID = c1.getSelectedItem();
				String query = "select * from customer where number = '" + ID + "'";
				String roomNumber= null;
				String deposit = null;
				String price = null;
				int amountOutstanding;
				ResultSet rs = c.s.executeQuery(query);
				
				while (rs.next()) {
					t1.setText(rs.getString("allocatedRoom"));
					t2.setText(rs.getString("name"));
					t3.setText(rs.getString("checkedIn"));
					t4.setText(rs.getString("deposit"));
					deposit = rs.getString("deposit");
					roomNumber = rs.getString("allocatedRoom");
				}
				
				
				// Sets the room cost oustanding = room cost - desposit paid
				ResultSet rs1 = c.s.executeQuery("select * from room where roomNumber = '" +roomNumber +"'" );
				
				while(rs1.next()) {
					t5.setText(rs1.getString("roomPrice"));
					price = rs1.getString("roomPrice");
					amountOutstanding = Integer.parseInt(price) - Integer.parseInt(deposit);
					t5.setText(Integer.toString(amountOutstanding));
					
				}
				
			} catch (Exception e ) {
				System.out.println(e);
			}
			
		//closes current window and opens new reception
		}else if (ae.getSource() == b3) {
			this.setVisible(false);
			new Reception().setVisible(true);
			
		}
		
	}
}
