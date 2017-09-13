package viewhelper;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import core.Resultado;
import dao.DAOCategorias;
import model.Bebida;
import model.Categoria;
import model.EntidadeDominio;
import util.ConvertDate;

public class BebidasVH implements IViewHelper {

	@Override
	public EntidadeDominio getEntidade(HttpServletRequest request) {
		// TODO: use Builder design pattern
		
		String nome = request.getParameter("txtNome");
		String fabricante = request.getParameter("txtFabricante");
		String fornecedor = request.getParameter("txtFornecedor");
		String categoria = request.getParameter("txtCategoria");
		String dataFabricacao = request.getParameter("txtDataFabricacao");
		String dataValidade = request.getParameter("txtDataValidade");
		String teorAlcool = request.getParameter("txtTeorAlcool");
		String dtCadastro = request.getParameter("txtDtCadastro");
		String id = request.getParameter("txtId");
		
		Bebida bebida = new Bebida();
		
		if(id != null) {
			bebida.setId(Integer.parseInt(request.getParameter("txtId")));
		}
		
		if(dtCadastro != null && !dtCadastro.trim().equals("")) {
			bebida.setDtCadastro(ConvertDate.converteStringDate(dtCadastro));
		}
		
		if(dataFabricacao != null && !dataFabricacao.trim().equals("")) {
			bebida.setDataFabricacao(ConvertDate.converteStringDate(dataFabricacao));
		}
		
		if(dataValidade != null && !dataValidade.trim().equals("")) {
			bebida.setDataValidade(ConvertDate.converteStringDate(dataValidade));
		}
		
		if(teorAlcool != null && !teorAlcool.trim().equals("")) {
			bebida.setTeorAlcool(Double.parseDouble(teorAlcool));
		}
		
		if(categoria != null && !categoria.trim().equals("")) {
			Categoria c = new Categoria();
			c.setId(Integer.parseInt(categoria));
			bebida.setCategoria(c);
		}
		
		bebida.setNome(nome);
		bebida.setFabricante(fabricante);
		bebida.setFornecedor(fornecedor);
		
		return bebida;
	}

	@Override
	public void setView(Resultado resultado,
			            HttpServletRequest request,
			            HttpServletResponse response) throws IOException, ServletException {
		RequestDispatcher d = null;
		
		String operacao = request.getParameter("operacao");
		
		if(resultado.getMsg() == null && operacao.equals("LISTAR")) {
			request.setAttribute("resultado", resultado);
			d = request.getRequestDispatcher("FormListarBebidas.jsp");  
		}
		
		if(resultado.getMsg() == null && operacao.equals("INSERIR")) {
			DAOCategorias daoCategorias = new DAOCategorias();
			List<Categoria> categorias = new ArrayList<Categoria>();
			
			try {
				List<EntidadeDominio> entidades = daoCategorias.listar(null);
				for(EntidadeDominio entidade: entidades) {
					categorias.add((Categoria) entidade);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			request.setAttribute("categorias", categorias);
			d = request.getRequestDispatcher("NovaBebida.jsp");  
		}
		
		if(resultado.getMsg() == null && operacao.equals("VISUALIZAR")){
			DAOCategorias daoCategorias = new DAOCategorias();
			List<Categoria> categorias = new ArrayList<Categoria>();
			
			try {
				List<EntidadeDominio> entidades = daoCategorias.listar(null);
				for(EntidadeDominio entidade: entidades) {
					categorias.add((Categoria) entidade);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			
			request.setAttribute("categorias", categorias);
			request.setAttribute("bebida", resultado.getEntidades().get(0));
			d = request.getRequestDispatcher("ConsultaBebida.jsp");  			
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
		
		if(resultado.getMsg() != null) {
			if(operacao.equals("SALVAR") || operacao.equals("ALTERAR")){
				request.setAttribute("resultado", resultado);
				d= request.getRequestDispatcher("index.jsp");  	
			}
		}
		
		d.forward(request, response);
		
	}

}
