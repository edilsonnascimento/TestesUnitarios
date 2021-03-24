package br.ce.wcaquino.entidades;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class Locacao {

	private Usuario usuario;
	private List<Filme> filmes;
	private Date dataLocacao;
	private Date dataRetorno;
	private Double valor;
	
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public Date getDataLocacao() {
		return dataLocacao;
	}
	public void setDataLocacao(Date dataLocacao) {
		this.dataLocacao = dataLocacao;
	}
	public Date getDataRetorno() {
		return dataRetorno;
	}
	public void setDataRetorno(Date dataRetorno) {
		this.dataRetorno = dataRetorno;
	}
	public Double getValor() {
		return valor;
	}
	public void setValor(List<Filme> filmes) {
		this.valor = filmes.parallelStream()
				.mapToDouble(Filme::getPrecoLocacao)
				.sum();
	}
	public List<Filme> getFilmes() {
		return filmes;
	}
	public void setFilmes (List<Filme> filmes) {
		this.filmes = filmes;
	}
}