<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form action="CadastrarCategoria">
		<h1>Categorias</h1>
		Listar categorias: <input type='submit' id='operacao' name='operacao' value='LISTAR'/><br>
		Nova categorias:   <input type='submit' id='operacao' name='operacao' value='INSERIR'/>
	</form>
	
	<form action="CadastrarBebida">
		<h1>Bebidas</h1>
		Listar bebidas: <input type='submit' id='operacao' name='operacao' value='LISTAR'/><br>
		Cadastrar bebidas:   <input type='submit' id='operacao' name='operacao' value='INSERIR'/>
	</form>
</body>
</html>
