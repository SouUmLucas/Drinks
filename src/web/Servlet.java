package web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.AlterarCommand;
import command.ConsultarCommand;
import command.ExcluirCommand;
import command.ICommand;
import command.InserirCommand;
import core.Resultado;
import model.EntidadeDominio;
import viewhelper.CategoriasVH;
import viewhelper.IViewHelper;

public class Servlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	Map<String, ICommand> commands;
	Map<String, IViewHelper> vhs;
	
	public Servlet() {
		
		// setup commands
		commands = new HashMap<String, ICommand>();
		commands.put("salvar", new InserirCommand());
		commands.put("consultar", new ConsultarCommand());
		commands.put("alterar", new AlterarCommand());
		commands.put("excluir", new ExcluirCommand());
		
		// setup view helpers
		vhs = new HashMap<String, IViewHelper>();
		vhs.put("/Drinks/CadastrarCategoria", new CategoriasVH());
		
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) {
		doProcessRequest(req, res);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) {
		doProcessRequest(req, res);
	}

	private void doProcessRequest(HttpServletRequest req, HttpServletResponse res) {
		String uri = req.getRequestURI();
		IViewHelper vh = vhs.get(uri);
		EntidadeDominio entidade = vh.getEntidade(req);
		String operacao = req.getParameter("operacao");
		ICommand command = commands.get(operacao);
		Resultado resultado = command.execute(entidade);
		
		// redirect page
	}
}
