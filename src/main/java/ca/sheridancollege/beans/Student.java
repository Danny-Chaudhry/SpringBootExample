package ca.sheridancollege.beans;
import java.io.Serializable;

import lombok.*;

@NoArgsConstructor
@Data
public class Student implements Serializable{

	private static final long serialVersionUID = -1151601425008580749L;
	private int id;
	private String name;
	private String studentId;
	private double exercises;
	private double assignment1;
	private double assignment2;
	private double assignment3;
	private double midterm;
	private double finalExam;
	private double finalGrade;
	private String letterGrade;
	
	public Student(int id, String name, double exercises, double assignment1, double assignment2, double assignment3,
			double midterm, double finalExam) {
		this.id = id;
		this.name = name;
		this.exercises = exercises;
		this.assignment1 = assignment1;
		this.assignment2 = assignment2;
		this.assignment3 = assignment3;
		this.midterm = midterm;
		this.finalExam = finalExam;
		this.calculateFinalGrade();
		this.calculateLetterGrade();
	}
	
	public void calculateFinalGrade() {
		setFinalGrade(this.exercises*(0.1) 
				+ this.assignment1*(0.06)
				+ this.assignment2*(0.12)
				+ this.assignment3*(0.12)
				+ this.midterm*(0.3)
				+ this.finalExam*(0.3));
	}
	
	public void calculateLetterGrade() {
		this.calculateFinalGrade();
		double fg = this.finalGrade;
		setLetterGrade(LetterGrade(fg));
	}
	
	public static String LetterGrade(Double fg) {
		
		if (fg >= 90.0) {
			return "A+";
		}
		else if (85.0<=fg && fg<90.0) {
			return "A";
		}
		else if (80.0<=fg && fg<85.0) {
			return "A-";
		}
		else if (75.0<=fg && fg<80.0) {
			return "B+";
		}
		else if (70.0<=fg && fg<75.0) {
			return "B";
		}
		else if (65.0<=fg && fg<70.0) {
			return "C+";
		}
		else if (60.0<=fg && fg<65.0) {
			return "C";
		}
		else if (55.0<=fg && fg<60.0) {
			return "D+";
		}
		else if (50.0<=fg && fg<55.0) {
			return "D";
		}
		else {
			return "F";
		}
	}
}
