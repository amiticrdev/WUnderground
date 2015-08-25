<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Weather Report from Underground Weather</title>

<link href="<c:url value="css/weather.css" />" rel="stylesheet">

</head>
<body>

<form:form method="POST" action="/WUnderground/weatherReport" modelAttribute="weatherData" >
<h3>Enter the Zip code to get the temperature details:</h3>
   <table class="rwd-table" width= "60%">
    <tr>
        <td align="left" width = "20%"><form:label path="zipCode">Zip Code</form:label></td>
        <td align="left" width = "30%"><form:input path="zipCode" /></td>
        <td align="left" width = "50%"><form:errors path="*" cssClass="error"/></td>
    </tr>
    <tr>
        <td colspan="3">
            <input type="submit" value="Submit"/>
        </td>
    </tr>
</table>  
</form:form>
</body>
</html>