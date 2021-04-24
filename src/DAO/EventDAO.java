package DAO;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import core.Event;

public class EventDAO {
    private Connection Con;

    private SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

    // connect SQL
    public EventDAO() throws Exception {
        Properties prop = new Properties();
        prop.load(new FileInputStream("database/preson.properties"));
        String user = prop.getProperty("user");
        String password = prop.getProperty("password");
        String url = prop.getProperty("url");
        Con = DriverManager.getConnection(url, user, password);
    }

    //  Get all Events
    public List<Event> getAllEvent() throws Exception {
        List<Event> listAllEvent = new ArrayList<>();

        Statement St = null;
        ResultSet Rs = null;

        try {
            St = Con.createStatement();
            Rs = St.executeQuery("select * from event");

            while (Rs.next()) {
                Event tempEvent = convertRowToEvent(Rs);
                listAllEvent.add(tempEvent);
            }

            return listAllEvent;
        } finally {
            close(St, Rs);
        }
    }

    // Adding an Event
    public void addEvent(Event newEvent) throws Exception {
        PreparedStatement St = null;
        try {
            String sql = "Insert into Event" + " (name, date, description)" + " values (?, ?, ?) ";

            St = Con.prepareStatement(sql);

            String stringDate = formatter.format(newEvent.getDate());

            St.setString(1, newEvent.getName());
            St.setString(2, stringDate);
            St.setString(3, newEvent.getDescription());
            St.executeUpdate();
        } finally {
            St.close();
        }
    }

    // Converting one Event in table -> object Event
    private Event convertRowToEvent(ResultSet Rs) throws SQLException {

        String name = Rs.getString("name");
        String StringDate = Rs.getString("date");
        String description = Rs.getString("description");
        Date dateinDate = null;
        try {
            dateinDate = formatter.parse(StringDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Event tempEvent = new Event(name, description, dateinDate);

        return tempEvent;
    }

    // Delete an event according to event'name
    public void deleteEvent(String name) throws SQLException {
        PreparedStatement St = null;
        try {
            String sql = "delete from event where  name = ? ";

            St = Con.prepareStatement(sql);

            St.setString(1, name);

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
