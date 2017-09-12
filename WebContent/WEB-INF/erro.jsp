<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Erro</title>
</head>
<body>
<div style="margin-left:auto;margin-right:auto;max-width: 500px;
background: #F7F7F7;padding: 25px 15px 25px 10px;
font: 20px Times New Roman; border: 1px solid #E4E4E4;">
<div style="height: 15em; align-items: center; justify-content: center;
text-align:center;">
<h1>ERRO</h1>
<% Exception e = (Exception) request.getAttribute( "erro" ); %>
<h1>
<%=e.getMessage() %>
</h1>
<hr>
<a href="http://localhost:8080/Tarefa_Final5/SistaCad" style="text-decoration: none;
text-align: center;"><button style="margin:auto; display:block;">Voltar</button></a>
</div>
</div>
</body>
</html>

