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

public class WelcomeScreen  {

	private JPanel contentPane;
	private JFrame MainScreen;
	private PatientDAO patientDAO;
	private DoctorDAO doctorDAO;
	private EventDAO eventDAO;

	private WeightHeightDAO whDAO;
	private ClinicDAO clinicDAO;
	private JTable patientTable;
	private JTable doctorTable;
	private JTextField doctorNameField;
	private JButton btnNewButton_15;
	private JTable eventTable;
	private JTextField clinicNameField;
	private JTable clinicTable;
	private JTextField patientNameField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTable table;
	private JTable table_1;

	
	public static void main(String[] args) {                            // run chuong trinh o day
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
		/*doctorDAO = new DoctorDAO();
		whDAO = new WeightHeightDAO();
		eventDAO = new EventDAO();
		clinicDAO = new ClinicDAO();*/
		initialize();
	}
	
	public void  initialize() {
		
		MainScreen = new JFrame();
		MainScreen.getContentPane().setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBorder(new CompoundBorder(new LineBorder(new Color(0, 0, 0), 3), null));
		tabbedPane.setForeground(Color.LIGHT_GRAY);
		tabbedPane.setFont(new Font("Tahoma", Font.BOLD, 20));
		GroupLayout groupLayout = new GroupLayout(MainScreen.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(tabbedPane, GroupLayout.PREFERRED_SIZE, 1161, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(15, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(tabbedPane, GroupLayout.PREFERRED_SIZE, 635, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(18, Short.MAX_VALUE))
		);
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.inactiveCaption);
		tabbedPane.addTab("Home", null, panel, null);
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
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addComponent(toolBar, GroupLayout.PREFERRED_SIZE, 1181, GroupLayout.PREFERRED_SIZE)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addComponent(toolBar_1, GroupLayout.PREFERRED_SIZE, 118, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 1025, GroupLayout.PREFERRED_SIZE))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(1)
					.addComponent(toolBar, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(toolBar_1, GroupLayout.DEFAULT_SIZE, 546, Short.MAX_VALUE)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 547, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		
		table_1 = new JTable();
		scrollPane.setViewportView(table_1);
		
		
		
		JButton btnNewButton_5 = new JButton("Appointment\r\n");
		btnNewButton_5.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		btnNewButton_5.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton_5.setBackground(SystemColor.info);
		toolBar_1.add(btnNewButton_5);
		
		JButton btnNewButton_4 = new JButton("Vaccination  ");
		btnNewButton_4.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		btnNewButton_4.setBackground(SystemColor.info);
		btnNewButton_4.setFont(new Font("Tahoma", Font.BOLD, 15));
		toolBar_1.add(btnNewButton_4);
		
		JButton btnNewButton_6 = new JButton("Index           ");
		btnNewButton_6.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		btnNewButton_6.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton_6.setBackground(SystemColor.info);
		toolBar_1.add(btnNewButton_6);
		
		JButton btnNewButton = new JButton("Add   ");
		btnNewButton.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		btnNewButton.setBackground(SystemColor.info);
		btnNewButton.setForeground(Color.DARK_GRAY);
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 15));
		toolBar.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Update");
		btnNewButton_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		btnNewButton_1.setBackground(SystemColor.info);
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		toolBar.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Delete");
		btnNewButton_2.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		btnNewButton_2.setBackground(SystemColor.info);
		btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 15));
		toolBar.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Search");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try{
					String name = patientNameField.getText();
				
					List <Patient> patients = null;
					
					if(name != null && name.trim().length() > 0 ) {
						patients = patientDAO.getPatientByName(name);
					}
					else {
						patients = patientDAO.getAllPatient();
					}
					
                    PatientTableModel model = new PatientTableModel(patients);
                    table_1.setModel(model);
                    
	
				}
				catch(Exception exc) {
					JOptionPane.showMessageDialog(panel_1, "Error: "+ exc, "Error",JOptionPane.ERROR_MESSAGE);
					
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
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.TRAILING)
				.addComponent(toolBar_2, GroupLayout.DEFAULT_SIZE, 1160, Short.MAX_VALUE)
				.addGroup(Alignment.LEADING, gl_panel_2.createSequentialGroup()
					.addComponent(toolBar_3, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(1029, Short.MAX_VALUE))
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addComponent(toolBar_2, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(toolBar_3, GroupLayout.DEFAULT_SIZE, 537, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		JButton btnNewButton_12 = new JButton("Appointment");
		btnNewButton_12.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		btnNewButton_12.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton_12.setBackground(SystemColor.info);
		toolBar_3.add(btnNewButton_12);
		
		JButton btnNewButton_13 = new JButton("Vaccination ");
		btnNewButton_13.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		btnNewButton_13.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton_13.setBackground(SystemColor.info);
		toolBar_3.add(btnNewButton_13);
		
		JButton btnNewButton_11 = new JButton("Index          ");
		btnNewButton_11.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton_11.setBackground(SystemColor.info);
		btnNewButton_11.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		toolBar_3.add(btnNewButton_11);
		
		JButton btnNewButton_7 = new JButton("Add   ");
		btnNewButton_7.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		btnNewButton_7.setBackground(SystemColor.info);
		btnNewButton_7.setFont(new Font("Tahoma", Font.BOLD, 15));
		toolBar_2.add(btnNewButton_7);
		
		JButton btnNewButton_8 = new JButton("Update");
		btnNewButton_8.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		btnNewButton_8.setBackground(SystemColor.info);
		btnNewButton_8.setFont(new Font("Tahoma", Font.BOLD, 15));
		toolBar_2.add(btnNewButton_8);
		
		JButton btnNewButton_9 = new JButton("Delete");
		btnNewButton_9.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		btnNewButton_9.setBackground(SystemColor.info);
		btnNewButton_9.setFont(new Font("Tahoma", Font.BOLD, 15));
		toolBar_2.add(btnNewButton_9);
		
		JButton btnNewButton_10 = new JButton("Search");
		btnNewButton_10.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		btnNewButton_10.setBackground(SystemColor.info);
		btnNewButton_10.setFont(new Font("Tahoma", Font.BOLD, 15));
		toolBar_2.add(btnNewButton_10);
		
		textField_1 = new JTextField();
		textField_1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		textField_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		toolBar_2.add(textField_1);
		textField_1.setColumns(10);
		panel_2.setLayout(gl_panel_2);
		tabbedPane.setForegroundAt(2, Color.BLACK);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(SystemColor.inactiveCaption);
		tabbedPane.addTab("Clinic", null, panel_3, null);
		
		JToolBar toolBar_4 = new JToolBar();
		toolBar_4.setBackground(SystemColor.activeCaption);
		GroupLayout gl_panel_3 = new GroupLayout(panel_3);
		gl_panel_3.setHorizontalGroup(
			gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_3.createSequentialGroup()
					.addComponent(toolBar_4, GroupLayout.DEFAULT_SIZE, 1140, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_panel_3.setVerticalGroup(
			gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_3.createSequentialGroup()
					.addComponent(toolBar_4, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(558, Short.MAX_VALUE))
		);
		
		JButton btnNewButton_14 = new JButton("Add   ");
		btnNewButton_14.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		btnNewButton_14.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton_14.setBackground(SystemColor.info);
		btnNewButton_14.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		toolBar_4.add(btnNewButton_14);
		
		JButton btnNewButton_16 = new JButton("Update");
		btnNewButton_16.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton_16.setBackground(SystemColor.info);
		btnNewButton_16.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		btnNewButton_16.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		toolBar_4.add(btnNewButton_16);
		
		JButton btnNewButton_17 = new JButton("Delete");
		btnNewButton_17.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton_17.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		btnNewButton_17.setBackground(SystemColor.info);
		toolBar_4.add(btnNewButton_17);
		
		JButton btnNewButton_18 = new JButton("Search");
		btnNewButton_18.setBackground(SystemColor.info);
		btnNewButton_18.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		btnNewButton_18.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton_18.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		toolBar_4.add(btnNewButton_18);
		
		textField_2 = new JTextField();
		textField_2.setFont(new Font("Tahoma", Font.BOLD, 15));
		textField_2.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		toolBar_4.add(textField_2);
		textField_2.setColumns(10);
		panel_3.setLayout(gl_panel_3);
		tabbedPane.setForegroundAt(3, Color.BLACK);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(SystemColor.inactiveCaption);
		tabbedPane.addTab("Event", null, panel_4, null);
		GroupLayout groupLayout_2 = new GroupLayout(panel_4);
		groupLayout_2.setHorizontalGroup(
			groupLayout_2.createParallelGroup(Alignment.LEADING)
				.addGap(0, 1150, Short.MAX_VALUE)
		);
		groupLayout_2.setVerticalGroup(
			groupLayout_2.createParallelGroup(Alignment.LEADING)
				.addGap(0, 590, Short.MAX_VALUE)
		);
		panel_4.setLayout(groupLayout_2);
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
				.addGroup(gl_panel_5.createSequentialGroup()
					.addComponent(toolBar_5, GroupLayout.PREFERRED_SIZE, 952, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(198, Short.MAX_VALUE))
		);
		gl_panel_5.setVerticalGroup(
			gl_panel_5.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_5.createSequentialGroup()
					.addComponent(toolBar_5, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(557, Short.MAX_VALUE))
		);
		
		JButton btnNewButton_19 = new JButton("OpenChatBot");
		btnNewButton_19.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ChatBotTab();
			}
		});
		btnNewButton_19.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton_19.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		btnNewButton_19.setBackground(SystemColor.info);
		btnNewButton_19.setForeground(SystemColor.desktop);
		toolBar_5.add(btnNewButton_19);
		panel_5.setLayout(gl_panel_5);
		tabbedPane.setForegroundAt(5, Color.BLACK);
		
		JPanel panel_6 = new JPanel();
		panel_6.setBackground(SystemColor.inactiveCaption);
		tabbedPane.addTab("AboutUs", null, panel_6, null);
		GroupLayout groupLayout_1 = new GroupLayout(panel_6);
		groupLayout_1.setHorizontalGroup(
			groupLayout_1.createParallelGroup(Alignment.LEADING)
				.addGap(0, 1150, Short.MAX_VALUE)
		);
		groupLayout_1.setVerticalGroup(
			groupLayout_1.createParallelGroup(Alignment.LEADING)
				.addGap(0, 590, Short.MAX_VALUE)
		);
		panel_6.setLayout(groupLayout_1);
		tabbedPane.setForegroundAt(6, Color.BLACK);
		MainScreen.getContentPane().setLayout(groupLayout);
		MainScreen.setForeground(Color.GRAY);
		MainScreen.setFont(new Font("Dialog", Font.PLAIN, 15));
		MainScreen.setBackground(Color.LIGHT_GRAY);
		MainScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		MainScreen.setTitle("MedicalCenter\r\n");
		MainScreen.setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
		MainScreen.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Admin\\Desktop\\Project I\\MedicalCenter\\src\\Image\\healthcare.png"));
		
		MainScreen.setBounds(100, 100, 1200, 700);


		
	}
}
