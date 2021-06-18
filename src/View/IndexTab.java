package View;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

import DAO.WeightHeightDAO;
import javax.swing.border.LineBorder;
import java.awt.Toolkit;

public class IndexTab extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private String personID;
	private WeightHeightDAO whDao;
	private DrawChart drawChart;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IndexTab frame = new IndexTab();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public IndexTab(String thePersonId) throws Exception {
		this();
		this.personID = thePersonId;
		whDao = new WeightHeightDAO();
		drawChart = new DrawChart(thePersonId);
	}

	/**
	 * Create the frame.
	 */
	public IndexTab() {
		setTitle("Index");
		setIconImage(Toolkit.getDefaultToolkit().getImage(IndexTab.class.getResource("/Image/exercise.png")));
		setBounds(100, 100, 408, 538);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		panel.setBorder(null);
		panel.setBackground(SystemColor.inactiveCaption);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 378, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 482, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		JLabel labelImage = new JLabel("");
		labelImage.setBorder(new LineBorder(SystemColor.info, 2));
		JPanel panelImage = new JPanel();

		JButton btnNewButton = new JButton("Add     "); // Add Index
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddIndexDialog addialog;
				try {
					addialog = new AddIndexDialog(personID);
					addialog.setVisible(true);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton.setBackground(SystemColor.info);

		JButton btnNewButton_1 = new JButton("Height Chart");// Draw Height Chart
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				drawChart.drawingHeightChart();

			}
		});
		btnNewButton_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton_1.setBackground(SystemColor.info);

		JButton btnNewButton_2 = new JButton("Weight Chart");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				drawChart.drawingWeightChart();

			}
		});
		btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton_2.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		btnNewButton_2.setBackground(SystemColor.info);

		JButton btnNewButton_3 = new JButton("Advices    ");
		btnNewButton_3.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		btnNewButton_3.setBackground(SystemColor.info);
		btnNewButton_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double bmi_index = 0;
				BufferedImage image;
				try {
					bmi_index = whDao.getBmiByID(personID);
				} catch (Exception e1) {

					e1.printStackTrace();
				}
				String advices_text = null;
				if (bmi_index == 0) {
				} else if (bmi_index < 16) {

					advices_text = "Servere Thinness" + " More exercises More nutrients!";
					try {
						image = ImageIO.read(new File(
								"C:\\Users\\Admin\\Desktop\\Project I\\MedicalCenter\\src\\Image\\More exercises Severe thinness.jpg"));
						labelImage.setIcon(resizeImage(image, panelImage.getWidth(), panelImage.getHeight()));
					} catch (IOException e1) {

						e1.printStackTrace();
					}

				} else if (bmi_index < 17) {

					advices_text = "Moderate Thinness" + " Excercises and eat healthy!";
					try {
						image = ImageIO.read(new File(
								"C:\\Users\\Admin\\Desktop\\Project I\\MedicalCenter\\src\\Image\\eat healthy.png"));
						labelImage.setIcon(resizeImage(image, panelImage.getWidth(), panelImage.getHeight()));
					} catch (IOException e1) {

						e1.printStackTrace();
					}

				} else if (bmi_index < 18.5) {

					advices_text = "Mild Thinness" + " More Exercises";
					try {
						image = ImageIO.read(new File(
								"C:\\Users\\Admin\\Desktop\\Project I\\MedicalCenter\\src\\Image\\Ride a bike.jpg"));
						labelImage.setIcon(resizeImage(image, panelImage.getWidth(), panelImage.getHeight()));
					} catch (IOException e1) {

						e1.printStackTrace();
					}

				} else if (bmi_index < 25) {

					advices_text = "Normal" + " OK perfect Body, keep going!";
					try {
						image = ImageIO.read(new File(
								"C:\\Users\\Admin\\Desktop\\Project I\\MedicalCenter\\src\\Image\\keepgoing.jpg"));
						labelImage.setIcon(resizeImage(image, panelImage.getWidth(), panelImage.getHeight()));
					} catch (IOException e1) {

						e1.printStackTrace();
					}

				} else if (bmi_index < 30) {

					advices_text = "Overweight" + " More exercises ,diet Food";
					try {
						image = ImageIO.read(new File(
								"C:\\Users\\Admin\\Desktop\\Project I\\MedicalCenter\\src\\Image\\diet food.jpg"));
						labelImage.setIcon(resizeImage(image, panelImage.getWidth(), panelImage.getHeight()));
					} catch (IOException e1) {

						e1.printStackTrace();
					}

				} else if (bmi_index < 35) {

					advices_text = "Obese class I" + " Exercises every day !";
					try {
						image = ImageIO.read(new File(
								"C:\\Users\\Admin\\Desktop\\Project I\\MedicalCenter\\src\\Image\\workout.jpeg"));
						labelImage.setIcon(resizeImage(image, panelImage.getWidth(), panelImage.getHeight()));
					} catch (IOException e1) {

						e1.printStackTrace();
					}

				} else if (bmi_index < 40) {

					advices_text = "Obese class II" + " Stop eating workout harder";
					try {
						image = ImageIO.read(new File(
								"C:\\Users\\Admin\\Desktop\\Project I\\MedicalCenter\\src\\Image\\stop eat.jpg"));
						labelImage.setIcon(resizeImage(image, panelImage.getWidth(), panelImage.getHeight()));
					} catch (IOException e1) {

						e1.printStackTrace();
					}

				} else if (bmi_index > 40) {

					advices_text = "Obese class III" + " Emergency Workout Now!";
					try {
						image = ImageIO.read(new File(
								"C:\\Users\\Admin\\Desktop\\Project I\\MedicalCenter\\src\\Image\\Stop eat2.jpg"));
						labelImage.setIcon(resizeImage(image, panelImage.getWidth(), panelImage.getHeight()));
					} catch (IOException e1) {

						e1.printStackTrace();
					}

				}
				textField_1.setText(advices_text);
				textField.setText(Double.toString(bmi_index));

			}

		});

		JLabel lblNewLabel = new JLabel("Click Advices to get  your index Body");
		lblNewLabel.setBackground(SystemColor.info);
		lblNewLabel.setForeground(new Color(0, 0, 0));
		lblNewLabel.setFont(new Font("Tahoma", Font.ITALIC, 15));

		JLabel lblNewLabel_1 = new JLabel("BMI index");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));

		textField = new JTextField();
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setColumns(10);

		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(btnNewButton)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnNewButton_1)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnNewButton_2)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnNewButton_3))
						.addGroup(gl_panel.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblNewLabel_1)
							.addGap(31)
							.addComponent(textField, GroupLayout.PREFERRED_SIZE, 163, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(47)
							.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 269, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(63)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(panelImage, GroupLayout.PREFERRED_SIZE, 274, GroupLayout.PREFERRED_SIZE)
								.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, 269, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap(28, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton)
						.addComponent(btnNewButton_1)
						.addComponent(btnNewButton_2)
						.addComponent(btnNewButton_3))
					.addGap(31)
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
					.addGap(70)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
					.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(panelImage, GroupLayout.PREFERRED_SIZE, 156, GroupLayout.PREFERRED_SIZE)
					.addGap(18))
		);

		panelImage.add(labelImage);
		panel.setLayout(gl_panel);
		contentPane.setLayout(gl_contentPane);
	}

	ImageIcon resizeImage(BufferedImage originalImage, int targetWidth, int targetHeight) throws IOException {
		BufferedImage resizedImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB);
		Graphics2D graphics2D = resizedImage.createGraphics();
		graphics2D.drawImage(originalImage, 0, 0, targetWidth, targetHeight, null);
		graphics2D.dispose();

		return new ImageIcon(resizedImage);

	}

}
