package View;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import DAO.WeightHeightDAO;
import core.WeightHeight;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddIndexDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String personID;
	private WeightHeightDAO whDAO;
	private SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

	private final JPanel contentPanel = new JPanel();
	private JTextField dateField;
	private JTextField heightField;
	private JTextField weightField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			AddIndexDialog dialog = new AddIndexDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public AddIndexDialog(String thepersonID) throws Exception {
		this();
		this.personID = thepersonID;
		whDAO = new WeightHeightDAO();
	}

	public AddIndexDialog() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(IndexTab.class.getResource("/Image/add.png")));
		setTitle("Add New Index");
		setBounds(100, 100, 500, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPanel.setBackground(SystemColor.inactiveCaption);
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Date");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1.setBounds(60, 40, 80, 30);
		contentPanel.add(lblNewLabel_1);
		
		dateField = new JTextField();
		dateField.setFont(new Font("Tahoma", Font.PLAIN, 12));
		dateField.setColumns(10);
		dateField.setBounds(150, 40, 250, 30);
		contentPanel.add(dateField);
		
		JLabel lblNewLabel_3 = new JLabel("Height");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_3.setBounds(60, 90, 80, 30);
		contentPanel.add(lblNewLabel_3);
		
		heightField = new JTextField();
		heightField.setFont(new Font("Tahoma", Font.PLAIN, 12));
		heightField.setColumns(10);
		heightField.setBounds(150, 90, 130, 30);
		contentPanel.add(heightField);
		
		JLabel lblNewLabel_3_1 = new JLabel("Weight");
		lblNewLabel_3_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_3_1.setBounds(60, 140, 80, 30);
		contentPanel.add(lblNewLabel_3_1);
		
		weightField = new JTextField();
		weightField.setFont(new Font("Tahoma", Font.PLAIN, 12));
		weightField.setColumns(10);
		weightField.setBounds(150, 140, 130, 30);
		contentPanel.add(weightField);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Save");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							saveIndex();
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

	// Save Index
	public void saveIndex() throws ParseException {
		try {
			String weight_String = weightField.getText();
			String height_String = heightField.getText();
			String date_String = dateField.getText();

			int weight = Integer.parseInt(weight_String);
			int height = Integer.parseInt(height_String);
			Date date = formatter.parse(date_String);
			WeightHeight temp = new WeightHeight(height, weight, date);
			whDAO.addWeightHeight(temp, personID);
			JOptionPane.showMessageDialog(AddIndexDialog.this, "Added successfully", "Added successfully ",
					JOptionPane.INFORMATION_MESSAGE);
			setVisible(false);
			dispose();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(AddIndexDialog.this, "Error saving: " + e.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
		}

	}
}
