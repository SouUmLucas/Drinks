package dao;

import java.sql.SQLException;
import java.util.List;

import model.EntidadeDominio;

public interface IDAO {
	
	public EntidadeDominio inserir(EntidadeDominio entidade) throws SQLException;
	public EntidadeDominio alterar(EntidadeDominio entidade) throws SQLException;
	public void deletar(EntidadeDominio entidade) throws SQLException;
	public EntidadeDominio consultar(EntidadeDominio entidade) throws SQLException;
	public void procurar(EntidadeDominio entidade) throws SQLException;
	public List<EntidadeDominio> listar(EntidadeDominio entidade) throws SQLException;
	
}
