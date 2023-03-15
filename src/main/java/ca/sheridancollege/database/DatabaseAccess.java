package ca.sheridancollege.database;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import ca.sheridancollege.beans.Student;
import ca.sheridancollege.beans.User;

@Repository
public class DatabaseAccess {
	@Autowired
	protected NamedParameterJdbcTemplate jdbc;
	
	public void addStudent(Student s) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		String query = "INSERT INTO students (name, studentId, "
						+ "exercises, assignment1, "
						+ "assignment2, assignment3, "
						+ "midterm, finalExam, "
						+ "finalGrade, letterGrade) "
						+ "VALUES (:name, :studentId, " 
						+ ":exercises, :assignment1, " 
						+ ":assignment2, :assignment3, " 
						+ ":midterm, :finalExam, "
						+ ":finalGrade, :letterGrade)";
		parameters.addValue("name", s.getName());
		parameters.addValue("studentId", s.getStudentId());
		parameters.addValue("exercises", s.getExercises());
		parameters.addValue("assignment1", s.getAssignment1());
		parameters.addValue("assignment2", s.getAssignment2());
		parameters.addValue("assignment3", s.getAssignment3());
		parameters.addValue("midterm", s.getMidterm());
		parameters.addValue("finalExam", s.getFinalExam());
		parameters.addValue("finalGrade", s.getFinalGrade());
		parameters.addValue("letterGrade", s.getLetterGrade());
		jdbc.update(query, parameters);

	}
	
	public ArrayList<Student> getStudents(){
		ArrayList<Student> students = new ArrayList<Student>();
		String query = "SELECT * FROM students";
		List<Map<String,Object>> rows = jdbc.queryForList(query, new HashMap<String, Object>());
		for(Map<String,Object> row:rows) {
			Student s = new Student();
			s.setId((Integer)row.get("id"));
			s.setName((String)row.get("name"));
			s.setStudentId((String)row.get("studentId"));
			s.setExercises((Double)row.get("exercises"));
			s.setAssignment1((Double)row.get("assignment1"));
			s.setAssignment2((Double)row.get("assignment2"));
			s.setAssignment3((Double)row.get("assignment3"));
			s.setMidterm((Double)row.get("midterm"));
			s.setFinalExam((Double)row.get("finalExam"));
			s.setFinalGrade((Double)row.get("finalGrade"));
			s.setLetterGrade((String)row.get("letterGrade"));
			students.add(s);
		}
		return students;
	}
	
	public ArrayList<Student> getStudentByName(String name){
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		ArrayList<Student> students = new ArrayList<Student>();
		String query = "SELECT * FROM students WHERE name=:name";
		parameters.addValue("name", name);
		List<Map<String,Object>> rows = jdbc.queryForList(query, parameters);
		for(Map<String,Object> row:rows) {
			Student s = new Student();
			s.setId((Integer)row.get("id"));
			s.setName((String)row.get("name"));
			s.setStudentId((String)row.get("studentId"));
			s.setExercises((Double)row.get("exercises"));
			s.setAssignment1((Double)row.get("assignment1"));
			s.setAssignment2((Double)row.get("assignment2"));
			s.setAssignment3((Double)row.get("assignment3"));
			s.setMidterm((Double)row.get("midterm"));
			s.setFinalExam((Double)row.get("finalExam"));
			s.setFinalGrade((Double)row.get("finalGrade"));
			s.setLetterGrade((String)row.get("letterGrade"));
			students.add(s);
		}
		return students;
	}
	
	
	public Student getStudentById(int id) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		ArrayList<Student> students	= new ArrayList<Student>();
		String query = "SELECT * FROM students WHERE ID=:id";
		parameters.addValue("id", id);
		List<Map<String,Object>> rows = jdbc.queryForList(query, parameters);
		for(Map<String, Object> row: rows) {
			Student s = new Student();
			s.setId((Integer)row.get("id"));
			s.setName((String)row.get("name"));
			s.setStudentId((String)row.get("studentId"));
			s.setExercises((Double)row.get("exercises"));
			s.setAssignment1((Double)row.get("assignment1"));
			s.setAssignment2((Double)row.get("assignment2"));
			s.setAssignment3((Double)row.get("assignment3"));
			s.setMidterm((Double)row.get("midterm"));
			s.setFinalExam((Double)row.get("finalExam"));
			s.setFinalGrade((Double)row.get("finalGrade"));
			s.setLetterGrade((String)row.get("letterGrade"));
			students.add(s);
		}
		if(students.size()>0) {
			return students.get(0);
		}
		else {
			return null;
		}
	}
	
	public void updateStudent(Student s) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		String query = "UPDATE students " 
						+"SET name=:name, studentId=:studentId, " 
						+ "exercises=:exercises, assignment1=:assignment1, " 
						+ "assignment2=:assignment2, assignment3=:assignment3, " 
						+ "midterm=:midterm, finalExam=:finalExam, "
						+ "finalGrade=:finalGrade, letterGrade=:letterGrade "
						+ "WHERE id=:id";
		parameters.addValue("id", s.getId());
		parameters.addValue("name", s.getName());
		parameters.addValue("studentId", s.getStudentId());
		parameters.addValue("exercises", s.getExercises());
		parameters.addValue("assignment1", s.getAssignment1());
		parameters.addValue("assignment2", s.getAssignment2());
		parameters.addValue("assignment3", s.getAssignment3());
		parameters.addValue("midterm", s.getMidterm());
		parameters.addValue("finalExam", s.getFinalExam());
		parameters.addValue("finalGrade", s.getFinalGrade());
		parameters.addValue("letterGrade", s.getLetterGrade());
		jdbc.update(query, parameters);
		
	}
	
	public void deleteStudent(int id) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		String query = "DELETE FROM students WHERE ID=:id";
		parameters.addValue("id", id);
		jdbc.update(query, parameters);
	}
	
	public double getAvgExercises() {
		String query = "SELECT AVG(exercises) avg FROM STUDENTS";
		List<Map<String,Object>> row = jdbc.queryForList(query, new HashMap<String, Object>());
		return ((Double)row.get(0).get("avg"));
	}
	public double getAvgAssignment1() {
		String query = "SELECT AVG(Assignment1) avg FROM STUDENTS";
		List<Map<String,Object>> row = jdbc.queryForList(query, new HashMap<String, Object>());
		return ((Double)row.get(0).get("avg"));
	}
	public double getAvgAssignment2() {
		String query = "SELECT AVG(Assignment2) avg FROM STUDENTS";
		List<Map<String,Object>> row = jdbc.queryForList(query, new HashMap<String, Object>());
		return ((Double)row.get(0).get("avg"));
	}
	public double getAvgAssignment3() {
		String query = "SELECT AVG(Assignment3) avg FROM STUDENTS";
		List<Map<String,Object>> row = jdbc.queryForList(query, new HashMap<String, Object>());
		return ((Double)row.get(0).get("avg"));
	}
	public double getAvgMidterm() {
		String query = "SELECT AVG(midterm) avg FROM STUDENTS";
		List<Map<String,Object>> row = jdbc.queryForList(query, new HashMap<String, Object>());
		return ((Double)row.get(0).get("avg"));
	}
	public double getAvgFinalExam() {
		String query = "SELECT AVG(finalExam) avg FROM STUDENTS";
		List<Map<String,Object>> row = jdbc.queryForList(query, new HashMap<String, Object>());
		return ((Double)row.get(0).get("avg"));
	}
	public double getAvgFinalGrade() {
		String query = "SELECT AVG(finalGrade) avg FROM STUDENTS";
		List<Map<String,Object>> row = jdbc.queryForList(query, new HashMap<String, Object>());
		return ((Double)row.get(0).get("avg"));
	}
