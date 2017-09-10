package viewhelper;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import core.Resultado;
import model.Categoria;
import model.EntidadeDominio;

public class CategoriasVH implements IViewHelper {

	@Override
	public EntidadeDominio getEntidade(HttpServletRequest request) {
		// TODO: use Builder design pattern
		
		String descricao = request.getParameter("txtDescricao");
		int diasValidade = Integer.parseInt(request.getParameter("txtDiasValidade"));
		String id = request.getParameter("txtId");
		
		Categoria categoria = new Categoria();
		
		if(id != null) {
			categoria.setId(Integer.parseInt(request.getParameter("txtId")));
		}
		
		categoria.setDescricao(descricao);
		categoria.setDiasValidade(diasValidade);
		
		return categoria;
	}

	@Override
	public void setView(Resultado resultado,
			            HttpServletRequest request,
			            HttpServletResponse response) throws IOException, ServletException {
		
		
	}

}
