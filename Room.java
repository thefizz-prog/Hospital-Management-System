package hotel.management.system;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import net.proteanit.sql.DbUtils;

/*
	Displays rooms to the user
	Allows users to seaech for room type based on room type and number
*/

public class Room extends JFrame implements ActionListener{

	// variable declaration
	JPanel mainPanel;
	JTable table;
	DefaultTableModel model_table;
	JScrollPane scroll_table;
	JButton b1,b2,b3;
	JComboBox c1;
	JTextField t1;


	public static void main(String[] args) {
	    Room room = new Room();
	    room.setVisible(true);
	}   
	
	// constructor
	public Room() {
	    mainPanel = new JPanel(null);
	    setSize(1000, 500);

	    //table setup
	    table = new JTable();
	    model_table = new DefaultTableModel();
	    model_table.addColumn("Room Number");
	    model_table.addColumn("Room Availability");
	    model_table.addColumn("Cleaning Status");
	    model_table.addColumn("Room Price");
	    model_table.addColumn("Bed Type");

	    table.setModel(model_table);

	    
	    // Search for room type
	    b1 = new JButton();
		b1.setBackground(Color.black);
		b1.setForeground(Color.white);
		b1.setBounds(150,300,200,30);
		b1.setLabel("Search Room Type");
		b1.addActionListener(this);
		add(b1);
		
		
		c1 = new JComboBox(new String[] {"King", "Queen", "Double", "Single"});
		c1.setBounds(350,300,180,30);
		c1.setBackground(Color.WHITE);
		c1.addActionListener(this);
	    add(c1);
	    
	    // Search for room number
	    b2 = new JButton();
		b2.setBackground(Color.black);
		b2.setForeground(Color.white);
		b2.setBounds(150,350,200,30);
		b2.setLabel("Search Room Number");
		b2.addActionListener(this);
		add(b2);
		
		
		t1 = new JTextField();
		t1.setBounds(350,350,180,30);
		t1.setBackground(Color.WHITE);
		t1.addActionListener(this);
	    add(t1);
	    
	    //Adds the image 
	    ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("hotel/management/system/icons/roomtypes.jpg"));
		//Scale the image correctly 
		Image i2 = i1.getImage().getScaledInstance(350, 350, Image.SCALE_DEFAULT);
		ImageIcon i3 = new ImageIcon(i2);
		
		JLabel l1 = new JLabel(i3);
		l1.setBounds(0,0,1600,400);
		add(l1);
	    
	    // Return to reception window button
	    b3 = new JButton();
		b3.setBackground(Color.black);
		b3.setForeground(Color.white);
		b3.setBounds(250,400,200,30);
		b3.setLabel("Back");
		b3.addActionListener(this);
		add(b3);
		
	    //Loads existing Room info by default for the user
		try {
			
			conn c = new conn();
			String str = "select * from room";
			ResultSet rs = c.s.executeQuery(str);
			
			table.setModel(DbUtils.resultSetToTableModel(rs));
			getContentPane().setBackground(Color.white);
			
		} catch(Exception e) {
			
		}

	    scroll_table = new JScrollPane(table);            // add table to scroll panel
	    scroll_table.setBounds(5, 10, 600, 150);
	    scroll_table.setVisible(true);
	    mainPanel.add(scroll_table);

	    this.add(mainPanel);
	  }


	@Override
	public void actionPerformed(ActionEvent ae) {
		
		// gets matching room types
		if (ae.getSource()==b1) {
			
			try {
				
				conn c = new conn();
				String bedType = (String) c1.getSelectedItem();
				System.out.println(bedType);
				String str = "select * from room where bedType ='" +bedType+ "'" ;
				
				
				ResultSet rs = c.s.executeQuery(str);
				
				table.setModel(DbUtils.resultSetToTableModel(rs));
				getContentPane().setBackground(Color.white);
				
			} catch(Exception e) {
				
			}
		
		// Gets matching room numbers
		} else if (ae.getSource()==b2) {
			
			try {
				
				conn c = new conn();
				String roomNumber = t1.getText();
				System.out.println(roomNumber);
				String str = "select * from room where roomNumber ='" +roomNumber+ "'" ;
				
				
				ResultSet rs = c.s.executeQuery(str);
				
				table.setModel(DbUtils.resultSetToTableModel(rs));
				getContentPane().setBackground(Color.white);
				
			} catch(Exception e) {
				
			}
			
		// if back button is pressed open the reception window and close current window 
		} else if (ae.getSource()==b3) { 
			
			this.setVisible(false);
			new Reception().setVisible(true);
		}
		
	}


		

	
}
