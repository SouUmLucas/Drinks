<%@page import="util.ConvertDate"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ page
	import="core.Resultado, model.*, java.util.*"%>
	

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>:::: CADASTRO DE PRODUTO::::</title>
</head>
<body>

<%
		Categoria produto = (Categoria) request.getAttribute("categoria");
	%>

	<form action="CadastrarCategoria" method="post">
		<label for="txtId">Id:</label>
		<input type="text" id="txtId" name="txtId" value=
		<%		
			if(produto != null) out.print("'"+produto.getId()+"' readonly>"); 
			else out.print(">"); 		
		%>
		</input>
		
		</br> 
		
		<label for="txtDescricao">Descrição:</label>
		<input type="text" id="txtDescricao" name="txtDescricao" value=
		<%		
			if(produto != null) 
				out.print("'"+produto.getDescricao()+"'>"); 
			else 
				out.print(">"); 		
		%>
		</input>
		
		<label for="txtQtd">Dias validade:</label>
		<input type="text" id="txtDiasValidade" name="txtDiasValidade" value=
		<%		
			if(produto != null) out.print("'"+produto.getDiasValidade()+"'>"); 
			else out.print(">"); 		
		%>
		</input>
		
		
		
		<%		
			if(produto != null){
				String dtCadastro = ConvertDate.convertDateString(produto.getDtCadastro());
				out.print("<label for='txtDtCadastro'>Data de Cadastro:</label>");
				out.print("<input type='text' id='txtDtCadastro' name='txtDtCadastro' value='"+dtCadastro+"' readonly>");
			}
		%>
		
		</input>
		
		
		<%		
			if(produto != null) {
				out.print("<input type='submit' id='operacao' name='operacao' value='ALTERAR'/>");	
				out.print("<input type='submit' id='operacao' name='operacao' value='EXCLUIR'/>");	
			}else{
				out.print("<input type='submit' id='operacao' name='operacao' value='SALVAR'/>");
			}
				
		%>
		
	
	</form>
	<a href="/Drinks/index.jsp">Início</a>
</body>
</html>