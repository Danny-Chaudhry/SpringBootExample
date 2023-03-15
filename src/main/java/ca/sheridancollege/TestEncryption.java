package ca.sheridancollege;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class TestEncryption {

	public static String encryptPassword(String password) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder.encode(password);
	}
	
	public static void main(String[] args) {
		String[] passwords = {"111222001", "111222002", "111222003", "111222004", "111222005", 
				"111222006", "111222007", "111222008", "111222009","111222010"};
		String[] students = {"One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten"};
		
		for(int i=0; i<students.length; i++){
			String encryptPassword = encryptPassword(passwords[i]);
			System.out.println("('" + students[i] + "', " 
					+ "'" + encryptPassword + "', "
					+ "1),");
		}
		
		System.out.println(encryptPassword("123"));
	}
}
