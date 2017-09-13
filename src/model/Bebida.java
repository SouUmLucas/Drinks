package model;

import java.util.Date;

public class Bebida extends EntidadeDominio {
	
	private String nome;
	private String fabricante;
	private String fornecedor;
	private Categoria categoria;
	private Date dataFabricacao;
	private Date dataValidade;
	private double teorAlcool;
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getFabricante() {
		return fabricante;
	}
	public void setFabricante(String fabricante) {
		this.fabricante = fabricante;
	}
	public String getFornecedor() {
		return fornecedor;
	}
	public void setFornecedor(String fornecedor) {
		this.fornecedor = fornecedor;
	}
	public Categoria getCategoria() {
		return categoria;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	public Date getDataFabricacao() {
		return dataFabricacao;
	}
	public void setDataFabricacao(Date dataFabricacao) {
		this.dataFabricacao = dataFabricacao;
	}
	public Date getDataValidade() {
		return dataValidade;
	}
	public void setDataValidade(Date dataValidade) {
		this.dataValidade = dataValidade;
	}
	public double getTeorAlcool() {
		return teorAlcool;
	}
	public void setTeorAlcool(double teorAlcool) {
		this.teorAlcool = teorAlcool;
	}

}
