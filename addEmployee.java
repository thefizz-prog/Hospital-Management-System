package hotel.management.system;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

import javax.swing.*;

import com.mysql.cj.protocol.Resultset;

/*
	Adds a new employee to the employee database
*/
public class addEmployee extends JFrame implements ActionListener{ 
	
	JTextField t1,t2,t3,t4,t5,t6,t7,t8;
	JRadioButton r1,r2;
	JComboBox c1;
	JButton b1;
	
	//constructor 
	addEmployee() {
		JLabel name = new JLabel("NAME");
		name.setFont(new Font("Tahoma", Font.PLAIN, 20));
		name.setBounds(60,30,120,30);
		add(name);
		
		t1 = new JTextField();
		t1.setBounds(150,30,120,30);
		add(t1);
		
		JLabel age = new JLabel("AGE");
		age.setFont(new Font("Tahoma", Font.PLAIN, 20));
		age.setBounds(60,70,120,30);
		add(age);
		
		t2 = new JTextField();
		t2.setBounds(150,70,120,30);
		add(t2);
		
		JLabel gender = new JLabel("GENDER");
		gender.setFont(new Font("Tahoma", Font.PLAIN, 20));
		gender.setBounds(60,110,120,30);
		add(gender);
		
		r1 = new JRadioButton("Male");
		r1.setFont(new Font("Calibri", Font.PLAIN, 18));
		r1.setBounds(150,110,70,30);
		r1.setBackground(Color.white);
		add(r1);
		
		r2 = new JRadioButton("Female");
		r2.setBounds(220,110,80,30);
		r2.setBackground(Color.white);
		r2.setFont(new Font("Calibri", Font.PLAIN, 18));
		add(r2);
		
		JLabel job = new JLabel("JOB");
		job.setFont(new Font("Tahoma", Font.PLAIN, 20));
		job.setBounds(60,150,120,30);
		add(job);
		
		String str[] = {"Waiter", "Porter", "Clerk", "Room Service", "Manager"};
		c1 = new JComboBox(str);
		c1.setBounds(150,150,120,30);
		c1.setBackground(Color.white);
		add(c1);
		
		JLabel salary = new JLabel("SALARY");
		salary.setFont(new Font("Tahoma", Font.PLAIN, 20));
		salary.setBounds(60,190,120,30);
		add(salary);
		
		t5 = new JTextField();
		t5.setBounds(150,190,120,30);
		add(t5);
		
		JLabel phone = new JLabel("PHONE");
		phone.setFont(new Font("Tahoma", Font.PLAIN, 20));
		phone.setBounds(60,230,120,30);
		add(phone);
		
		t6 = new JTextField();
		t6.setBounds(150,230,120,30);
		add(t6);
		
		JLabel address = new JLabel("ADDRESS");
		address.setFont(new Font("Tahoma", Font.PLAIN, 20));
		address.setBounds(60,270,120,30);
		add(address);
		
		t7 = new JTextField();
		t7.setBounds(150,270,120,30);
		add(t7);
		
		JLabel email = new JLabel("EMAIL");
		email.setFont(new Font("Tahoma", Font.PLAIN, 20));
		email.setBounds(60,310,120,30);
		add(email);
		
		t8 = new JTextField();
		t8.setBounds(150,310,120,30);
		add(t8);
		
		b1 = new JButton();
		b1.setBackground(Color.black);
		b1.setForeground(Color.white);
		b1.setBounds(150,360,120,30);
		b1.setLabel("Next");
		b1.addActionListener(this);
		add(b1);
		
		ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("hotel/management/system/icons/staff.jpg"));
		Image i2 = i1.getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT);
		ImageIcon i3 = new ImageIcon(i2);
		JLabel l1 = new JLabel(i3);
		l1.setBounds(260,5,300,300);
		add(l1);
		
		JLabel l2 = new JLabel("ADD EMPLOYEE DETAILS");
		l2.setForeground(Color.RED);
		l2.setBounds(320,30,400,30);
		l2.setFont(new Font("Calibri", Font.PLAIN,20));
		add(l2);
		
		//sets window properties
		getContentPane().setBackground(Color.WHITE);
		setLayout(null);
		setBounds(400,200,600,500);
		setVisible(true);
		
		
	}

	public static void main (String[] args) {
		new addEmployee().setVisible(true);
		
		
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		
		//grabs info placed in fields
		String name = t1.getText();
		String age = t2.getText();
		String salary = t5.getText();
		String phone = t6.getText();
		String address = t7.getText();
		String email = t8.getText();
		
		String gender = null;
		
		if (r1.isSelected()) {
			gender = "male";
		}
		else  if (r2.isSelected()){
			gender = "female";
		}
		
		String job = (String) c1.getSelectedItem();
		
		conn c = new conn();
		              
		//creates a new employee row in the employee database         		
		String str = "INSERT INTO employee (name,age,gender,job,salary, phone, address, email) VALUES( '"+name+"', '"+age+"', '"+gender+"','"+job+"', '"+salary+"', '"+phone+"','"+address+"', '"+email+"')";
				
		try {
			c.s.executeUpdate(str);
			JOptionPane.showMessageDialog(null, "Employee Added Successfully");
			this.setVisible(false);

		}catch (Exception e) {
			System.out.println(e);
		}

	}
}
