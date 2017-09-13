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
		Bebida bebida = (Bebida) request.getAttribute("bebida");
		List<Categoria> categorias = (ArrayList<Categoria>) request.getAttribute("categorias");
	%>

	<form action="CadastrarBebida" method="post">
		<label for="txtId">Id:</label>
		<input type="text" id="txtId" name="txtId" value=
		<%		
			if(bebida != null) out.print("'"+bebida.getId()+"' readonly>"); 
			else out.print(">"); 		
		%>
		</input>
		
		</br> 
		
		<label for="txtNome">Descrição:</label>
		<input type="text" id="txtNome" name="txtNome" value=
		<%		
			if(bebida != null) 
				out.print("'"+bebida.getNome()+"'>"); 
			else 
				out.print(">"); 		
		%>
		</input>
		
		<br>
		
		<label for="txtFabricante">Fabricante</label>
		<input type="text" id="txtFabricante" name="txtFabricante" value=
		<%		
			if(bebida != null) out.print("'"+bebida.getFabricante()+"'>"); 
			else out.print(">"); 		
		%>
		</input>
		
		<br>
		
		<label for="txtFornecedor">Fornecedor</label>
		<input type="text" id="txtFornecedor" name="txtFornecedor" value=
		<%		
			if(bebida != null) out.print("'"+bebida.getFornecedor()+"'>"); 
			else out.print(">"); 		
		%>
		</input>
		
		<br>
		
		<%		
			if(bebida != null){
				String dtCadastro = ConvertDate.convertDateString(bebida.getDataFabricacao());
				out.print("<label for='txtDataFabricacao'>Data de Cadastro:</label>");
				out.print("<input type='text' id='txtDataFabricacao' name='txtDataFabricacao' value='"+dtCadastro+"' readonly>");
			}
		%>
		
		<br>
		
		<%		
			if(bebida != null){
				String dtCadastro = ConvertDate.convertDateString(bebida.getDataValidade());
				out.print("<label for='txtDataValidade'>Data de Cadastro:</label>");
				out.print("<input type='text' id='txtDataValidade' name='txtDataValidade' value='"+dtCadastro+"' readonly>");
			}
		%>
		
		<br>
		
		<label for="txtTeorAlcool">Teor de alcool</label>
		<input type="text" id="txtTeorAlcool" name="txtTeorAlcool" value=
		<%		
			if(bebida != null) out.print("'"+bebida.getTeorAlcool()+"'>"); 
			else out.print(">"); 		
		%>
		</input>
		
		<label for="txtTeorAlcool">Categoria</label>
		<select id="txtCategoria" name="txtCategoria">
			<%
				for(Categoria categoria : categorias) {
					if(categoria.getId() == bebida.getCategoria().getId())
						out.print("<option value='" + categoria.getId() + "' selected>" + categoria.getDescricao() + " - Dias validade: " + categoria.getDiasValidade() + "</option>");
					else
						out.print("<option value='" + categoria.getId() + "'>" + categoria.getDescricao() + " - Dias validade: " + categoria.getDiasValidade() + "</option>");
				}
			%>
		</select>
		
		<%		
			if(bebida != null){
				String dtCadastro = ConvertDate.convertDateString(bebida.getDtCadastro());
				out.print("<label for='txtDtCadastro'>Data de Cadastro:</label>");
				out.print("<input type='text' id='txtDtCadastro' name='txtDtCadastro' value='"+dtCadastro+"' readonly>");
			}
		%>
		
		<br>
		
		
		<%		
			if(bebida != null) {
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