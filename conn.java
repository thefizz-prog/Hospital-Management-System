package hotel.management.system;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JOptionPane;

/*
	Creates a connection to the localhost mql server
*/
public class conn{
    Connection c;
    Statement s;
    public conn(){  
        try{  
            Class.forName("com.mysql.cj.jdbc.Driver");  
            c =DriverManager.getConnection("jdbc:mysql://localhost:3306/hospitalmanagementsystem","root","TestTest"); 
            
            //tests if connection is successful
            if(c!=null) {
            	System.out.println("Connection");
            }
            else {
            	System.out.println("NOT");
            }
            
            s =c.createStatement();  
            
           
        }catch(Exception e){ 
            System.out.println(e);
        }  
    } 
    

}  