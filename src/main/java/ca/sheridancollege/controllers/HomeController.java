package ca.sheridancollege.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import ca.sheridancollege.beans.Student;
import ca.sheridancollege.beans.User;
import ca.sheridancollege.database.DatabaseAccess;


@Controller
public class HomeController {
	
	@Autowired
	@Lazy
	private DatabaseAccess da ;

	@GetMapping("/") //url localhost:8080
	public String goHome(Model model) {
		return "home.html";
	}
	
	@GetMapping("/viewStudents") //url localhost:8080/viewStudents
	public String viewStudents(Model model, Authentication authentication) {
		System.out.println("attempting to view students");
		ArrayList<Student> students = new ArrayList<Student>();
		ArrayList<String> roles = new ArrayList<String>();
		for (GrantedAuthority ga : authentication.getAuthorities()) {
			roles.add(ga.getAuthority());
		}
		for(int i=0; i<roles.size();i++) {
			System.out.println(roles.get(i));
		}
		System.out.println("Name is: " + authentication.getName());
		
		
		if (((String) roles.get(0)).equals("ROLE_PROFESSOR")) {
			students.addAll(da.getStudents());
			model.addAttribute("avgExercises", da.getAvgExercises());
			model.addAttribute("avgAssignment1", da.getAvgAssignment1());
			model.addAttribute("avgAssignment2", da.getAvgAssignment2());
			model.addAttribute("avgAssignment3", da.getAvgAssignment3());
			model.addAttribute("avgMidterm", da.getAvgMidterm());
			model.addAttribute("avgFinalExam", da.getAvgFinalExam());
			double AFG = da.getAvgFinalGrade();
			model.addAttribute("avgFinalGrade", AFG);
			model.addAttribute("avgLetterGrade", Student.LetterGrade(AFG));
			model.addAttribute("students", students);
			
			return "viewStudents.html";
		}
		else if (((String) roles.get(0)).equals("ROLE_STUDENT")) {
			students.addAll(da.getStudentByName(authentication.getName()));
			model.addAttribute("students", students);
			return "viewStudent.html";
		}
		else {
			System.out.println("Does not have a role");
			return null;
		}

	}
	
	@GetMapping("/addStudent") //url localhost:8080/addStudent
	public String addStudent(Model model) {
		model.addAttribute("student", new Student());
		return "/addStudent.html";
	}
	
	@GetMapping("/add") //url localhost:8080/addStudent
	public String add(@ModelAttribute Student student) {
		student.calculateFinalGrade();
		student.calculateLetterGrade();
		da.addStudent(student);
		da.addNewUser(student.getName(), student.getStudentId());
		User user = da.findUserAccount(student.getName());
		da.addUserRoles(user.getUserid(), 2);
		return "redirect:/addStudent";
	}

	@GetMapping("/edit/{id}") //url localhost:8080/editStudent
	public String editStudent(Model model, @PathVariable int id) {
		Student student = da.getStudentById(id);
		model.addAttribute("student",student);
		return "editStudent.html";
	}
	
	@GetMapping("/updateStudent")
	public String updateStudent(@ModelAttribute Student student) {
		User user = da.findUserAccount((da.getStudentById(student.getId())).getName());
		da.updateUser(student.getName(), student.getStudentId(), user.getUserid());
		student.calculateFinalGrade();
		student.calculateLetterGrade();
		da.updateStudent(student);		
		return "redirect:/viewStudents";
	}
	
	@GetMapping("/delete/{id}") //url localhost:8080/editStudent
	public String editStudent(@PathVariable int id) {
		User user = da.findUserAccount((da.getStudentById(id)).getName());
		da.deleteUser(user);
		da.deleteStudent(id);
		return "redirect:/viewStudents";
	}
	

}
