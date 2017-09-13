package command;

import core.Resultado;
import model.EntidadeDominio;

public class VisualizarCommand extends AbstractCommand {

	@Override
	public Resultado execute(EntidadeDominio entidade) {
		return fachada.visualizar(entidade);
	}

}
