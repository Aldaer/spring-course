<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Booking App</title>
</head>
<body>
<h1>Events</h1>
<table border="solid 1px">
    <tr>
        <th>Name</th>
        <th>Rate</th>
        <th>Base price</th>
        <th>When</th>
        <th>Where</th>
    </tr>
    <#list events as event>
    <tr>
        <td>${event.name}</td>
        <td>${event.rate}</td>
        <td>${event.basePrice}</td>
        <td>${event.dateTime}</td>
        <td><a href="/rooms?roomName=${event.auditorium.name}">${event.auditorium.name}</a></td>
    </tr>
    </#list>
</table>
<p><a href="/">Home...</a></p>
</body>
</html>