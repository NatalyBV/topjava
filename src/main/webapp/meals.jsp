<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<html lang="ru">
<head>
    <link rel="stylesheet" type="text/css" href="css/meals.css">
    <title>Meals</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<h2>Meals</h2>
<table>
    <tr>
        <th>Date</th>
        <th>Description</th>
        <th>Calories</th>
        <th colspan=2>Action</th>
    </tr>

    <c:forEach var="mealTo" items="${requestScope.meals}">
        <tr class="${mealTo.excess ? 'ExcessValues' : 'NotExcessValues'}">
            <td>${mealTo.dateTime.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))}</td>
            <td>${mealTo.description}</td>
            <td>${mealTo.calories}</td>
            <td><a href="MealServlet?action=edit&mealId=<c:out value="${mealTo.mealId}"/>">Update</a></td>
            <td><a href="MealServlet?action=delete&mealId=<c:out value="${mealTo.mealId}"/>">Delete</a></td>
        </tr>
    </c:forEach>

</table>
<li><a href="MealServlet?action=insert">Add a meal</a></li>
</body>
</html>