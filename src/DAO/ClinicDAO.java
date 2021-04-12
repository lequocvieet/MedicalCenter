package DAO;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import core.Clinic;

public class ClinicDAO {
	private Connection Con;

	// connect SQL
	public ClinicDAO() throws Exception {
		Properties prop = new Properties();
		prop.load(new FileInputStream("database/preson.properties"));
		String user = prop.getProperty("user");
		String password = prop.getProperty("password");
		String url = prop.getProperty("url");
		Con = DriverManager.getConnection(url, user, password);
	}

	public List<Clinic> getAllClinic() throws Exception {
		List<Clinic> listAllClinic = new ArrayList<>();
		Statement St = null;
		ResultSet Rs = null;

		try {
			St = Con.createStatement();
			Rs = St.executeQuery("select * from clinic");

			while (Rs.next()) {
				Clinic tempClinic = convertRowToClinic(Rs);
				listAllClinic.add(tempClinic);
			}
			return listAllClinic;
		} finally {
			close(St, Rs);
		}
	}

	// Lay Clinic bang ten tu bang vao list
	public List<Clinic> getClinicrByName(String name) throws Exception {
		List<Clinic> list = new ArrayList<>();

		PreparedStatement St = null;
		ResultSet Rs = null;

		try {
			name += "%";
			St = Con.prepareStatement("select * from clinic where clinicName like ? ");
			St.setString(1, name);
			St.setString(2, name);
			Rs = St.executeQuery();

			while (Rs.next()) {
				Clinic tempClinic = convertRowToClinic(Rs);
				list.add(tempClinic);

			}
			return list;
		} finally {
			close(St, Rs);
		}
	}

	// Them Clinic
	public void addClinic(Clinic newClinic) throws Exception {
		PreparedStatement St = null;
		try {
			String sql = "Insert into clinic" + " (clinicID, clinicName, address, email, phoneNum, type)"
					+ " values (?, ? ,? , ?, ?, ?) ";

			St = Con.prepareStatement(sql);

			St.setString(1, newClinic.getID());
			St.setString(2, newClinic.getClinicName());
			St.setString(3, newClinic.getAddress());
			St.setString(4, newClinic.getEmail());
			St.setString(5, newClinic.getPhoneNum());
			St.setString(6, newClinic.getType());

			St.executeUpdate();

		} finally {
			St.close();
		}
	}

	// Chuyen doi 1 Clinic trong bang -> doi tuong
	private Clinic convertRowToClinic(ResultSet Rs) throws SQLException, java.text.ParseException {
		String ID = Rs.getString("clinicID");
		String clinicName = Rs.getString("clinicName");
		String address = Rs.getString("address");
		String email = Rs.getString("email");
		String phoneNum = Rs.getString("phoneNum");
		String type = Rs.getString("type");
		Clinic tempClinic = new Clinic();

		return tempClinic;
	}

	// Update Clinic
	public void updateClinic(Clinic temp) throws SQLException {
		PreparedStatement St = null;
		try {
			String sql = "Update clinic " 
		            + " set clinicName = ?, address= ?,email=?, phoneNum=?, type = ?"
					+ " where clinicID = ? ";
			St = Con.prepareStatement(sql);

			St.setString(1, temp.getClinicName());
			St.setString(2, temp.getAddress());
			St.setString(3, temp.getEmail());
			St.setString(4, temp.getPhoneNum());
			St.setString(5, temp.getType());
			St.setString(6, temp.getID());

			St.executeUpdate();

		} finally {
			St.close();
		}
	}

	// Xoa clinic theo clinicID
	public void deleteClinic(String clinicID) throws SQLException {
		PreparedStatement St = null;
		try {
			String sql = "delete from clinic where clinicID = ?";
			St = Con.prepareStatement(sql);
			St.setString(1, clinicID);
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
