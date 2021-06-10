package DAO;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Formatter;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ArrayBlockingQueue;

import com.mysql.cj.xdevapi.Result;

import core.*;

public class HistoryMedicalDAO {
	private Connection myConnection;
	private SimpleDateFormat fomatter = new SimpleDateFormat("dd/MM/yyyy");
	private String today;
	private String tomorrow;
	private PatientDAO patientDAO;

	public HistoryMedicalDAO() throws Exception {
		Properties prop = new Properties();
		prop.load(new FileInputStream("Database/person.properties"));
		String user = prop.getProperty("user");
		String password = prop.getProperty("password");
		String url = prop.getProperty("url");
		myConnection = DriverManager.getConnection(url, user, password);

		patientDAO = new PatientDAO();
		Calendar calendar = Calendar.getInstance();
		Date todayDay = calendar.getTime();
		calendar.add(Calendar.DAY_OF_YEAR, 1); // cong them 1 ngay
		Date tomorrowDay = calendar.getTime();
		this.today = fomatter.format(todayDay); // tu kieu Date format ra String theo fomatter
		this.tomorrow = fomatter.format(tomorrowDay);

	}

	// Get all HistoryMedicals of "a person " according to person ID

	public List<HistoryMedical> getAllPatient(String thePersonID) throws SQLException {
		List<HistoryMedical> list = new ArrayList<>();
		PreparedStatement prepareStatement = null;
		ResultSet resultSet = null;
		try {
			prepareStatement = myConnection.prepareStatement("select * from medicalhistory where personID = ? ");
			prepareStatement.setString(1, thePersonID);
			resultSet = prepareStatement.executeQuery();
			while (resultSet.next()) {
				HistoryMedical tempHistoryMedical = ConvertRowToHistoryMedical(resultSet);
				list.add(tempHistoryMedical);

			}
			return list;

		} finally {
			close(null, prepareStatement, resultSet);
		}
	}

	// Adding historymedical object to table according person Id

	public void addHistoryMedical(HistoryMedical historyMedical, String personID) throws SQLException {
		PreparedStatement preparedStatement = null;
		try {
			String sql = "Insert into medicalhistory "
					+ "(personID,dateOfInjection,typeOfVaccine,IDVaccine,address,interaction,imageHist,nextAppointment)"
					+ "value (?,?,?,?,?,?,?,?)";
			preparedStatement = myConnection.prepareStatement(sql);

			preparedStatement.setString(1, personID);
			preparedStatement.setString(2, fomatter.format(historyMedical.getDateOfInjection()));
			preparedStatement.setString(3, historyMedical.getTypeOfVaccine());
			preparedStatement.setInt(4, historyMedical.getIDVaccine());
			preparedStatement.setString(5, historyMedical.getAddress());
			preparedStatement.setString(6, historyMedical.getInteraction());
			preparedStatement.setString(7, historyMedical.getImageHist());
			preparedStatement.setString(8, fomatter.format(historyMedical.getNextAppointment()));

			preparedStatement.executeUpdate();
		} finally {
			close(null, preparedStatement, null);

		}
	}

	// Getting person have appointment Today

	public List<Person> getPersonAppointToday() throws Exception {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String sql = " Select * from medicalhistory ";

		try {
			List<Person> list = new ArrayList<>();
			preparedStatement=myConnection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery(sql);

			while (resultSet.next()) {

				String personId = resultSet.getString("personID");
				String dateAppoint = resultSet.getString("nextAppointment");
				if (   (dateAppoint!=null ) &&   dateAppoint.equals(today)) {
					Person patient = patientDAO.getPatientByID(personId);

					list.add(patient);
				}

			}

			return list;
		} finally {

			close(null, preparedStatement, resultSet);

		}
	}

	// Getting list person have appointment tomorrow

	public List<Person> getPersonAppointTomorrow() throws Exception {

		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String Sql = " Select * from medicalhistory";
		try {
			List<Person> list = new ArrayList<>();

			preparedStatement = myConnection.prepareStatement(Sql);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {

				String personId = resultSet.getString("personID");
				String dateAppoint = resultSet.getString("nextAppointment");
				if (dateAppoint!= null  &&       dateAppoint.equals(tomorrow)) {
					Person patient = patientDAO.getPatientByID(personId);
					list.add(patient);
				}

			}

			return list;

		} finally {
			close(myConnection, preparedStatement, resultSet);
		}
	}

	private HistoryMedical ConvertRowToHistoryMedical(ResultSet resultSet) throws SQLException {
		String stringDateInjection = resultSet.getString("dateOfInjection");
		String stringDateNextAppoint = resultSet.getString("nextAppointment");
		Date dateInjectionToDate = null;
		Date dateAppointmentToDate = null;

		try {
			dateInjectionToDate = fomatter.parse(stringDateInjection);
			dateAppointmentToDate = fomatter.parse(stringDateNextAppoint);

		} catch (ParseException e) {

			e.printStackTrace();
		}
		String interaction = resultSet.getString("interaction");
		String typeOfVaccine = resultSet.getString("typeOfVaccine");
		String address = resultSet.getString("address");
		int idVaccine = resultSet.getInt("IDVaccine");
		String imageHist = resultSet.getString("imageHist");

		HistoryMedical historyMedical = new HistoryMedical(dateInjectionToDate, typeOfVaccine, idVaccine, address,
				interaction, imageHist, dateAppointmentToDate);

		return historyMedical;

	}

	// Delete history according to id
	public void deleteHist(String id) throws SQLException {
		PreparedStatement myStmt = null;
		try {
			String sql = "delete from medicalhistory where  personID = ? ";

			myStmt = myConnection.prepareStatement(sql);

			myStmt.setString(1, id);

			myStmt.executeUpdate();
		} finally {
			myStmt.close();
		}
	}

	private void close(Connection connection, PreparedStatement prepareStatement, ResultSet resultSet)
			throws SQLException {
		if (resultSet != null) {
			resultSet.close();
		}
		if (prepareStatement != null) {
			prepareStatement.close();
		}
		if (connection != null) {
			connection.close();
		}
	}

}
