<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>:::: CADASTRO DE CATEGORIA::::</title>
</head>
<body>
	<form action="CadastrarCategoria" method="post">		
		<label for="txtNome">Descrição:</label>
		<input type="text" id="txtDescricao" name="txtDescricao"/>
		</br> 
		
		<label for="txtCpf">Dias Validade:</label>
		<input type="text" id="txtDiasValidade" name="txtDiasValidade"/>
		</br> 		
		
		
		<input type="submit" id="operacao" name="operacao" value="SALVAR"/>
	
	</form>
	<a href="/Drinks/index.jsp">Início</a>
</body>
</html>