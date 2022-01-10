package hotel.management.system;

import java.awt.Choice;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;




/*
	Adds a new customer to the customer database
	A new customer can only be assigned to a room which has the status roomAvailability = 'Available'" 
*/
public class AddCustomer extends JFrame implements ActionListener {

	
	
	JTextField t1,t2,t3,t4,t5,t6,t7,t8;
	JRadioButton r1,r2;
	JComboBox c1,c3;
	Choice c2;
	JButton b1,b2;
	
	//constructor 
	AddCustomer() {
		
		
		// allows for dynamic setting of fields and labels 
		int xposEntryField = 320;
		int xposEntryFieldLength = 250;
		int yposEntryField =30;
		int xstaticField = 60;
		int ystaticField = 30;
		
		JLabel id = new JLabel("ID");
		id.setFont(new Font("Tahoma", Font.PLAIN, 20));
		id.setBounds(xstaticField,ystaticField,xposEntryFieldLength,30);
		add(id);
		
		c1 = new JComboBox(new String[] {"Passport", "Drivers License"});
		c1.setBounds(xposEntryField,yposEntryField,120,30);
		c1.setForeground(Color.BLACK);
		c1.setBackground(Color.WHITE);
		c1.addActionListener(this);
		add(c1);
		
		JLabel number = new JLabel("ID Number");
		number.setFont(new Font("Tahoma", Font.PLAIN, 20));
		number.setBounds(xstaticField,ystaticField+=40,xposEntryFieldLength,30);
		add(number);
		
		t1 = new JTextField();
		t1.setBounds(xposEntryField,yposEntryField+=40,120,30);
		add(t1);
		
		JLabel name = new JLabel("Name");
		name.setFont(new Font("Tahoma", Font.PLAIN, 20));
		name.setBounds(xstaticField,ystaticField+=40,xposEntryFieldLength,30);
		add(name);
		
		t2 = new JTextField();
		t2.setBounds(xposEntryField,yposEntryField+=40,120,30);
		add(t2);
		
		JLabel gender = new JLabel("Gender");
		gender.setFont(new Font("Tahoma", Font.PLAIN, 20));
		gender.setBounds(xstaticField,ystaticField+=40,xposEntryFieldLength,30);
		add(gender);

		r1 = new JRadioButton("Male");
		r1.setFont(new Font("Calibri", Font.PLAIN, 18));
		r1.setBounds(xposEntryField,yposEntryField+=40,70,30);
		r1.setBackground(Color.white);
		add(r1);
		
		r2 = new JRadioButton("Female");
		r2.setBounds(xposEntryField+80,yposEntryField,80,30);
		r2.setBackground(Color.white);
		r2.setFont(new Font("Calibri", Font.PLAIN, 18));
		add(r2);
		
		
		JLabel country = new JLabel("Country");
		country.setFont(new Font("Tahoma", Font.PLAIN, 20));
		country.setBounds(xstaticField,ystaticField+=40,xposEntryFieldLength,30);
		add(country);

		t3 = new JTextField();
		t3.setBounds(xposEntryField,yposEntryField+=40,120,30);
		add(t3);
		
		
		JLabel salary = new JLabel("Allocated Room Number");
		salary.setFont(new Font("Tahoma", Font.PLAIN, 20));
		salary.setBounds(xstaticField,ystaticField+=40,xposEntryFieldLength,30);
		add(salary);
		
		c2 = new Choice();
		
		//Displays only rooms available for booking and adds to choice field
		try {
			conn c = new conn();
			String str = "select * from room where roomAvailability = 'Available'";
			ResultSet rs = c.s.executeQuery(str);
			
			while(rs.next()) {
				c2.add(rs.getString("roomNumber"));
			}
			
			
		} catch (Exception e) {
			
		}
		
		c2.setBounds(xposEntryField,yposEntryField+=40,120,30);
		add(c2);

		
		JLabel checkedin = new JLabel("Checked In");
		checkedin.setFont(new Font("Tahoma", Font.PLAIN, 20));
		checkedin.setBounds(xstaticField,ystaticField+=40,xposEntryFieldLength,30);
		add(checkedin);
		
		c3 = new JComboBox(new String[] {"Yes", "No"});
		c3.setBounds(xposEntryField,yposEntryField+=40,120,30);
		c3.setForeground(Color.BLACK);
		c3.setBackground(Color.WHITE);
		c3.addActionListener(this);
		add(c3);
		
		JLabel Desposit = new JLabel("Deposit");
		Desposit.setFont(new Font("Tahoma", Font.PLAIN, 20));
		Desposit.setBounds(xstaticField,ystaticField+=40,xposEntryFieldLength,30);
		add(Desposit);
		
		t7 = new JTextField();
		t7.setBounds(xposEntryField,yposEntryField+=40,120,30);
		add(t7);
		
		
		b1 = new JButton();
		b1.setBackground(Color.black);
		b1.setForeground(Color.white);
		b1.setBounds(150,360,120,30);
		b1.setLabel("Next");
		b1.addActionListener(this);
		add(b1);
		
		b2 = new JButton();
		b2.setBackground(Color.black);
		b2.setForeground(Color.white);
		b2.setBounds(300,360,120,30);
		b2.setLabel("Back");
		b2.addActionListener(this);
		add(b2);
		
		ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("hotel/management/system/icons/addcustomer.jpg"));
		Image i2 = i1.getImage().getScaledInstance(400, 400, Image.SCALE_DEFAULT);
		ImageIcon i3 = new ImageIcon(i2);
		JLabel l1 = new JLabel(i3);
		l1.setBounds(450,20,500,300);
		add(l1);

		
		//sets window properties
		getContentPane().setBackground(Color.WHITE);
		setLayout(null);
		setBounds(800,200,1000,500);
		setVisible(true);
		
		
	}

	public static void main (String[] args) {
		new AddCustomer().setVisible(true);
		
		
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		
		String id = (String) c1.getSelectedItem();
		String number = t1.getText();
		String name = t2.getText();
		String gender = null;
		
		if (r1.isSelected()) {
			gender = "male";
		}
		else  if (r2.isSelected()){
			gender = "female";
		}
		 
		String country = t3.getText();
		String allocatedRoom = c2.getSelectedItem();
		String checkedIn = (String) c3.getSelectedItem();
		String deposit = t7.getText();
		
		
		
		//adds a new customer row into the customer database  
		if (ae.getSource() == b1 ) {
			conn c = new conn();
            	
				String str = "INSERT INTO customer (id, number, name, gender, country , allocatedRoom , checkedIn , deposit ) VALUES( '"+id+"', '"+number+"', '"+name+"','"+gender+"', '"+country+"', '"+allocatedRoom+"','"+checkedIn+"', '"+deposit+"')";
				String newRoomStatus = 	"update room set roomAvailability = 'Occupied' where roomNumber =" +allocatedRoom+ "";
				try {
					c.s.executeUpdate(str);
					c.s.executeUpdate(newRoomStatus);
					JOptionPane.showMessageDialog(null, "Customer Added Successfully, room status changed");
					this.setVisible(false);

				}catch (Exception e) {
					System.out.println(e);
				}
		// closes the current view and returns to the main reception menu 
		} else if (ae.getSource() == b2) {
			this.setVisible(false);
			new Reception().setVisible(true);
		}

	}
}
