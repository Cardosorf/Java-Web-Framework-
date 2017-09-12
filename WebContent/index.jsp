<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login</title>
</head>
<body>
<div style="margin-left:auto;margin-right:auto;max-width: 500px;
background: #F7F7F7;padding: 25px 15px 25px 10px;
font: 20px Times New Roman; border: 1px solid #E4E4E4;">

<h1 style="text-align:center;">Login</h1>
<p style="text-align:center;">${accessError}</p>
<p style="text-align:center;">${numberAttempts}</p>
<br>
<div style="height: 10em; display: flex; align-items: center; justify-content: center;
text-align:center;">
    <form action="Validate" method="post">
  		Usuario: <input type="text" name="username" style="height:20px; width: 120px;
        clear: both;">
        <br>
        <br>
  		Senha: <input type="password" name="password" style="height:20px; width: 120px;
        clear: both;">
  		<br>
  		<br>
  		<input type="submit" value="Enviar">
	</form>
</div>
<br>
<br>
</div>
</body>
</html>
