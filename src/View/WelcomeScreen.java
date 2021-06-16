package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import DAO.ClinicDAO;
import DAO.DoctorDAO;
import DAO.EventDAO;
import DAO.HistoryMedicalDAO;
import DAO.PatientDAO;
import DAO.WeightHeightDAO;

import java.awt.Color;
import java.awt.Dialog.ModalExclusionType;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import javax.swing.JButton;

import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.CardLayout;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;

import java.awt.Window.Type;
import java.awt.Font;
import javax.swing.JTabbedPane;
import java.awt.Panel;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.JToolBar;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.CompoundBorder;
import java.awt.SystemColor;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;

import core.*;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class WelcomeScreen {

	private JPanel contentPane;
	private JFrame MainScreen;
	private PatientDAO patientDAO;
	private DoctorDAO doctorDAO;
	private EventDAO eventDAO;
	private HistoryMedicalDAO histDAO;
	private ValidateEmailPhone checkValid;

	private WeightHeightDAO whDAO;
	private ClinicDAO clinicDAO;
	private JButton btnNewButton_15;
	private JTextField patientNameField;
	private JTextField doctorNameField;
	private JTextField clinicNameField;
	private JTable table_1;
	private JTable table_2;
	private JTable table_3;
	private JTable table_4;

	public static void main(String[] args) { // run chuong trinh o day
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WelcomeScreen frame = new WelcomeScreen();
					frame.MainScreen.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public WelcomeScreen() throws Exception {
		patientDAO = new PatientDAO();
		doctorDAO = new DoctorDAO();
		whDAO = new WeightHeightDAO();
		eventDAO = new EventDAO();
		clinicDAO = new ClinicDAO();
		histDAO=new HistoryMedicalDAO();
		checkValid=new ValidateEmailPhone();
		initialize();
	}

	public void initialize() {

		MainScreen = new JFrame();
		MainScreen.getContentPane().setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBorder(new CompoundBorder(new LineBorder(new Color(0, 0, 0), 3), null));
		tabbedPane.setForeground(Color.LIGHT_GRAY);
		tabbedPane.setFont(new Font("Tahoma", Font.BOLD, 20));
		GroupLayout groupLayout = new GroupLayout(MainScreen.getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addContainerGap()
						.addComponent(tabbedPane, GroupLayout.PREFERRED_SIZE, 1161, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(15, Short.MAX_VALUE)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addContainerGap()
						.addComponent(tabbedPane, GroupLayout.PREFERRED_SIZE, 635, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(18, Short.MAX_VALUE)));

		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.inactiveCaption);
		tabbedPane.addTab("Home", null, panel, null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(WelcomeScreen.class.getResource("/Image/MedicalCenterHome.png")));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 1131, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 569, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(11, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		tabbedPane.setBackgroundAt(0, SystemColor.inactiveCaption);
		tabbedPane.setForegroundAt(0, Color.BLACK);
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(240, 240, 240));
		tabbedPane.addTab("Patient", null, panel_1, null);
		tabbedPane.setForegroundAt(1, Color.BLACK);

		JToolBar toolBar = new JToolBar();
		toolBar.setForeground(Color.WHITE);
		toolBar.setFont(new Font("Segoe UI", Font.BOLD, 18));
		toolBar.setBackground(SystemColor.activeCaption);

		JToolBar toolBar_1 = new JToolBar();
		toolBar_1.setBackground(SystemColor.activeCaption);
		toolBar_1.setFont(new Font("Segoe UI", Font.BOLD, 15));
		toolBar_1.setOrientation(SwingConstants.VERTICAL);

		JScrollPane scrollPane = new JScrollPane();
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addComponent(toolBar, GroupLayout.PREFERRED_SIZE, 1181, GroupLayout.PREFERRED_SIZE)
				.addGroup(gl_panel_1.createSequentialGroup()
						.addComponent(toolBar_1, GroupLayout.PREFERRED_SIZE, 118, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 1025, GroupLayout.PREFERRED_SIZE)));
		gl_panel_1.setVerticalGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup().addGap(1)
						.addComponent(toolBar, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addComponent(toolBar_1, GroupLayout.DEFAULT_SIZE, 546, Short.MAX_VALUE)
								.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 547, GroupLayout.PREFERRED_SIZE))
						.addContainerGap()));

		table_1 = new JTable();
		table_1.setBorder(null);
		scrollPane.setViewportView(table_1);

		JButton btnNewButton_5 = new JButton("Appointment\r\n");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AppointmentTab appointmentTab;
				try {
					appointmentTab = new AppointmentTab();
					appointmentTab.setVisible(true);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_5.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		btnNewButton_5.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton_5.setBackground(SystemColor.info);
		toolBar_1.add(btnNewButton_5);

		JButton btnNewButton_4 = new JButton("Vaccination  ");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table_1.getSelectedRow();
				if (row < 0) {
					JOptionPane.showMessageDialog(panel_1, "Please select a Patient", "Warning",
							JOptionPane.ERROR_MESSAGE);
					return;
				}

				Patient temp = (Patient) table_1.getValueAt(row, PatientTableModel.OBJECT_COL);
				String id = temp.getID();
				VaccinationTab vactab = null;
				try {
					vactab = new VaccinationTab(id);
				} catch (Exception e1) {
					e1.printStackTrace();
				}

				vactab.setVisible(true);
			}
		});
		btnNewButton_4.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		btnNewButton_4.setBackground(SystemColor.info);
		btnNewButton_4.setFont(new Font("Tahoma", Font.BOLD, 15));
		toolBar_1.add(btnNewButton_4);

		JButton btnNewButton_6 = new JButton("Index           ");// Index
		btnNewButton_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int row = table_1.getSelectedRow();
				if (row < 0) {
					JOptionPane.showMessageDialog(panel_1, "You must select a Patient !");
				}
				else {
				Patient patient = (Patient) table_1.getValueAt(row, PatientTableModel.OBJECT_COL);
				String idPerson = patient.getID();
				IndexTab indexTab;
				try {
					indexTab = new IndexTab(idPerson);
					indexTab.setVisible(true);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			}
		});
		btnNewButton_6.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		btnNewButton_6.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton_6.setBackground(SystemColor.info);
		toolBar_1.add(btnNewButton_6);

		JButton btnNewButton = new JButton("Add   ");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddUpdateDialog adddialog = new AddUpdateDialog(panel_1, patientDAO, null, null, null, false, false,checkValid);
				adddialog.setVisible(true);

			}
		});
		btnNewButton.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		btnNewButton.setBackground(SystemColor.info);
		btnNewButton.setForeground(Color.DARK_GRAY);
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 15));
		toolBar.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Update");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table_1.getSelectedRow();// nhan hang da chon de update
				if (row < 0) {
					JOptionPane.showMessageDialog(panel_1, "Please select a patient", "Warning",
							JOptionPane.ERROR_MESSAGE);
					return;
				}

				Patient temp = (Patient) table_1.getValueAt(row, PatientTableModel.OBJECT_COL);

				AddUpdateDialog updatedialog = new AddUpdateDialog(panel_1, patientDAO, temp, null, null, false, true,checkValid);

				updatedialog.setVisible(true);
			}
		});
		btnNewButton_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		btnNewButton_1.setBackground(SystemColor.info);
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		toolBar.add(btnNewButton_1);

		JButton btnNewButton_2 = new JButton("Delete");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int row = table_1.getSelectedRow();
					if (row < 0) {
						JOptionPane.showMessageDialog(panel_1, "Please select a patient", "Warning",
								JOptionPane.ERROR_MESSAGE);
						return;
					}

					Patient temp = (Patient) table_1.getValueAt(row, PatientTableModel.OBJECT_COL);
					patientDAO.deletePatient(temp.getID());
					 histDAO.deleteHist(temp.getID());
					 whDAO.deleteWH(temp.getID());

				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				JOptionPane.showMessageDialog(panel_1, "Deleted", "Deleted", JOptionPane.INFORMATION_MESSAGE);

			}
		});
		btnNewButton_2.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		btnNewButton_2.setBackground(SystemColor.info);
		btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 15));
		toolBar.add(btnNewButton_2);

		JButton btnNewButton_3 = new JButton("Search");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					String name = patientNameField.getText();

					List<Patient> patients = null;

					if (name != null && name.trim().length() > 0) {
						patients = patientDAO.getPatientByName(name);
					} else {
						patients = patientDAO.getAllPatient();
					}

					PatientTableModel model = new PatientTableModel(patients);
					table_1.setModel(model);

				} catch (Exception exc) {
					JOptionPane.showMessageDialog(panel_1, "Error: " + exc, "Error", JOptionPane.ERROR_MESSAGE);

				}
			}
		});

		btnNewButton_3.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		btnNewButton_3.setBackground(SystemColor.info);
		btnNewButton_3.setFont(new Font("Tahoma", Font.BOLD, 15));
		toolBar.add(btnNewButton_3);

		patientNameField = new JTextField();
		patientNameField.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		patientNameField.setFont(new Font("Tahoma", Font.BOLD, 15));
		toolBar.add(patientNameField);
		patientNameField.setColumns(10);
		panel_1.setLayout(gl_panel_1);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(SystemColor.inactiveCaption);
		tabbedPane.addTab("Doctor", null, panel_2, null);

		JToolBar toolBar_2 = new JToolBar();
		toolBar_2.setBackground(SystemColor.activeCaption);

		JToolBar toolBar_3 = new JToolBar();
		toolBar_3.setBackground(SystemColor.activeCaption);
		toolBar_3.setOrientation(SwingConstants.VERTICAL);

		JScrollPane scrollPane_1 = new JScrollPane();
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addComponent(toolBar_2, GroupLayout.DEFAULT_SIZE, 1150, Short.MAX_VALUE)
				.addGroup(gl_panel_2.createSequentialGroup()
						.addComponent(toolBar_3, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 1018, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		gl_panel_2.setVerticalGroup(gl_panel_2.createParallelGroup(Alignment.LEADING).addGroup(gl_panel_2
				.createSequentialGroup()
				.addComponent(toolBar_2, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addComponent(toolBar_3, GroupLayout.DEFAULT_SIZE, 537, Short.MAX_VALUE)
						.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 541, GroupLayout.PREFERRED_SIZE))
				.addContainerGap()));

		table_2 = new JTable();
		table_2.setBorder(null);
		scrollPane_1.setViewportView(table_2);

		JButton btnNewButton_7 = new JButton("Add   ");// add Doctor
		btnNewButton_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddUpdateDialog adddialog = new AddUpdateDialog(panel_2, null, null, doctorDAO, null, true, false,checkValid);
				adddialog.setVisible(true);
			}
		});
		btnNewButton_7.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		btnNewButton_7.setBackground(SystemColor.info);
		btnNewButton_7.setFont(new Font("Tahoma", Font.BOLD, 15));
		toolBar_2.add(btnNewButton_7);

		JButton btnNewButton_8 = new JButton("Update");
		btnNewButton_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table_2.getSelectedRow();
				if (row < 0) {
					JOptionPane.showMessageDialog(panel_2, "Please select a Doctor", "Warning",
							JOptionPane.ERROR_MESSAGE);
					return;
				}

				Doctor temp = (Doctor) table_2.getValueAt(row, DoctorTableModel.OBJECT_COL);

				AddUpdateDialog updatedialog = new AddUpdateDialog(panel_2, null, null, doctorDAO, temp, true, true,checkValid);

				updatedialog.setVisible(true);

			}
		});
		btnNewButton_8.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		btnNewButton_8.setBackground(SystemColor.info);
		btnNewButton_8.setFont(new Font("Tahoma", Font.BOLD, 15));
		toolBar_2.add(btnNewButton_8);

		JButton btnNewButton_9 = new JButton("Delete");// delete Doctor
		btnNewButton_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int row = table_2.getSelectedRow();
					if (row < 0) {
						JOptionPane.showMessageDialog(panel_2, "Please select a Doctor", "Warning",
								JOptionPane.ERROR_MESSAGE);
						return;
					}

					Doctor temp = (Doctor) table_2.getValueAt(row, DoctorTableModel.OBJECT_COL);
					doctorDAO.deleteDoctor(temp.getID());
					
				   histDAO.deleteHist(temp.getID());
				   whDAO.deleteWH(temp.getID());
					 

				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				JOptionPane.showMessageDialog(panel_2, "Deleted", "Deleted", JOptionPane.INFORMATION_MESSAGE);

			}

		});
		btnNewButton_9.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		btnNewButton_9.setBackground(SystemColor.info);
		btnNewButton_9.setFont(new Font("Tahoma", Font.BOLD, 15));
		toolBar_2.add(btnNewButton_9);

		JButton btnNewButton_10 = new JButton("Search"); // search Doctor
		btnNewButton_10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String name = doctorNameField.getText();

					List<Doctor> docs = null;

					if (name != null && name.trim().length() > 0) {
						docs = doctorDAO.getDoctorByName(name);
					} else {
						docs = doctorDAO.getAllDoctor();
					}

					DoctorTableModel model1 = new DoctorTableModel(docs);
					table_2.setModel(model1);

				} catch (Exception exc) {
					JOptionPane.showMessageDialog(panel_1, "Error: " + exc, "Error", JOptionPane.ERROR_MESSAGE);
				}

			}
		});
		btnNewButton_10.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		btnNewButton_10.setBackground(SystemColor.info);
		btnNewButton_10.setFont(new Font("Tahoma", Font.BOLD, 15));
		toolBar_2.add(btnNewButton_10);

		doctorNameField = new JTextField();
		doctorNameField.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		doctorNameField.setFont(new Font("Tahoma", Font.BOLD, 15));
		toolBar_2.add(doctorNameField);
		doctorNameField.setColumns(10);
		panel_2.setLayout(gl_panel_2);
		tabbedPane.setForegroundAt(2, Color.BLACK);

		JPanel panel_3 = new JPanel();
		panel_3.setBackground(SystemColor.inactiveCaption);
		tabbedPane.addTab("Clinic", null, panel_3, null);

		JToolBar toolBar_4 = new JToolBar();
		toolBar_4.setBackground(SystemColor.activeCaption);

		JScrollPane scrollPane_2 = new JScrollPane();
		GroupLayout gl_panel_3 = new GroupLayout(panel_3);
		gl_panel_3.setHorizontalGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_3.createSequentialGroup()
						.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
								.addComponent(toolBar_4, GroupLayout.DEFAULT_SIZE, 1140, Short.MAX_VALUE)
								.addGroup(gl_panel_3.createSequentialGroup().addContainerGap().addComponent(
										scrollPane_2, GroupLayout.PREFERRED_SIZE, 1135, GroupLayout.PREFERRED_SIZE)))
						.addContainerGap()));
		gl_panel_3.setVerticalGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_3.createSequentialGroup()
						.addComponent(toolBar_4, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
						.addComponent(scrollPane_2, GroupLayout.PREFERRED_SIZE, 549, GroupLayout.PREFERRED_SIZE)));

		table_3 = new JTable();
		scrollPane_2.setViewportView(table_3);

		JButton btnNewButton_14 = new JButton("Add   ");
		btnNewButton_14.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		btnNewButton_14.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton_14.setBackground(SystemColor.info);
		btnNewButton_14.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { // Add Clinic
				AddUpdateClinicDialog diag;
				try {
					diag = new AddUpdateClinicDialog(panel_3, null, clinicDAO, false,checkValid);
					diag.setVisible(true);
				} catch (Exception e1) {

					e1.printStackTrace();
				}

			}
		});
		toolBar_4.add(btnNewButton_14);

		JButton btnNewButton_16 = new JButton("Update");
		btnNewButton_16.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton_16.setBackground(SystemColor.info);
		btnNewButton_16.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		btnNewButton_16.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { // Update Clinic
				int row = table_3.getSelectedRow();
				if (row < 0) {
					JOptionPane.showMessageDialog(panel_3, "Please select a clinic", "Warning",
							JOptionPane.ERROR_MESSAGE);
					return;
				}

				Clinic temp = (Clinic) table_3.getValueAt(row, ClinicTableModel.OBJECT_COL);

				AddUpdateClinicDialog updatedialog;
				try {
					updatedialog = new AddUpdateClinicDialog(panel_3, temp, clinicDAO, true,checkValid);
					updatedialog.setVisible(true);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		toolBar_4.add(btnNewButton_16);

		JButton btnNewButton_17 = new JButton("Delete");
		btnNewButton_17.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { // Delete Clinic
				try {
					int row = table_3.getSelectedRow();
					if (row < 0) {
						JOptionPane.showMessageDialog(panel_3, "Please select a clinic", "Warning",
								JOptionPane.ERROR_MESSAGE);
						return;
					}

					Clinic temp = (Clinic) table_3.getValueAt(row, ClinicTableModel.OBJECT_COL);
					clinicDAO.deleteClinic(temp.getID());
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				JOptionPane.showMessageDialog(panel_3, "Deleted", "Deleted", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		btnNewButton_17.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton_17.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		btnNewButton_17.setBackground(SystemColor.info);
		toolBar_4.add(btnNewButton_17);

		JButton btnNewButton_18 = new JButton("Search");
		btnNewButton_18.setBackground(SystemColor.info);
		btnNewButton_18.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		btnNewButton_18.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton_18.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { // Search Clinic
				try {
					String name = clinicNameField.getText();

					List<Clinic> clinics = null;

					if (name != null && name.trim().length() > 0) {
						clinics = clinicDAO.getClinicByName(name);
					} else {
						clinics = clinicDAO.getAllClinic();
					}

					ClinicTableModel model = new ClinicTableModel(clinics);
					table_3.setModel(model);

				} catch (Exception exc) {
					JOptionPane.showMessageDialog(panel_3, "Error: " + exc, "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		toolBar_4.add(btnNewButton_18);

		clinicNameField = new JTextField();
		clinicNameField.setFont(new Font("Tahoma", Font.BOLD, 15));
		clinicNameField.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		toolBar_4.add(clinicNameField);
		clinicNameField.setColumns(10);
		panel_3.setLayout(gl_panel_3);
		tabbedPane.setForegroundAt(3, Color.BLACK);

		JPanel panel_4 = new JPanel();
		panel_4.setBackground(SystemColor.inactiveCaption);
		tabbedPane.addTab("Event", null, panel_4, null);

		JToolBar toolBar_6 = new JToolBar();
		toolBar_6.setBackground(SystemColor.activeCaption);

		JScrollPane scrollPane_3 = new JScrollPane();
		GroupLayout gl_panel_4 = new GroupLayout(panel_4);
		gl_panel_4.setHorizontalGroup(gl_panel_4.createParallelGroup(Alignment.LEADING)
				.addComponent(toolBar_6, GroupLayout.DEFAULT_SIZE, 1150, Short.MAX_VALUE)
				.addGroup(gl_panel_4.createSequentialGroup().addContainerGap()
						.addComponent(scrollPane_3, GroupLayout.PREFERRED_SIZE, 1136, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		gl_panel_4.setVerticalGroup(gl_panel_4.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_4.createSequentialGroup()
						.addComponent(toolBar_6, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(scrollPane_3, GroupLayout.PREFERRED_SIZE, 541, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

		table_4 = new JTable();
		table_4.setBorder(null);
		scrollPane_3.setViewportView(table_4);

		JButton btnNewButton_20 = new JButton("News Event"); // Create New Event
		btnNewButton_20.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddEventDialog adddialog;
				try {
					adddialog = new AddEventDialog();
					adddialog.setVisible(true);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_20.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		btnNewButton_20.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton_20.setBackground(SystemColor.info);
		toolBar_6.add(btnNewButton_20);

		JButton btnNewButton_21 = new JButton("Delete");
		btnNewButton_21.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		btnNewButton_21.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton_21.setBackground(SystemColor.info);
		btnNewButton_21.addActionListener(new ActionListener() { // Delete Event
			public void actionPerformed(ActionEvent e) {
				try {
					int row = table_4.getSelectedRow();
					if (row < 0) {
						JOptionPane.showMessageDialog(panel_4, "Please select an event", "Warning",
								JOptionPane.ERROR_MESSAGE);
						return;
					}

					Event temp = (Event) table_4.getValueAt(row, EventTableModel.OBJECT_COL);
					eventDAO.deleteEvent(temp.getName());
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				JOptionPane.showMessageDialog(panel_4, "Deleted", "Deleted", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		toolBar_6.add(btnNewButton_21);

		JButton btnNewButton_22 = new JButton("Shows Event"); // Shows Event
		btnNewButton_22.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					List<Event> events = eventDAO.getAllEvent();
					EventTableModel eventmodel = new EventTableModel(events);
					table_4.setModel(eventmodel);
				} catch (Exception exc) {
					JOptionPane.showMessageDialog(panel_4, "Error: " + exc, "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnNewButton_22.setBackground(SystemColor.info);
		btnNewButton_22.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton_22.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		toolBar_6.add(btnNewButton_22);

		JButton btnNewButton_23 = new JButton("Statistic");
		btnNewButton_23.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DrawChart drawer;
				drawer = new DrawChart();
				drawer.drawingStatistics();

			}
		});
		btnNewButton_23.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		btnNewButton_23.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton_23.setBackground(SystemColor.info);
		toolBar_6.add(btnNewButton_23);

		JButton btnNewButton_24 = new JButton("Send Email");
		btnNewButton_24.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int row = table_4.getSelectedRow();
				if (row < 0) {
					JOptionPane.showMessageDialog(panel_4, "Please select an Event", "Warning",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
				List<String> allEmails = new ArrayList<String>();
				try {
					List<Patient> allPatients = patientDAO.getAllPatient();

					for (Patient patient : allPatients) {
						String s = patient.getEmail();
						allEmails.add(s);
					}

				} catch (Exception e1) {
					e1.printStackTrace();
				}

				Event temp = (Event) table_4.getValueAt(row, EventTableModel.OBJECT_COL);
				EmailSender sender;
				try {
					sender = new EmailSender(null, null);
					sender.sendEvent(temp, allEmails);
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}

			}

		});
		btnNewButton_24.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton_24.setBackground(SystemColor.info);
		btnNewButton_24.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		toolBar_6.add(btnNewButton_24);
		panel_4.setLayout(gl_panel_4);
		tabbedPane.setForegroundAt(4, Color.BLACK);

		JPanel panel_5 = new JPanel();
		panel_5.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel_5.setBackground(SystemColor.inactiveCaption);
		tabbedPane.addTab("ChatBot", null, panel_5, null);

		JToolBar toolBar_5 = new JToolBar();
		toolBar_5.setBackground(SystemColor.activeCaption);
		GroupLayout gl_panel_5 = new GroupLayout(panel_5);
		gl_panel_5.setHorizontalGroup(
			gl_panel_5.createParallelGroup(Alignment.LEADING)
				.addComponent(toolBar_5, GroupLayout.DEFAULT_SIZE, 1150, Short.MAX_VALUE)
		);
		gl_panel_5.setVerticalGroup(
			gl_panel_5.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_5.createSequentialGroup()
					.addComponent(toolBar_5, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(557, Short.MAX_VALUE))
		);

		JButton btnNewButton_19 = new JButton("OpenChatBot");// Open chatBot
		btnNewButton_19.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ChatBotTab();
			}
		});
		btnNewButton_19.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton_19.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		btnNewButton_19.setBackground(SystemColor.info);
		btnNewButton_19.setForeground(SystemColor.desktop);
		toolBar_5.add(btnNewButton_19);
		panel_5.setLayout(gl_panel_5);
		tabbedPane.setForegroundAt(5, Color.BLACK);

		JPanel panel_6 = new JPanel();
		panel_6.setBackground(SystemColor.inactiveCaption);
		tabbedPane.addTab("AboutUs", null, panel_6, null);
		
		JScrollPane scrollPane_4 = new JScrollPane();
		GroupLayout gl_panel_6 = new GroupLayout(panel_6);
		gl_panel_6.setHorizontalGroup(
			gl_panel_6.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_6.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane_4, GroupLayout.PREFERRED_SIZE, 1132, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_panel_6.setVerticalGroup(
			gl_panel_6.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_6.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane_4, GroupLayout.PREFERRED_SIZE, 565, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(15, Short.MAX_VALUE))
		);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\Admin\\Desktop\\Technical\\AboutUSPage.png"));
		scrollPane_4.setViewportView(lblNewLabel_1);
		panel_6.setLayout(gl_panel_6);
		tabbedPane.setForegroundAt(6, Color.BLACK);
		MainScreen.getContentPane().setLayout(groupLayout);
		MainScreen.setForeground(Color.GRAY);
		MainScreen.setFont(new Font("Dialog", Font.PLAIN, 15));
		MainScreen.setBackground(Color.LIGHT_GRAY);
		MainScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		MainScreen.setTitle("MedicalCenter\r\n");
		MainScreen.setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
		MainScreen.setIconImage(Toolkit.getDefaultToolkit()
				.getImage("C:\\Users\\Admin\\Desktop\\Project I\\MedicalCenter\\src\\Image\\healthcare.png"));

		MainScreen.setBounds(100, 100, 1200, 700);

	}
}
