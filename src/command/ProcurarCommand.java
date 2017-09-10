package command;

import core.Resultado;
import model.EntidadeDominio;

public class ProcurarCommand extends AbstractCommand {

	@Override
	public Resultado execute(EntidadeDominio entidade) {
		return (Resultado) fachada.procurar(entidade);
	}

}
