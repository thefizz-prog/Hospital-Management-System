package hotel.management.system;

import java.awt.Choice;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

import javax.swing.*;

/*
	Allows the user to get and set room status 
	
*/
public class UpdateRoomStatus extends JFrame implements ActionListener{
	
	// variable declaration
	JLabel l1,l2,l3,l4,l5;
	Choice c1,c2;
	JTextField t1,t2,t3;
	JButton b1,b2,b3;
	
	//Constructor
	UpdateRoomStatus() {
		int xposLabel = 20;
		int xposField =  xposLabel +200;
		int yposLabel = 30;
		
		
		l1 = new JLabel("Update Room Status"); 
		l1.setFont(new Font("Calibri", Font.BOLD, 22));
		l1.setBounds(xposLabel, yposLabel, 200,40);
		l1.setForeground(Color.BLUE);
		add(l1);
		
		
		l2 = new JLabel("Guest ID"); 
		l2.setFont(new Font("Calibri", Font.PLAIN, 20));
		l2.setBounds(xposLabel, yposLabel+=40, 200,30);
		l2.setForeground(Color.black);
		add(l2);
		c1 = new Choice();
		
		// Loops through all rows in table:customer, column: number 
		// and adds to choice field for user to select
		try {
			conn c = new conn();
			ResultSet rs = c.s.executeQuery("select * from customer");
			
			while (rs.next()) {
				c1.add(rs.getString("number"));
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		c1.setBounds(xposField, yposLabel ,200 ,30);
		add(c1);
		
		
		l3 = new JLabel("Room Number"); 
		l3.setFont(new Font("Calibri", Font.PLAIN, 20));
		l3.setBounds(xposLabel, yposLabel+=40, 200,30);
		l3.setForeground(Color.black);
		add(l3);
		
		t1 = new JTextField();
		t1.setBounds(xposField, yposLabel ,200 ,25);
		t1.setForeground(Color.black);
		add(t1);
		
		l4 = new JLabel("Availability"); 
		l4.setFont(new Font("Calibri", Font.PLAIN, 20));
		l4.setBounds(xposLabel, yposLabel+=40, 200,30);
		l4.setForeground(Color.black);
		add(l4);
		
		t2 = new JTextField();
		t2.setBounds(xposField, yposLabel ,200 ,25);
		t2.setForeground(Color.black);
		add(t2);
		
		l5 = new JLabel("Clean: Status"); 
		l5.setFont(new Font("Calibri", Font.PLAIN, 20));
		l5.setBounds(xposLabel, yposLabel+=40, 200,30);
		l5.setForeground(Color.black);
		add(l5);
		
		t3 = new JTextField();
		t3.setBounds(xposField, yposLabel ,200 ,25);
		t3.setForeground(Color.black);
		add(t3);
		
		//dynamic button positioning 
		int yposButton =  yposLabel+40;
		int xposButton = xposLabel;
		// Buttons 
		b1 = new JButton("Get Room Number");
		b1.setBackground(Color.black);
		b1.setForeground(Color.white);
		b1.setBounds(xposButton, yposButton, 150,30);
		b1.addActionListener(this);
		add(b1);
		
		b2 = new JButton("Update");
		b2.setBackground(Color.black);
		b2.setForeground(Color.white);
		b2.setBounds(xposButton+=150, yposButton, 150,30);
		b2.addActionListener(this);
		add(b2);
		
		
		b3 = new JButton("Back");
		b3.setBackground(Color.black);
		b3.setForeground(Color.white);
		b3.setBounds(xposButton+=150, yposButton, 150,30);
		b3.addActionListener(this);
		add(b3);
		
		//window layout
		setLayout(null);
		setBounds(500,200,1000,500);
		setVisible(true);
	}
	
	
	public static void main(String[] args) {
		new UpdateRoomStatus().setVisible(true);
	}


	@Override
	public void actionPerformed(ActionEvent ae) {
		
		// gets room number avaiability and clean status based on the guest ID as input
		if (ae.getSource() == b1) {
			
			String s1 = c1.getSelectedItem();
			String allocatedRoom = null;
			String a,b;
			conn c = new conn();
			try {
				ResultSet rs = c.s.executeQuery("select * from customer where number = '" + s1 + "'");
				
				while (rs.next()) {
					t1.setText(rs.getString("allocatedRoom"));
					allocatedRoom = rs.getString("allocatedRoom");

				}
				
				ResultSet rs1 = c.s.executeQuery("select * from room where roomNumber = '" + allocatedRoom + "'");
				while (rs1.next()) {

					t2.setText(rs1.getString("roomAvailability"));
					t3.setText(rs1.getString("cleaningStatus"));
					System.out.println(rs1.getString("roomAvailability"));
					System.out.println(rs1.getString("cleaningStatus"));
				}
				
			} catch (Exception e) {
				System.out.println(e);
			}
			
		//based on guest id as the input the user can amend the cleaning status to cleaned
		} else if (ae.getSource() == b2) {
			
			try {
				conn c = new conn();
				String roomNumber = t1.getText();
				String roomAvailabilty = t2.getText();
				String cleaningStatus =t3.getText();
				
				String query = "update room set roomAvailability = '" + roomAvailabilty + "', cleaningStatus = '" + cleaningStatus + "'";
				
				c.s.executeUpdate(query);
				JOptionPane.showMessageDialog(null, "Room Details (Availability & Cleaning Status) Updated Successfully");
				new Reception().setVisible(true);
				this.setVisible(false);
				
			}catch (Exception e) {
				System.out.println(e);
			}
		
		//closes the current window and opens new reception
	    } else if (ae.getSource() == b3) {
	    	new Reception().setVisible(true);
	    	this.setVisible(false);
	    	
	    }

	}
}
