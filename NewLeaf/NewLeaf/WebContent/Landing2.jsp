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

<title>MudHens Landing Page</title>
</head>
<body>


	<div class="header">
	<form action="findlocation" method="post">
		<h2>Welcome to the MudHens' Toronto AirBnB Search Portal</h2>
		<h5> We hope you find what you're looking for!</h5>
	</form>
	</div>
	
	<br>
	<br>
	
	<div class="container"> 
		<div class="row">
			<div class="col-sm-2">
			</div>
			<div class="col-sm-4">
				<a href="findairbnb"><img src="images/airbnb.png" width=300 height=220 class="imageformat"/></a>
				<h4><a href="findairbnb">Explore Airbnb Data</a></h4>
				
			</div>
			<div class="col-sm-4">
				<a href="findcrime"><img src="images/crime.jpg" width=300 height=220 class="imageformat"/></a>
				<h4><a href="findcrime">Explore Crime Data</a></h4>
				
				
			</div>
	
			<div class="col-sm-2"></div>
		</div>
		
		<div class="row top-buffer">
			<div class="col-sm-2">
			</div>
		
			<div class="col-sm-4">
				<a href="findbicycleparking"><img src="images/bikeparking.png" width=300 height=220 class="imageformat"/></a>
				<h4><a href="findbicycleparking">Explore Bicycle Parking</a></h4>
				
			</div>
			<div class="col-sm-4">
				<a href="findbusiness"><img src="images/business.jpg" width=300 height=220 class="imageformat"/></a>
				<h4><a href="findbusiness">Explore Yelp Businesses</a></h4>
				
				
			</div>
		
			<div class="col-sm-2">
			</div>
		
		</div>
		
		<div class="row top-buffer">
		<div class="col-sm-2">
			</div>
		
			<div class="col-sm-4">
				<a href="searchreviews"><img src="images/airbnbreview.png" width=300 height=220 class="imageformat"/></a>
				<h4><a href="searchreviews">Explore Airbnb Reviews</a></h4>
				
			</div>
			<div class="col-sm-4">
				
			</div>
		
			<div class="col-sm-2">
			</div>
		</div>
		
       </div>
	
</body>
</html>