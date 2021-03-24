package br.ce.wcaquino.servicos;

import br.ce.wcaquino.entidades.Filme;
import br.ce.wcaquino.entidades.Locacao;
import br.ce.wcaquino.entidades.Usuario;
import br.ce.wcaquino.exception.FilmeSemEstoqueException;
import br.ce.wcaquino.exception.LocadoraException;

import java.util.Date;
import java.util.List;

import static br.ce.wcaquino.utils.DataUtils.adicionarDias;

public class LocacaoService {

	public Locacao alugarFilme(Usuario usuario, List<Filme> filmes) throws FilmeSemEstoqueException, LocadoraException {

		if(usuario == null){
			throw new LocadoraException("Usuario vazio!");
		}
		if(filmes == null || filmes.isEmpty()){
			throw new LocadoraException("Lista de filmes não pode ser vazia!");
		}

		if(estaSemEstoque(filmes)){
			throw new FilmeSemEstoqueException();
		}

		Locacao locacao = new Locacao();
		locacao.setFilmes(filmes);
		locacao.setUsuario(usuario);
		locacao.setDataLocacao(new Date());
		locacao.setValor(filmes);

		//Entrega no dia seguinte
		Date dataEntrega = new Date();
		dataEntrega = adicionarDias(dataEntrega, 1);
		locacao.setDataRetorno(dataEntrega);

		//Salvando a locacao...
		//TODO adicionar método para salvar

		return locacao;
	}

	public boolean estaSemEstoque(List<Filme> filmes){
		long quantidadeFilmesSemEstoque = 0;

		quantidadeFilmesSemEstoque = filmes.parallelStream().filter(LocacaoService::semEstoque).count();

		return quantidadeFilmesSemEstoque > 0;
	}

	private static boolean semEstoque(Filme filme) {
		return filme.getEstoque() < 1;
	}



}