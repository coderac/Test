<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"  import="java.util.List" import="org.itech.test.model.Student" import="org.itech.test.model.Subject"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
    <%@page import="org.itech.test.dao.UserDao" %>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Student Subject Selection</title>
<script>
function studentValue()
{
	var sub_Id = 0;
	var stu_Id = document.getElementById("studentDropDown").value;
	if(stu_Id==null){
		stu_Id=0;
	}
	else if(sub_Id==null){
		sub_Id=0;
	}
	location.href="ssc?studentID="+stu_Id+"&subjectID="+sub_Id;
	}
	
function SubjectValue()
{
	var sub_Id = document.getElementById("subDropDown").value;
	var stu_Id = 0;
	if(stu_Id==null){
		stu_Id=0;
	}
	else if(sub_Id==null){
		sub_Id=0;
	}
	location.href="ssc?studentID="+stu_Id+"&subjectID="+sub_Id;
	}
	
</script>
</head>
<body bgcolor="skyblue" style="text-align: right; outline-style: groove; outline-color: blue; outline: fuchsia; table-layout: fixed;
padding-left: 40%; list-style: georgian;">

<form action="ssc" method="post">
<table><tbody>
<tr><td>
Select Student :
<%
UserDao dao = new UserDao();
List<Student> students = dao.getAllStudent();
%>
<select id="studentDropDown" name="studentDropDown" onchange="studentValue()"> 

<option value="0">Select Student</option>
<c:forEach items="<%=students%>" var="student">
	<option value="${student.stu_Id}" ${stu_Id==student.stu_Id?'selected':'' }>${student.stu_Name}</option>
</c:forEach>

</select>
</td><td>
<tr><td>
Select Subject :
<%
List<Subject> subjects = dao.getAllSubjects();
%>
<select id="subDropDown" name="subDropDown" onchange="SubjectValue()">
<option value="0">Select Subject</option>
<c:forEach items="<%=subjects%>" var="subject">
<option value="${subject.sub_Id}" ${sub_Id==subject.sub_Id?'selected':'' }>${subject.sub_Name}</option>
</c:forEach>

</select>
</td><td>

<tr><td>
 Selected Subjects :<br>
 			<tr><th><select name="subList" size="8" multiple="multiple">
	           <c:forEach items="${list}" var="subList">
					<option value="${subList.sub_Name}">${subList.sub_Name}</option>
				</c:forEach>
       </select></th></tr>
       
<tr><td>
 Selected Students :<br>
 			<tr><th><select name="stuList" size="8" multiple="multiple">
 			<c:forEach items="${stulist}" var="sList">
					<option value="${sList.stu_Name}" >${sList.stu_Name}</option>
				</c:forEach>
       </select></th></tr>
       
	<tr><td><input type="submit" value="Enter"></td></tr>       
</table>

</form>
</body>
        
</html>