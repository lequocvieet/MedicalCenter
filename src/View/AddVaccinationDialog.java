package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import javax.swing.border.EtchedBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import DAO.HistoryMedicalDAO;
import core.HistoryMedical;

import javax.swing.border.BevelBorder;

public class AddVaccinationDialog extends JFrame {

	private JPanel contentPane;
	private JTextField dateofinjection;
	private JTextField vaccineIDfield;
	private JTextField typeVaccineField;
	private JTextField interactionField;
	private JTextField AddressField;
	private JTextField nextAppointmentField;
	
	private SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
	private String personID;
	private HistoryMedicalDAO histDAO;
	public String s;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddVaccinationDialog frame = new AddVaccinationDialog();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	
	public AddVaccinationDialog(HistoryMedicalDAO thehistDAO,String theIDPerson) {
		this();
		this.personID = theIDPerson;
		this.histDAO = thehistDAO;
		
	}
	
	public AddVaccinationDialog() {
		setTitle("Add new History");
		setIconImage(Toolkit.getDefaultToolkit().getImage(AddVaccinationDialog.class.getResource("/Image/add.png")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 515, 513);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.inactiveCaption);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 475, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(24, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 451, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(13, Short.MAX_VALUE))
		);
		
		JLabel lblNewLabel = new JLabel("Date Injection");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JLabel lblNewLabel_1 = new JLabel("Type Of Vaccine");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JLabel lblNewLabel_2 = new JLabel("Interactions");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JLabel lblNewLabel_3 = new JLabel("Address");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JLabel lblNewLabel_4 = new JLabel("Date Appointment");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JButton btnNewButton = new JButton("Image Injection");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
		         fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
		         FileNameExtensionFilter filter = new FileNameExtensionFilter("*.IMAGE", "jpg","gif","png");
		         fileChooser.addChoosableFileFilter(filter);
		         int result = fileChooser.showSaveDialog(null);
		         if(result == JFileChooser.APPROVE_OPTION){
		             File selectedFile = fileChooser.getSelectedFile();
		             String path = selectedFile.getAbsolutePath();
		             s = path;
		         }
		         else if(result == JFileChooser.CANCEL_OPTION){
		            JOptionPane.showMessageDialog(btnNewButton, "Please select an image");
		         }	
			}
		});
		btnNewButton.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		btnNewButton.setBackground(SystemColor.info);
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JButton btnNewButton_1 = new JButton("Add    ");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					saveHist();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				
				
				
			}
		});
		btnNewButton_1.setBackground(SystemColor.info);
		btnNewButton_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JButton btnNewButton_2 = new JButton("Cancel");
		btnNewButton_2.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		btnNewButton_2.setBackground(SystemColor.info);
		btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		JLabel lblNewLabel_5 = new JLabel("ID Vaccine");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		dateofinjection = new JTextField();
		dateofinjection.setFont(new Font("Tahoma", Font.BOLD, 14));
		dateofinjection.setColumns(10);
		
		vaccineIDfield = new JTextField();
		vaccineIDfield.setColumns(10);
		
		typeVaccineField = new JTextField();
		typeVaccineField.setFont(new Font("Tahoma", Font.BOLD, 14));
		typeVaccineField.setColumns(10);
		
		interactionField = new JTextField();
		interactionField.setFont(new Font("Tahoma", Font.BOLD, 14));
		interactionField.setColumns(10);
		
		AddressField = new JTextField();
		AddressField.setFont(new Font("Tahoma", Font.BOLD, 14));
		AddressField.setColumns(10);
		
		nextAppointmentField = new JTextField();
		nextAppointmentField.setFont(new Font("Tahoma", Font.BOLD, 14));
		nextAppointmentField.setColumns(10);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblNewLabel_4, GroupLayout.PREFERRED_SIZE, 146, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(nextAppointmentField, GroupLayout.DEFAULT_SIZE, 219, Short.MAX_VALUE))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblNewLabel_3, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(AddressField, GroupLayout.DEFAULT_SIZE, 239, Short.MAX_VALUE))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 108, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(interactionField, GroupLayout.DEFAULT_SIZE, 243, Short.MAX_VALUE))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(typeVaccineField, GroupLayout.DEFAULT_SIZE, 248, Short.MAX_VALUE))
						.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
							.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(dateofinjection, GroupLayout.PREFERRED_SIZE, 151, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblNewLabel_5, GroupLayout.DEFAULT_SIZE, 93, Short.MAX_VALUE))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(btnNewButton)
							.addGap(82)
							.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(vaccineIDfield, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnNewButton_2, GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
						.addComponent(vaccineIDfield, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(dateofinjection, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_5))
					.addGap(42)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
						.addComponent(typeVaccineField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(49)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
						.addComponent(interactionField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(45)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_3, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addComponent(AddressField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(54)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_4, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
						.addComponent(nextAppointmentField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 61, Short.MAX_VALUE)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton)
						.addComponent(btnNewButton_2)
						.addComponent(btnNewButton_1))
					.addGap(19))
		);
		panel.setLayout(gl_panel);
		contentPane.setLayout(gl_contentPane);
	}
	
	protected void saveHist() throws Exception {
		String dateInjectstring = dateofinjection.getText();
		try {
			int idvaccine = Integer.parseInt(vaccineIDfield.getText());
			String type = typeVaccineField.getText();
			String interaction = interactionField.getText();
			String nextappoint = nextAppointmentField.getText();
			String address = AddressField.getText();
			
			
			Date injectdate = formatter.parse(dateInjectstring);
			Date appointmentdate = formatter.parse(nextappoint);
			HistoryMedical newhist  = new HistoryMedical(injectdate, type, idvaccine, address, interaction, s, appointmentdate);
					
			
			histDAO.addHistoryMedical(newhist, personID);
			JOptionPane.showMessageDialog(AddVaccinationDialog.this, "Added successfully", "Added successfully ", JOptionPane.INFORMATION_MESSAGE);

				
			setVisible(false);
			dispose();

		} catch(Exception e) {
			JOptionPane.showMessageDialog(AddVaccinationDialog.this, "Fail to save.Please enter right format");
		}
		
		
		
	}

}
