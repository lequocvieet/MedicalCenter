package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

 

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

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
import java.awt.Window.Type;
import java.awt.Font;
import javax.swing.JTabbedPane;
import java.awt.Panel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class WelcomeScreen  {

	private JPanel contentPane;
	private JFrame MainScreen;
	private JTextField patientNameField;
	private PatientDAO patientDAO;
	//private DoctorDAO doctorDAO;
	private EventDAO eventDAO;

	private WeightHeightDAO whDAO;
	//private ClinicDAO clinicDAO;
	private JTable kidTable;
	private JTable doctorTable;
	private JTextField doctorNameField;
	private JButton btnNewButton_15;
	private JTable eventTable;
	private JTextField clinicNameField;
	private JTable clinicTable;

	
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
		//doctorDAO = new DoctorDAO();
		whDAO = new WeightHeightDAO();
		eventDAO = new EventDAO();
		//clinicDAO = new ClinicDAO();
		initialize();
	}
	
	public void  initialize() {
		
		MainScreen = new JFrame();
		MainScreen.setForeground(Color.GRAY);
		MainScreen.setFont(new Font("Dialog", Font.PLAIN, 15));
		MainScreen.setBackground(Color.LIGHT_GRAY);
		MainScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		MainScreen.setTitle("MedicalCenter\r\n");
		MainScreen.setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
		MainScreen.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Admin\\Desktop\\Project I\\MedicalCenter\\src\\Image\\healthcare.png"));
		
		MainScreen.setBounds(100, 100, 1200, 700);
		MainScreen.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		MainScreen.getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		Panel panel = new Panel();
		tabbedPane.addTab("Home", null, panel, null);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.setHorizontalAlignment(SwingConstants.LEADING);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		panel.add(btnNewButton);
		
		Panel panel_1 = new Panel();
		tabbedPane.addTab("Patient", null, panel_1, null);
		
		Panel panel_2 = new Panel();
		tabbedPane.addTab("Doctor", null, panel_2, null);
		
		Panel panel_3 = new Panel();
		tabbedPane.addTab("Clinic", null, panel_3, null);
		
		Panel panel_4 = new Panel();
		tabbedPane.addTab("Event", null, panel_4, null);
		
		Panel panel_5 = new Panel();
		tabbedPane.addTab("ChatBot", null, panel_5, null);
		
		Panel panel_6 = new Panel();
		tabbedPane.addTab("AboutUs", null, panel_6, null);


		
	}
	
	
	

}
