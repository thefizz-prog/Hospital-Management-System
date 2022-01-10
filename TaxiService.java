package hotel.management.system;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.mysql.cj.protocol.Resultset;

import net.proteanit.sql.DbUtils;

/*
	Allows user to search for available taxis at the hotels service
	User can search for car by car brand and car model 
*/


public class TaxiService extends JFrame implements ActionListener {

	//Variables
	private JPanel mainPanel;
	private JTable table;
	private DefaultTableModel model_table;
	private JScrollPane scroll_table;
	JComboBox c1;
	JCheckBox c2;
	JButton b1,b2,b3;
	JTable t1;
	JLabel l1,l2;
	Choice ch1,ch2;
	
	// Constructor 
	@SuppressWarnings("deprecation")
	TaxiService () {
		
		// Relative Co-ords for fields and labels
		int xposLabel = 20;
		int xposField =  xposLabel +150;
		int yposLabel = 30;
		
		
		mainPanel = new JPanel(null);
	    setSize(800, 500);

	    //table config
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
	    
	    // Pulls in table of all taxi/cars available to the hotel
	    conn c = new conn();
	    String diplayDefaultTableQuery  ="select * from drivers";
		try {
			ResultSet rs = c.s.executeQuery(diplayDefaultTableQuery);
			table.setModel(DbUtils.resultSetToTableModel(rs));
			getContentPane().setBackground(Color.white);
		
		} catch (Exception e) {
			System.out.println(e);
		}
		
		l1 = new JLabel("Taxi Search");
		l1.setBounds(xposLabel,yposLabel, 200,40);
		l1.setFont(new Font("Calibri", Font.BOLD, 22));
		l1.setForeground(Color.BLUE);
		add(l1);
		
		l2 = new JLabel("Car Brand");
		l2.setBounds(xposLabel,yposLabel+=40, 150,40);
		l2.setFont(new Font("Calibri", Font.PLAIN, 20));
		l2.setForeground(Color.BLACK);
		add(l2);
		
		ch1 = new Choice();
		
		
		// Pulls in a list of all unique car brands for the user to select from
		try {
			//conn c = new conn();
			String query = "select * from drivers";
			ResultSet rs =c.s.executeQuery(query); 
			
			
			//local variables 
			int i =0;
			
			// Adds all car types in column carCompany in the database to an arraylist
			// Contents of arraylist are added to a hashset
			//Hashset is then fed back into ch1 so that only unique car companys are displayed
			ArrayList<String> choiceItems= new ArrayList<>();
			while(rs.next()) {
				//ch1.add();
				choiceItems.add(rs.getString("carCompany"));
			}

			HashSet<String> hashSet = new HashSet<>(choiceItems);
			Iterator itr = hashSet.iterator();

			// adds tbe loops through the items in the hashset and adds them to the choice list
	       while (itr.hasNext()) {
	            //System.out.println(itr.next());
	            ch1.add(itr.next().toString());
	        }
	       
	       
			System.out.println(hashSet);
		} catch (Exception e) {
			System.out.println(e);
		}
		
		ch1.setBounds(xposField,yposLabel, 250,40);
		add(ch1);
		
		
		// Sets position of b3 - triggers action event
		b3 = new JButton("Search Car Model");
		b3.setBackground(Color.black);
		b3.setForeground(Color.white);
		b3.setBounds(xposField+300,yposLabel, 150,30);
		b3.addActionListener(this);
		add(b3);
		
		l2 = new JLabel("Car Model");
		l2.setBounds(xposLabel,yposLabel+=40, 150,40);
		l2.setFont(new Font("Calibri", Font.PLAIN, 20));
		l2.setForeground(Color.BLACK);
		add(l2);
		
		ch2 = new Choice();
		
		
		ch2.setBounds(xposField,yposLabel, 250,40);
		add(ch2);
		//Relative Co-ordinates for buttons
		int yposButton =  yposLabel+40;
		int xposButton = xposLabel;
		
		
		// Buttons 
		b1 = new JButton("Search Available Cars");
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


	    scroll_table = new JScrollPane(table);            // add table to scroll panel
	    scroll_table.setBounds(10, 200, 600, 150);
	    scroll_table.setVisible(true);
	    mainPanel.add(scroll_table);

	    this.add(mainPanel);

	}
	
	
	public static void main(String[] args) {
		new TaxiService().setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent ae) {
		
		// When user has select a car type and a car model, 
		//clicking b1 displays a list of all car brand and model 
		if (ae.getSource() == b1 ) {
			
			String carCompany = (String) ch1.getSelectedItem();
			String carModel = (String) ch2.getSelectedItem();
			System.out.println(carCompany);
			conn c = new conn();
			String roomAvailability = null;

			String query  ="select * from drivers where carCompany = '"+carCompany+"' and carModel ='" +carModel+"'";
			try {
				ResultSet rs = c.s.executeQuery(query);
				table.setModel(DbUtils.resultSetToTableModel(rs));
				getContentPane().setBackground(Color.white);
			
			} catch (Exception e) {
				System.out.println(e);
			}

			
			
		} else if (ae.getSource() == b2) {
			this.setVisible(false);
			new Reception().setVisible(true);
		
		// pulls in unique car models for the user to select from.
		// info is delivered to user into ch2
		} else if (ae.getSource() == b3) {
			
			try {
				conn c = new conn();
				
				String carCompanySelected = ch1.getSelectedItem();
				String query = "select * from drivers where carCompany = '" +carCompanySelected +"'";
				ResultSet rs =c.s.executeQuery(query); 
				
				
				//local variables 
				int i =0;
				
				// Adds all car models in column carCompany in the database to an arraylist
				// Contents of arraylist are added to a hashset
				//Hashset is then fed bak into ch1 so that only unique car companys are displayed
				ArrayList<String> choiceItemsCarModel= new ArrayList<>();
				while(rs.next()) {
					//ch1.add();
					choiceItemsCarModel.add(rs.getString("carModel"));
				}

				HashSet<String> hashSet = new HashSet<>(choiceItemsCarModel);
				Iterator itr = hashSet.iterator();

			   if (ch2.countItems() >0) {
				   ch2.removeAll();
			   }
		       while (itr.hasNext()) {
		            //System.out.println(itr.next());
		            ch2.add(itr.next().toString());
		        }
		       
		       
				System.out.println(hashSet);
			} catch (Exception e) {
				System.out.println(e);
			}
		}
		
	}

		
}
