package View;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.jdbc.JDBCCategoryDataset;

public class DrawChart {
	private String personId;
	private Connection myConnection;

	public DrawChart(String personID1) {
		this();
		this.personId = personID1;
	}

	public DrawChart() {
		try {
			Properties prop = new Properties();
			prop.load(new FileInputStream("database/person.properties"));
			String user = prop.getProperty("user");
			String password = prop.getProperty("password");
			String url = prop.getProperty("url");
			myConnection = DriverManager.getConnection(url, user, password);

		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}
	}

	public void drawingHeightChart() {
		try {
			final String SQL = "SELECT id, height FROM weightheight where personID = " + this.personId;
			final CategoryDataset dataset = new JDBCCategoryDataset(myConnection, SQL);

			JFreeChart chart = ChartFactory.createLineChart("Height Chart", "Time", "Height", dataset,
					PlotOrientation.VERTICAL, false, false, false);
			CategoryPlot catplot = chart.getCategoryPlot();
			catplot.setRangeGridlinePaint(Color.BLACK);
			JButton saveHeight=new JButton();
			saveHeight.setIcon(new ImageIcon("C:\\Users\\Admin\\Desktop\\Project I\\MedicalCenter\\src\\Image\\download.png"));
			saveHeight.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					saveChartImage(chart, saveHeight);
				}
			});

			ChartFrame frame = new ChartFrame("Chart of Height", chart);
			frame.setLayout(new FlowLayout(FlowLayout.LEFT));
			frame.setIconImage(Toolkit.getDefaultToolkit().getImage(IndexTab.class.getResource("/Image/line-chart.png")));
			frame.setVisible(true);
			frame.setSize(450, 450);
			frame.add(saveHeight);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
		

	}

	public void drawingWeightChart() {

		try {
			final String SQL = "SELECT id, weight FROM weightheight where personID = " + this.personId;
			final CategoryDataset dataset = new JDBCCategoryDataset(myConnection, SQL);

			JFreeChart chart = ChartFactory.createLineChart("Weight Chart", "Time", "Weight", dataset,
					PlotOrientation.VERTICAL, false, false, false);
			CategoryPlot catplot = chart.getCategoryPlot();
			catplot.setRangeGridlinePaint(Color.BLACK);
			JButton saveWeight =new JButton();
			saveWeight.setIcon(new ImageIcon("C:\\Users\\Admin\\Desktop\\Project I\\MedicalCenter\\src\\Image\\download.png"));
			saveWeight.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					saveChartImage(chart, saveWeight);
					
					
				}
			});

			ChartFrame frame = new ChartFrame("Chart of Weight", chart);
			frame.setLayout(new FlowLayout(FlowLayout.LEFT));
			frame.setIconImage(Toolkit.getDefaultToolkit().getImage(IndexTab.class.getResource("/Image/line-chart.png")));
			frame.setVisible(true);
			frame.setSize(450, 450);
			frame.add(saveWeight);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}

	}

	public void drawingStatistics() {
		try {
			String SQL = "SELECT typeOfVaccine, COUNT(*) as count \r\n" + "FROM medicalhistory \r\n"
					+ "GROUP BY typeOfVaccine \r\n" + "ORDER BY count DESC";
			Statement statement = myConnection.createStatement();
			ResultSet resultSet = statement.executeQuery(SQL);
			DefaultPieDataset dataset = new DefaultPieDataset();

			while (resultSet.next()) {
				dataset.setValue(resultSet.getString("typeOfVaccine"),
						Double.parseDouble(resultSet.getString("count")));
			}

			JFreeChart chart = ChartFactory.createPieChart("vaccination statistic", // chart title
					dataset, // data
					true, // include legend
					true, false);
			JButton saveStatistic = new JButton("Save");
			saveStatistic.setIcon(new ImageIcon("C:\\Users\\Admin\\Desktop\\Project I\\MedicalCenter\\src\\Image\\download.png"));
			saveStatistic.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
                saveChartImage(chart, saveStatistic);
				}

			});
			ChartFrame frame = new ChartFrame("Chart of vaccination statistic", chart);
			frame.setIconImage(Toolkit.getDefaultToolkit().getImage(IndexTab.class.getResource("/Image/pie-chart.png")));
			frame.setLayout(new FlowLayout(FlowLayout.LEFT));
			frame.setVisible(true);
			frame.setBounds(450, 100, 450, 450);
			frame.add(saveStatistic);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	public void saveChartImage(JFreeChart chart,JButton save) {
		
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileFilter(new FileNameExtensionFilter("*.png", "png"));
		if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
			File chartFile = fileChooser.getSelectedFile();
			try {
				int width = 560;
				int height = 370;
				ChartUtilities.saveChartAsPNG(chartFile, chart, width, height);
			} catch (IOException ex) {
				JOptionPane.showMessageDialog(save, ex);
			}
		} else {
			JOptionPane.showMessageDialog(save, "No file choosen!");

		}	
	}

}
