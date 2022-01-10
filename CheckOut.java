package hotel.management.system;

import java.awt.Choice;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

import javax.crypto.AEADBadTagException;
import javax.swing.*;

import com.mysql.cj.xdevapi.Result;

/*
	checksout a guest
*/
public class CheckOut extends JFrame implements ActionListener{

	
	// variable declaration
	JLabel l1,l2,l3,l4;
	Choice c1;
	JTextField t1,t2,t3;
	JButton b1, b2, b3;
	
	// Constructor 
	CheckOut() {
		
		// Relative Co-ords for fields and labels
		int xposLabel = 20;
		int xposField =  xposLabel +200;
		int yposLabel = 30;
		
		l1 = new JLabel("Check Out");
		l1.setBounds(xposLabel,yposLabel, 200,40);
		l1.setFont(new Font("Calibri", Font.PLAIN, 22));
		l1.setForeground(Color.BLUE);
		add(l1);
		
		l2 = new JLabel("Customer ID");
		l2.setBounds(xposLabel,yposLabel+=40, 200,40);
		l2.setFont(new Font("Calibri", Font.PLAIN, 20));
		l2.setForeground(Color.BLACK);
		add(l2);
		
		c1 = new Choice();
		//pulls in customer numbers and adds to the choice field
		try {
			
			conn c = new conn();
			String query = "select * from customer";
			ResultSet rs = c.s.executeQuery(query);
			
			while (rs.next()) {
				c1.add(rs.getString("number"));
			}
			
		} catch (Exception e) {
			System.out.println(e);
		}
		
		c1.setBounds(xposField, yposLabel, 150,30);
		add(c1);
		
		l3 = new JLabel("Customer Name");
		l3.setBounds(xposLabel,yposLabel+=40, 200,40);
		l3.setFont(new Font("Calibri", Font.PLAIN, 20));
		l3.setForeground(Color.BLACK);
		add(l3);
		
		t1 = new JTextField();
		t1.setBounds(xposField, yposLabel, 150,25);
		add(t1);
		
		l4 = new JLabel("Room Number");
		l4.setBounds(xposLabel,yposLabel+=40, 200,40);
		l4.setFont(new Font("Calibri", Font.PLAIN, 20));
		l4.setForeground(Color.BLACK);
		add(l4);
		
		t2 = new JTextField();
		t2.setBounds(xposField, yposLabel, 150,25);
		add(t2);
		
		//Relative Co-ordinates for buttons
		int yposButton =  yposLabel+40;
		int xposButton = xposLabel;
		
		
		// Buttons 
		b1 = new JButton("Get Room Number");
		b1.setBackground(Color.black);
		b1.setForeground(Color.white);
		b1.setBounds(xposButton, yposButton, 150,30);
		b1.addActionListener(this);
		add(b1);
		
		b2= new JButton("Back");
		b2.setBackground(Color.black);
		b2.setForeground(Color.white);
		b2.setBounds(xposButton+=150, yposButton, 150,30);
		b2.addActionListener(this);
		add(b2);
		
		b3 = new JButton("CheckOut");
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
		new CheckOut().setVisible(true);
	}


	@Override
	public void actionPerformed(ActionEvent ae) {
		
		// pulls room number from room database based on customer ID 
		// and pulls in corresponding customer name and allocated room
		if (ae.getSource() == b1) {
			
			String IDnumber = c1.getSelectedItem();
			
			conn c = new conn();
		
			String query = "select * from customer where number = '" + IDnumber + "'";
			
			try {
				ResultSet rs = c.s.executeQuery(query);
				while(rs.next()) {
					t1.setText(rs.getString("name"));
					t2.setText(rs.getString("allocatedRoom"));
				}
			} catch (Exception e) {
				System.out.println(e);
			}
		//closes current window and returns to main reception
		} else if (ae.getSource() == b2) {
			new Reception().setVisible(true);
			this.setVisible(false);
		
		//	removed customer row from customer database and updates the room to available once 
		//checkout is completed
		} else if (ae.getSource() == b3) {

			String IDnumber = c1.getSelectedItem();
			String roomNumber = t1.getText();
			String queryDelete = "delete from customer where number = '" + IDnumber +"'";
			String queryUpdate = "update room set roomAvailability = 'Available' where roomNumber = '" + roomNumber + "'";
			
			conn c = new conn();
			
			try {
				c.s.executeUpdate(queryDelete);
				c.s.executeUpdate(queryUpdate);
				
				new Reception().setVisible(true);
				this.setVisible(false);
				JOptionPane.showMessageDialog(null, "Checkout Complete");
				
			} catch (Exception e) {
				System.out.println(e);
			}
			
		}
		
	}
}
