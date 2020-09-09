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

<title>Find Bicycle Parking</title>
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
	
		<form action="findbicycleparking" method="post">
			<h1 class="header">Search for Bike Parking by Neighborhood</h1>
			<p>
				<label for="neighborhoodin"></label>
				<input id="neighborhoodin" name="neighborhoodin" value="${fn:escapeXml(param.neighborhoodin)}">
			</p>
			<p>
				<input type="submit">
				<br/><br/><br/>
				<span id="successMessage"><b>${messages.success}</b></span>
			</p>
		</form>
	<br/>
	<div id="bicycleparkingcreate"><a href="bicycleparkingcreate">Create Bicycle Parking</a></div>
	<br/>
	<h1>Matching Bicycle Parking </h1>
        <table class="table">
            <tr>
                <th>BicycleParkingPK</th>
                <th>AssetType</th>
                <th>IsActive</th>
                <th>Address</th>
                <th>Neighborhood</th>
                <th>Delete Parking</th>
                <th>Update Parking</th>
            </tr>
            <c:forEach items="${bikeparking}" var="bp">
            <tr>
                <td><c:out value="${bp.getBicycleParkingPK()}" /></td>
                <td><c:out value="${bp.getAssetType()}" /></td>
                <td><c:out value="${bp.getIsActive()}" /></td>
                <td><c:out value="${bp.getAddress()}" /></td>
                <td><c:out value="${bp.getNeighborhood().getName()}" /></td>
                <td><a href="bicycleparkingdelete?bicycleparkingkey=<c:out value="${bp.getBicycleParkingPK()}"/>">Delete</a></td>
                <td><a href="bicycleparkingupdate?bicycleparkingkey=<c:out value="${bp.getBicycleParkingPK()}"/>">Update</a></td>
            </tr>
            </c:forEach>
            
       </table>
	</div>

</body>
</html>
