<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Booking App</title>
    <link rel="shortcut icon" href="#" />
</head>
<body>
<h1>Booking App</h1>

<a href="/user?email=${user.email}">Welcome, ${user.name}!</a>

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
<a href="/logout">Logout</a>
</body>
</html>