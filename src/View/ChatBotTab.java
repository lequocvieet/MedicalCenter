package View;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import DAO.ChatBotDAO;


public class ChatBotTab extends JFrame implements KeyListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JPanel p = new JPanel();
	JTextArea dialog = new JTextArea(15, 50);
	JTextArea input = new JTextArea(5, 50);

	JScrollPane scroll = new JScrollPane(dialog, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
			JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

	public ChatBotTab() {
		super("VINY - HEALTHCARE CHATBOT");
		setIconImage(Toolkit.getDefaultToolkit().getImage(IndexTab.class.getResource("/Image/chatbot.png")));
		setBounds(400, 200, 600, 400);
		setResizable(false);

		dialog.setEditable(false); 
		dialog.setFont(new Font("Tahoma", Font.BOLD, 12));
		
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
			input.setFont(new Font("Tahoma", Font.BOLD, 12));
			// -----grab quote-----------
			String quote = input.getText();
			input.setText("");
			addText("-->You:\t" + quote);
			quote.trim();// delete space lead and end of the string
			while (quote.charAt(quote.length() - 1) == '!' || quote.charAt(quote.length() - 1) == '.'   //
					|| quote.charAt(quote.length() - 1) == '?') {                                      // remove character !, ?,. at the end of string
				quote = quote.substring(0, quote.length() - 1);                                       //
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
				if (inArray(quote.toLowerCase(), chatBot[j * 2])) {       // check if quote == user input at 2,4,6,8... chatbot array elements
					response = 2;
					int r = (int) Math.floor(Math.random() * chatBot[(j * 2) + 1].length);  // find a random answer in group of answer 1,3,5,7....
					addText("\n-->VINY\t" + chatBot[(j * 2) + 1][r]);
				}
				j++;
				if (j * 2 == chatBot.length - 1 && response == 0) {         
					response = 1;                                      // find the final quote at final line: that you type wrong
				}                                                      // break out while loop
			}

			// -----default--------------
			if (response == 1) {
				int r = (int) Math.floor(Math.random() * chatBot[chatBot.length - 1].length);  // random answer in group wrong answer array
				addText("\n-->VINY\t" + chatBot[chatBot.length - 1][r]);                        // answer
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
		for (int i = 0; i < str.length; i++) {                      // compare two string
			if (str[i].equals(in)) {
				match = true;
			}
		}
		return match;
	}
}