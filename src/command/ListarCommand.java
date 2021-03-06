package command;

import core.Resultado;
import model.EntidadeDominio;

public class ListarCommand extends AbstractCommand {

	@Override
	public Resultado execute(EntidadeDominio entidade) {
		return fachada.listar(entidade);
	}

}
