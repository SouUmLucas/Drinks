<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cadastro de Categoria</title>
<style>
form{
margin-left: 10%;
width: 100%;
}
</style>
</head>
<body>

<form action="CadastrarCategoria">

	<h1>Cadastro de Categoria</h1>
	Descricação: <input type="text" name="txtDescricao" >
	<br/>
	<br>
	Quantidade de dias de validade: <input type="text" name="txtDiasValidade" >
	<br/>
	
	<input type="submit" value="salvar" name="operacao">
</form>	
</body>
</html>