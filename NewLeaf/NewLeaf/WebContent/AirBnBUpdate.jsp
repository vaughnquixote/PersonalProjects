<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta charset="UTF-8">

<script src="https://code.jquery.com/jquery-1.10.2.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>

<style type="text/css">
    <jsp:include page="bootstrap/css/bootstrap.css" />
    <jsp:include page="bootstrap/css/mudhen-base.css" />
</style>

<title>Update AirBnB</title>
</head>

<body>

	<div id="nav">
	
	</div>
	
	<script>
	$(function(){
	  $("#nav").load("nav.html");
	});
	</script>
	
	<div class="container">
	
		<h1 class="header">Update AirBnB Price</h1>
		<form action="airbnbupdate" method="post">
			<p>
				<label for="airbnbkey">AirPK</label>
				<input id="airbnbkey" name="airbnbkey" value="${fn:escapeXml(param.airpk)}">
			</p>
			<p>
				<label for="newprice">New Price</label>
				<input id="newprice" name="newprice" value="">
			</p>
			<p>
				<input type="submit">
			</p>
		</form>
		<br/><br/>
		
		<p>
			<span id="successMessage"><b>${messages.success}</b></span>
		</p>
	</div>

</body>
</html>