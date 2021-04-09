package DAO;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import core.Doctor;


public class DoctorDAO {
  private Connection Con;
     private SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
     
  public DoctorDAO() throws Exception{
	  Properties prop= new Properties();
	  prop.load(new FileInputStream("Database/preson.properties"));
	  String user = prop.getProperty("user");
	  String password = prop.getProperty("password");
	  String url = prop.getProperty("url");
	  Con = DriverManager.getConnection(url,user,password);	  
  }
  // lay du lieu bang Doctors vao list
  public List<Doctor> getAllDoctor() throws Exception{
	  List<Doctor> listAllDoctor = new ArrayList<>();
	  Statement St = null;
	  ResultSet Rs = null;
	   
	  try {
		  St = Con.createStatement();
		  Rs = St.executeQuery("select * from doctors");
		  
		  while (Rs.next()) {
	          Doctor tempDoctor = covertRowToDoctor(Rs);
	          listAllDoctor.add(tempDoctor);
	          
		  }
			return listAllDoctor;
	  }  

    finally {
	   close(St,Rs);
}
	
  }
}
