<%--
  Created by IntelliJ IDEA.
  User: 1
  Date: 18.07.2017
  Time: 10:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<html>
<head>
    <title>Meal</title>
</head>
<body>
<h3><a href="meals">Cancel</a></h3>
<h2>Meal</h2>

<form method="POST">

    <c:if test="${not empty meal.id}">
        <input type="text" hidden name="id" value="${meal.id}"/>
    </c:if>

    Description : <input
        type="text" name="description"
        value="<c:out value="${meal.description}" />"/>
    <br/>
    <br/>

    Calories : <input
        type="number" name="calories"
        value="<c:out value="${meal.calories}" />"/>
    <br/>
    <br/>
    DateTime : <input
        type="datetime-local" name="dateTime" value="${meal.dateTime}"/>
    <br/>
    <br/>
    <input type="submit" value="Submit"/>
</form>
</body>
</html>
