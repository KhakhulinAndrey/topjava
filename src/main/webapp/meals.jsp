<%@ page import="java.util.List" %>
<%@ page import="ru.javawebinar.topjava.model.MealWithExceed" %>
<%--
  Created by IntelliJ IDEA.
  User: 1
  Date: 14.07.2017
  Time: 10:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Meals</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<h2>Meals</h2>

<style>
    .green {
        color: green;
    }

    .red {
        color: red;
    }
</style>


<table>
    <tr>
        <td>Description</td>
        <td>Time</td>
        <td>Calories</td>
        <th colspan=2>Action</th>
    </tr>


    <jsp:useBean id="meals" scope="request" type="java.util.List"/>
    <c:forEach items="${meals}" var="m">

        <tr class="${m.isExceed() ? 'red' : 'green'}"/>

        <td>${m.description}</td>
        <td>${m.getStrDateTime()}</td>
        <td>${m.calories}</td>
        <td><a href="?action=edit&mealId=<c:out value="${m.getId()}"/>">Update</a></td>
        <td><a href="?action=delete&mealId=<c:out value="${m.getId()}"/>">Delete</a></td>
        </tr>

    </c:forEach>
</table>
<p><a href="?action=insert">Add Meal</a></p>

</body>
</html>
