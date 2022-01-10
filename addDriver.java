package hotel.management.system;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/*
	Adds a new driver to the driver database
*/
public class addDriver extends JFrame implements ActionListener {

	//variable declaration 
	JTextField t1,t2,t3,t4,t5;
	JComboBox c1,c2;
	JButton b1,b2;
	
	//constuctor 
	addDriver() {
		
		
		// dynamic label and field co-ordinates
		int xposJLabel =60;
	    int yposJLabel =70;
	    
	    int xposJText = xposJLabel +150;
	    int yposJText =yposJLabel;
		
	    // Set label and field properties
		JLabel title = new JLabel("Add Drivers");
		title.setFont(new Font("Calibri", Font.BOLD, 20));
		title.setBounds(xposJLabel+80, yposJLabel-40, 150,30);
		add(title);
		
		JLabel name = new JLabel("NAME");
		name.setBounds(xposJLabel,yposJLabel,100,30);
		name.setFont(new Font("Calibri", Font.PLAIN, 20));
		add(name);
		
		t1 = new JTextField();
		t1.setBounds(xposJText, yposJText, 130, 20);
		add(t1);
		
		
		JLabel age = new JLabel("AGE");
		age.setBounds(xposJLabel,yposJLabel+=40,100,30);
		age.setFont(new Font("Calibri", Font.PLAIN, 20));
		add(age);
		
		t2 = new JTextField();
		t2.setBounds(xposJText, yposJText+=40, 130, 20);
		add(t2);
		
		JLabel gender = new JLabel("GENDER");
		gender.setBounds(xposJLabel,yposJLabel+=40,100,30);
		gender.setFont(new Font("Calibri", Font.PLAIN, 20));
		add(gender);
		
		c1 = new JComboBox(new String[] {"Male", "Female"});
		c1.setBounds(xposJText, yposJText+=40, 130, 20);
		add(c1);
		
		JLabel carCompany = new JLabel("CAR COMPANY");
		carCompany.setBounds(xposJLabel,yposJLabel+=40,130,30);
		carCompany.setFont(new Font("Calibri", Font.PLAIN, 20));
		add(carCompany);
		
		t3 = new JTextField();
		t3.setBounds(xposJText, yposJText+=40, 130, 20);
		add(t3);
		
		
		JLabel carModel = new JLabel("CAR MODEL");
		carModel.setBounds(xposJLabel,yposJLabel+=40,100,30);
		carModel.setFont(new Font("Calibri", Font.PLAIN, 20));
		add(carModel);
		
		t4 = new JTextField();
		t4.setBounds(xposJText, yposJText+=40, 130, 20);
		add(t4);
		
		JLabel available = new JLabel("AVAILABLE");
		available.setBounds(xposJLabel,yposJLabel+=40,100,30);
		available.setFont(new Font("Calibri", Font.PLAIN, 20));
		add(available);
		
		c2 = new JComboBox(new String[] {"Available", "Not available"});
		c2.setBounds(xposJText, yposJText+=40, 130, 20);
		add(c2);
		
		JLabel location = new JLabel("LOCATION");
		location.setBounds(xposJLabel,yposJLabel+=40,100,30);
		location.setFont(new Font("Calibri", Font.PLAIN, 20));
		add(location);
		
		t5 = new JTextField();
		t5.setBounds(xposJText, yposJText+=40, 130, 20);
		add(t5);
		
		
		b1 = new JButton("ADD DRIVER");
		b1.setBackground(Color.white);
		b1.setForeground(Color.BLACK);
		b1.setBounds(xposJLabel+5,yposJLabel+=40,150,30);
		b1.addActionListener(this);
		add(b1);
		
		b2 = new JButton("CANCEL");
		b2.setBackground(Color.white);
		b2.setForeground(Color.BLACK);
		b2.setBounds(xposJLabel+150,yposJLabel,150,30);
		b2.addActionListener(this);
		add(b2);
		
		
		ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("hotel/management/system/icons/driver.jpg"));
		JLabel l1 = new JLabel(i1);
		l1.setBounds(400,30,340,400);
		add(l1);
		
		
		//sets window properties
		getContentPane().setBackground(Color.white);
		setLayout(null);
		setBounds(400,200,800,500);
		setVisible(true);
		
	}
	
	public static void main(String[] args) {
		new addDriver().setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource() == b1) {
			
			//Grabs info in fields 
			String name = t1.getText();
			String age = t2.getText();
			String gender = (String) c1.getSelectedItem();
			String carCompany = t3.getText();
			String carModel = t4.getText();
			String available = (String) c2.getSelectedItem();
			String location = t5.getText();
			
			
		conn d = new conn();
        
		// Inserts new driver row into driver database
		String str = "INSERT INTO drivers (name, age, gender, carCompany, carModel, available, location) VALUES( '"+name+"', '"+age+"', '"+gender+"','"+carCompany+"', '"+carModel+"', '"+available+"' , '"+location+"')";
		
		try {
			d.s.executeUpdate(str);
			JOptionPane.showMessageDialog(null, "Driver Added Successfully");
			this.setVisible(false);

		}catch (Exception e) {
			System.out.println(e);
		}

		}
		
	}
}
