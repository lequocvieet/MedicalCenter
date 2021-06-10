package DAO;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import core.Doctor;
import core.Person;

public class DoctorDAO {

    private Connection Con;

    private SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

    //connect SQL
    public DoctorDAO() throws Exception {
        Properties prop = new Properties();
        prop.load(new FileInputStream("database/person.properties"));
        String user = prop.getProperty("user");
        String password = prop.getProperty("password");
        String url = prop.getProperty("url");
        Con = DriverManager.getConnection(url, user, password);
    }

    // lay du lieu bang Doctors vao list
    public List<Doctor> getAllDoctor() throws Exception {
        List<Doctor> listAllDoctor = new ArrayList<>();
        Statement St = null;
        ResultSet Rs = null;

        try {
            St = Con.createStatement();
            Rs = St.executeQuery("select * from doctor");

            while (Rs.next()) {
                Doctor tempDoctor = convertRowToDoctor(Rs);
                listAllDoctor.add(tempDoctor);
            }
            return listAllDoctor;
        } finally {
            close(St, Rs);
        }
    }

    // Lay Doctor bang ten
    public List<Doctor> getDoctorByName(String name) throws Exception {
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
        } finally {
            close(St, Rs);
        }
    }

    // Lay Doctor bang ID tu bang vao list "Person"
    public Person getDoctorByID(String id) throws Exception {

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
        } finally {
            close(St, Rs);
        }
    }

    // Them Doctor
    public void addDoctor(Doctor newDoctor) throws Exception {
        PreparedStatement St = null;
        try {
            String sql = "Insert into doctor"
                    + " (doctorId, lastName, firstName, dateOfBirth,address,email, phoneNumber)"
                    + " values (?, ? ,? , ?, ?, ?, ?) ";

            St = Con.prepareStatement(sql);

            String stringDate = formatter.format(newDoctor.getDateOfBirth());

            St.setString(1, newDoctor.getID());
            St.setString(2, newDoctor.getLastName());
            St.setString(3, newDoctor.getFirstName());
            St.setString(4, stringDate);
            St.setString(5, newDoctor.getAddress());
            St.setString(6, newDoctor.getEmail());
            St.setString(7, newDoctor.getPhoneNum());

            St.executeUpdate();

        } finally {
            St.close();
        }

    }

    // Chuyen doi 1 Doctor trong bang -> doi tuong
    private Doctor convertRowToDoctor(ResultSet Rs) throws SQLException, java.text.ParseException {
        String doctorID = Rs.getString("doctorId");
        String lastName = Rs.getString("lastName");
        String firstName = Rs.getString("firstName");
        String dateOfBirth = Rs.getString("dateOfBirth");
        String email = Rs.getString("email");
        String address = Rs.getString("address");
        String phoneNum = Rs.getString("phoneNumber");
        Date tempdate = formatter.parse(dateOfBirth);
        Doctor tempDoctor = new Doctor(doctorID, lastName, firstName, tempdate, address, email, phoneNum);

        return tempDoctor;
    }

    // Updating Doctor trong bang
    public void updateDoctor(Doctor temp) throws SQLException {
        PreparedStatement St = null;
        try {
            String sql = "Update doctor "
                    + " set lastName = ?, firstName = ?, dateOfBirth=?,address= ?,email=?, phoneNumber=?"
                    + " where doctorId = ? ";
            St = Con.prepareStatement(sql);

            String stringDate = formatter.format(temp.getDateOfBirth());

            St.setString(1, temp.getLastName());
            St.setString(2, temp.getFirstName());
            St.setString(3, stringDate);
            St.setString(4, temp.getAddress());
            St.setString(5, temp.getEmail());
            St.setString(6, temp.getPhoneNum());
            St.setString(7, temp.getID());

            St.executeUpdate();

        } finally {
            St.close();
        }
    }

    // Xoa doctor theo doctorId
    public void deleteDoctor(String doctorID) throws SQLException {
        PreparedStatement St = null;
        try {
            String sql = "delete from doctor where doctorId = ?";
            St = Con.prepareStatement(sql);
            St.setString(1, doctorID);
            St.executeUpdate();

        } finally {
            St.close();
        }
    }

    // Close connection
    private static void close(Connection Con, Statement St, ResultSet Rs) throws SQLException {
        if (Rs != null) {
            Rs.close();
        }
        if (St != null) {
            St.close();
        }
        if (Con != null) {
            Con.close();
        }

    }

    private void close(Statement St, ResultSet Rs) throws SQLException {
        close(null, St, Rs);
    }

}

