package hotel.management.system;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

/*
	Reception menu for authorised user 
	Allows the user to select a variety of options such as customer check in 

*/
public class Reception extends JFrame implements ActionListener  {
		
		// variable declaration
		JButton b1,b2,b3,b4,b5,b6,b7,b8,b9,b10,b11,b12;
		
		
		//constructor 
		Reception() {
			
			//dyanmic positioning of the fields and labels 
			int xposJButt = 10;
			int yposJButt = 30;
			
			//buttons and labels 
			b1 = new JButton("Customer Check-In");
			b1.setBackground(Color.BLACK);
			b1.setForeground(Color.white);
			b1.setFont(new Font("Calibri", Font.PLAIN, 20));
			b1.setBounds(xposJButt,yposJButt,220,30);
			b1.addActionListener(this);
			add(b1);
			
			b2 = new JButton("Find Room");
			b2.setBackground(Color.BLACK);
			b2.setForeground(Color.white);
			b2.setFont(new Font("Calibri", Font.PLAIN, 20));
			b2.setBounds(xposJButt,yposJButt+=50,220,30);
			b2.addActionListener(this);
			add(b2);
			
			b3 = new JButton("Department");
			b3.setBackground(Color.BLACK);
			b3.setForeground(Color.white);
			b3.setFont(new Font("Calibri", Font.PLAIN, 20));
			b3.setBounds(xposJButt,yposJButt+=50,220,30);
			b3.addActionListener(this);
			add(b3);
			
			b4 = new JButton("All Employee Info");
			b4.setBackground(Color.BLACK);
			b4.setForeground(Color.white);
			b4.setFont(new Font("Calibri", Font.PLAIN, 20));
			b4.setBounds(xposJButt,yposJButt+=50,220,30);
			b4.addActionListener(this);
			add(b4);
			
			b5 = new JButton("Customer Info");
			b5.setBackground(Color.BLACK);
			b5.setForeground(Color.white);
			b5.setFont(new Font("Calibri", Font.PLAIN, 20));
			b5.setBounds(xposJButt,yposJButt+=50,220,30);
			b5.addActionListener(this);
			add(b5);
			
			b6 = new JButton("Manager Info");
			b6.setBackground(Color.BLACK);
			b6.setForeground(Color.white);
			b6.setFont(new Font("Calibri", Font.PLAIN, 20));
			b6.setBounds(xposJButt,yposJButt+=50,220,30);
			b6.addActionListener(this);
			add(b6);
			
			b7 = new JButton("Check Out");
			b7.setBackground(Color.BLACK);
			b7.setForeground(Color.white);
			b7.setFont(new Font("Calibri", Font.PLAIN, 20));
			b7.setBounds(xposJButt,yposJButt+=50,220,30);
			b7.addActionListener(this);
			add(b7);
			
			b8 = new JButton("Update Check Status");
			b8.setBackground(Color.BLACK);
			b8.setForeground(Color.white);
			b8.setFont(new Font("Calibri", Font.PLAIN, 20));
			b8.setBounds(xposJButt,yposJButt+=50,220,30);
			b8.addActionListener(this);
			add(b8);
			
			b9 = new JButton("Update Room Status");
			b9.setBackground(Color.BLACK);
			b9.setForeground(Color.white);
			b9.setFont(new Font("Calibri", Font.PLAIN, 20));
			b9.setBounds(xposJButt,yposJButt+=50,220,30);
			b9.addActionListener(this);
			add(b9);
			
			b10 = new JButton("Pick Up Service");
			b10.setBackground(Color.BLACK);
			b10.setForeground(Color.white);
			b10.setFont(new Font("Calibri", Font.PLAIN, 20));
			b10.setBounds(xposJButt,yposJButt+=50,220,30);
			b10.addActionListener(this);
			add(b10);
			
			b11 = new JButton("Search Room");
			b11.setBackground(Color.BLACK);
			b11.setForeground(Color.white);
			b11.setFont(new Font("Calibri", Font.PLAIN, 20));
			b11.setBounds(xposJButt,yposJButt+=50,220,30);
			b11.addActionListener(this);
			add(b11);
			
			b12 = new JButton("Logout");
			b12.setBackground(Color.BLACK);
			b12.setForeground(Color.white);
			b12.setFont(new Font("Calibri", Font.PLAIN, 20));
			b12.setBounds(xposJButt,yposJButt+=50,220,30);
			b12.addActionListener(this);
			add(b12);
			
			
			
			ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("hotel/management/system/icons/reception.jpg"));
			JLabel l1 = new JLabel(i1);
			l1.setBounds(300,20,450,600);
			add(l1);
			
			
			//window properties 
			setLayout(null);
			setBounds(500,200,800,800);
			setVisible(true);
			
		}
		
		public static void main(String[] args) {
			new Reception().setVisible(true);
		}

		@Override
		public void actionPerformed(ActionEvent ae) {
			
			//Opens add customer screen, closes current window
			if (ae.getSource() ==b1) {
				new AddCustomer().setVisible(true);
				this.setVisible(false);
			
			// opens new room screen, closes current window
			} else if (ae.getSource() == b2) {
				new Room().setVisible(true);
				this.setVisible(false);
				
			} else if (ae.getSource() == b3) {
			
			// open new employee screen, closes current window
			}else if (ae.getSource() == b4) {
				new EmployeeInfo().setVisible(true);
				this.setVisible(false);
			
			//opens customer info page, closes current window
			}else if (ae.getSource() == b5) {
				new CustomerInfo().setVisible(true);
				this.setVisible(false);
			
			//opens new manager screen, closes current window
			}else if (ae.getSource() == b6) {
				new ManagerInfo().setVisible(true);
				this.setVisible(false);
			
			//opens new checkout screen, closes current window
			}else if (ae.getSource() == b7) {
				new CheckOut().setVisible(true);
				this.setVisible(false);
			
			//opens new update checkin status, closes current window
			}else if (ae.getSource() == b8) {
				new UpdateCheckInStatus().setVisible(true);
				this.setVisible(false);
			
			// opens a new update room status, closes current window 
			}else if (ae.getSource() == b9) {
				new UpdateRoomStatus().setVisible(true);
				this.setVisible(false);
				
			//opens a new taxi service , closes current window
			} else if (ae.getSource() == b10) {
				new TaxiService().setVisible(true);
				this.setVisible(false);
			
			//opens new room screen, closes current window
			}else if (ae.getSource() == b11) {
				new Room().setVisible(true);
				this.setVisible(false);
				
			//logs user out by closing current screen
			}else if (ae.getSource() == b12) {
				setVisible(false);
			}
			
			
		}
		
}
