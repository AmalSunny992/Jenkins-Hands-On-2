<!DOCTYPE html>
<html>
<head>
    <title>Weather App</title>
</head>
<body>
    <h1>Select a city to see the weather report</h1>
    <form action="weather" method="post">
        <select name="city">
            <option value="New York">New York</option>
            <option value="London">London</option>
            <option value="Tokyo">Tokyo</option>
            <option value="Sydney">Sydney</option>
        </select>
        <input type="submit" value="Get Weather">
    </form>
</body>
</html>