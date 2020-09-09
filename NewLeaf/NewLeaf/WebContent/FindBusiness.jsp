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

<title>Find Businesses</title>
</head>
<body>

	<form action="findbusiness" method="post">


	<div id="nav">
	
	</div>


	<script>
		$(function(){
			$("#nav").load("nav.html");
		});
	
	</script>

	<h1 class="header">Search For Businesses by Rating</h1>
	
	<div class="container" style="text-align:center">
		<form action="findbusiness" method="post">
		
		<p>
			<label for="rating">Minimum Rating</label>
			<input id="rating" name="rating" value="${fn:escapeXml(param.rating)}">
		</p>
		<p>
			<input type="submit">
			<br/><br/>
			<span id="successMessage"><b>${messages.success}</b></span>
		</p>
	</form>
	<br/><br/>
	
	<form action="findbusinessbyneighborhood" method ="post">
		<h1>See the Top 20 Businesses with a Certain Number of Reviews in a Neighborhood</h1>
		
		<label for="neighborhood">Neighborhood:</label>
		<input id="neighborhood" name="neighborhood" value="${fn:escapeXml(param.neighborhood)}">
		<br/>
		<label for="reviewcount">Review Count:</label>
		<input id="reviewcount" name="reviewcount" value="${fn:escapeXml(param.reviewcount)}">
`		<br/>
		<label for="rating">Minimum Rating:</label>
		<input id="rating" name="rating" value="${fn:escapeXml(param.rating)}">
		<input type="submit">
		<br/><br/>
	</form>
	<br/>
	<span id ="successMessage"><b>${messages.success1}</B></span>
	<br/>
	<form action="findbusiness" method="get">
		<h4>Please hit this to make additional searches:</h4>
		<button type="submit">Search again</button>
	</form>
	<br/><br/><br/>
	
	<h1>Matching Businesses</h1>
        <table class="table inline">
            <tr>
                <th>ParksPK</th>
                <th>Name</th>
                <th>Stars</th>
                <th>ReviewCount</th>
                <th>Attributes</th>
                <th>Categories</th>
                <th>Hours</th>
                <th>Location</th>
                <th>Delete Business</th>
                <th>Update Business</th>
            </tr>
            <c:forEach items="${businesses}" var="business" >
                <tr>
                    <td><c:out value="${business.getParksPK()}" /></td>
                    <td><c:out value="${business.getName()}" /></td>
                    <td><c:out value="${business.getStars()}" /></td>
                    <td><c:out value="${business.getReviewCount()}" /></td>
                    <td><c:out value="${business.getAttributes()}" /></td>
                    <td><c:out value="${business.getCategories()}" /></td>
                    <td><c:out value="${business.getHours()}" /></td>
                    <td><c:out value="${business.getLocation().getAddress()}" /></td>
                    
                    <td><a href="businessdelete?businesskey=<c:out value="${business.getParksPK()}"/>">Delete</a></td>
                    <td><a href="businessupdate?businesskey=<c:out value="${business.getParksPK()}"/>">Update</a></td>
                </tr>
            </c:forEach>
       </table>
	
	</div>
	
	
</body>
</html>
