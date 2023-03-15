package ca.sheridancollege.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ca.sheridancollege.database.DatabaseAccess;

@Controller
public class SecurityController {
	
	@Autowired
	@Lazy
	private DatabaseAccess da;
	
	@GetMapping ("/login")
	public String goLogin() {
		return "login.html";
	}
	
	@GetMapping ("/access-denied")
	public String accessDenied() {
		return "access-denied.html";
	}
	
	/*
	@GetMapping ("/register")
	public String goRegister() {
		return "register.html";
	}
	
	@PostMapping ("/register")
	public String addUser(@RequestParam String name, @RequestParam String password, @RequestParam (required = false) Boolean userRole1, @RequestParam (required = false) Boolean userRole2, @RequestParam (required = false) Boolean userRole3) {
		da.addNewUser(name, password);
		long userId  =da.findUserAccount(name).getUserid();
		//System.out.println(userRole1);
		//System.out.println(userRole2);
		//System.out.println(userRole3);
		
		if(userRole1 == null)
			userRole1 = false;
		if(userRole2 == null)
			userRole2 = false;
		if(userRole3 == null)
			userRole3 = false;
		if(!userRole1 && !userRole2 && !userRole3)
			userRole3 = true;
		
		if(userRole1)
			da.addUserRoles(userId, 1);
		if(userRole2)
			da.addUserRoles(userId, 2);
		if(userRole3)
			da.addUserRoles(userId, 3);
			
		return "register.html";
	}
	*/
}
