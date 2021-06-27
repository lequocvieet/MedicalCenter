package View;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class ValidateEmailPhone {
	public JPanel parentPanel;
	public String emailStr;
	
	public static final Pattern VALID_EMAIL_ADDRESS_REGEX = 
		    Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
	
	public static final Pattern VALID_PHONE_NUMBER_REGEX =                                         //regex pattern 
			Pattern.compile(  "^(\\+\\d{1,3}( )?)?((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$" 
				      + "|^(\\+\\d{1,3}( )?)?(\\d{3}[ ]?){2}\\d{3}$" 
				      + "|^(\\+\\d{1,3}( )?)?(\\d{3}[ ]?)(\\d{2}[ ]?){2}\\d{2}$", Pattern.CASE_INSENSITIVE);

	
	public ValidateEmailPhone() {
			
	}
	public static boolean validateEmail(JPanel parentPanel,String emailStr) {
		        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
		       return matcher.find();
	}
		
		public static boolean validatePhone(JPanel parentPanel,String phoneNum) {
	        Matcher matcher = VALID_PHONE_NUMBER_REGEX.matcher(phoneNum);
	        return matcher.find();
	}
			
}
