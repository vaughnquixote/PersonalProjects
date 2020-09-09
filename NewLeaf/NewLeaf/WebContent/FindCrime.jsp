<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

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

<title>Crimes</title>
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
		<div class="row inline">
			<div class="header">
			
				<div class="col-sm">
					<h1>Search for a crime</h1>
					<h6>"I came from a real tough neighborhood..."</h6>
					<br>
				</div>
			</div>
		</div>
	</div>
	
	
	
	<div class="container search">
		<div class="row inline">
			<form action="findcrimeneighborhood" method="post">
				
				<h4>By Neighborhood</h4>
				<p>
					<select name="neighborhood">
		        		<c:forEach items="${neighborhoods}" var="neighborhood">
		            		<option value="${neighborhood.name}">${neighborhood.name}</option>
		        		</c:forEach>
		   			</select>
		   			<input type="submit" value="Submit" />
		   		</p>
		    <br/>
			</form>	
		</div>
		
		<div class="row inline">
			<form action="findcrimelatlong" method="post">
				<h4>By Latitude and Longitude</h4>	
				Latitude:
					<label for="latitude"></label>
					<input id="latitude" name="latitude" value="${fn:escapeXml(param.latitude)}">
				Longitude:
					<label for="longitude"></label>
					<input id="longitude" name="longitude" value="${fn:escapeXml(param.longitude)}">
					<input type="submit">
					<br/><br/>
			</form>
			
		</div>
		
		
		<form action="/PM4/findcrime" method="get">
				<button type="submit">Search again</button>
			</form>
	
	</div>
	
	<p style="text-align:center">
		<span id="successMessage"><b>${messages.success}</b></span>
	</p>
	
	<div class="container search">
		<h1>Matching crimes</h1>
	        <table class="table inline">
	            <tr>
	                <th>CrimeKey</th>
	                <th>PremiseType</th>
	                <th>Offense</th>
	                <th>Year</th>
	                <th>Latitude</th>
	                <th>Longitude</th>
	                <th>Neighborhood</th>
	            </tr>
				<c:forEach items="${crimes}" var="crime">
					<tr>
		            	<td><c:out value="${crime.getCrimeKey()}" /></td>
		                <td><c:out value="${crime.getPremiseType()}" /></td>
		                <td><c:out value="${crime.getOffense()}" /></td>
		                <td><c:out value="${crime.getYear()}" /></td>
		                <td><c:out value="${crime.getLatitude()}" /></td>
		                <td><c:out value="${crime.getLongitude()}" /></td>
		                <td><c:out value="${crime.getNeighborhood().getName()}" /></td>
		        	 </tr>
		        </c:forEach>
	       </table>
       </div>
</body>
</html>
