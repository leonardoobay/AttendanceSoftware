<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Student</title>
</head>
<body>
	<div class="form-group">
		<form action="AddStudent" method="post">
			<input type="text" name="firstName" value="${newStudent.firstName}">
			<input type="text" name="lastName" value="${newStudent.lastName}">
			<input type="number" name="cin" value="${newStudent.cin}">
			<button type="submit" class="btn btn-success">Add Student</button>
		</form>
	</div>
</body>
</html>