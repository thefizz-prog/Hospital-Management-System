package hotel.management.system;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

/*
	Adds a new room and insert as a row in the room database
	
*/
public class addRooms extends JFrame implements ActionListener {

	//Variable declaration
	JTextField t1,t2;
	JComboBox c1,c2,c3;
	JButton b1,b2;
		
	//constructor
	addRooms() {
		
		//dynamic field and label cor-ordinates
		int xposLabels=50;
		int yposLabels=30;
		int xposComboText = 0;
		int xposJButton = 0;
				
		JLabel addRooms = new JLabel("Add Rooms");
		addRooms.setBounds(150,30,150,20);
		addRooms.setFont(new Font("Calibri", Font.BOLD,20));
		add(addRooms);
		
		JLabel room = new JLabel("Room Number");
		room.setBounds(xposLabels,yposLabels+=40,200,20);
		room.setFont(new Font("Calibri", Font.PLAIN,20));
		add(room);
		
		xposComboText+=xposLabels+140;
		t1 = new JTextField();
		t1.setBounds(xposComboText,yposLabels,200,20);
		add(t1);
		
		JLabel available = new JLabel("Available");
		available.setFont(new Font("Calibri", Font.PLAIN, 20));
		available.setBounds(xposLabels,yposLabels+=40,200,20);
		add(available);
		
		
		c1 = new JComboBox(new String[] {"Available", "Occupied"});
		c1.setBounds(xposComboText,yposLabels,200,20);
		c1.setBackground(Color.WHITE);
		add(c1);
		
		JLabel cleaningStatus = new JLabel("Cleaning Status");
		cleaningStatus.setFont(new Font("Calibri", Font.PLAIN, 20));
		cleaningStatus.setBounds(xposLabels,yposLabels+=40,200,20);
		add(cleaningStatus);
		
		c2 = new JComboBox(new String[] {"Cleaned", "Not Cleaned",});
		c2.setBounds(xposComboText,yposLabels,200,20);
		c2.setBackground(Color.WHITE);
		add(c2);
		
		JLabel price = new JLabel("Price");
		price.setFont(new Font("Calibri", Font.PLAIN, 20));
		price.setBounds(xposLabels,yposLabels+=40,200,20);
		add(price);
		
		t2 = new JTextField();
		t2.setBounds(xposComboText,yposLabels,200,20);
		add(t2);
		
		JLabel type = new JLabel("Bed Type");
		type.setFont(new Font("Calibri", Font.PLAIN, 20));
		type.setBounds(xposLabels,yposLabels+=40,200,20);
		add(type);
		
		c3 = new JComboBox(new String[] {"King", "Queen", "Double", "Single"});
		c3.setBounds(xposComboText,yposLabels,200,20);
		c3.setBackground(Color.WHITE);
		add(c3);
		
		xposJButton =xposLabels -5;
		b1 = new JButton("Add Room");
		b1.setBounds(xposJButton, yposLabels+=40,150,20);
		b1.setBackground(Color.BLACK);
		b1.setForeground(Color.white);
		b1.addActionListener(this);
		add(b1);
		
		b2 = new JButton("Cancel Booking");
		b2.setBounds(xposJButton+180, yposLabels,150,20);
		b2.setBackground(Color.BLACK);
		b2.setForeground(Color.white);
		b2.addActionListener(this);
		add(b2);

		ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("hotel/management/system/icons/HotelRoom.jpg"));
		JLabel l1 = new JLabel(i1);
		l1.setBounds(450,30,500,400);
		add(l1);
		
		//sets window properties
		getContentPane().setBackground(Color.white);
		setBounds(380,220,1000,550);
		setLayout(null);
		setVisible(true);
		
		
	}
	
	
	public static void main(String[] args ) {
		new addRooms().setVisible(true);
	}


	@Override
	public void actionPerformed(ActionEvent ae) {
		
		if (ae.getSource() == b1) {
			//grabs info from fields
			String roomNumber = t1.getText();
			String roomAvailability = (String) c1.getSelectedItem();
			String cleaningStatus = (String) c2.getSelectedItem();
			String roomPrice = t2.getText();
			String bedType = (String) c3.getSelectedItem();
			
			
			conn d = new conn();
			//inserts new room into room database			
			String str = "INSERT INTO room (roomNumber, roomAvailability, cleaningStatus, roomPrice, bedType) VALUES( '"+roomNumber+"', '"+roomAvailability+"', '"+cleaningStatus+"','"+roomPrice+"', '"+bedType+"')";
			
			try {
				d.s.executeUpdate(str);
				JOptionPane.showMessageDialog(null, "Room Added Successfully");
				this.setVisible(false);

			}catch (Exception e) {
				System.out.println(e);
			}
		
	} else if (ae.getSource() == b2) { 
		JOptionPane.showMessageDialog(null, "Room Cancelled");
		this.setVisible(false);
	}
 }
}
