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

<title>Find a Location</title>
</head>
<body>
	<form action="findlocation" method="post">
		<h1>Search for a location by address</h1>
		<p>
			<label for="address"></label>
			<input id="address" name="address" value="${fn:escapeXml(param.address)}">
		</p>
		<p>
			<input type="submit">
			<br/><br/><br/>
			<span id="successMessage"><b>${messages.success}</b></span>
		</p>
	</form>
	<br/>
	<div id="locationCreate"><a href="locationcreate">Create Location</a></div>
	<br/>
	<h1>Matching locations</h1>
        <table border="1">
            <tr>
                <th>LocationKey</th>
                <th>Address</th>
                <th>City</th>
                <th>State</th>
                <th>ZipCode</th>
                <th>Neighborhood</th>
                <th>Delete location</th>
                <th>Update location</th>
            </tr>
            <tr>
                <td><c:out value="${location.getLocationKey()}" /></td>
                <td><c:out value="${location.getAddress()}" /></td>
                <td><c:out value="${location.getCity()}" /></td>
                <td><c:out value="${location.getState()}" /></td>
                <td><c:out value="${location.getZipCode()}" /></td>
                <td><c:out value="${neighborhood.getName()}" /></td>
                <td><a href="locationdelete?locationkey=<c:out value="${location.getLocationKey()}"/>">Delete</a></td>
                <td><a href="locationupdate?locationkey=<c:out value="${location.getLocationKey()}"/>">Update</a></td>
            </tr>
       </table>
</body>
</html>
