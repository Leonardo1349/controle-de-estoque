<!DOCTYPE html>
<%@page import="br.com.projeto.ce.principal.persistencia.entidade.Usuario"%>
<html>

<head>
<meta charset="UTF-8">
<title>Projeto Web</title>

</head>

<body>

<%@include file="menu.jsp" %>

<% 
Usuario usuario = (Usuario)request.getAttribute("usuario");
%>
 <form action="usucontroller.do" method="post">
   
   ID    <input type="number" name="id" value="<%=usuario.getId()%>">
   Nome: <input type="text" name="nome" value="<%=usuario.getNome()%>"/>
   Login: <input type="text" name="login" value="<%=usuario.getLogin()%>"/>
   Senha: <input type="text" name="senha" value="<%=usuario.getSenha()%>"/>
   
   <input type="submit" value="Salvar"> 

 </form>

</body>

</html>