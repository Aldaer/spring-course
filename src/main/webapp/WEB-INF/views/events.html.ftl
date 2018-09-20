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
<form action="/events" method="post">
    <h2>New event</h2>
    <p>
        <label for="name">Event name:</label>
        <input type="text" name="name" id="name">
        <label for="datetime">Date & Time:</label>
        <input type="datetime-local" name="datetime" id="datetime">
    </p>
    <p>Rate:
        <label for="high">High</label>
        <input type="radio" name="rate" id="high" value="HIGH" checked="checked"/>
        <label for="mid">Mid</label>
        <input type="radio" name="rate" id="mid" value="MID"/>
        <label for="low">Low</label>
        <input type="radio" name="rate" id="low" value="LOW"/>
    </p>
    <p><label for="room">Room:</label>
        <select id="room" name="room">
        <#list rooms as room>
            <option value="${room.name}">${room.name}</option>
        </#list>
        </select>
    </p>
    <button type="submit">Schedule event</button>
</form>
<p><a href="/">Home...</a></p>
</body>
</html>