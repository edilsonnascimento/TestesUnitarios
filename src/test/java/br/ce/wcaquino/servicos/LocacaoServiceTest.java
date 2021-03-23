package br.ce.wcaquino.servicos;

import br.ce.wcaquino.entidades.Filme;
import br.ce.wcaquino.entidades.Locacao;
import br.ce.wcaquino.entidades.Usuario;
import br.ce.wcaquino.exception.FilmeSemEstoqueException;
import br.ce.wcaquino.exception.LocadoraException;
import org.junit.*;
import org.junit.rules.ErrorCollector;
import org.junit.rules.ExpectedException;

import java.util.Date;

import static br.ce.wcaquino.utils.DataUtils.isMesmaData;
import static br.ce.wcaquino.utils.DataUtils.obterDataComDiferencaDias;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class LocacaoServiceTest {

    private LocacaoService service;

    @Rule
    public ErrorCollector error = new ErrorCollector();

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void setup(){
        this.service = new LocacaoService();
    }

    @After
    public void tearDown(){
    }


    @Test
    public void teste() throws Exception {
        //cenario
        Usuario usuario = new Usuario("Usuario 1");
        Filme filme = new Filme("Filme 1", 2, 5.0);

        //acao
        Locacao locacao = service.alugarFilme(usuario, filme);

        //verificacao
        error.checkThat(locacao.getValor(), is(equalTo(5.0)));
        error.checkThat(locacao.getDataLocacao(), is(not(5)));
        error.checkThat(isMesmaData(locacao.getDataLocacao(), new Date()), is(true));
        error.checkThat(isMesmaData(locacao.getDataRetorno(), obterDataComDiferencaDias(1)), is(true));
    }

    @Test(expected = FilmeSemEstoqueException.class)
    public void testLocacaoFilmeSemEstoque() throws Exception {
        //cenario
        Usuario usuario = new Usuario("Usuario 1");
        Filme filme = new Filme("Filme 1", 0, 5.0);

        //acao lança Excepetion
        Locacao locacao = service.alugarFilme(usuario, filme);

    }

    @Test
    public void testLocacaoFilmeUsuarioVazioException() throws FilmeSemEstoqueException {
        //cenario
        Filme filme = new Filme("Filme 1", 1, 5.0);

        //acao lança LocadoraException
        try {
            Locacao locacao = service.alugarFilme(null, filme);
            Assert.fail("Deveria gerar uma exception LocadoraException()");
        } catch (LocadoraException e) {
            assertThat(e.getMessage(), is("Usuario vazio!"));
        }
    }

    @Test
    public void testLocacaoFilmeFilmeVazioException() throws FilmeSemEstoqueException, LocadoraException {
        //cenario
        Usuario usuario = new Usuario("Zezinho");
        exception.expect(LocadoraException.class);
        exception.expectMessage("Filme vazio!");

        //acao
        service.alugarFilme(usuario,null);
    }
}
