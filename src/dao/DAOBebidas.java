package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import model.Bebida;
import model.Categoria;
import model.EntidadeDominio;

public class DAOBebidas implements IDAO {

	@Override
	public EntidadeDominio inserir(EntidadeDominio entidade) throws SQLException {
		Bebida bebida = (Bebida) entidade;
		String sql = "INSERT INTO public.bebidas( "+
                     "nome, fabricante, fornecedor, fk_categoria, data_fabricacao, "+ 
                     "data_validade, teor_alcool, dtcadastro) "+
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
		try {
			
			Connection conn = BDConnection.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, bebida.getNome());
			ps.setString(2, bebida.getFabricante());
			ps.setString(3, bebida.getFornecedor());
			ps.setInt(4, bebida.getCategoria().getId());
			
			Timestamp dataFabricacao = new Timestamp(bebida.getDataFabricacao().getTime());
			Timestamp dataValidade = new Timestamp(bebida.getDataValidade().getTime());
			
			ps.setTimestamp(5, dataFabricacao);
			ps.setTimestamp(6, dataValidade);
			ps.setDouble(7, bebida.getTeorAlcool());
			
			Timestamp time = new Timestamp(bebida.getDtCadastro().getTime());
			ps.setTimestamp(8, time);
			
			if(ps.executeUpdate() > 0) {
				ResultSet generatedkey = ps.getGeneratedKeys();
				if(generatedkey.next()) {
					bebida.setId(generatedkey.getInt(1));
				}
			}
			ps.close();
			conn.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Erro ao inserir a bebida");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("Erro ao instanciar a classe do PostgreSQL");
		}
		
		return bebida;
	}

	@Override
	public EntidadeDominio alterar(EntidadeDominio entidade) throws SQLException {
		Bebida bebida = (Bebida) entidade;
		String sql = "UPDATE public.bebidas "+
                     "SET nome = ?, fabricante = ?, fornecedor = ?, fk_categoria = ?, data_fabricacao = ?, " +
                     "data_validade = ?, teor_alcool = ?, dtcadastro = ? "+
                     "WHERE id = ?;";
		
		try {
			
			Connection conn = BDConnection.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, bebida.getNome());
			ps.setString(2, bebida.getFabricante());
			ps.setString(3, bebida.getFornecedor());
			ps.setInt(4, bebida.getCategoria().getId());
			
			Timestamp dataFabricacao = new Timestamp(bebida.getDataFabricacao().getTime());
			Timestamp dataValidade = new Timestamp(bebida.getDataValidade().getTime());
			
			ps.setTimestamp(5, dataFabricacao);
			ps.setTimestamp(6, dataValidade);
			ps.setDouble(7, bebida.getTeorAlcool());
			
			Timestamp time = new Timestamp(bebida.getDtCadastro().getTime());
			ps.setTimestamp(8, time);
			
			ps.setInt(9, bebida.getId());
			
			if(ps.executeUpdate() > 0) {
				ResultSet generatedkey = ps.getGeneratedKeys();
				if(generatedkey.next()) {
					bebida.setId(generatedkey.getInt(1));
				}
			}
			
			ps.close();
			conn.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Erro ao inserir a bebida");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("Erro ao instanciar a classe do PostgreSQL");
		}
		
		return bebida;
	}

	@Override
	public void deletar(EntidadeDominio entidade) throws SQLException {

		Bebida bebida = (Bebida) entidade;
		String sql = "DELETE FROM bebidas WHERE id = ?";
		
		try {
			
			Connection conn = BDConnection.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, bebida.getId());
			
			ps.executeUpdate();
			
			ps.close();
			conn.close();
			
		} catch(SQLException e) {
			e.printStackTrace();
			System.out.println("Erro ao apagar a bebida");
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("Erro ao instanciar a classe do PostgreSQL");
		}
		
	}

	@Override
	public EntidadeDominio consultar(EntidadeDominio entidade) throws SQLException {
		Bebida bebida = (Bebida) entidade;
		String sql = "SELECT bebidas.id, nome, fabricante, fornecedor, data_fabricacao, "+
                     "data_validade, teor_alcool, bebidas.dtcadastro, "+
				     "categorias.id, categorias.descricao, categorias.diasvalidade "+
                     "FROM public.bebidas " +
                     "JOIN categorias ON categorias.id = bebidas.fk_categoria "+
                     "WHERE bebidas.id = ?";
		Bebida b = new Bebida();
		try {
			Connection conn = BDConnection.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, bebida.getId());
			
			ResultSet result = ps.executeQuery();
			
			if(result.next()) {
				b.setId(result.getInt(1));
				b.setNome(result.getString(2));
				b.setFabricante(result.getString(3));
				b.setFornecedor(result.getString(4));
				b.setDataFabricacao(result.getDate(5));
				b.setDataValidade(result.getDate(6));
				b.setTeorAlcool(result.getDouble(7));
				b.setDtCadastro(result.getDate(8));
				
				Categoria c = new Categoria();
				c.setId(result.getInt(9));
				c.setDescricao(result.getString(10));
				c.setDiasValidade(result.getInt(11));
				
				b.setCategoria(c);
			}
			
			result.close();
			ps.close();
			conn.close();
			
		} catch(SQLException e) {
			e.printStackTrace();
			System.out.println("Erro ao selecionar o produto");
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("Erro ao instanciar a classe do PostgreSQL");
		}
		
		return b;
	}

	@Override
	public void procurar(EntidadeDominio entidade) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<EntidadeDominio> listar(EntidadeDominio entidade) throws SQLException {
		List<EntidadeDominio> bebidas = new ArrayList<EntidadeDominio>();
		String sql = "SELECT bebidas.id, nome, fabricante, fornecedor, data_fabricacao, "+
                "data_validade, teor_alcool, bebidas.dtcadastro, "+
			     "categorias.id, categorias.descricao, categorias.diasvalidade "+
                "FROM public.bebidas " +
                "JOIN categorias ON categorias.id = bebidas.fk_categoria ";
		
		try {
			
			Connection conn = BDConnection.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet result = ps.executeQuery();
			
			while(result.next()) {
				Bebida b = new Bebida();
				b.setId(result.getInt(1));
				b.setNome(result.getString(2));
				b.setFabricante(result.getString(3));
				b.setFornecedor(result.getString(4));
				b.setDataFabricacao(result.getDate(5));
				b.setDataValidade(result.getDate(6));
				b.setTeorAlcool(result.getDouble(7));
				b.setDtCadastro(result.getDate(8));
				
				Categoria c = new Categoria();
				c.setId(result.getInt(9));
				c.setDescricao(result.getString(10));
				c.setDiasValidade(result.getInt(11));
				
				b.setCategoria(c);
				bebidas.add(b);
			}
			
			result.close();
			ps.close();
			conn.close();
			
		} catch(SQLException e) {
			e.printStackTrace();
			System.out.println("Erro ao executar a query");
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("Erro ao instanciar a classe do PostgreSQL");
		}
		
		return bebidas;
	}

}
