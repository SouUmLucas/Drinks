package fachada;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import core.Resultado;
import model.Bebida;
import model.Categoria;
import model.EntidadeDominio;
import strategy.IStrategy;
import strategy.ValidadorDataValidade;
import strategy.ValidadorTeorAlcool;
import dao.DAOBebidas;
import dao.DAOCategorias;
import dao.IDAO;

public class Fachada implements IFachada {
	
	private Map<String, IDAO> daos;
	private Map<String, List<IStrategy>> rnsBebida;
	Resultado resultado;
	
	public Fachada() {
		daos = new HashMap<String, IDAO>();
		rnsBebida = new HashMap<String, List<IStrategy>>();
		
		// put all your daos here!
		daos.put(Categoria.class.getName(), new DAOCategorias());
		daos.put(Bebida.class.getName(), new DAOBebidas());
		
		List<IStrategy> rnsSalvarBebida = new ArrayList<IStrategy>();
		rnsSalvarBebida.add(new ValidadorDataValidade());
		rnsSalvarBebida.add(new ValidadorTeorAlcool());
		
		rnsBebida.put("SALVAR", rnsSalvarBebida);
	}

	@Override
	public Resultado inserir(EntidadeDominio entidade) {
		resultado = new Resultado();
		String nmClasse = entidade.getClass().getName();
		String msg = executarRegras(entidade, "SALVAR");
		
		if(msg == null) {
			try {
				java.util.Date utilDate = new java.util.Date();
				entidade.setDtCadastro(new java.sql.Date(utilDate.getTime()));
				IDAO dao = daos.get(nmClasse);
				List<EntidadeDominio> entidades = new ArrayList<EntidadeDominio>();	
				entidades.add(dao.inserir(entidade));
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
				List<EntidadeDominio> entidades = new ArrayList<EntidadeDominio>();
				entidades.add(dao.alterar(entidade));
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
		List<EntidadeDominio> entidades = new ArrayList<EntidadeDominio>();
		String nmClasse = entidade.getClass().getName();
		
		try {
			IDAO dao = daos.get(nmClasse);
			entidades.add(dao.consultar(entidade));
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Erro ao tentar consultar por uma categoria");
		}
		
		resultado.setEntidades(entidades);
		
		return resultado;
	}
	
	@Override
	public Resultado visualizar(EntidadeDominio entidade) {
		resultado = new Resultado();
		List<EntidadeDominio> entidades = new ArrayList<EntidadeDominio>();
		String nmClasse = entidade.getClass().getName();
		
		try {
			IDAO dao = daos.get(nmClasse);
			entidades.add(dao.consultar(entidade));
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Erro ao tentar consultar por uma categoria");
		}
		
		resultado.setEntidades(entidades);
		
		return resultado;
	}

	@Override
	public Resultado listar(EntidadeDominio entidade) {
		resultado = new Resultado();
		List<EntidadeDominio> entidades = new ArrayList<EntidadeDominio>();
		String nmClasse = entidade.getClass().getName();
		
		try {
			IDAO dao = daos.get(nmClasse);
			resultado.setEntidades(dao.listar(entidade));
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
	
	private String executarRegras(EntidadeDominio entidade, String operacao) {
		List<IStrategy> strategies = rnsBebida.get(operacao);
		
		StringBuilder msg = new StringBuilder();
		
		if(strategies != null) {
			for(IStrategy strategy : strategies) {
				String m = strategy.processar(entidade);
				if(m != null) {
					msg.append(m);
					msg.append("\n");
				}
			}
		}
		
		if(msg.length()>0)
			return msg.toString();
		else
			return null;
	}
}
