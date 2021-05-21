package View;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import DAO.DoctorDAO;
import DAO.PatientDAO;
import core.Doctor;
import core.Patient;
import core.Person;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddUpdateDialog extends JDialog {

	private static final long serialVersionUID = 1L;

	private DoctorDAO doctorDAO;
	private PatientDAO patientDAO;
	private Doctor prevDoctor;
	private Patient prevPatient;
	private boolean doctorTab;
	private boolean updateMode;
	private SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

	private final JPanel contentPanel = new JPanel();
	private JPanel thisPanel;

	private JTextField idField;
	private JTextField lastNameField;
	private JTextField firstNameField;
	private JTextField dateOfBirthField;
	private JTextField genderField = null;
	private JTextField phoneField;
	private JTextField addressField;
	private JTextField emailField;
	private JTextField doctorNameField = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			AddUpdateDialog dialog = new AddUpdateDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public AddUpdateDialog(JPanel thePanel, PatientDAO thePatientDAO, Patient thePrevPatient, DoctorDAO theDoctorDAO,
			Doctor thePrevDoctor, boolean theDoctorTab, boolean theUpdateMode) {
		this();
		thisPanel = thePanel;
		prevPatient = thePrevPatient;
		patientDAO = thePatientDAO;
		prevDoctor = thePrevDoctor;
		doctorDAO = theDoctorDAO;
		doctorTab = theDoctorTab;
		updateMode = theUpdateMode;
		setTitle("Add");
		if (updateMode) {
			setTitle("Update");
			if (!doctorTab)
				populateGui(thePrevPatient);
			else
				populateGui(thePrevDoctor);
		}
		if (doctorTab) {
			genderField.setEditable(false);
			doctorNameField.setEditable(false);
		}
	}

	private void populateGui(Person thepreviousPerson) {
		idField.setText(thepreviousPerson.getID());
		lastNameField.setText(thepreviousPerson.getLastName());
		firstNameField.setText(thepreviousPerson.getFirstName());
		emailField.setText(thepreviousPerson.getEmail());
		dateOfBirthField.setText(formatter.format(thepreviousPerson.getDateOfBirth()));
		phoneField.setText(thepreviousPerson.getPhoneNum());
		addressField.setText(thepreviousPerson.getAddress());
		if (thepreviousPerson instanceof Patient) {
			genderField.setText(((Patient) thepreviousPerson).getGender());
			doctorNameField.setText(thepreviousPerson.getFirstName());
		}
		idField.setEditable(false);
	}

	public AddUpdateDialog() {

		setBounds(100, 100, 500, 500);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JLabel lblNewLabel = new JLabel("ID");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel.setBounds(40, 30, 80, 30);
		contentPanel.add(lblNewLabel);

		idField = new JTextField();
		idField.setFont(new Font("Tahoma", Font.PLAIN, 12));
		idField.setColumns(10);
		idField.setBounds(130, 30, 320, 30);
		contentPanel.add(idField);

		lastNameField = new JTextField();
		lastNameField.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lastNameField.setColumns(10);
		lastNameField.setBounds(130, 80, 320, 30);
		contentPanel.add(lastNameField);

		JLabel lblNewLabel_1 = new JLabel("Last Name");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1.setBounds(40, 80, 80, 30);
		contentPanel.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("First Name");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_2.setBounds(40, 130, 80, 30);
		contentPanel.add(lblNewLabel_2);

		firstNameField = new JTextField();
		firstNameField.setFont(new Font("Tahoma", Font.PLAIN, 12));
		firstNameField.setColumns(10);
		firstNameField.setBounds(130, 130, 320, 30);
		contentPanel.add(firstNameField);

		dateOfBirthField = new JTextField();
		dateOfBirthField.setFont(new Font("Tahoma", Font.PLAIN, 12));
		dateOfBirthField.setColumns(10);
		dateOfBirthField.setBounds(130, 180, 120, 30);
		contentPanel.add(dateOfBirthField);

		JLabel lblNewLabel_5 = new JLabel("Gender");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_5.setBounds(260, 180, 55, 30);
		contentPanel.add(lblNewLabel_5);

		genderField = new JTextField();
		genderField.setFont(new Font("Tahoma", Font.PLAIN, 12));
		genderField.setColumns(10);
		genderField.setBounds(325, 180, 125, 30);
		contentPanel.add(genderField);

		phoneField = new JTextField();
		phoneField.setFont(new Font("Tahoma", Font.PLAIN, 12));
		phoneField.setColumns(10);
		phoneField.setBounds(325, 230, 125, 30);
		contentPanel.add(phoneField);

		JLabel lblNewLabel_7 = new JLabel("Phone");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_7.setBounds(260, 230, 55, 30);
		contentPanel.add(lblNewLabel_7);

		addressField = new JTextField();
		addressField.setFont(new Font("Tahoma", Font.PLAIN, 12));
		addressField.setColumns(10);
		addressField.setBounds(130, 230, 120, 30);
		contentPanel.add(addressField);

		JLabel lblNewLabel_6 = new JLabel("Address");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_6.setBounds(40, 230, 80, 30);
		contentPanel.add(lblNewLabel_6);

		JLabel lblNewLabel_3 = new JLabel("Date Of Birth");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_3.setBounds(40, 180, 80, 30);
		contentPanel.add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("Email");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_4.setBounds(40, 280, 80, 30);
		contentPanel.add(lblNewLabel_4);

		emailField = new JTextField();
		emailField.setFont(new Font("Tahoma", Font.PLAIN, 12));
		emailField.setColumns(10);
		emailField.setBounds(130, 280, 320, 30);
		contentPanel.add(emailField);

		doctorNameField = new JTextField();
		doctorNameField.setFont(new Font("Tahoma", Font.PLAIN, 12));
		doctorNameField.setColumns(10);
		doctorNameField.setBounds(130, 330, 320, 30);
		contentPanel.add(doctorNameField);

		JLabel lblNewLabel_8 = new JLabel("Doctor Name");
		lblNewLabel_8.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_8.setBounds(40, 330, 80, 30);
		contentPanel.add(lblNewLabel_8);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Save");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (doctorTab)
							saveDoctor();
						else
							savePatient();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
 //Save Doctor
	protected void saveDoctor() {
		String id = idField.getText();
		String last_name = lastNameField.getText();
		String first_name = firstNameField.getText();
		String date_of_birth = dateOfBirthField.getText();
		String email = emailField.getText();
		String address = addressField.getText();
		String phone = phoneField.getText();
		Doctor temp = null;
		if (id.length() == 0) {
			JOptionPane.showMessageDialog(thisPanel, "Please enter ID!");

		} else {
			try {
				if (updateMode) {
					temp = prevDoctor;
					temp.setLastName(last_name);
					temp.setFirstName(first_name);
					temp.setDateOfBirth(formatter.parse(date_of_birth));
					temp.setEmail(email);
					temp.setPhoneNum(phone);
					temp.setAddress(address);
				} else {
					temp = new Doctor(id, last_name, first_name, formatter.parse(date_of_birth), address, email, phone);
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}

			try {
				if (updateMode) {
					doctorDAO.updateDoctor(temp);
					JOptionPane.showMessageDialog(thisPanel, "Updated successfully", "Updated successfully ",
							JOptionPane.INFORMATION_MESSAGE);
				} else {
					doctorDAO.addDoctor(temp);
					JOptionPane.showMessageDialog(thisPanel, "Added successfully", "Added successfully ",
							JOptionPane.INFORMATION_MESSAGE);
				}

				setVisible(false);
				dispose();

			} catch (Exception e) {
				JOptionPane.showMessageDialog(thisPanel, "Error saving" + e.getMessage(), "Error",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}
 // Save Patient
	protected void savePatient() {
		String id = idField.getText();
		String last_name = lastNameField.getText();
		String first_name = firstNameField.getText();
		String date_of_birth = dateOfBirthField.getText();
		String email = emailField.getText();
		String address = addressField.getText();
		String phone = phoneField.getText();
		String doctor_name = doctorNameField.getText();
		String gender = genderField.getText();
		Patient temp = null;
		if (id.length() == 0) {
			JOptionPane.showMessageDialog(thisPanel, "Please enter ID");
		} else {
			try {
				if (updateMode) {

					temp = prevPatient;
					temp.setLastName(last_name);
					temp.setFirstName(first_name);
					temp.setDateOfBirth(formatter.parse(date_of_birth));
					temp.setEmail(email);
					temp.setPhoneNum(phone);
					temp.setAddress(address);
					temp.setGender(gender);
					temp.setDoctorName(doctor_name);
				} else {
					temp = new Patient(id, last_name, first_name, formatter.parse(date_of_birth), address, email, phone,
							gender, doctor_name);

				}

			} catch (ParseException e) {
				e.printStackTrace();
			}
			try {
				if (updateMode) {
					patientDAO.updatePatient(temp);
					JOptionPane.showMessageDialog(thisPanel, "Updated successfully", "Updated successfully ",
							JOptionPane.INFORMATION_MESSAGE);
				} else {
					patientDAO.addPatient(temp);
					JOptionPane.showMessageDialog(thisPanel, "Added successfully", "Added successfully ",
							JOptionPane.INFORMATION_MESSAGE);
				}

				setVisible(false);
				dispose();

			} catch (Exception e) {
				JOptionPane.showMessageDialog(thisPanel, "Error saving: " + e.getMessage(), "Error",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}
