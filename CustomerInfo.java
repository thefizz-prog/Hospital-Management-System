package hotel.management.system;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import net.proteanit.sql.DbUtils;

/*
	Visualisation of the customer database
	Pulls all of the rows in the customer database and adds a 
	Scrollable sidebar 
	
	User can also search for a specific customer based on customer name OR 
	allocated room input

*/
public class CustomerInfo extends JFrame implements ActionListener{

	
	// variable declaration
	private JPanel mainPanel;
	private JTable table;
	private DefaultTableModel model_table;
	private JScrollPane scroll_table;
	JButton b1,b2,b3;
	JTextField t1,t2;


public static void main(String[] args) {
    CustomerInfo main = new CustomerInfo();
    main.setVisible(true);
}   
	// constructor
	CustomerInfo() {
		
		// window properties
	    mainPanel = new JPanel(null);
	    setSize(800, 500);
	
	    //table to hold the database info
	    table = new JTable();
	    model_table = new DefaultTableModel();
	    model_table.addColumn("Name");
	    model_table.addColumn("Age");
	    model_table.addColumn("Gender");
	    model_table.addColumn("Job");
	    model_table.addColumn("Salary");
	    model_table.addColumn("Phone");
	    model_table.addColumn("Address");
	    model_table.addColumn("Email");
	    table.setModel(model_table);
	
	    //buttons and fields 
	    b1 = new JButton();
		b1.setBackground(Color.black);
		b1.setForeground(Color.white);
		b1.setBounds(150,280,200,30);
		b1.setLabel("Search Customer Name");
		b1.addActionListener(this);
		add(b1);
		
		t1 = new JTextField();
		t1.setBackground(Color.WHITE);
		t1.setForeground(Color.BLACK);
		t1.setBounds(360,280,150,30);
		t1.addActionListener(this);
	    add(t1);
		
	    b2 = new JButton();
		b2.setBackground(Color.black);
		b2.setForeground(Color.white);
		b2.setBounds(150,320,200,30);
		b2.setLabel("Search Allocated Room");
		b2.addActionListener(this);
		add(b2);
		
		t2 = new JTextField();
		t2.setBackground(Color.WHITE);
		t2.setForeground(Color.BLACK);
		t2.setBounds(360,320,150,30);
		t2.addActionListener(this);
	    add(t2);
	    
	    b3 = new JButton();
		b3.setBackground(Color.black);
		b3.setForeground(Color.white);
		b3.setBounds(150,400,200,30);
		b3.setLabel("Back");
		b3.addActionListener(this);
		add(b3);
		
		
	
	    
	    //Loads existing staff info by default for the user
		try {
			
			conn c = new conn();
			String str = "select * from customer";
			ResultSet rs = c.s.executeQuery(str);
			
			table.setModel(DbUtils.resultSetToTableModel(rs));
			getContentPane().setBackground(Color.white);
			
		} catch(Exception e) {
			System.out.println(e);
		}
	
	    scroll_table = new JScrollPane(table);            // add table to scroll panel
	    scroll_table.setBounds(5, 10, 600, 150);
	    scroll_table.setVisible(true);
	    mainPanel.add(scroll_table);
	
	    this.add(mainPanel);
  }


@Override
public void actionPerformed(ActionEvent ae) {
	
	//searches for customer name using simple regex match and places results into the table for user
	if (ae.getSource()==b1) {
		
		try {
			
			conn c = new conn();
			String name = t1.getText();
			String str = "select * from customer where name REGEXP '(\\w)*("+name+")(\\w)*'";
			
			
			ResultSet rs = c.s.executeQuery(str);
			
			table.setModel(DbUtils.resultSetToTableModel(rs));
			getContentPane().setBackground(Color.white);
			
		} catch(Exception e) {
			System.out.println(e);
		}
	
	//searches for customer room number using simple regex match and places results into the table for user
	} else if (ae.getSource() == b2) {
		try {
			
			conn c = new conn();
			String allocatedRoom = t2.getText();
			System.out.println(allocatedRoom);
			String str = "select * from customer where allocatedRoom REGEXP '(\\w)*("+allocatedRoom+")(\\w)*'";
			
			
			ResultSet rs = c.s.executeQuery(str);
			
			table.setModel(DbUtils.resultSetToTableModel(rs));
			getContentPane().setBackground(Color.white);
			
		} catch(Exception e) {
			System.out.println(e);
		}
	
	//closes current window and opens the reception
	} else if (ae.getSource() == b3) {
		this.setVisible(false);
		new Reception().setVisible(true);
	}
	
}


	


}


