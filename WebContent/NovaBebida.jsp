<%@page import="util.ConvertDate"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ page
	import="core.Resultado, model.*, java.util.*"%>
	
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>:::: CADASTRO DE FORNECEDOR::::</title>
</head>
<%
	List<Categoria> categorias = (ArrayList<Categoria>) request.getAttribute("categorias");
%>
<body>
	<form action="CadastrarBebida" method="post">		
		<label for="txtNome">Nome:</label>
		<input type="text" id="txtNome" name="txtNome"/>
		</br> 
		
		<label for="txtFabricante">Fabricante:</label>
		<input type="text" id="txtFabricante" name="txtFabricante"/>
		</br> 	
			
		<label for="txtFornecedor">Fornecedor:</label>
		<input type="text" id="txtFornecedor" name="txtFornecedor"/>
		</br> 	
			
		<label for="txtCategoria">Categoria:</label>
		<select id="txtCategoria" name="txtCategoria">
			<%
				for(Categoria categoria: categorias) {
					out.print("<option value='" + categoria.getId() + "'>" + categoria.getDescricao() + " - Dias validade: " + categoria.getDiasValidade() + "</option>");
				}
			%>
		</select>
		</br>
			
		<label for="txtDataFabricacao">Data de fabricação:</label>
		<input type="text" id="txtDataFabricacao" name="txtDataFabricacao"/>
		</br> 
		
		<label for="txtDataValidade">Data de validade:</label>
		<input type="text" id="txtDataValidade" name="txtDataValidade"/>
		</br> 
		
		<label for="txtTeorAlcool">Teor de alcool:</label>
		<input type="text" id="txtTeorAlcool" name="txtTeorAlcool"/>
		</br> 
		
		<input type="submit" id="operacao" name="operacao" value="SALVAR"/>
	
	</form>
</body>
</html>