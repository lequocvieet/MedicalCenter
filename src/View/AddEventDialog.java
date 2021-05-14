package View;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import DAO.EventDAO;
import core.Event;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddEventDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField nameField;
	private JTextField dateField;
	private JTextField descriptionField;
	private SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
	private EventDAO eventDAO;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			AddEventDialog dialog = new AddEventDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public AddEventDialog() {
		setType(Type.UTILITY);
		setTitle("Create New Event");
		setBounds(100, 100, 500, 320);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNewLabel_1 = new JLabel("Name");
			lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
			lblNewLabel_1.setBounds(30, 30, 80, 30);
			contentPanel.add(lblNewLabel_1);
		}
		{
			nameField = new JTextField();
			nameField.setFont(new Font("Tahoma", Font.PLAIN, 12));
			nameField.setColumns(10);
			nameField.setBounds(120, 30, 320, 30);
			contentPanel.add(nameField);
		}
		{
			JLabel lblNewLabel_3 = new JLabel("Date");
			lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 11));
			lblNewLabel_3.setBounds(30, 80, 80, 30);
			contentPanel.add(lblNewLabel_3);
		}
		{
			dateField = new JTextField();
			dateField.setFont(new Font("Tahoma", Font.PLAIN, 12));
			dateField.setColumns(10);
			dateField.setBounds(120, 80, 160, 30);
			contentPanel.add(dateField);
		}
		{
			JLabel lblNewLabel_3 = new JLabel("Description");
			lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 11));
			lblNewLabel_3.setBounds(30, 130, 80, 30);
			contentPanel.add(lblNewLabel_3);
		}
		{
			descriptionField = new JTextField();
			descriptionField.setFont(new Font("Tahoma", Font.PLAIN, 12));
			descriptionField.setColumns(10);
			descriptionField.setBounds(120, 130, 320, 80);
			contentPanel.add(descriptionField);
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
							saveEvent();
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

	// Save event
	public void saveEvent() throws ParseException {
		try {
			String name = nameField.getText();
			String date_string = dateField.getText();
			String description = descriptionField.getText();
			Date date = formatter.parse(date_string);
			Event temp = new Event(name, description, date);
			eventDAO.addEvent(temp);
			JOptionPane.showMessageDialog(AddEventDialog.this, "Added successfully", "Added successfully ",
					JOptionPane.INFORMATION_MESSAGE);
			setVisible(false);
			dispose();

		} catch (Exception e) {
			JOptionPane.showMessageDialog(AddEventDialog.this, "Error saving:" + e.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
		}

	}
}
