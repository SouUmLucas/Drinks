package model;

import java.util.Date;

public interface IEntidade {
	public void setId(int id);
	public int getId();
	public void setDtCadastro(Date dtCadastro);
	public Date getDtCadastro();
}
