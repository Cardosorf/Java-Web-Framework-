<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
 <%@ page  import="sv.FormAluno" %>
 <%@ page  import="sv.FormExibir" %>
 <%@ page  import="sv.FormProfessor" %>
 <%@ page  import="sv.FormDisciplina" %>
 <%@ page  import="sv.FormTurma" %>
<body>
<div style="margin-left:auto;margin-right:auto;max-width: 500px;
background: #F7F7F7;padding: 25px 15px 25px 10px;
font: 20px Times New Roman; border: 1px solid #E4E4E4;">
<div style="height: 15em; align-items: center; justify-content: center;
text-align:center;">

<%
if(request.getAttribute("form").getClass().getName().equals("sv.FormAluno")){
	FormAluno form = (FormAluno) request.getAttribute( "form" );
	if( form == null )
	  form = new FormAluno();
	out.print("<h1>OK, aluno(a) " + form.getNome() + " foi incluido.</h1>");
}

if(request.getAttribute("form").getClass().getName().equals("sv.FormDisciplina")){
	FormDisciplina form = (FormDisciplina) request.getAttribute( "form" );
	if( form == null )
	  form = new FormDisciplina();
	out.print("<h1>OK, a disciplina " + form.getNome() + " foi incluida.</h1>");
}

if(request.getAttribute("form").getClass().getName().equals("sv.FormTurma")){
	FormTurma form = (FormTurma) request.getAttribute( "form" );
	if( form == null )
	  form = new FormTurma();
	out.print("<h1>OK, a turma " + "\"" + form.getNome() + "\"" + " foi incluida.</h1>");
}

if(request.getAttribute("form").getClass().getName().equals("sv.FormProfessor")){
	FormProfessor form = (FormProfessor) request.getAttribute( "form" );
	if( form == null )
	  form = new FormProfessor();
	out.print("<h1>OK, o professor " + form.getNome() + " foi incluido.</h1>");
}

if(request.getAttribute("form").getClass().getName().equals("sv.FormExibir")){
	FormExibir form = (FormExibir) request.getAttribute( "form" );
	if( form == null )
	  form = new FormExibir();
	out.print("<h1>OK, " + form.getEntidade().toLowerCase() + " " + "\"" + form.getCampos().get("Nome") + "\""+ " foi deletado(a).</h1>");
}

%>

<hr>
<a href="http://localhost:8080/Tarefa_Final5/SistaCad?control=sv.Cadastro&action=listar&entidade=${form.entidade}" style="text-decoration: none;
text-align: center;"><button style="margin:auto; display:block;">Voltar</button></a>
</div>
</div>
</body>
</html>

<!-- 
Right-click on your project in Eclipse, select Properties
Click on Java Build path on the left
Click source tab on the right
Click "Add Folder" button and add your source folder (/src or wherever you moved it to) - só se já não estiver lá.
Ensure Allow output folders for source folders is checked below
Under newly added source path select output folder and point it 
to /WEB-INF/classes or other location of your choice (eu usei o /WEB-INF/lib).
 -->