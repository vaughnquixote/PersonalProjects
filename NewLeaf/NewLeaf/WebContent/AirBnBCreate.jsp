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

<title>Create an AirBnB</title>
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

		<h1 class="header">Create AirBnB</h1>
		<form action="airbnbcreate" method="post" style="text-align:center">
			<p>
				<label for="summary">Summary</label>
				<input id="summary" name="summary" value="">
			</p>
			<p>
				<label for="space">Space</label>
				<input id="space" name="space" value="">
			</p>
			<p>
				<label for="description">Description</label>
				<input id="description" name="description" value="">
			</p>
			<p>
				<label for="propertytype">Property Type</label>
				<input id="propertytype" name="propertytype" value="">
			</p>
			<p>
				<label for="roomtype">Room Type</label>
				<input id="roomtype" name="roomtype" value="">
			</p>
			<p>
				<label for="bedrooms">Bedrooms</label>
				<input id="bedrooms" name="bedrooms" value="">
			</p>
			<p>
				<label for="bathrooms">Bathrooms</label>
				<input id="bathrooms" name="bathrooms" value="">
			</p>
			<p>
				<label for="squarefeet">Square Feet</label>
				<input id="squarefeet" name="squarefeet" value="">
			</p>
			<p>
				<label for="price">Price</label>
				<input id="price" name="price" value="">
			</p>
			<p>
				<label for="weeklyprice">Weekly Price</label>
				<input id="weeklyprice" name="weeklyprice" value="">
			</p>
			<p>
				<label for="monthlyprice">Monthly Price</label>
				<input id="monthlyprice" name="monthlyprice" value="">
			</p>
			<p>
				<label for="securitydeposit">Security Deposit</label>
				<input id="securitydeposit" name="securitydeposit" value="">
			</p>
			<p>
				<label for="rating">Rating</label>
				<input id="rating" name="rating" value="">
			</p>
			<p>
				<label for="location">LocationKey</label>
				<input id="location" name="location" value="">
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