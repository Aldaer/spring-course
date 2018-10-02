<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Booking App</title>
</head>
<body>
<h1>Ticket info</h1>
<h2>User: ${user.name}</h2>
<table border="solid 1px">
    <tr>
        <th>Event</th>
        <th>Date&Time</th>
        <th>Place</th>
        <th>Seats</th>
    </tr>
    <#list tickets as ticket>
    <tr>
        <td>${ticket.event.name}</td>
        <td>${ticket.event.dateTime}</td>
        <td>${ticket.event.auditorium.name}</td>
        <td>${ticket.seats}</td>
    </tr>
    </#list>
</table>
<p><a href="/">Home...</a></p>
</body>
</html>