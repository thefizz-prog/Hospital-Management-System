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
	Page for searching managers
	Info is pulled from the employee database wherre job = Manager
*/


public class ManagerInfo extends JFrame implements ActionListener{
	
	// variable declaration
	JPanel mainPanel;
	JTable table;
	DefaultTableModel model_table;
	JScrollPane scroll_table;
	JButton b1,b2;
	JTextField t1;


public static void main(String[] args) {
    ManagerInfo main = new ManagerInfo();
    main.setVisible(true);
}   
public ManagerInfo() {
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

    //buttons, labels and fields 
    b1 = new JButton();
	b1.setBackground(Color.black);
	b1.setForeground(Color.white);
	b1.setBounds(150,360,200,30);
	b1.setLabel("Search Manager Name");
	b1.addActionListener(this);
	add(b1);
	
    b2 = new JButton();
	b2.setBackground(Color.black);
	b2.setForeground(Color.white);
	b2.setBounds(150,410,200,30);
	b2.setLabel("Back");
	b2.addActionListener(this);
	add(b2);
	
	t1 = new JTextField();
	t1.setBackground(Color.WHITE);
	t1.setForeground(Color.BLACK);
	t1.setBounds(360,360,150,30);
	t1.addActionListener(this);
    add(t1);
    
    //Loads existing staff info by default for the user
	try {
		
		conn c = new conn();
		String str = "select * from employee where job = 'Manager'";
		ResultSet rs = c.s.executeQuery(str);
		
		table.setModel(DbUtils.resultSetToTableModel(rs));
		getContentPane().setBackground(Color.white);
		
	} catch(Exception e) {
		
	}

	//scroll table properties
    scroll_table = new JScrollPane(table);            // add table to scroll panel
    scroll_table.setBounds(5, 10, 600, 150);
    scroll_table.setVisible(true);
    mainPanel.add(scroll_table);

    this.add(mainPanel);
  }


@Override
public void actionPerformed(ActionEvent ae) {
	
	//gets specific manager by name
	if (ae.getSource()==b1) {
		
		try {
			
			conn c = new conn();
			String name = t1.getText();
			String str = "select * from employee where name REGEXP '(\\w)*("+name+")(\\w)*'";
			
			
			ResultSet rs = c.s.executeQuery(str);
			
			table.setModel(DbUtils.resultSetToTableModel(rs));
			getContentPane().setBackground(Color.white);
			
		} catch(Exception e) {
			
		}
	// close current window and open reception
	} else if (ae.getSource() == b2) {
		this.setVisible(false);
		new Reception().setVisible(true);
	}
	
}


	


}


