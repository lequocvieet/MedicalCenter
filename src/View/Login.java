package View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.net.URL;
import java.util.Properties;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import javax.swing.JCheckBox;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Toolkit;

public class Login {

	private JFrame frmLogin;
	private JTextField userField;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.frmLogin.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmLogin = new JFrame();
		frmLogin.setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/Image/healthcare.png")));
		frmLogin.setTitle("Login");
		frmLogin.setResizable(false);
		frmLogin.setBounds(100, 100, 560, 360);
		frmLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmLogin.setLocationRelativeTo(null);
		frmLogin.getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(25, 25, 112));
		panel.setBounds(0, 0, 220, 331);
		frmLogin.getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setIcon(new ImageIcon(Login.class.getResource("/Image/login_picture.png")));
		lblNewLabel_4.setBounds(55, 58, 116, 100);
		panel.add(lblNewLabel_4);

		JLabel lblNewLabel_5 = new JLabel("Medical");
		lblNewLabel_5.setVerticalAlignment(SwingConstants.BOTTOM);
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblNewLabel_5.setForeground(new Color(255, 255, 255));
		lblNewLabel_5.setBounds(55, 169, 116, 55);
		panel.add(lblNewLabel_5);

		JLabel lblNewLabel_5_1 = new JLabel("Center");
		lblNewLabel_5_1.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel_5_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5_1.setForeground(Color.WHITE);
		lblNewLabel_5_1.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblNewLabel_5_1.setBounds(55, 232, 116, 55);
		panel.add(lblNewLabel_5_1);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(219, 0, 335, 331);
		frmLogin.getContentPane().add(panel_1);
		panel_1.setLayout(null);

		JLabel lblNewLabel = new JLabel("Login");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblNewLabel.setForeground(new Color(25, 25, 112));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(113, 11, 97, 43);
		panel_1.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Username");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1.setForeground(new Color(25, 25, 112));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setBounds(25, 133, 86, 30);
		panel_1.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Password");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_2.setForeground(new Color(25, 25, 112));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2.setBounds(25, 182, 86, 30);
		panel_1.add(lblNewLabel_2);

		userField = new JTextField();
		userField.setFont(new Font("Tahoma", Font.PLAIN, 12));
		userField.setBounds(129, 133, 170, 30);
		panel_1.add(userField);
		userField.setColumns(10);

		JButton btnNewButton = new JButton("Login");
		btnNewButton.setBackground(new Color(25, 25, 112));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					login();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNewButton.setBounds(45, 257, 102, 36);
		panel_1.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Cancel");
		btnNewButton_1.setBackground(new Color(128, 0, 0));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				userField.setText("");
				passwordField.setText("");
			}
		});
		btnNewButton_1.setForeground(new Color(255, 255, 255));
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNewButton_1.setBounds(197, 257, 102, 36);
		panel_1.add(btnNewButton_1);

		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setIcon(new ImageIcon(Login.class.getResource("/Image/Admin-icon.png")));
		lblNewLabel_3.setBounds(132, 55, 60, 60);
		panel_1.add(lblNewLabel_3);

		passwordField = new JPasswordField();
		passwordField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					try {
						login();
					} catch (Exception e2) {
						e2.printStackTrace();
					}
				}
			}
		});
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 12));
		passwordField.setBounds(129, 184, 170, 30);
		panel_1.add(passwordField);

		JCheckBox showPassCheckBox = new JCheckBox("Show Password");
		showPassCheckBox.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		showPassCheckBox.setHorizontalAlignment(SwingConstants.LEFT);
		showPassCheckBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (showPassCheckBox.isSelected()) {
					passwordField.setEchoChar((char) 0);
				} else {
					passwordField.setEchoChar('*');
				}
			}
		});
		showPassCheckBox.setBounds(129, 221, 170, 23);
		panel_1.add(showPassCheckBox);

	}

	// Login
	public void login() throws Exception {
		Properties prop = new Properties();
		prop.load(new FileInputStream("Database/login.properties"));
		String defaultUser = prop.getProperty("username");
		String defaultPassword = prop.getProperty("password");

		String user = userField.getText();
		String pass = passwordField.getText();
		if (user.equals(defaultUser) && pass.equals(defaultPassword)) {
			WelcomeScreen.main(new String[0]);
			this.frmLogin.dispose();
		} else {
			JOptionPane.showMessageDialog(frmLogin, "Incorrect account or password");
		}
	}
}
