package hotel.management.system;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Dashboard extends JFrame implements ActionListener{

	//Variable declaration 
	JMenuBar mb;
	JMenu m1,m2;
	JMenuItem i1,i2,i3,i4;
	
	//constructor 
	Dashboard() {
		//menu, field and texts
		mb = new JMenuBar();
		add(mb);
		
		m1 = new JMenu ("Hotel Management");
		m1.setForeground(Color.RED);
		mb.add(m1);
		
		m2 = new JMenu ("ADMIN");
		m2.setForeground(Color.BLUE);
		mb.add(m2);
		
		i1 = new JMenuItem ("RECEPTION");
		i1.addActionListener(this);
		m1.add(i1);
		
		i2 = new JMenuItem ("ADD EMPLOYEE");
		i2.addActionListener(this);
		m2.add(i2);
		
		i3 = new JMenuItem ("ADD ROOMS");
		i3.addActionListener(this);
		m2.add(i3);
		
		i4 = new JMenuItem ("ADD DRIVERS");
		i4.addActionListener(this);
		m2.add(i4);
		
		
		mb.setBounds(0,0,1500,30);
		
		ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("hotel/management/system/icons/Hotel1.jpg"));
		//Scale the image correctly 
		Image i2 = i1.getImage().getScaledInstance(1950, 1000, Image.SCALE_DEFAULT);
		ImageIcon i3 = new ImageIcon(i2);
		
		JLabel l1 = new JLabel(i3);
		l1.setBounds(0,0,1200,600);
		add(l1);
		
		
		JLabel l2 = new JLabel("THE HOTEL WELCOMES YOU");
		
		l2.setBounds(300,50,600,50);
		l2.setForeground(Color.BLACK);
		l2.setFont(new Font("Calibri", Font.PLAIN, 25));
		
		l1.add(l2);
		
		
		//window properties
		setLayout(null);
		setBounds(0,0,900,600);
		setVisible(true);
		}
	
	public static void main(String[] args) {
		new Dashboard().setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		
		//open reception if reception selected
		if (ae.getActionCommand().equals("RECEPTION")) {
			new Reception().setVisible(true);
			
		//open add employee if add employee  selected
		}else if (ae.getActionCommand().equals("ADD EMPLOYEE")) {
			new addEmployee().setVisible(true);
		
		//open add rooms if  selected	
		}else if (ae.getActionCommand().equals("ADD ROOMS")) {
			new addRooms().setVisible(true);
		
		//open add drivers if add drivers selected
		} else if (ae.getActionCommand().equals("ADD DRIVERS")) {
			new addDriver().setVisible(true); 
		}
		
	}
	
}
