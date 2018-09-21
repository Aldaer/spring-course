<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Booking App</title>
</head>
<body>
<h1>Booking App</h1>

<form action="/user" method="get">
    <p><label for="usermail">User info:</label>
        <select name="email" id="usermail">
            <#list userEmails as email>
                <option>${email}</option>
            </#list>
        </select>
        <input type="submit" value="View"/>
    </p>
</form>
<p><a href="/rooms">Auditoriums</a></p>
<p><a href="/events">Events</a></p>
<div style="border: darkgreen solid 1px; padding: 10px;">
    <h2>Batch upload</h2>
    <form action="/upload" method="post" enctype="multipart/form-data">
        <p><label for="events">Users:</label>
            <input type="file" id="users" name="users" accept="application/json">
        </p>
        <p>
            <label for="events">Events:</label>
            <input type="file" id="events" name="events" accept="application/json">
        </p>
        <button type="submit">Upload</button>
    </form>
</div>
</body>
</html>