package View;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

import DAO.HistoryMedicalDAO;
import core.HistoryMedical;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@SuppressWarnings("serial")
public class VaccinationTab extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTable table;
	private String personID;
	private HistoryMedicalDAO histDAO;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VaccinationTab frame = new VaccinationTab();
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

	public VaccinationTab(String ID) throws Exception {
		this();
		personID = ID;
		histDAO = new HistoryMedicalDAO();
		List<HistoryMedical> hists = null;

		hists = histDAO.getAllPatient(personID);
		VaccinationTableModel model = new VaccinationTableModel(hists);
		table.setModel(model);
		String txt = "Vaccination Hisory of " + personID;
		textField.setText(txt);

	}

	public VaccinationTab() {
		setBackground(Color.WHITE);
		setTitle("Vaccination");
		setIconImage(Toolkit.getDefaultToolkit().getImage(VaccinationTab.class.getResource("/Image/vaccine (1).png")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 862, 459);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.activeCaption);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup().addContainerGap()
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 798, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(30, Short.MAX_VALUE)));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 404, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

		JToolBar toolBar = new JToolBar();
		toolBar.setBackground(SystemColor.activeCaption);

		JScrollPane scrollPane = new JScrollPane();

		JPanel panelImage = new JPanel();
		panelImage.setBackground(Color.WHITE);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(gl_panel.createParallelGroup(Alignment.LEADING).addGroup(gl_panel
				.createSequentialGroup().addContainerGap()
				.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(toolBar, GroupLayout.DEFAULT_SIZE, 778, Short.MAX_VALUE)
						.addGroup(gl_panel.createSequentialGroup()
								.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 456, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(panelImage, GroupLayout.PREFERRED_SIZE, 314, GroupLayout.PREFERRED_SIZE)))
				.addContainerGap()));
		gl_panel.setVerticalGroup(gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup().addContainerGap()
						.addComponent(toolBar, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE).addGap(18)
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 337, GroupLayout.PREFERRED_SIZE)
								.addComponent(panelImage, GroupLayout.PREFERRED_SIZE, 333, GroupLayout.PREFERRED_SIZE))
						.addContainerGap(11, Short.MAX_VALUE)));

		JLabel lblImage = new JLabel("");
		panelImage.add(lblImage);
		lblImage.setForeground(SystemColor.inactiveCaptionBorder);
		lblImage.setBackground(SystemColor.text);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				int selectedRow = table.getSelectedRow();

				HistoryMedical temp = (HistoryMedical) table.getValueAt(selectedRow, VaccinationTableModel.OBJECT_COL);
				String path = temp.getImageHist();

				BufferedImage image = null;

				if (path != null) {
					try {
						image = ImageIO.read(new File(path));
						lblImage.setIcon(resizeImage(image, panelImage.getWidth(), panelImage.getHeight()));
					} catch (IOException e1) {

						e1.printStackTrace();
					}

				} else {
					ImageIcon iconNull = new ImageIcon(getClass().getResource("/Image/noImage.png"));
					lblImage.setIcon(iconNull);
				}

			}

			ImageIcon resizeImage(BufferedImage originalImage, int targetWidth, int targetHeight) throws IOException {
				BufferedImage resizedImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB);
				Graphics2D graphics2D = resizedImage.createGraphics();
				graphics2D.drawImage(originalImage, 0, 0, targetWidth, targetHeight, null);
				graphics2D.dispose();

				return new ImageIcon(resizedImage);

			}
		});

		scrollPane.setViewportView(table);

		JButton btnAdd = new JButton("Add New History");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddVaccinationDialog diag = new AddVaccinationDialog(histDAO, personID);
				diag.setVisible(true);
			}
		});
		btnAdd.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		btnAdd.setBackground(SystemColor.info);
		btnAdd.setFont(new Font("Tahoma", Font.BOLD, 14));
		toolBar.add(btnAdd);

		textField = new JTextField();
		textField.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		toolBar.add(textField);
		textField.setColumns(10);
		panel.setLayout(gl_panel);
		contentPane.setLayout(gl_contentPane);
	}
}
