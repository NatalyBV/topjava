<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<html lang="ru">
<head>
    <link rel="stylesheet" type="text/css" href="css/meals.css">
    <title>Meal</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<form action="MealServlet" method="post">
    <p>Date and time</p>
    <p><input type="datetime-local" name="Date"></p>
    <p><strong>Description</strong></p>
    <p><input maxlength="500" size="40" name="Description" value=""></p>
    <p><strong>Calories</strong></p>
    <p><input maxlength="5" size="40" name="Calories" value=""></p>
    <p><input type="submit" value="Save"></p>
    </form>
</body>
</html>