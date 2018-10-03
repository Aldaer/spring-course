<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Book tickets</title>
</head>
<body>
<h1>Book tickets</h1>
<p>Logged in user: ${currentUser.name}</p>
<p>Current balance: ${balance}</p>
<p>Selected event: <b>${event.name}</b> in <b>${event.auditorium.name}</b> on <b>${event.dateTime}</b></p>

<form style="border: solid; padding: 10px" >
<#if seats??>
    <p>Selected seats: ${seats}</p>
    <p>Total price: <span <#if balance < price>style="color: red"</#if>>${price}</span></p>
    <input type="submit" value="Confirm" formmethod="post">
<#else>
    <label for="seats">Enter desired seats: </label><input type="text" name="seats" id="seats">
    <input type="submit" value="Select">
</#if>
    <input type="hidden" name="eventId" value="${event.id}">
</form>

<p><a href="/">Home...</a></p>
</body>
</html>