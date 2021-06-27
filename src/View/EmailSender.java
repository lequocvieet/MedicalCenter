package View;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;

import core.Event;

public class EmailSender {

	private String sender;
	private String password;
	private String host;
	private Session session; // session luu lai du lieu phien lam viec (thong tin dang nhap)
	Properties properties = System.getProperties(); // getProperties() tìm nạp các thuộc tính hiện tại
	private List<String> emailToday;
	private List<String> emailTomorrow;
	private String messToday;
	private String messTomorrow;
	private String header;

	public EmailSender() throws FileNotFoundException, IOException {
		
		//Lấy user và pass email dùng để gửi mail
		Properties prop = new Properties();
		prop.load(new FileInputStream("database/email.properties"));
		sender = prop.getProperty("sender");
		password = prop.getProperty("password");
		host = prop.getProperty("host");
		
        // Nạp các thuộc tính 
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", "465");
		properties.put("mail.smtp.ssl.enable", "true");
		properties.put("mail.smtp.auth", "true");
		session = Session.getInstance(properties, new javax.mail.Authenticator() {

			protected PasswordAuthentication getPasswordAuthentication() {
       //Các Password Authentication thu thập từ người dùng, hoặc null nếu không được cung cấp.
				return new PasswordAuthentication(sender, password);
			}
		});
		//Tạo form header và mess
		header = "Reminder from Medical Center";
		messToday = "Hi friends,You have an appointment at our center today!";
		messTomorrow = "Hi friends,You have an appointment at our center tomorrow";

	}
    //Tạo List email để send
	public EmailSender(List<String> thelisttoday, List<String> thelisttomorrow)
			throws FileNotFoundException, IOException {
		this();
		emailToday = thelisttoday;
		emailTomorrow = thelisttomorrow;
	}

	// sending email today
	public void sendToday() {
		JOptionPane.showMessageDialog(null, "Sending today...");
		for (String receiver : emailToday) {                             //Chạy List email cần send
			try {
				// tao mot doi tuong MimeMessage mac dinh
				MimeMessage message = new MimeMessage(session);

				// Set From : header field of the header
				message.setFrom(new InternetAddress(sender));
				// Set To: header field of the header
				message.setRecipient(Message.RecipientType.TO, new InternetAddress(receiver));
				// Set Subject: header field
				message.setSubject(header);

				// Now set the actual message
				message.setContent(messToday, "text/html");

				// Send message
				Transport.send(message);

			} catch (MessagingException mex) {
				mex.printStackTrace();
				JOptionPane.showMessageDialog(null, "Error");
			}
		}
		JOptionPane.showMessageDialog(null, "Sent email successfully");
	}

	// Sending email tomorrow
	public void SendTomorrow() {
		JOptionPane.showMessageDialog(null, "Sending tomorrow...");
		for (String receiver : emailTomorrow) {                
			try {
				// tao mot doi tuong MimeMessage mac dinh
				MimeMessage message = new MimeMessage(session);

				// Set From : header field of the header
				message.setFrom(new InternetAddress(sender));
				// Set To: header field of the header
				message.setRecipient(Message.RecipientType.TO, new InternetAddress(receiver));
				// Set Subject: header field
				message.setSubject(header);

				// Now set the actual message
				message.setContent(messTomorrow, "text/html");

				// Send message
				Transport.send(message);

			} catch (MessagingException mex) {
				mex.printStackTrace();
				JOptionPane.showMessageDialog(null, "Error");
			}
		}
		JOptionPane.showMessageDialog(null, "Sent email successfully");
	}

	// Sending email event
	public void sendEvent(Event event, List<String> emails) {
		String messEvent = "Hi friends, Our center is going to hold an event. This event; " + event.getName()
				+ ",Date: " + event.getDate() + ", Description: " + event.getDescription()
				+ ". Contact with us to get more details. Have a nice day! ";
		int i = 0;
		JOptionPane.showMessageDialog(null, "Sending...");
		for (String receiver : emails) {
			i++;
			if (i == 2)
				break;
			try {
				// tao mot doi tuong MimeMessage mac dinh
				MimeMessage message = new MimeMessage(session);

				// Set From : header field of the header
				message.setFrom(new InternetAddress(sender));
				// Set To: header field of the header
				message.setRecipient(Message.RecipientType.TO, new InternetAddress(receiver));
				// Set Subject: header field
				message.setSubject(header);

				// Now set the actual message
				message.setContent(messEvent, "text/html");

				// Send message
				Transport.send(message);

			} catch (MessagingException mex) {
				mex.printStackTrace();
				JOptionPane.showMessageDialog(null, "Error");
			}
		}
		JOptionPane.showMessageDialog(null, "Sent email successfully");
	}
}
