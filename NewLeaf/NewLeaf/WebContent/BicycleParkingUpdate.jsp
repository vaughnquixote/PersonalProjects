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

<title>Update a Bicycle Parking</title>
</head>
<body>
	<h1>Update a Bicycle Parking Address</h1>
	<form action="bicycleparkingupdate" method="post">
		<p>
			<label for="bicycleparkingkey">Bicycle Parking Key</label>
			<input id="bicycleparkingkey" name="bicycleparkingkey" value="${fn:escapeXml(param.bicycleparkingkey)}">
		</p>
		<p>
			<label for="newIsActive">New IsActive</label>
			<input id="newIsActive" name="newIsActive" value="">
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