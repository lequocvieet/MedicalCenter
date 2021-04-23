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

import core.WeightHeight;

public class WeightHeightDAO {
    private Connection Con;

    private SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

    // connect SQL
    public WeightHeightDAO() throws Exception {
        Properties prop = new Properties();
        prop.load(new FileInputStream("database/preson.properties"));
        String user = prop.getProperty("user");
        String password = prop.getProperty("password");
        String url = prop.getProperty("url");
        Con = DriverManager.getConnection(url, user, password);
    }

    //  Get all WeightHeights of "a person " according to person ID
    public List<WeightHeight> getWeightHeightByID(String theIDPerson) throws Exception {
        List<WeightHeight> list = new ArrayList<>();

        PreparedStatement St = null;
        ResultSet Rs = null;

        try {

            theIDPerson += "%";
            St = Con.prepareStatement("select * from weightheight where personID = ? ");
            St.setString(1, theIDPerson);
            Rs = St.executeQuery();

            while (Rs.next()) {
                WeightHeight tempWeightHeight = convertRowToWeightHeight(Rs);
                list.add(tempWeightHeight);
            }

            return list;
        } finally {
            close(St, Rs);
        }
    }

    // Adding a WeightHeight object to table according to person ID
    public void addWeightHeight(WeightHeight newWeightHeight, String thePersonID) throws Exception {
        PreparedStatement St = null;
        try {
            String sql = "Insert into WeightHeight" + "(personID, weight, height, date)" + " values (?, ? ,?, ?) ";

            St = Con.prepareStatement(sql);

            String stringDate = formatter.format(newWeightHeight.getDate());

            St.setString(1, thePersonID);
            St.setInt(2, newWeightHeight.getWeight());
            St.setInt(3, newWeightHeight.getHeight());
            St.setString(4, stringDate);

            St.executeUpdate();
        } finally {
            St.close();
        }
    }

    // Converting one WeightHeight in table -> object WeightHeight (with out ID)
    private WeightHeight convertRowToWeightHeight(ResultSet Rs) throws SQLException {

        String StringDate = Rs.getString("date");

        Date dateinDate = null;
        try {
            dateinDate = formatter.parse(StringDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        int weight = Rs.getInt("weight");
        int height = Rs.getInt("height");

        WeightHeight tempWeightHeight = new WeightHeight(height, weight, dateinDate);

        return tempWeightHeight;
    }

    // Get BMI according personID
    public double getBmiByID(String theIDPerson) throws Exception {
        List<WeightHeight> list = new ArrayList<>();

        PreparedStatement St = null;
        ResultSet Rs = null;

        try {

            St = Con.prepareStatement("select * from weightheight where personID = ? ");
            St.setString(1, theIDPerson);
            Rs = St.executeQuery();

            while (Rs.next()) {
                WeightHeight tempWeightHeight = convertRowToWeightHeight(Rs);
                list.add(tempWeightHeight);
            }

            WeightHeight latestWH = list.get(list.size() - 1);
            double height = latestWH.getHeight();
            double weight = latestWH.getWeight();
            height = height / 100;
            double bmi = weight / (height * height);
            return bmi;
        } finally {
            close(St, Rs);
        }
    }

    // Delete WeightHeight history according to personID
    public void deleteWH(String id) throws SQLException {
        PreparedStatement St = null;
        try {
            String sql = "delete from weightheight where personID = ? ";

            St = Con.prepareStatement(sql);

            St.setString(1, id);

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
