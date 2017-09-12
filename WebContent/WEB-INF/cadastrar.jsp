<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<%@ page import="sv.FormCampos"%>
<%@ page import="sv.DisciplinaDAO"%>
<%@ page import="java.util.TreeMap" %>
<%@ page import="java.util.Map"%>
<%@ page import="sv.Disciplina"%>
<body>
<div style="margin-left:auto;margin-right:auto;max-width: 500px;
background: #F7F7F7;padding: 25px 15px 25px 10px;
font: 20px Times New Roman; border: 1px solid #E4E4E4;">
<a href="http://localhost:8080/Tarefa_Final5/SistaCad?&action=logout" style="text-decoration: none;
text-align: center;"><button style="margin:auto; display:block; float: right">Sair</button></a>
<div style="height: 15em; align-items: center; justify-content: center;
text-align:center;">
<h1>Adicionar ${form.entidade}</h1>
	<form
		action="SistaCad?control=sv.Cadastro${form.entidade}&action=adicionar"
		method="post">
		<%
		Map<Integer, Disciplina> map = new TreeMap<Integer, Disciplina>();
		map = DisciplinaDAO.getDisciplinas();
		FormCampos form2 = (FormCampos) request.getAttribute( "form" );
	    for( String key : form2.getCampos().keySet() ) {
	        out.print( "<label>" + key + " " + "</label>" );
	        if(key.equals("Disciplina")){
	        	out.print("<select name=\"Disciplina\">");
	        	for(Integer x: map.keySet()){
	        		out.print( "<option name=\"" + map.get(x) + "\"" + " value=\"" + map.get(x) + "\"/>"+ map.get(x) + "</option><br>" );
	        	}
	        	out.print("</select><br>");
	        }
	        else
	        out.print( "<input type=\"text\" name=\"" + key + "\" value=\"" + "\"/><br>" );
	    }
		%>
		<br>
		<button type="submit">Adicionar</button>
		<br>
	</form>
<a href="http://localhost:8080/Tarefa_Final5/SistaCad?control=sv.Cadastro&action=listar&entidade=${form.entidade}" style="text-decoration: none;
text-align: center;"><button style="margin:auto; display:block;">Cancelar</button></a>
</div>
</div>
</body>
</html>