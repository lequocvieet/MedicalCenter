package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.LineBorder;

import DAO.HistoryMedicalDAO;
import core.Patient;
import core.Person;

import java.awt.Color;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;

public class AppointmentTab extends JFrame {

	private JPanel contentPane;
	private JTable tableToday;
	private JTable tableTomorrow;
	private List<String> emailToday;
	private List<String> emailTomorrow;
	private HistoryMedicalDAO histDAO;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AppointmentTab frame = new AppointmentTab();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @throws Exception
	 */
	public AppointmentTab() throws Exception {
		setTitle("Appointment");
		setIconImage(Toolkit.getDefaultToolkit().getImage(AppointmentTab.class.getResource("/Image/appointment.png")));
		setBounds(100, 100, 818, 398);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addComponent(panel,
				GroupLayout.PREFERRED_SIZE, 794, GroupLayout.PREFERRED_SIZE));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addComponent(panel,
				GroupLayout.PREFERRED_SIZE, 351, GroupLayout.PREFERRED_SIZE));

		JButton sendEmailButton = new JButton("Send Email To All ");
		sendEmailButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EmailSender sender;
                
				try {
					sender = new EmailSender(emailToday, emailTomorrow);
					sender.sendToday();
					sender.SendTomorrow();
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}

		});
		sendEmailButton.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		sendEmailButton.setBackground(SystemColor.info);
		sendEmailButton.setForeground(SystemColor.desktop);
		sendEmailButton.setFont(new Font("Tahoma", Font.BOLD, 15));

		JLabel lblToday = new JLabel("");
		lblToday.setBorder(new LineBorder(new Color(153, 180, 209), 2));
		lblToday.setFont(new Font("Tahoma", Font.BOLD, 14));

		JLabel lblTomorrow = new JLabel("");
		lblTomorrow.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTomorrow.setBorder(new LineBorder(SystemColor.activeCaption, 2));

		JPanel panel_1 = new JPanel();

		JPanel panel_2 = new JPanel();
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup().addGap(65)
						.addComponent(lblToday, GroupLayout.PREFERRED_SIZE, 156, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED, 314, Short.MAX_VALUE)
						.addComponent(lblTomorrow, GroupLayout.PREFERRED_SIZE, 195, GroupLayout.PREFERRED_SIZE)
						.addGap(60))
				.addGroup(gl_panel.createSequentialGroup().addGap(323).addComponent(sendEmailButton)
						.addContainerGap(324, Short.MAX_VALUE))
				.addGroup(gl_panel.createSequentialGroup().addContainerGap()
						.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 371, GroupLayout.PREFERRED_SIZE).addGap(18)
						.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 380, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(11, Short.MAX_VALUE)));
		gl_panel.setVerticalGroup(gl_panel.createParallelGroup(Alignment.LEADING).addGroup(gl_panel
				.createSequentialGroup().addGap(5).addComponent(sendEmailButton)
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addGroup(gl_panel
						.createParallelGroup(Alignment.BASELINE).addComponent(lblToday).addComponent(lblTomorrow))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 267, GroupLayout.PREFERRED_SIZE)
						.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 265, GroupLayout.PREFERRED_SIZE))
				.addContainerGap(15, Short.MAX_VALUE)));

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBorder(new LineBorder(SystemColor.activeCaption, 3));
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup().addContainerGap()
						.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 361, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		gl_panel_2.setVerticalGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup().addContainerGap()
						.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 247, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

		tableTomorrow = new JTable();
		scrollPane_1.setViewportView(tableTomorrow);
		panel_2.setLayout(gl_panel_2);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(new LineBorder(SystemColor.activeCaption, 3));
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup().addContainerGap()
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 352, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		gl_panel_1.setVerticalGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup().addContainerGap()
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 252, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

		tableToday = new JTable();
		scrollPane.setViewportView(tableToday);
		panel_1.setLayout(gl_panel_1);
		panel.setLayout(gl_panel);
		contentPane.setLayout(gl_contentPane);

		histDAO = new HistoryMedicalDAO();
		emailToday = new ArrayList<String>();
		emailTomorrow = new ArrayList<String>();
		List<Person> listPersonToday = histDAO.getPersonAppointToday();
		List<Person> listPersonTomorrow = histDAO.getPersonAppointTomorrow();

		for (Person p : listPersonToday) {

			String e = p.getEmail();
			System.out.println(e);
			emailToday.add(e);

		}

		for (Person p : listPersonTomorrow) {

			String e = p.getEmail();
			System.out.println(e);
			emailTomorrow.add(e);
		}
		int numToday = listPersonToday.size();
		int numTom = listPersonTomorrow.size();

		AppointmentTableModel modelToday = new AppointmentTableModel(listPersonToday);
		AppointmentTableModel modelTomorrow = new AppointmentTableModel(listPersonTomorrow);
		tableToday.setModel(modelToday);
		tableTomorrow.setModel(modelTomorrow);

		String txt1 = "Today, we have " + numToday;
		String txt2 = "Tomorrow, we have " + numTom;
		lblToday.setText(txt1);
		lblTomorrow.setText(txt2);

	}
}
