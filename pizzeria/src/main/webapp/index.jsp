<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
</head>
<body>
	<form method="post" action="UtenteSrv">

		<p>Inserisci user e password:</p>
		
		<label>User </label>
		<input type="text" name="user" required> 
		
		<label>Password</label>
		<input type="password" name="pswd" required> 
		
		<input value="Login" type=submit>

	</form>
	<p style="color: red">${error}</p>
</body>
</html>

