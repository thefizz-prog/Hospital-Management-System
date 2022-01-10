package hotel.management.system;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import net.proteanit.sql.DbUtils;

/*
	Allows the user to search for rooms based on type , availability etc

*/
public class RoomSearch extends JFrame implements ActionListener {

	//Variables
	private JPanel mainPanel;
	private JTable table;
	private DefaultTableModel model_table;
	private JScrollPane scroll_table;
	JComboBox c1;
	JCheckBox c2;
	JButton b1,b2;
	JTable t1;
	JLabel l1,l2;
	
	// Constructor 
	RoomSearch () {
		
		// Relative Co-ords for fields and labels
		int xposLabel = 20;
		int xposField =  xposLabel +150;
		int yposLabel = 30;
		
		
		mainPanel = new JPanel(null);
	    setSize(800, 500);
	    
	    //table setup
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
	    
	    //labels and fields 
		l1 = new JLabel("Room Search");
		l1.setBounds(xposLabel,yposLabel, 200,40);
		l1.setFont(new Font("Calibri", Font.BOLD, 22));
		l1.setForeground(Color.BLUE);
		add(l1);
		
		l2 = new JLabel("Room Type");
		l2.setBounds(xposLabel,yposLabel+=40, 200,40);
		l2.setFont(new Font("Calibri", Font.PLAIN, 20));
		l2.setForeground(Color.BLACK);
		add(l2);
		
		c1 = new JComboBox(new String[] {"Double", "King", "Master", "Single"});
		c1.setBounds(xposField,yposLabel, 200,30);
		c1.setFont(new Font("Calibri", Font.PLAIN, 20));
		c1.setForeground(Color.BLACK);
		add(c1);
		
		c2 = new JCheckBox("Only Show Available");
		c2.setBounds(xposLabel+400,yposLabel, 200,40);
		c2.setFont(new Font("Calibri", Font.PLAIN, 20));
		c2.setForeground(Color.BLACK);
		add(c2);
		
		//Relative Co-ordinates for buttons
		int yposButton =  yposLabel+40;
		int xposButton = xposLabel;
		
		
		// Buttons 
		b1 = new JButton("Search Room Type");
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
	    
		//Loads existing double rooms info by default for the user
		try {
			
			conn c = new conn();
			String str = "select * from room";
			ResultSet rs = c.s.executeQuery(str);
			
			table.setModel(DbUtils.resultSetToTableModel(rs));
			getContentPane().setBackground(Color.white);
			
		} catch(Exception e) {
			System.out.println(e);
		}

	    scroll_table = new JScrollPane(table);            // add table to scroll panel
	    scroll_table.setBounds(10, 200, 600, 150);
	    scroll_table.setVisible(true);
	    mainPanel.add(scroll_table);

	    this.add(mainPanel);
	    
	}
	
	
	public static void main(String[] args) {
		new RoomSearch().setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent ae) {
		
		//pulls from database room with matching type room and room availability
		if (ae.getSource() == b1 ) {
			
			String roomType = (String) c1.getSelectedItem();
			conn c = new conn();
			String roomAvailability = null;
			if (c2.isSelected()) {
				roomAvailability = "Available";
				
			} else {
				roomAvailability = "Occupied";
			}
			
			
			String query  ="select * from room where bedType = '"+roomType+"' and roomAvailability = '"+roomAvailability+"'";
			try {
				ResultSet rs = c.s.executeQuery(query);
				table.setModel(DbUtils.resultSetToTableModel(rs));
				getContentPane().setBackground(Color.white);
			
			} catch (Exception e) {
				System.out.println(e);
			}

			
		//close current window and open reception	
		} else if (ae.getSource() == b2) {
			this.setVisible(false);
			new Reception().setVisible(true);
			
		}
		
	}

		
}
