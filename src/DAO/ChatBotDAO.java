package DAO;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Properties;

// dua Data nay xuong DAO va luu trong sql trong tuong lai

public class ChatBotDAO {
	 private Connection Con;
	

	    // connect SQL
	 /*   public ChatBotDAO() throws Exception {
	        Properties prop = new Properties();
	        prop.load(new FileInputStream("database/person.properties"));
	        String user = prop.getProperty("user");
	        String password = prop.getProperty("password");
	        String url = prop.getProperty("url");
	        Con = DriverManager.getConnection(url, user, password);
	    }

	/*public void  insertData(String[][] data) throws SQLException {
	    PreparedStatement stm = null;
		Con.setAutoCommit(false);
		stm = Con.prepareStatement("INSERT INTO YOURTABLE (NUMBER1,NUMBER2 ,NUMBER3, NUMBER4, NUMBER5) VALUES (?, ?, ?, ?, ?)");
		for (int row = 0; row < 53; row ++){ // rows 
			for(int column=0;column  )
		        stm.setString(1, data[i][0]);  
		        stm.setString(2, data[i][1]); 
		        stm.setString(3, data[i][2]); 
		        stm.setString(4, data[i][3]); 
		        stm.setString(5, data[i][4]);      
		        stm.addBatch();
		}
		stm.executeBatch();
		Con.commit();
	}*/
	
