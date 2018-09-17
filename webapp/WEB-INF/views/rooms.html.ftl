<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Booking App</title>
</head>
<body>
<h1>Auditoriums</h1>
<h2>(click to view info)</h2>
<ul>
    <#list roomNames as roomName>
        <li><a href="/rooms?roomName=${roomName}">${roomName}</a></li>
    </#list>
</ul>
<p><a href="/">Home...</a></p>
</body>
</html>