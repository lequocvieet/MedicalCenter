package View;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import DAO.ChatBotDAO;

public class ChatBotTab extends JFrame implements KeyListener {
	JPanel p = new JPanel();
	JTextArea dialog = new JTextArea(15, 50);
	JTextArea input = new JTextArea(5, 50);

	JScrollPane scroll = new JScrollPane(dialog, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
			JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

	public ChatBotTab() {
		super("VINY - HEALTHCARE CHATBOT");
		setBounds(800, 200, 600, 400);
		setResizable(false);

		dialog.setEditable(false); // isko nahi chedna
		input.addKeyListener(this);

		p.add(scroll);
		p.add(input);
		p.setBackground(new Color(128, 128, 128));
		add(p);
		setVisible(true);

//for background
		dialog.setBackground(getBackground());

	}

	ChatBotDAO chatBotDao = new ChatBotDAO();
	String[][] chatBot = chatBotDao.openChatBotData();

	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			input.setEditable(false);
			// -----grab quote-----------
			String quote = input.getText();
			input.setText("");
			addText("-->You:\t" + quote);
			quote.trim();
			while (quote.charAt(quote.length() - 1) == '!' || quote.charAt(quote.length() - 1) == '.'
					|| quote.charAt(quote.length() - 1) == '?') {
				quote = quote.substring(0, quote.length() - 1);
			}
			quote.trim();
			byte response = 0;
			/*
			 * 0:we're searching through chatBot[][] for matches 1:we didn't find anything
			 * 2:we did find something
			 */
			// -----check for matches----
			int j = 0;// which group we're checking
			while (response == 0) {
				if (inArray(quote.toLowerCase(), chatBot[j * 2])) {
					response = 2;
					int r = (int) Math.floor(Math.random() * chatBot[(j * 2) + 1].length);
					addText("\n-->VINY\t" + chatBot[(j * 2) + 1][r]);
				}
				j++;
				if (j * 2 == chatBot.length - 1 && response == 0) {
					response = 1;
				}
			}

			// -----default--------------
			if (response == 1) {
				int r = (int) Math.floor(Math.random() * chatBot[chatBot.length - 1].length);
				addText("\n-->VINY\t" + chatBot[chatBot.length - 1][r]);
			}
			addText("\n");
		}
	}

	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			input.setEditable(true);
		}
	}

	public void keyTyped(KeyEvent e) {
	}

	public void addText(String str) {
		dialog.setText(dialog.getText() + str);
	}

	public boolean inArray(String in, String[] str) {
		boolean match = false;
		for (int i = 0; i < str.length; i++) {
			if (str[i].equals(in)) {
				match = true;
			}
		}
		return match;
	}
}