	public String[][] openChatBotData() {

		String[][] chatBot = {
				// standard greetings
				{ "HI", "HELLO", "HOLA", "OLA ", "hi", "hello", "hallo", "HALLO", "hola", "ola", "howdy", "hey" },
				{ "Hey there , I'm VIKI your personal healthcare assistant \n \tHow are you feeling today ? \n \t a. BAD \n  \t b.VERYBAD \n  \t c. SOMETHING ELSE " },

				{ "bad", "verybad", "something else", "BAD", "VERY BAD", "SOMETHING ELSE" },
				{ "What discomfort are you feeling \n \t Choose from the following options?? \n \t a) HEADACHE \n \t b) COUGH \n \t c) MUSCLE PAIN " },
				// headache

				{ "headache", "HEADACHE" },
				{ "I,m sorry to hear that , What kind of headache ?? \n"
						+ "\t1) Headache over temple?? {type temple} \n"
						+ "\t2) Sinus headache(pain in facial sinuses)? {type sinus} \n"
						+ "\t3) Temporal headache? {type temporal}" },
				// headache over temples

				{ "temple" },
				{ "OK ! I just need a little more information. \n" + "\tPlease select any of the following \n"
						+ "\t1.one or both side of my forehead \n" + "\t2.at the back \n" + "\t3. On one side \n"
						+ "\t4. All over \n" + "\t5.Somewhere else \n" },

				// sinus headache
				{ "sinus" },
				{ "OK ! I just need a little more information. \n" + "\tPlease select any of the following \n"
						+ "\t1.one or both side of my forehead \n" + "\t2.at the back \n" + "\t3. On one side \n"
						+ "\t4. All over \n" + "\t5.Somewhere else \n" },

				// temporal headache
				{ "temporal" },
				{ "OK ! I just need a little more information. \n" + "\tPlease select any of the following \n"
						+ "\t1.one or both side of my forehead \n" + "\t2.at the back \n" + "\t3. On one side \n"
						+ "\t4. All over \n" + "\t5.Somewhere else \n" },

				{ "1", "2", "3", "4", "5", "1 ", "2 ", "3 ", "4 ", "5 " },
				{ "Is this the worst headache you can imagine \n" + "\tyes" + "\n\tno" },

				// ans for yes
				{ "yes", "YES", "yes" }, { "Does your headache come and go , or is it there all the time?" },

				{ "no", "NO" }, { "Does your headache come and go or is it there all the time ?" },

				{ "come and go", "It's there all the time", "it's there all the time", "it comes and goes",
						"all the time" },
				{ "OK WE UNDERSTAND YOUR CONDITION \n" + "\tKindly tell us your location. \n" + "\teast delhi \t"
						+ "north delhi \t" + "south delhi \t" + "west delhi \t" },

				{ "east delhi", "EAST DELHI" },
				{ "WE ARE PROVIDING YOU WITH THE BEST PANEL OF DOCTORS IN YOUR AREA \n" + "\tDr. Amit Batra\r\n"
						+ "\tAddress: Mangalam Cardio Neuro Clinic, 66, Vikas Marg, Opposite Deepak Memorial, Hargobind Enclave, Karkardooma, Delhi, 110092\r\n"
						+ "\tPhone: 099996 62292"
						+ " Do You need any more assistance ? Type 'start' for yes , 'end' for no" },

				{ "north delhi", "NORTH DELHI" },
				{ "WE ARE PROVIDING YOU WITH THE BEST PANEL OF DOCTORS IN YOUR AREA \n" + "\tDr Vipin Satija\r\n"
						+ "\tAddress: Agrawal medical centre, 2, Ashok Vihar Phase 1 Rd, Ashok Vihar II, "
						+ " Pocket C 2, Phase 2, Ashok Vihar, Delhi, 110052\r\n" + "\tPhone: 095400 99633" },

				{ "south delhi", "SOUTH DELHI" },
				{ "WE ARE PROVIDING YOU WITH THE BEST PANEL OF DOCTORS IN YOUR AREA \n" + "\tDr. Sumanto Chatterjee\r\n"
						+ "\tAddress: E791, Bipin Chandra Pal Marg, Block E, Chittaranjan, New Delhi, Delhi 110019\r\n"
						+ "\tPhone: 081302 21560" },

				{ "west delhi", "WEST DEHLI" },
				{ "WE ARE PROVIDING YOU WITH THE BEST PANEL OF DOCTORS IN YOUR AREA \n" + "\tDr. Sweta Singla\r\n"
						+ "\tAddress: C-2, 144, Major P Srikumar Marg, Block C4A, Block C2C, Janakpuri, New Delhi, Delhi 110058\r\n"
						+ "\tPhone: 011 2808 2828" },
				{ "start" },
				{ "What discomfort are you feeling \n \t Choose from the following options?? \n \t a) HEADACHE \n \t b) COUGH \n \t c) MUSCLE PAIN" },

				{ "end" }, { "Your welcome. It's our pleasure to help you " },

				// cough
				{ "cough", "COUGH" },
				{ "I,m sorry to hear that , What kind of COUGH ?? \n" + "\t1) Coughing {type intense} \n"
						+ "\t2) Coughing blood? {type bloody} \n" + "\t3) Coughing at night? {type nighty}" },

				{ "intense", "bloody", "nighty" },
				{ "OK ! I just need a little more information. \n" + "\tPlease select any of the following \n"
						+ "\tI.Clear or White phlegm (type clear phlegm) \n"
						+ "\tII.Yellow, green, or brown phlegm (type colorful phlegm)\n"
						+ "\tIII. Blood (type blood)\n" },

				{ "blood", "clear phlegm", "colorful phlegm" },
				{ "Is this the worst Cough you can imagine \n" + "\tFOR YES TYPE ya" + "\n\tFOR NO TYPE nah" },

				{ "ya", "yaa", "nah" }, { "Does your Cough come and go or is it there all the time ?" },

				// MUSCLE PAIN
				{ "MUSCLE PAIN", "muscle pain" },
				{ "I,m sorry to hear that , What kind of MUSCLE PAIN ?? \n" + "\t1) Aching muscles ?{type aching} \n"
						+ "\t2) Cramping muscles? {type cramp} \n" + "\t3) Muscle tightness? {type tightness}" },

				{ "aching", "cramp", "tightness" },
				{ "OK ! I just need a little more information. \n" + "\tPlease select any of the following \n"
						+ "\tI.Proble in Biscep (type biceps) \n" + "\tII.Problem in Tricep (type triceps)\n"
						+ "\tIII. Problem in Shoulder (type shoulder)\n" },

				{ "biceps", "triceps", "shoulder" },
				{ "Is this the worst Muscle pain you can imagine \n" + "\tFOR YES TYPE-: true"
						+ "\n\tFOR NO TYPE-: false" },

				{ "true", "false", "TRUE", "FALSE" }, { "Does your Cough come and go or is it there all the time ?" },

				// question greetings
				{ "how are you", "how r you", "how r u", "how are u" }, { "good", "doing well" },
				// yes
				{ "yes" }, { "no", "NO", "NO!!!!!!!" },
				// default
				{ "pls correct your option/choice", "You may have typed wrong", " Kindly Check your reply ",
						"(VINY IS NOT ABLE TO REPLY . PLEASE CHECK YOUR REPLY)" }
				// asking

		};
		return chatBot;
	}
}
