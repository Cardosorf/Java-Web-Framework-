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
<%@ page import="sv.FormExibir"%>
<body>
<div style="margin-left:auto;margin-right:auto;max-width: 500px;
background: #F7F7F7;padding: 25px 15px 25px 10px;
font: 20px Times New Roman; border: 1px solid #E4E4E4;">
<a href="http://localhost:8080/Tarefa_Final5/SistaCad?&action=logout" style="text-decoration: none;
text-align: center;"><button style="margin:auto; display:block; float: right">Sair</button></a>
<div style="height: 15em; align-items: center; justify-content: center;
text-align:center;">
	<h1>Ficha ${form.entidade}</h1>
	<hr>
	<%
	    FormExibir form = (FormExibir) request.getAttribute( "form" );
	    for( String key : form.getCampos().keySet() ) {
	        out.print( key + ":" + form.getCampos().get( key ) + "<br>" );
	    }
	%>
	<br>
<a href="SistaCad?control=sv.Cadastro&action=exibir&entidade=${form.entidade}&ID=${form.id}&editar=true" style="text-decoration: none;
text-align: center;"><button style="margin:auto; display:block;">Editar ${form.entidade}</button></a>
<br>
<a href="http://localhost:8080/Tarefa_Final5/SistaCad?control=sv.Cadastro&action=listar&entidade=${form.entidade}" style="text-decoration: none;
text-align: center;"><button style="margin:auto; display:block;">Voltar</button></a>
</div>
</div>
</body>
</html>

