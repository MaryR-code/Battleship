<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta content="width=device-width, initial-scale=1" name="viewport">
    <link href="https://www.w3schools.com/w3css/4/w3.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Raleway:400,800,900" rel="stylesheet">
    <title>Battleship - Setting up</title>
    <style>
        table, td {
            border: 1px solid black;
            text-align: center;
            border-collapse: collapse;
        }

        td {
            width: 2em;
            height: 2em;
        }
        td.SHIP {
            background-color: black;
        }
    </style>
</head>
<body>
<div class="w3-display-container" style="min-height: 100%">
    <div class="w3-display-middle w3-card">
        <div class="w3-container w3-padding-large w3-center">
            <form method="post">
                <table>
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
                                <td class="${player.playerField[addr]}">
                                    <input
                                        type="checkbox"
                                        name="addr"
                                        value="${addr}"
                                    <c:if test="${player.playerField[addr] eq 'SHIP'}">checked</c:if>
                                    ></td>
                            </c:forEach>
                        </tr>
                    </c:forEach>
                </table>
                <button type="submit">Ready</button>
            </form>
        </div>
    </div>
</div>
</body>
</html>
