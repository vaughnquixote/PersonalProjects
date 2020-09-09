<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<style type="text/css">
    <jsp:include page="bootstrap/css/bootstrap.css" />
    <jsp:include page="bootstrap/css/mudhen-base.css" />
</style>

<title>Create a Business</title>
</head>
<body>
	<h1>Create Business</h1>
	<form action="businesscreate" method="post">
		<p>
			<label for="Name">Name</label>
			<input id="Name" name="Name" value="">
		</p>
		<p>
			<label for="Stars">Stars</label>
			<input id="Stars" name="Stars" value="">
		</p>
		<p>
			<label for="ReviewCount">ReviewCount</label>
			<input id="ReviewCount" name="ReviewCount" value="">
		</p>
		<p>
			<label for="Attributes">Attributes</label>
			<input id="Attributes" name="Attributes" value="">
		</p>
		<p>
			<label for="Categories">Categories</label>
			<input id="Categories" name = "Categories" value="">
		</p>
		<p>
			<label for="Hours">Hours</label>
			<input id="Hours" name="Hours" value="">
		</p>
		<p>
			<label for="LocationFK">LocationFK</label>
			<input id="LocationFK" name="LocationFK" value="">
		</p>
		<p>
			<input type="submit">
		</p>
	</form>
	<br/><br/>
	<p>
		<span id="successMessage"><b>${messages.success}</b></span>
	</p>
</body>
</html>