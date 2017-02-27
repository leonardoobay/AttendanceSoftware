<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
<title>Attendance</title>
<style>
	.form-container form,
	.form-container form div {
	    display: inline;
	}
	
	.form-container button {
	    display: inline;
	    vertical-align: middle;
	}
	
	.div-container{
		text-align:center;
	}
	
	ol,ul{
		display:inline-block;
	}
</style>
</head>
<body>
	<div class="div-container">
		<c:choose>
			<c:when test="${empty swipe}">
				<form class="form-inline" action="Swipe" method="post">
					<input class ="btn btn-primary" name="swipe" placeholder="New Swipe" type="password">
				</form>
				<ol>
					<li>Click "New Swipe"</li>
					<li>Swipe Student ID Card</li>
				</ol>
			</c:when>
			<c:otherwise>
				<br/>
				<div class="jumbotron">
					<p>Verify the information below:</p>
					<p>${swipe.firstName}</p>
					<p>${swipe.lastName}</p>
					<p>${swipe.cin}</p>
				</div>
				
				<div class="form-container">
					<form class="form-inline" action="checkIn" method="post">
						<input type="hidden" name="swipe" value=${swipe}>
						<input class="btn btn-success" type="submit" value="Yes,This is me">
					</form>
					<form class="form-inline" action="Swipe" method="post">
						<input type="hidden" name="swipe" value="">
						<input class="btn btn-danger" type="submit" value="No,This is not me">
					</form>
				</div>
			</c:otherwise>
		</c:choose>
	</div>
</body>
</html>