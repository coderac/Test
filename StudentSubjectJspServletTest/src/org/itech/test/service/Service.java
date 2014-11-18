package org.itech.test.service;

import org.itech.test.dao.UserDao;

public class Service {
	private UserDao dao = new UserDao();
	private int[] ids = {1,2,3,4,5,6,7,8,9};
	public void addSubjects(int stu_Id, String[] list){
		for (int i=0;i<list.length;i++) {
			ids[i] = dao.getSubjectId(list[i]);
		}
	
		for(int i=0;i<list.length;i++){
			dao.getSubjectToStudentAssignment(stu_Id, ids[i]);
			//getSubjectToStudentAssignment(stu_Id, ids[i]);
		}
	}
	
	public void addStudents(int sub_Id, String[] list){
		for (int i=0;i<list.length;i++) {
			ids[i] = dao.getStudentId(list[i]);
		}
	
		for(int i=0;i<list.length;i++){
			dao.getSubjectToStudentAssignment(ids[i], sub_Id);
		}
	}

}
