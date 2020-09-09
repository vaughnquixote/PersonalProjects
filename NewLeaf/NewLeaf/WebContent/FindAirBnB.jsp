<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<script src="https://code.jquery.com/jquery-1.10.2.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>

<style type="text/css">
    <jsp:include page="bootstrap/css/bootstrap.css" />
    <jsp:include page="bootstrap/css/mudhen-base.css" />
</style>

<title>AirBnB Data</title>
</head>
<body>
	 <div id="nav">
	
	</div>
	
	<script>
	$(function(){
	  $("#nav").load("nav.html");
	});
	</script>
	
	<div class="container search">
	
		<form action="findavgairbnbbyneighborhood" method="post">
		<h4>Find average AirBnB price by neighborhood</h4>
		<p>
			<select name="neighborhood">
				<c:forEach items="${neighborhoods}" var ="neighborhood">
					<option value ="${neighborhood.name}">${neighborhood.name}</option>
				</c:forEach>
			</select>
			<input type="submit" value="Submit"/>
			<br/>
		</p>
	</form>
	<br/><br/>
	<form action="findavgairbnbbylocation" method= "post">
		<h4>Find the average AirBnB price by Location</h4>
		Latitude:
			<label for="latitude"></label>
			<input id="latitude" name="latitude" value="${fn:escapeXml(param.latitude)}">
			<br/>
		Longitude:
			<label for="longitude"></label>
			<input id="longitude" name="longitude" value="${fn:escapeXml(param.longitude)}">
			<br/>
		Search Radius (km):
			<label for="searchradius"></label>
			<input id="searchradius" name="searchradius" values="${fn:escapeXml(param.searchradius)}">
			<input type="submit">
			<br/><br/>
	</form>
	
	<span id="successMessage"><b>${messages.success}</b></span>
	<br/><br/>
	
		<form action="topairbyneighborhood" method="post">
		<h4>See features for 20 of the top AirBnBs in a neighborhood</h4>
		<p>
			<select name="neighborhood">
				<c:forEach items="${neighborhoods}" var ="neighborhood">
					<option value ="${neighborhood.name}">${neighborhood.name}</option>
				</c:forEach>
			</select>
			<input type="submit" value="Submit"/>
			<br/>
		</p>
	</form>
	<br/>
	
	<form action="findairbnb" method="get">
	<h4>Please hit this to make additional searches:</h4>
				<button type="submit">Search again</button>
	</form>
	<br/>
		<h1>Matching AirBnB</h1>
			<table class="table inline">
	            <tr>
	                <th>AirPK</th>
	                <th>Summary</th>
	                <th>Space</th>
	                <th>Description</th>
	                <th>PropertyType</th>
	                <th>RoomType</th>
	                <th>Bedrooms</th>
	                <th>Bathrooms</th>
	                <th>SquareFt</th>
	                <th>Price</th>
	                <th>WeeklyPrice</th>
	                <th>MonthlyPrice</th>
	                <th>SecurityDesposit</th>
	                <th>Rating</th>
	                <th>Location</th>
	                <th>Delete AirBnB</th>
	                <th>Update AirBnB</th>
	            </tr>
	            <c:forEach items="${airs}" var="air">
            <tr>
                <td><c:out value="${air.getAirPK()}" /></td>
                <td><c:out value="${air.getSummary()}" /></td>
                <td><c:out value="${air.getSpace()}" /></td>
                <td><c:out value="${air.getDescription()}" /></td>
                <td><c:out value="${air.getPropertyType()}" /></td>
                <td><c:out value="${air.getRoomType()}" /></td>
                <td><c:out value="${air.getBedrooms()}" /></td>
                <td><c:out value="${air.getBathrooms()}" /></td>
                <td><c:out value="${air.getSquareFeet()}" /></td>
                <td><c:out value="${air.getPrice()}" /></td>
                <td><c:out value="${air.getWeeklyPrice()}" /></td>
                <td><c:out value="${air.getMonthlyPrice()}" /></td>
                <td><c:out value="${air.getSecurityDeposit()}" /></td>
                <td><c:out value="${air.getRating()}" /></td>
                <td><c:out value="${air.getLocation().getLocationKey()}" /></td>
                <td><a href="airbnbdelete?airpk=<c:out value="${air.getAirPK()}"/>">Delete</a></td>
                <td><a href="airbnbupdate?airpk=<c:out value="${air.getAirPK()}"/>">Update</a></td>
            </tr>
            </c:forEach>
	       </table>
  </div>
</body>
</html>