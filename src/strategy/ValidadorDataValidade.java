package strategy;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;

import dao.DAOCategorias;
import model.Bebida;
import model.Categoria;
import model.EntidadeDominio;

public class ValidadorDataValidade implements IStrategy {

	@Override
	public String processar(EntidadeDominio entidade) {
		Calendar c = Calendar.getInstance();
		
		Bebida bebida = (Bebida) entidade;
		Categoria categoria = bebida.getCategoria();
		
		DAOCategorias daoCategoria = new DAOCategorias();
		try {
			categoria = (Categoria) daoCategoria.consultar(categoria);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Date dataFabricaca = bebida.getDataFabricacao();
		c.setTime(dataFabricaca);
		c.add(Calendar.DATE, categoria.getDiasValidade());
		Date dataMaximaValidade = c.getTime();
		
		if(bebida.getDataValidade().after(dataMaximaValidade)){
			return "Bebida fora da data de validade";
		}
		
		return null;
	}

}
