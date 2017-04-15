<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
<title>Attendance Settings</title>
</head>
<body>
	<h2>Attendance Settings</h2>
	<div class="container">
		<form action="Settings" method="post">
			<label>Select Course</label><br/>
			<select name="courseName">
				<option>---Select Course---</option>
				<c:forEach items="${courses}" var="course">
					<option value="${course}">
						${course}				
					</option>
				</c:forEach>
			</select>

		<br/>
			<label>On-time Deadline</label>
			<div class="form-group">
				<input type="number" name="hour" placeholder="Hour" min="0" max="11">
				<input type="number" name="minute" placeholder="Minute" min="0" max="59">
				<label class="radio-inline">
					<input type="radio" name="ampm" value="AM">AM
				</label>
				<label class="radio-inline">
					<input type="radio" name="ampm" value="PM">PM
				</label>
			</div>
			<button type="submit" class="btn btn-success">Apply Changes</button>
		</form>
		<br/>
		<label>Add Student to Attendance List</label><br/>
		<a href="AddStudent"><button class="btn btn-success">Add Student</button></a>
	</div>
	<div class="button-container" style="text-align:center">
		<a href="Swipe"><button class="btn btn-primary">Back to Swipe Page</button></a>
	</div>
	<c:choose>
		<c:when test="${not empty error}">
			${error}
		</c:when>
		<c:otherwise>
			${late }
		</c:otherwise>
	</c:choose>
</body>
</html>
