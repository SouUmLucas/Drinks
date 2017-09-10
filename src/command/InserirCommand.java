package command;

import core.Resultado;
import model.EntidadeDominio;

public class InserirCommand extends AbstractCommand {

	@Override
	public Resultado execute(EntidadeDominio entidade) {
		return fachada.inserir(entidade);
	}

}
