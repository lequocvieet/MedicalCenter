
package View;

import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.SwingConstants;

public class StartUp extends javax.swing.JFrame {

	private static final long serialVersionUID = 1L;
	private javax.swing.JProgressBar Myprogress;
	private javax.swing.JLabel Phantram;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JPanel jPanel1;

	/**
	 * Creates new form StartUp
	 */
	public StartUp() {
		initComponents();
	}

	private void initComponents() {

		jPanel1 = new javax.swing.JPanel();
		Phantram = new javax.swing.JLabel();
		Phantram.setHorizontalAlignment(SwingConstants.CENTER);
		Phantram.setBounds(300, 355, 110, 28);
		jLabel2 = new javax.swing.JLabel();
		jLabel2.setBounds(142, 28, 425, 49);

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setUndecorated(true);

		jPanel1.setBackground(new Color(25, 25, 112));

		Phantram.setFont(new Font("Century Gothic", Font.BOLD, 22));
		Phantram.setForeground(new java.awt.Color(255, 255, 255));
		Phantram.setText("%");

		jLabel2.setFont(new Font("Century Gothic", Font.BOLD, 40));
		jLabel2.setForeground(new java.awt.Color(255, 255, 255));
		jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		jLabel2.setText("MEDICAL CENTER");

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setIcon(new ImageIcon(StartUp.class.getResource("/Image/Logo.png")));
		lblNewLabel.setBounds(166, 106, 366, 218);

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		layout.setHorizontalGroup(layout.createParallelGroup(Alignment.LEADING).addComponent(jPanel1,
				GroupLayout.DEFAULT_SIZE, 710, Short.MAX_VALUE));
		layout.setVerticalGroup(layout.createParallelGroup(Alignment.LEADING).addComponent(jPanel1,
				GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE));
		jPanel1.setLayout(null);
		jPanel1.add(jLabel2);
		jPanel1.add(Phantram);
		jPanel1.add(lblNewLabel);
		Myprogress = new javax.swing.JProgressBar();
		Myprogress.setBounds(0, 386, 710, 14);
		jPanel1.add(Myprogress);

		Myprogress.setForeground(new Color(178, 34, 34));
		getContentPane().setLayout(layout);

		pack();
		setLocationRelativeTo(null);
	}

	public static void main(String args[]) {
		StartUp Myframe = new StartUp();
		Myframe.setVisible(true);
		try {
			for (int i = 0; i <= 100; i++) {
				Thread.sleep(40);
				Myframe.Myprogress.setValue(i);
				Myframe.Phantram.setText(Integer.toString(i) + "%");
			}
		} catch (Exception e) {
		}
		new Login();
		Login.main(new String[0]);
		Myframe.dispose();
	}

}
