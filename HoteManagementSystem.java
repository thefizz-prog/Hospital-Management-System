package hotel.management.system;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;



// This is the landing page for the hotel management system (HMS)

// From here the user signs in and can then access the HMS
public class HoteManagementSystem extends JFrame implements ActionListener {

		// constructor
		public HoteManagementSystem() {
			setBounds(300,300,800,547);
			//setSize(400,400);
			
			//setLocation(300,300);
			ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("hotel/management/system/icons/Bellagio-Hotel-Casino-Las-Vegas.jpg"));
			JLabel I1= new JLabel(i1);
			I1.setBounds(0,0,800,547);
			add(I1);
			
			JLabel I2= new JLabel("Hotel Management System");
			I2.setBounds(20,450,1000,60);
			I2.setForeground(Color.WHITE);
			I2.setFont(new Font("serif", Font.PLAIN, 40));
			
			I1.add(I2);
			setLayout(null);
			setVisible(true);
			

			JButton b1 = new JButton("Next");
			b1.setBackground(Color.black);
			b1.setForeground(Color.WHITE);
			b1.setBounds(580,440,150,30);
			b1.addActionListener(this);
			I1.add(b1);
			
			// Causes Hotel Management System to flicker in and out
			// Params set by sleep timer
			while (true) {
				I2.setVisible(false);
				
				try {
					Thread.sleep(200);
				} catch (Exception e) {
					
				}
				I2.setVisible(true);
				try {
					Thread.sleep(500);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
		
			
		}
		public void actionPerformed(ActionEvent ae) {
			new Login().setVisible(true);
			//hides the original pane from view when user select next 
			this.setVisible(false);
		}

		public static void main (String[] args) {
			new HoteManagementSystem();

		
		}

}
