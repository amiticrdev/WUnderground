<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Weather Underground</title>

<link href="css/weather.css" rel="stylesheet" />
</head>
<body>
<h3>Temperature Details:</h3>
<table class="rwd-table"  width= "60%">
    <tr>
     <th>City</th>
     <th>State</th>
     <th>Temperature</th>
    </tr>
    
  <#if currentObservation??> 
    <tr>   
     <td data-th="City">${currentObservation.observation_location.city}</td>
     <td data-th="State">${currentObservation.observation_location.state}</td>
     <td data-th="Temperature">${currentObservation.temp_f} &deg;F</td>
    </tr>
   </#if> 
   
  </table>

	<form action="/WUnderground/weather"> 
	    <input type="submit" value="Go Back">
	</form>

</body>
</html>