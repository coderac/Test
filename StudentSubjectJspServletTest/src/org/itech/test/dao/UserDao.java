package org.itech.test.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.itech.test.model.Student;
import org.itech.test.model.Subject;

import org.itech.test.util.Database;

public class UserDao {
	
	/*public static void main(String[] args) {
		dao.getUnassignedSubjects(2);
	}*/
	
	private Connection connection;
	public UserDao() {
		connection = Database.getConnection();
		
	}
	
	public int getStudentId(String stu_Name) {
		int result = 0;
		try{
		Statement stmt = connection.createStatement();
		ResultSet rs = stmt.executeQuery("select stu_Id from student where stu_Name= " + "'"+stu_Name+"'");
		while (rs.next()) {
			result=  rs.getInt(1);
		}

			connection.close();
		}catch(SQLException e){
			e.printStackTrace();
			
		}
		System.out.println(result);
		return result;
		
	}
	public List<Student> getAllStudent() {
		List<Student> students = new ArrayList<>();
		try {
			
			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery("select * from student");
			while (rs.next()) {
				Student student = new Student();
				student.setStu_Id(rs.getInt(1));
				student.setStu_Name(rs.getString(2));
				students.add(student);
			}
			//connection.close();
		} catch ( SQLException e) {
			System.out.println("Error found in getAllStudent students = " + e);
		}
		return students;
	}
	public List<Student> getUnassignedStudent(int Id) {

		List<Student> students = new ArrayList<Student>();
		try {
			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery("select * from student where stu_Id not in (select stu_Id from student_subject where sub_Id=" + Id+ ")");
			while (rs.next()) {
				Student student = new Student();
				student.setStu_Id(rs.getInt(1));
				student.setStu_Name(rs.getString(2));
				students.add(student);
			}
			//connection.close();
		} catch (SQLException e) {
			System.out.println("Error found in GetUnassigned students = " + e);
		}
		return students;
	}
	
	public int getSubjectId(String sub_Name) {
		int result =0 ; 
		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("select sub_Id from subject where sub_Name="+"'"+sub_Name+"'");
			while (rs.next()) {
				result=  rs.getInt(1);
			}
			//connection.close();
		} catch (SQLException e1) {
			System.out.println("Error found in get SubjectID is =" + e1);
		}
		System.out.println(result);
		return result;
	}
	public List<Subject> getAllSubjects() {
		List<Subject> subjects = new ArrayList<>();
		try {
			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery("select * from subject");
			while (rs.next()) {
				Subject subject = new Subject();
				subject.setSub_Id(rs.getInt(1));
				subject.setSub_Name(rs.getString(2));
				subjects.add(subject);
			}
			//connection.close();
		} catch (SQLException e) {
			System.out.println("Error found in getAllSubjects  = " + e);
		}
		return subjects;
	}
	public List<Subject> getUnassignedSubjects(int stu_Id) {
		List<Subject> subjects = new ArrayList<>();
		try {
			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery("select * from subject where sub_Id not in (select sub_Id from student_subject where stu_Id="+ stu_Id +")");
			while (rs.next()) {
				Subject subject = new Subject();
				subject.setSub_Id(rs.getInt(1));
				subject.setSub_Name(rs.getString(2));
				subjects.add(subject);
			}
			//connection.close();
		} catch (SQLException e) {
			
			System.out.println("Error found in getUnassignedSubjects = " + e);
		}
		return subjects;
	}	
	
	public void getSubjectToStudentAssignment(int stu_Id, int sub_Id) {
		try {
			Statement st = connection.createStatement();
			int val = st.executeUpdate("Insert into student_subject values ("+stu_Id+","+sub_Id+")");
			if (val != 1)
				System.out.println("Not Inserted");
			//connection.close();
		} catch ( SQLException e) {
			System.out.println("Error found in assign Subjects to Students = " + e);
		}

	}	
	
	

}



