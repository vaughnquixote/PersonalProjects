<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">


<!-- CSS only -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
<style type="text/css">
    <jsp:include page="bootstrap/css/mudhen-base.css" />
</style>

<!-- JS, Popper.js, and jQuery -->
<script src="https://code.jquery.com/jquery-1.10.2.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>


<title>Find a User</title>
</head>
<body>

	<div id="nav">
	
	</div>
	
	<script>
		$(function(){
		  $("#nav").load("nav.html");
		});
	
	</script>

	<br/>
	<br/>
	<div class="container">
		<div class="row justify-content-center">
	
		
			<h1 class="header">Search phrases in AirBnB Reviews</h1>
		</div>
		<br/>
		<br/>
		<div class="row justify-content-center">
		<form class="form-inline" action="searchreviews" method="post">
	  <div class="form-group mx-sm-3 mb-2">
	    <input type="text" class="form-control-plaintext text-center border" id="phrase" name="phrase" value="${fn:escapeXml(param.phrase)}" placeholder="ex : 'nice view'">
	  </div>
	  <button type="submit" class="btn btn-primary mb-2">Search Reviews</button>
	</form>
		
		</div>
		<br/>
		<br/>
			<div class="row justify-content-center">
		
		<h1>Results</h1>
		<br/>
		<br/>
		</div>
		<div class="row" style="text-align:center">
		
	        <table class="table" >
	            <thead>
	            <tr>
	                <th scope="col">Neighborhood</th>
	                <th scope="col">Count</th>
	                </tr>
	            </thead>
	            <c:forEach items="${pairs}" var="pair" >
	                <tr>
	                    <td><c:out value="${pair.getNeighborhood().getName()}" /></td>
	                    <td><c:out value="${pair.getCount()}" /></td>
	                </tr>
	            </c:forEach>
	       </table>
	       
		</div>
	 </div>
       
       
       
</body>
</html>