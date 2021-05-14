package View;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import DAO.ClinicDAO;
import core.Clinic;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddUpdateClinicDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel thisPanel;
	private ClinicDAO clinicDAO;
	private Clinic prevClinic;
	private boolean updateMode;

	private final JPanel contentPanel = new JPanel();
	private JTextField idField;
	private JTextField nameField;
	private JTextField addressField;
	private JTextField phoneField;
	private JTextField emailField;
	private JTextField typeField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			AddUpdateClinicDialog dialog = new AddUpdateClinicDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */

	public AddUpdateClinicDialog(JPanel thePanel, Clinic thePrevClinic, ClinicDAO theClinicDAO, boolean theUpdateMode)
			throws Exception {
		this();
		thisPanel = thePanel;
		prevClinic = thePrevClinic;
		clinicDAO = theClinicDAO;
		updateMode = theUpdateMode;

		if (updateMode) {
			setTitle("Update");
			populateGui(thePrevClinic);

		}
	}

	private void populateGui(Clinic thePrevClinic) {
		idField.setText(thePrevClinic.getID());
		nameField.setText(thePrevClinic.getClinicName());
		addressField.setText(thePrevClinic.getAddress());
		phoneField.setText(thePrevClinic.getPhoneNum());
		emailField.setText(thePrevClinic.getEmail());
		typeField.setText(thePrevClinic.getType());
	}

	public AddUpdateClinicDialog() {
		setBounds(100, 100, 500, 450);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNewLabel = new JLabel("ID");
			lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
			lblNewLabel.setBounds(40, 30, 80, 30);
			contentPanel.add(lblNewLabel);
		}
		{
			idField = new JTextField();
			idField.setFont(new Font("Tahoma", Font.PLAIN, 12));
			idField.setEditable(false);
			idField.setColumns(10);
			idField.setBounds(130, 30, 320, 30);
			contentPanel.add(idField);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("Name");
			lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
			lblNewLabel_1.setBounds(40, 80, 80, 30);
			contentPanel.add(lblNewLabel_1);
		}
		{
			nameField = new JTextField();
			nameField.setFont(new Font("Tahoma", Font.PLAIN, 12));
			nameField.setColumns(10);
			nameField.setBounds(130, 80, 320, 30);
			contentPanel.add(nameField);
		}
		{
			JLabel lblNewLabel_2 = new JLabel("Address");
			lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 11));
			lblNewLabel_2.setBounds(40, 130, 80, 30);
			contentPanel.add(lblNewLabel_2);
		}
		{
			addressField = new JTextField();
			addressField.setFont(new Font("Tahoma", Font.PLAIN, 12));
			addressField.setColumns(10);
			addressField.setBounds(130, 130, 320, 30);
			contentPanel.add(addressField);
		}
		{
			JLabel lblNewLabel_3 = new JLabel("Phone");
			lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 11));
			lblNewLabel_3.setBounds(40, 180, 80, 30);
			contentPanel.add(lblNewLabel_3);
		}
		{
			phoneField = new JTextField();
			phoneField.setFont(new Font("Tahoma", Font.PLAIN, 12));
			phoneField.setColumns(10);
			phoneField.setBounds(130, 180, 320, 30);
			contentPanel.add(phoneField);
		}
		{
			JLabel lblNewLabel_6 = new JLabel("Email");
			lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 11));
			lblNewLabel_6.setBounds(40, 230, 80, 30);
			contentPanel.add(lblNewLabel_6);
		}
		{
			emailField = new JTextField();
			emailField.setFont(new Font("Tahoma", Font.PLAIN, 12));
			emailField.setColumns(10);
			emailField.setBounds(130, 230, 320, 30);
			contentPanel.add(emailField);
		}
		{
			JLabel lblNewLabel_4 = new JLabel("Type");
			lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 11));
			lblNewLabel_4.setBounds(40, 280, 80, 30);
			contentPanel.add(lblNewLabel_4);
		}
		{
			typeField = new JTextField();
			typeField.setFont(new Font("Tahoma", Font.PLAIN, 12));
			typeField.setColumns(10);
			typeField.setBounds(130, 280, 320, 30);
			contentPanel.add(typeField);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Save");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							saveClinic();
						} catch (Exception e2) {
							e2.printStackTrace();
						}						
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

//Save Clinic 
	protected void saveClinic() {

		String id = idField.getText();
		String clinicName = nameField.getText();
		String address = addressField.getText();
		String phoneNum = phoneField.getText();
		String email = emailField.getText();
		String type = typeField.getText();

		Clinic temp = null;
		if (updateMode) {
			temp = prevClinic;
			temp.setClinicName(clinicName);
			temp.setAddress(address);
			temp.setPhoneNum(phoneNum);
			temp.setEmail(email);
			temp.setType(type);
			temp.setID(id);
		} else {
			temp = new Clinic(id, clinicName, address, phoneNum, email, type);
		}
		try {
			if (updateMode) {
				clinicDAO.updateClinic(temp);
				JOptionPane.showMessageDialog(thisPanel, "Update successfully", "Update successfully",
						JOptionPane.INFORMATION_MESSAGE);
			} else {
				clinicDAO.addClinic(temp);
				JOptionPane.showMessageDialog(thisPanel, "Add successfully", "Add successfully",
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
