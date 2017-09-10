package fachada;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import core.Resultado;
import model.Categoria;
import model.EntidadeDominio;
import dao.DAOCategorias;
import dao.IDAO;

public class Fachada implements IFachada {
	
	private Map<String, IDAO> daos;
	Resultado resultado;
	
	public Fachada() {
		daos = new HashMap<String, IDAO>();
		
		// put all your daos here!
		daos.put(Categoria.class.getName(), new DAOCategorias());
	}

	@Override
	public Resultado inserir(EntidadeDominio entidade) {
		resultado = new Resultado();
		String nmClasse = entidade.getClass().getName();
		String msg = executarRegras(entidade, "SALVAR");
		
		if(msg == null) {
			try {
				IDAO dao = daos.get(nmClasse);
				dao.inserir(entidade);
				
				List<EntidadeDominio> entidades = new ArrayList<EntidadeDominio>();	
				entidades.add(entidade);
				resultado.setEntidades(entidades);
				
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("Erro ao tentar inserir a categoria");
			}
			
			
		} else {
			resultado.setMsg(msg);
		}
		
		return resultado;
	}

	@Override
	public Resultado alterar(EntidadeDominio entidade) {
		resultado = new Resultado();
		String nmClasse = entidade.getClass().getName();
		String msg = executarRegras(entidade, "ALTERAR");
		
		if(msg == null) {
			
			try {
				IDAO dao = daos.get(nmClasse);
				dao.alterar(entidade);
				List<EntidadeDominio> entidades = new ArrayList<EntidadeDominio>();
				entidades.add(entidade);
				resultado.setEntidades(entidades);
				
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("Erro ao tentar alterar uma categoria");
			}
			
			
		} else {
			resultado.setMsg(msg);
		}
		
		return resultado;
	}

	@Override
	public Resultado excluir(EntidadeDominio entidade) {
		resultado = new Resultado();
		String nmClasse = entidade.getClass().getName();
		String msg = executarRegras(entidade, "EXCLUIR");
		
		if(msg == null) {
			IDAO dao = daos.get(nmClasse);
			
			try {
				dao.deletar(entidade);
				List<EntidadeDominio> entidades = new ArrayList<EntidadeDominio>();
				entidades.add(entidade);
				resultado.setEntidades(entidades);
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("Erro ao tentar excluir um produto");
			}
		}
		
		return resultado;
	}
	
	@Override
	public Resultado consultar(EntidadeDominio entidade) {
		resultado = new Resultado();
		String nmClasse = entidade.getClass().getName();
		
		try {
			IDAO dao = daos.get(nmClasse);
			List<EntidadeDominio> entidades = new ArrayList<EntidadeDominio>();
			entidades.add(dao.consultar(entidade));
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Erro ao tentar consultar por uma categoria");
		}
		
		return resultado;
	}

	@Override
	public Object procurar(EntidadeDominio entidade) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private String executarRegras(EntidadeDominio entidade, String string) {
		// TODO Auto-generated method stub
		return null;
	}

}