/*** DataBase Access Code for dealing with registration and logging in ***/
	
	public User findUserAccount(String userName) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		String query = "SELECT * FROM SEC_USER WHERE username=:name";
		parameters.addValue("name", userName);
		ArrayList<User> users = (ArrayList<User>) jdbc.query(query, parameters, 
				new BeanPropertyRowMapper<User>(User.class));

		if(users.size()>0) {
			return users.get(0);
		}
		else {
			return null;
		}
	}
	public List<String> getRolesById(long userId) {
		ArrayList<String> roles = new ArrayList<String>();
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		String query = "select user_role.userId, sec_role.roleName "
				+ "FROM user_role, sec_role "
				+ "WHERE user_role.roleId=sec_role.roleId "
				+ "and userId=:userId";
		parameters.addValue("userId", userId);
		List<Map<String, Object>> rows = jdbc.queryForList(query, parameters);
		for (Map<String, Object> row : rows) {
			roles.add((String)row.get("roleName"));
		}
		return roles;
	}

	@Autowired
	private BCryptPasswordEncoder passEncoder;
	

	public void updateUser(String username, String password, int userId) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		String query = "UPDATE SEC_User "
				+ "SET userName=:userName, encryptedPassword=:encryptedPassword "
				+ "WHERE userId=:userId";
		
		parameters.addValue("userName", username);
		parameters.addValue("encryptedPassword", passEncoder.encode(password));
		parameters.addValue("userId", userId);
		jdbc.update(query, parameters);
	}
	public void deleteUser(User user) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		String query = "DELETE FROM User_Role where userId=:userId";
		parameters.addValue("userId", user.getUserid());
		jdbc.update(query, parameters);
		
		query = "DELETE FROM Sec_User where userId=:userId";
		jdbc.update(query, parameters);
		
	}
	
	public void addNewUser(String username, String password) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		String query = "insert into SEC_User (userName, encryptedPassword, ENABLED) "
				+ "values (:userName, :encryptedPassword, 1)";
		
		parameters.addValue("userName", username);
		parameters.addValue("encryptedPassword", passEncoder.encode(password));
		
		jdbc.update(query, parameters);
	}
	
	public void addUserRoles(long userId, long roleId) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		String query = "insert into user_role (userId, roleId)" 
				+ "values (:userId, :roleId);";
		parameters.addValue("userId", userId);
		parameters.addValue("roleId", roleId);
		jdbc.update(query, parameters);	
	}
}
