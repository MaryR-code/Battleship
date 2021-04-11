<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <link rel="stylesheet" href="css/main.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Syne+Mono&display=swap">
    <title>Result</title>
</head>

<body>
<div class="w3-display-topleft w3-center" style="width:25%">
    <h1 class="w3-large w3-text-dark-grey">Results table:</h1>
    <table class="w3-table-all w3-margin">
     <tr>
            <th>Name</th>
            <th>Winner</th>
            <th>Shots</th>
        </tr>
        <c:forEach items="${rec}" var="rec">
            <tr>
                <td>${rec.name}</td>
                <td>${rec.win}</td>
                <td>${rec.shots}</td>
            </tr>
        </c:forEach>
    </table>
</div>

</body>
</html>
