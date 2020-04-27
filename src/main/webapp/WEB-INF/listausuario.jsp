<%@page import="br.com.projeto.ce.principal.persistencia.entidade.Usuario"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
 <head>
   <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
   <title>Lista de Usuários</title>
   
   <script type="text/javascript">
   function confirmaExclusao(id) {
	   if(window.confirm('Tem certeza que deseja excluir?')){
		   location.href="usucontroller.do?acao=excluir&id="+id;
	   }
	
}
   function cadastrar() {
   	location.href='usucontroller.do?acao=cadastrar';	
}
  
   </script>
 </head>
 <body> 
 
  <%@include file="menu.jsp" %>
 
  <%  
  List<Usuario> lista = (List<Usuario>)request.getAttribute("lista");
  %>
  
  <table border=1>
  <tr> <th> id </th> <th> nome </th> <th> Ação </th> </tr>
  
  <% for(Usuario usuario: lista){ %>
  <tr>
    <td><% out.print( usuario.getId() );%></td>
    <td><%=usuario.getNome()%></td>
    <td><a href="usucontroller.do?acao=alterar&id=<%=usuario.getId()%>"> Alterar </a>
     | <a href="javascript:confirmaExclusao(<%=usuario.getId()%>)"> Excluir </a> </td>   
   </tr> 	
  <%} %>
    
</table>

  <input type="button" onclick="javascript:cadastrar()" value="Cadastrar">
</body>
</html>