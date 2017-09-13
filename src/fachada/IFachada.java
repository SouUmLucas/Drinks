package fachada;

import core.Resultado;
import model.EntidadeDominio;

public interface IFachada {
	
	public Resultado inserir(EntidadeDominio entidade);
	public Resultado alterar(EntidadeDominio entidade);
	public Resultado excluir(EntidadeDominio entidade);
	public Resultado consultar(EntidadeDominio entidade);
	public Resultado visualizar(EntidadeDominio entidade);
	public Resultado listar(EntidadeDominio entidade);
	public Object procurar(EntidadeDominio entidade);
	
}
