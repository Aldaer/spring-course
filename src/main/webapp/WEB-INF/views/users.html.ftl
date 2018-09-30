<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Booking App</title>
</head>
<body>
<h1>User info</h1>
<h2>(click to view booked tickets)</h2>
<ul>
    <li><a href="/tickets?email=${user.email}">${user.name} &lt;${user.email}&gt;</a></li>
</ul>
<p>
    Account balance: ${balance?string.currency}
</p>
<p><a href="/">Home...</a></p>
</body>
</html>