package command;

import core.Resultado;
import model.EntidadeDominio;

public interface ICommand {
	public Resultado execute(EntidadeDominio entidade);
}
