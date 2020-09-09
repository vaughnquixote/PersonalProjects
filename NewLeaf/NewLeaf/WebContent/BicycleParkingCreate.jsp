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

<title>Create Bicycle Parking</title>
</head>
<body>
	<h1>Create Bicycle Parking</h1>
	<form action="bicycleparkingcreate" method="post">
		<p>
			<label for="AssetType">AssetType</label>
			<input id="AssetType" name="AssetType" value="">
		</p>
		<p>
			<label for="IsActive">IsActive</label>
			<input id="IsActive" name="IsActive" value="">
		</p>
		<p>
			<label for="Address">Address</label>
			<input id="Address" name="Address" value="">
		</p>
		<p>
			<label for="Neighborhood">Neighborhood</label>
			<input id="Neighborhood" name="Neighborhood" value="">
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
