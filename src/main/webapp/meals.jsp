<%@ page import="java.util.List" %>
<%@ page import="ru.javawebinar.topjava.model.MealWithExceed" %><%--
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
<%--<jsp:useBean id="meal" class="ru.javawebinar.topjava.model.MealWithExceed"/>--%>


<table>
    <tr>
        <td>Description</td>
        <td>Time</td>
        <td>Calories</td>
    </tr>

    <c:forEach items="${meals}" var="m">

        <c:if test="${m.isExceed()}">
            <tr class="red">
        </c:if>
        <c:if test="${!m.isExceed()}">
            <tr class="green">
        </c:if>


        <td>${m.getDescription()}</td>
        <td>${m.getDateTime().toString().replace('T',' ')}</td>
        <td>${m.getCalories()}</td>
        </tr>

    </c:forEach>
</table>

</body>
</html>
