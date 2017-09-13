package dao;

import java.sql.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Categoria;
import model.EntidadeDominio;

public class DAOCategorias implements IDAO {

	@Override
	public EntidadeDominio inserir(EntidadeDominio entidade) throws SQLException {
		Categoria categoria = (Categoria) entidade;
		String sql = "INSERT INTO categorias(descricao, diasValidade, dtCadastro) VALUES (?, ?, ?)";
		
		try {
			
			Connection conn = BDConnection.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, categoria.getDescricao());
			ps.setInt(2, categoria.getDiasValidade());
			Timestamp time = new Timestamp(categoria.getDtCadastro().getTime());
			ps.setTimestamp(3, time);
			
			if(ps.executeUpdate() > 0) {
				ResultSet generatedkey = ps.getGeneratedKeys();
				if(generatedkey.next()) {
					categoria.setId(generatedkey.getInt(1));
				}
			}
			ps.close();
			conn.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Erro ao inserir a categoria");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("Erro ao instanciar a classe do PostgreSQL");
		}
		
		return categoria;
	}

	@Override
	public EntidadeDominio alterar(EntidadeDominio entidade) throws SQLException {
		
		Categoria categoria = (Categoria) entidade;
		String sql = "UPDATE categorias SET descricao = ?, diasValidade = ?, dtCadastro = ? WHERE id = ?";
		
		try {
			
			Connection conn = BDConnection.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, categoria.getDescricao());
			ps.setInt(2, categoria.getDiasValidade());
			Timestamp time = new Timestamp(categoria.getDtCadastro().getTime());
			ps.setTimestamp(3, time);
			ps.setInt(4, categoria.getId());
			
			if(ps.executeUpdate() > 0) {
				ResultSet generatedkey = ps.getGeneratedKeys();
				if(generatedkey.next()) {
					categoria.setId(generatedkey.getInt(1));
				}
			}
			
			ps.close();
			conn.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Erro ao inserir a categoria");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("Erro ao instanciar a classe do PostgreSQL");
		}
		
		return categoria;
		
	}

	@Override
	public void deletar(EntidadeDominio entidade) throws SQLException {
		
		Categoria categoria = (Categoria) entidade;
		String sql = "DELETE FROM categorias WHERE id = ?";
		
		try {
			
			Connection conn = BDConnection.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, categoria.getId());
			
			ps.executeUpdate();
			
			ps.close();
			conn.close();
			
		} catch(SQLException e) {
			e.printStackTrace();
			System.out.println("Erro ao apagar o produto");
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("Erro ao instanciar a classe do PostgreSQL");
		}
		
	}

	@Override
	public EntidadeDominio consultar(EntidadeDominio entidade) throws SQLException {
		Categoria categoria = (Categoria) entidade;
		String sql = "SELECT id, descricao, diasValidade, dtcadastro FROM categorias WHERE id = ?";
		Categoria c = new Categoria();
		try {
			Connection conn = BDConnection.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, categoria.getId());
			
			ResultSet result = ps.executeQuery();
			
			if(result.next()) {
				c.setId(result.getInt(1));
				c.setDescricao(result.getString(2));
				c.setDiasValidade(result.getInt(3));
				c.setDtCadastro(result.getDate(4));
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
		
		return c;
	}
	
	@Override
	public void procurar(EntidadeDominio entidade) throws SQLException {
		/*
		Categoria categoria = (Categoria) entidade;
		String sql = "SELECT id, descricao, diasValidade, dtcadastro FROM categorias WHERE id = ?";
		
		try {
			Connection conn = BDConnection.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, categoria.getId());
			
			ResultSet result = ps.executeQuery();
			
			if(result.next()) {
				Categoria c = new Categoria();
				c.setId(result.getInt(1));
				c.setDescricao(result.getString(2));
				c.setDiasValidade(result.getInt(3));
				c.setDtCadastro(result.getDate(4));
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
		*/
	}

	@Override
	public List<EntidadeDominio> listar(EntidadeDominio entidade) throws SQLException {
		List<EntidadeDominio> categorias = new ArrayList<EntidadeDominio>();
		String sql = "SELECT id, descricao, diasValidade, dtCadastro FROM categorias ORDER BY id";
		
		try {
			
			Connection conn = BDConnection.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet result = ps.executeQuery();
			while(result.next()) {
				Categoria c = new Categoria();
				c.setId(result.getInt(1));
				c.setDescricao(result.getString(2));
				c.setDiasValidade(result.getInt(3));
				c.setDtCadastro(result.getDate(4));
				
				categorias.add(c);
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
		
		return categorias;
	}

}
