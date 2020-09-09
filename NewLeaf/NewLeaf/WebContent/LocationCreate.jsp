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

<title>Create a Location</title>
</head>
<body>
	<h1>Create Location</h1>
	<form action="locationcreate" method="post">
		<p>
			<label for="address">Address</label>
			<input id="address" name="address" value="">
		</p>
		<p>
			<label for="city">City</label>
			<input id="city" name="city" value="">
		</p>
		<p>
			<label for="state">State</label>
			<input id="state" name="state" value="">
		</p>
		<p>
			<label for="zipcode">Zipcode</label>
			<input id="zipcode" name="zipcode" value="">
		</p>
		<p>
			<label for="latitude">Latitude</label>
			<input id="latitude" name="latitude" value="">
		</p>
		<p>
			<label for="longitude">Longitude</label>
			<input id="longitude" name="longitude" value="">
		</p>
		<p>
			<label for="neighborhood">Neighborhood</label>
			<input id="neighborhood" name="neighborhood" value="">
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