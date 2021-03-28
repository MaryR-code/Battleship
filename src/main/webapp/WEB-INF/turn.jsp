<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta content="width=device-width, initial-scale=1" name="viewport">
    <link href="https://www.w3schools.com/w3css/4/w3.css" rel="stylesheet">
    <link href="css/main.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Raleway:400,800,900" rel="stylesheet">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <title>Battleship - Turn</title>
</head>
<body>
<form class="w3-container w3-padding-large w3-center" method="post">
    <h1>${player.name}, please select cell to fire</h1>
    <table class="field">
        <tr>
            <td></td>
            <c:forEach items="A,B,C,D,E,F,G,H,I,J" var="col">
                <td>${col}</td>
            </c:forEach>
        </tr>
        <c:forEach items="1,2,3,4,5,6,7,8,9,10" var="row">
            <tr>
                <td>${row}</td>
                <c:forEach items="A,B,C,D,E,F,G,H,I,J" var="col">
                    <c:set var="addr" value="${col}${row}"/>
                    <td class="${player.opponentView[addr]}"><input type="radio" name="addr" value="${addr}"></td>
                </c:forEach>
            </tr>
        </c:forEach>
    </table>
    <table class="field">
        <tr>
            <td></td>
            <c:forEach items="A,B,C,D,E,F,G,H,I,J" var="col">
                <td>${col}</td>
            </c:forEach>
        </tr>
        <c:forEach items="1,2,3,4,5,6,7,8,9,10" var="row">
            <tr>
                <td>${row}</td>
                <c:forEach items="A,B,C,D,E,F,G,H,I,J" var="col">
                    <c:set var="addr" value="${col}${row}"/>
                    <td class="${player.playerField[addr]}"></td>
                </c:forEach>
            </tr>
        </c:forEach>
    </table>
    <button type="submit" class="w3-button w3-green w3-margin-top">Fire</button>
    <c:forEach items="${player.history}" var="msg">
        <p>${msg}</p>
    </c:forEach>
</form>
</body>
</html>
