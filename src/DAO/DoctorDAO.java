package DAO;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.print.Doc;

import core.Doctor;
import core.Person;


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
		  Rs = St.executeQuery("select * from doctor");
		  
		  while (Rs.next()) {
	          Doctor tempDoctor = covertRowToDoctor(Rs);
	          listAllDoctor.add(tempDoctor);
	          
		  }
			return listAllDoctor;
	  }  

    finally {
	   close(St, Rs);
}
	
  }
  
  //Get Doctors by name
  public List<Doctor> getDoctorByName(String name) throws Exception{
	List<Doctor> list = new ArrayList<>();
	
	PreparedStatement St = null;
	ResultSet Rs = null;
	
	try {
		name += "%";
		St = Con.prepareStatement("select * from doctor where firstName like ? or lastName like ?");
		St.setString(1, name);
		St.setString(2, name);
		Rs = St.executeQuery();
		
		while (Rs.next()) {
			Doctor tempDoctor = convertRowToDoctor(Rs);
			list.add(tempDoctor);
		
		}
	  return list;
	}
	finally {
		close(St, Rs);
	}
  }
  
//Get Doctors by ID
  public Person getDoctorByID(String id) throws Exception{
	
	PreparedStatement St = null;
	ResultSet Rs = null;
	Person thisperson = null;
	
	try {
	
		St = Con.prepareStatement("select * from doctor where doctorId like ?");
		St.setString(1, id);
		Rs = St.executeQuery();
		
		while (Rs.next()) {
			Doctor tempDoctor = convertRowToDoctor(Rs);
            thisperson = tempDoctor;		
		}
	  return thisperson;
	}
	finally {
		close(St, Rs);
	}
  }
  
  //Add a Doctor 
  public void addDoctor(Doctor newDoctor) throws Exception{
	  PreparedStatement St = null;
	  try {
		  String sql = "Insert into doctor"
				  + " (doctorId, lastName, firstName, dateOfBirth,address,email, phoneNumber)"
					+ " values (?, ? ,? , ?, ?, ?, ?) " ;
	  
	 St = Con.prepareStatement(sql);
	 
	 String stringDate = formatter.format(newDoctor.getDateOfBirth());
	 
	 St.setString(1, newDoctor.getID() );
	 St.setString(2, newDoctor.getLastName());
	 St.setString(3, newDoctor.getFirstName());
	 St.setString(4, stringDate);
	 St.setString(5, newDoctor.getAddress());
	 St.setString(6, newDoctor.getEmail());
	 St.setString(7, newDoctor.getPhoneNum());
	  
	
	  }
	  finally {
		  St.close();
	  }
  }
}
