<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta content="width=device-width, initial-scale=1" name="viewport">
    <link href="https://www.w3schools.com/w3css/4/w3.css" rel="stylesheet">
    <link href="css/main.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Raleway:400,800,900" rel="stylesheet">
    <title>Battleship - Win</title>
</head>
<body>
<div class="w3-display-topmiddle">
    <img alt="win" src="img/winner.png">
</div>
<div class="w3-display-container" style="min-height: 100%">
    <div class="w3-display-middle w3-card" style="width: 50%">
        <form class="w3-container w3-padding-large w3-center" method="post">
            <h1>Congratulations!</h1>
            <h2>${player.name}, you have WON!</h2>
            <h3 class="w3-padding-large">Do you want to play again?</h3>
            <button type="submit" class="w3-btn w3-large w3-green">Start Over</button>
        </form>
    </div>
</div>
</body>
</html>
