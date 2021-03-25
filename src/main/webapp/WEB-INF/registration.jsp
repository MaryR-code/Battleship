<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta content="width=device-width, initial-scale=1" name="viewport">
    <link href="https://www.w3schools.com/w3css/4/w3.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Raleway:400,800,900" rel="stylesheet">
    <title>Battleship - Registration</title>
</head>
<body>
<div class="w3-display-container" style="min-height: 100%">
    <div class="w3-display-middle w3-card" style="width: 30%">
        <form method="post">
            <c:if test="${isPlayerNameIncorrect}">
                <div class="w3-panel w3-pale-red w3-display-container">
                    <span onclick="this.parentElement.style.display='none'" class="w3-button w3-display-topright"><b>&times;</b></span>
                    <h3>Error!</h3>
                    <p>Player name is too short!</p>
                </div>
            </c:if>
            <div class="w3-panel w3-display-container">
                <label for="playerNameInput" class="w3-padding-small">Enter your name:</label>
            </div>
            <input class="w3-input w3-margin w3-border w3-light-grey" type="text" style="width:90%" id="playerNameInput" name="playerName" value="${playerName}">
            <div class="w3-panel w3-display-container" style="height:50px">
                <button type="submit" class="w3-btn w3-large w3-indigo w3-display-bottommiddle">Start</button>
            </div>
        </form>
    </div>
</div>
</body>
</html>
