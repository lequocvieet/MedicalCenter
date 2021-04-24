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

import core.Patient;
import core.Person;

public class PatientDAO {
    private Connection Con;

    private SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

    // connect SQL
    public PatientDAO() throws Exception {
        Properties prop = new Properties();
        prop.load(new FileInputStream("database/preson.properties"));
        String user = prop.getProperty("user");
        String password = prop.getProperty("password");
        String url = prop.getProperty("url");
        Con = DriverManager.getConnection(url, user, password);
    }

    // lay du lieu bang Patient vao list
    public List<Patient> getAllPatient() throws Exception {
        List<Patient> listAllPatient = new ArrayList<>();
        Statement St = null;
        ResultSet Rs = null;

        try {
            St = Con.createStatement();
            Rs = St.executeQuery("select * from patient");

            while (Rs.next()) {
                Patient tempPatient = convertRowToPatient(Rs);
                listAllPatient.add(tempPatient);
            }
            return listAllPatient;
        } finally {
            close(St, Rs);
        }
    }

    // Lay Patient bang ten
    public List<Patient> getPatientByName(String name) throws Exception {
        List<Patient> list = new ArrayList<>();

        PreparedStatement St = null;
        ResultSet Rs = null;

        try {
            name += "%";
            St = Con.prepareStatement("select * from patient where firstName like ? or lastName like ?");
            St.setString(1, name);
            St.setString(2, name);
            Rs = St.executeQuery();

            while (Rs.next()) {
                Patient tempPatient = convertRowToPatient(Rs);
                list.add(tempPatient);

            }
            return list;
        } finally {
            close(St, Rs);
        }
    }

    // Lay tat ca Patient theo ID tu Table vao list "Person"
    public Person getPatientByID(String id) throws Exception {

        PreparedStatement St = null;
        ResultSet Rs = null;
        Person thisperson = null;

        try {

            St = Con.prepareStatement("select * from patient where patientId like ?");
            St.setString(1, id);
            Rs = St.executeQuery();

            while (Rs.next()) {
                Patient tempPatient = convertRowToPatient(Rs);
                thisperson = tempPatient;
            }
            return thisperson;
        } finally {
            close(St, Rs);
        }
    }

    // Them Patient
    public void addPatient(Patient newPatient) throws Exception {
        PreparedStatement St = null;
        try {
            String sql = "Insert into patient"
                    + "(patientId, lastName, firstName, dateOfBirth, gender, doctorName, address, phoneNumber, email)"
                    + " values (?, ?, ?, ?, ?, ?, ?, ?, ?) ";
            St = Con.prepareStatement(sql);

            String stringDate = formatter.format(newPatient.getDateOfBirth());

            St.setString(1, newPatient.getID());
            St.setString(2, newPatient.getLastName());
            St.setString(3, newPatient.getFirstName());
            St.setString(4, stringDate);
            St.setString(5, newPatient.getGender());
            St.setString(6, newPatient.getDoctorName());
            St.setString(7, newPatient.getAddress());
            St.setString(8, newPatient.getPhoneNum());
            St.setString(9, newPatient.getEmail());

            St.executeUpdate();

        } finally {
            St.close();
        }

    }

    // Chuyen doi 1 Patient trong bang -> doi tuong Patient
    private Patient convertRowToPatient(ResultSet Rs) throws SQLException, java.text.ParseException {
        String patientID = Rs.getString("patientId");
        String lastName = Rs.getString("lastName");
        String firstName = Rs.getString("firstName");
        String dateOfBirth = Rs.getString("dateOfBirth");
        String gender = Rs.getString("gender");
        String doctorName = Rs.getString("doctorName");
        String address = Rs.getString("address");
        String phoneNum = Rs.getString("phoneNumber");
        String email = Rs.getString("email");

        Date tempdate = formatter.parse(dateOfBirth);
        Patient tempPatient = new Patient(patientID, lastName, firstName, tempdate, address, email, phoneNum, gender,
                doctorName);

        return tempPatient;
    }

    // Updating Patient
    public void updatePatient(Patient temp) throws SQLException {
        PreparedStatement St = null;
        try {
            String sql = "update patient "
                    + " set lastName = ?, firstName = ?, dateOfBirth=?, gender = ?, doctorName = ? , address= ?, phoneNum=?, email=?,"
                    + " where patientID = ? ";
            St = Con.prepareStatement(sql);

            String stringDate = formatter.format(temp.getDateOfBirth());

            St.setString(1, temp.getLastName());
            St.setString(2, temp.getFirstName());
            St.setString(3, stringDate);
            St.setString(4, temp.getGender());
            St.setString(5, temp.getDoctorName());
            St.setString(6, temp.getAddress());
            St.setString(7, temp.getPhoneNum());
            St.setString(8, temp.getEmail());
            St.setString(9, temp.getID());

            St.executeUpdate();

        } finally {
            St.close();
        }
    }

    // Xoa 1 Patient theo patientID
    public void deletePatient(String patientID) throws SQLException {
        PreparedStatement St = null;
        try {
            String sql = "delete from patient where patientID = ?";
            St = Con.prepareStatement(sql);
            St.setString(1, patientID);
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
