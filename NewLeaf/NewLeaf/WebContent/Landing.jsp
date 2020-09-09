<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Choose an action</title>
</head>
<body>
	<form action="findlocation" method="post">
		<h1>Choose an action</h1>
		<p>
			<span id="successMessage"><b>${messages.success}</b></span>
		</p>
	</form>

	<h1>Options</h1>
        <table border="1">
            <tr>
                <th>Find Crime</th>
                <th>Find AirBnB</th>
                <th>Find Bike Parking</th>
                <th>Find Business</th>
                <th>Find Business</th>
                
            </tr>
            <tr>
            	<td><a href="findcrime">Find Crime</a></td>
            	<td><a href="findairbnb">Find AirBnB</a></td>
            	<td><a href="findbicycleparking">Find bike parking</a></td>
            	<td><a href="findbusiness">Find Business</a></td>
            	<td><a href="searchreviews">Search AirBnb Reviews</a></td>
            	
            </tr>
       </table>
</body>
</html>