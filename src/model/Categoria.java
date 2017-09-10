package model;

public class Categoria extends EntidadeDominio {
	
	private String descricao;
	private int diasValidade;
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public void setDiasValidade(int diasValidade) {
		this.diasValidade = diasValidade;
	}
	
	public int getDiasValidade() {
		return diasValidade;
	}
	
}
