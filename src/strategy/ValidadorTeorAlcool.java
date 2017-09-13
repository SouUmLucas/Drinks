package strategy;

import model.Bebida;
import model.EntidadeDominio;

public class ValidadorTeorAlcool implements IStrategy {

	@Override
	public String processar(EntidadeDominio entidade) {
		Bebida bebida = (Bebida) entidade;
		
		if(bebida.getCategoria().getId() == 3) {
			if (bebida.getTeorAlcool() == 0) {
				return "O teor de alcool para produtos da categoria Alcool deve ser informado";
			}
		}
		
		return null;
	}

}
