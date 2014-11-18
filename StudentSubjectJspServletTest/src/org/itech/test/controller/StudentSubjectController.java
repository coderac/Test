package org.itech.test.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.itech.test.dao.UserDao;
import org.itech.test.model.Student;
import org.itech.test.model.Subject;
import org.itech.test.service.Service;
/**
 * Servlet implementation class JSPServlet
 */
public class StudentSubjectController extends HttpServlet {
	UserDao dao = new UserDao();
	private static final long serialVersionUID = 1L;
       
	
   	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int stu_Id = Integer.parseInt(request.getParameter("studentID"));
		int sub_Id = Integer.parseInt(request.getParameter("subjectID"));
		RequestDispatcher dispatcher = request.getRequestDispatcher("StudentSubjectSubmission.jsp");
		
		PrintWriter out = response.getWriter();
		
		if(stu_Id!=0){
		List<Subject> subject = dao.getUnassignedSubjects(stu_Id);
		for ( Subject subject2 : subject) {
			out.println(subject2.getSub_Name());
		}
		request.getSession().setAttribute("list",subject);
		request.setAttribute("stu_Id", stu_Id);
		dispatcher.forward(request, response);
		}else{
		List<Student> student = dao.getUnassignedStudent(sub_Id);
		for ( Student stu : student) {
			out.println(stu.getStu_Name());
			}
		request.getSession().setAttribute("stulist",student);
		request.setAttribute("sub_Id", sub_Id);
		dispatcher.forward(request, response);
		}
		
 	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Service service = new Service();
		String[] subList = request.getParameterValues("subList");
		String[] stuList = request.getParameterValues("stuList");
		int subDropDown = Integer.parseInt(request.getParameter("subDropDown"));
		int stuDropDown = Integer.parseInt(request.getParameter("stuDropDown"));
		if(stuList!=null){
			service.addStudents(stuDropDown, stuList);
		}
		else if(subList!=null) {
			service.addSubjects(subDropDown, subList);
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("Welcome.jsp");
		dispatcher.forward(request, response);
		
	}

}
