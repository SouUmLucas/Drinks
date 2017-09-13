package viewhelper;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import core.Resultado;
import model.Categoria;
import model.EntidadeDominio;
import util.ConvertDate;

public class CategoriasVH implements IViewHelper {

	@Override
	public EntidadeDominio getEntidade(HttpServletRequest request) {
		// TODO: use Builder design pattern
		
		String descricao = request.getParameter("txtDescricao");
		String diasValidade = request.getParameter("txtDiasValidade");
		String dtCadastro = request.getParameter("txtDtCadastro");
		String id = request.getParameter("txtId");
		
		Categoria categoria = new Categoria();
		
		if(id != null) {
			categoria.setId(Integer.parseInt(request.getParameter("txtId")));
		}
		if(diasValidade != null) {
			categoria.setDiasValidade(Integer.parseInt(diasValidade));
		}
		
		if(dtCadastro != null && !dtCadastro.trim().equals("")){
			categoria.setDtCadastro(ConvertDate.converteStringDate(dtCadastro));
		}
		
		categoria.setDescricao(descricao);
		
		return categoria;
	}

	@Override
	public void setView(Resultado resultado,
			            HttpServletRequest request,
			            HttpServletResponse response) throws IOException, ServletException {
		RequestDispatcher d = null;
		
		String operacao = request.getParameter("operacao");
		
		if(resultado.getMsg() == null && operacao.equals("LISTAR")) {
			request.setAttribute("resultado", resultado);
			d = request.getRequestDispatcher("FormListarCategorias.jsp");  
		}
		
		if(resultado.getMsg() == null && operacao.equals("INSERIR")) {
			request.setAttribute("categoria", resultado);
			d = request.getRequestDispatcher("NovaCategoria.jsp");  
		}
		
		if(resultado.getMsg() == null && operacao.equals("VISUALIZAR")){
			request.setAttribute("categoria", resultado.getEntidades().get(0));
			d = request.getRequestDispatcher("ConsultaCategoria.jsp");  			
		}
		
		if(resultado.getMsg() == null && operacao.equals("EXCLUIR")){
			request.setAttribute("resultado", null);
			d= request.getRequestDispatcher("index.jsp");  
		}
		
		if(resultado.getMsg() == null){
			if(operacao.equals("SALVAR") || operacao.equals("ALTERAR")){
				d= request.getRequestDispatcher("index.jsp");  	
			}
		}
		
		d.forward(request, response);
		
	}

}
