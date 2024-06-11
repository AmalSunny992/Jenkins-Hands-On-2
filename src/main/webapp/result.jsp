<!DOCTYPE html>
<html>
<head>
    <title>Weather Report</title>
</head>
<body>
    <h1>Weather Report for <%= request.getAttribute("city") %></h1>
    <p>Weather: <%= request.getAttribute("weather") %></p>
    <p>Temperature: <%= String.format("%.2f", request.getAttribute("temperature")) %> °C</p>
    <a href="index.jsp">Back</a>
</body>
</html>