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

	public Locacao() {
		this.valor = 0.0;
	}

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
		for (int i = 0; i < filmes.size(); i++) {
			if ( i == 2) filmes.get(i).setPrecoLocacao(filmes.get(i).getPrecoLocacao() * 0.75);
			if ( i == 3) filmes.get(i).setPrecoLocacao(filmes.get(i).getPrecoLocacao() * 0.5);
			if ( i == 4) filmes.get(i).setPrecoLocacao(filmes.get(i).getPrecoLocacao() * 0.25);
			if ( i == 5) filmes.get(i).setPrecoLocacao(filmes.get(i).getPrecoLocacao() * 0);
			this.valor += filmes.get(i).getPrecoLocacao();
		}
	}

	public List<Filme> getFilmes() {
		return filmes;
	}

	public void setFilmes (List<Filme> filmes) {
		this.filmes = filmes;
	}
}