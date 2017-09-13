package web;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.AlterarCommand;
import command.ConsultarCommand;
import command.ExcluirCommand;
import command.ICommand;
import command.InserirCommand;
import command.ListarCommand;
import command.VisualizarCommand;
import core.Resultado;
import model.EntidadeDominio;
import viewhelper.BebidasVH;
import viewhelper.CategoriasVH;
import viewhelper.IViewHelper;

public class Servlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	Map<String, ICommand> commands;
	Map<String, IViewHelper> vhs;
	
	public Servlet() {
		
		// setup commands
		commands = new HashMap<String, ICommand>();
		commands.put("SALVAR", new InserirCommand());
		commands.put("CONSULTAR", new ConsultarCommand());
		commands.put("VISUALIZAR", new VisualizarCommand());
		commands.put("ALTERAR", new AlterarCommand());
		commands.put("EXCLUIR", new ExcluirCommand());
		commands.put("LISTAR", new ListarCommand());
		
		// setup view helpers
		vhs = new HashMap<String, IViewHelper>();
		vhs.put("/Drinks/CadastrarCategoria", new CategoriasVH());
		vhs.put("/Drinks/CadastrarBebida", new BebidasVH());
		
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		doProcessRequest(req, res);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		doProcessRequest(req, res);
	}

	private void doProcessRequest(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		String uri = req.getRequestURI();
		IViewHelper vh = vhs.get(uri);
		EntidadeDominio entidade = vh.getEntidade(req);
		String operacao = req.getParameter("operacao");
		ICommand command = commands.get(operacao);
		
		if(operacao.equals("INSERIR")) {
			vh.setView(new Resultado(), req, res);
		} else {
			Resultado resultado = command.execute(entidade);
			vh.setView(resultado, req, res);
		}

	}
}
