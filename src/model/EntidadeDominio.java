package model;

import java.util.Date;

public class EntidadeDominio implements IEntidade {
	
	private int id;
	private Date dtCadastro;

	@Override
	public void setId(int id) {
		this.id = id;
	}

	@Override
	public int getId() {
		return id;
	}

	@Override
	public void setDtCadastro(Date dtCadastro) {
		this.dtCadastro = dtCadastro;
	}

	@Override
	public Date getDtCadastro() {
		return dtCadastro;
	}
	
}
