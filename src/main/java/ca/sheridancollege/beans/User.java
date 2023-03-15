package ca.sheridancollege.beans;

import java.io.Serializable;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class User implements Serializable {
	
	private static final long serialVersionUID = -1131118606204199224L;
	private int userid;
	private String userName;
	private String encryptedPassword;
}
