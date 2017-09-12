<%@page
	import="com.sun.org.apache.xalan.internal.xsltc.runtime.Parameter"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Lista de ${form.entidade}</title>
</head>
<%@ page import="sv.FormListar"%>
<body>
<div style="margin-left:auto;margin-right:auto;max-width: 500px;
background: #F7F7F7;padding: 25px 15px 25px 10px;
font: 20px Times New Roman; border: 1px solid #E4E4E4;">
<a href="http://localhost:8080/Tarefa_Final5/SistaCad?&action=logout" style="text-decoration: none;
text-align: center;"><button style="margin:auto; display:block; float: right">Sair</button></a>
<div style="height: 15em; align-items: center; justify-content: center;
text-align:center;">

	<h1>Lista de ${form.entidade}</h1>
	<hr>
	<%
	    FormListar form = (FormListar) request.getAttribute( "form" );

	    for( int i = 0; i < form.getSize(); i++ )
	        out.print( form.getNome().get( i ) + form.getEntidade() + "\">" + "<button>Deletar</button></a>" + "<br>" );
	%>
	<br>
	<br>
<a href="http://localhost:8080/Tarefa_Final5/SistaCad?control=sv.Cadastro&action=cadastrar&entidade=${form.entidade}" style="text-decoration: none;
text-align: center;"><button style="margin:auto; display:block;">Cadastrar</button></a><br>
<a href="http://localhost:8080/Tarefa_Final5/SistaCad" style="text-decoration: none;
text-align: center;"><button style="margin:auto; display:block;">Voltar</button></a>
</div>
</div>
</body>
</html>

