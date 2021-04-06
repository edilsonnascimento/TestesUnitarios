package br.ce.wcaquino.servicos;

import br.ce.wcaquino.entidades.Filme;
import br.ce.wcaquino.entidades.Locacao;
import br.ce.wcaquino.entidades.Usuario;
import br.ce.wcaquino.exception.FilmeSemEstoqueException;
import br.ce.wcaquino.exception.LocadoraException;
import br.ce.wcaquino.utils.DataUtils;
import org.junit.*;
import org.junit.rules.ErrorCollector;
import org.junit.rules.ExpectedException;
import org.junit.runners.MethodSorters;

import java.util.*;

import static br.ce.wcaquino.utils.DataUtils.isMesmaData;
import static br.ce.wcaquino.utils.DataUtils.obterDataComDiferencaDias;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
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

        Assume.assumeFalse(DataUtils.verificarDiaSemana(new Date(), Calendar.SATURDAY));

        //cenario
        Usuario usuario = new Usuario("Usuario 1");
        List<Filme> filmes = Arrays.asList(new Filme("Filme 1", 2, 5.0));

        //acao
        Locacao locacao = service.alugarFilme(usuario, filmes);

        //verificacao
        error.checkThat(locacao.getValor(), is(equalTo(5.0)));
        error.checkThat(locacao.getDataLocacao(), is(not(5)));
        error.checkThat(isMesmaData(locacao.getDataLocacao(), new Date()), is(true));
        error.checkThat(isMesmaData(locacao.getDataRetorno(), obterDataComDiferencaDias(1)), is(true));
    }

    @Test(expected = FilmeSemEstoqueException.class)
    public void naoDeveAlugarFilmeSemEstoque() throws Exception {
        //cenario
        Usuario usuario = new Usuario("Usuario 1");
        List<Filme> filmes = Arrays.asList(new Filme("Filme 1", 0, 5.0));

        //acao lança Excepetion
        Locacao locacao = service.alugarFilme(usuario, filmes);

    }

    @Test
    public void naoDeveAlugarFilmeSemUsuario() throws FilmeSemEstoqueException {
        //cenario
        List<Filme> filmes = Arrays.asList(new Filme("Filme 1", 1, 5.0));

        //acao lança LocadoraException
        try {
            Locacao locacao = service.alugarFilme(null, filmes);
            Assert.fail("Deveria gerar uma exception LocadoraException()");
        } catch (LocadoraException e) {
            assertThat(e.getMessage(), is("Usuario vazio!"));
        }
    }

    @Test
    public void naoDeveAlugarFilmeSemFilme() throws FilmeSemEstoqueException, LocadoraException {
        //cenario
        Usuario usuario = new Usuario("Zezinho");
        exception.expect(LocadoraException.class);
        exception.expectMessage("Lista de filmes não pode ser vazia!");

        //acao
        service.alugarFilme(usuario,null);
    }

    @Test
    public void devePagar75PorCentoNoFilmeTerceiroLocado() throws FilmeSemEstoqueException, LocadoraException {

        Usuario usuario = new Usuario("Usuario");
        List<Filme> filmes = Arrays.asList(new Filme("Filme1", 2, 4.0),
                                           new Filme("Filme2", 2, 4.0),
                                           new Filme("Filme3", 2, 4.0));

        Locacao resultado = service.alugarFilme(usuario, filmes);

        assertThat(resultado.getValor(), is(11.0));
    }

    @Test
    public void devePagar25PorCentoNoFilmeQuintoLocado() throws FilmeSemEstoqueException, LocadoraException {

        Usuario usuario = new Usuario("Usuario");
        List<Filme> filmes = Arrays.asList(new Filme("Filme1", 2, 4.0), new Filme("Filme2", 2, 4.0),new Filme("Filme3", 2, 4.0), new Filme("Filme4", 2, 4.0), new Filme("Filme5", 2, 4.0));

        Locacao resultado = service.alugarFilme(usuario, filmes);

        assertThat(resultado.getValor(), is(14.0));
    }

    @Test
    public void devePagar0PorCentoNoFilmeSextoLocado() throws FilmeSemEstoqueException, LocadoraException {

        Usuario usuario = new Usuario("Usuario");
        List<Filme> filmes = Arrays.asList(new Filme("Filme1", 2, 4.0),
                                           new Filme("Filme2", 2, 4.0),
                                           new Filme("Filme3", 2, 4.0),
                                           new Filme("Filme4", 2, 4.0),
                                           new Filme("Filme5", 2, 4.0),
                                           new Filme("Filme6", 2, 4.0));

        Locacao resultado = service.alugarFilme(usuario, filmes);

        assertThat(resultado.getValor(), is(14.0));
    }
    @Test
    @Ignore
    public void deveDevolverNaSegundaAoAlugarNoSabado() throws FilmeSemEstoqueException, LocadoraException {

        Assume.assumeTrue(DataUtils.verificarDiaSemana(new Date(), Calendar.SATURDAY));

        Usuario usuario = new Usuario("José");
        List<Filme> filmes = Arrays.asList(new Filme("Filme 1", 1, 5.0));

        Locacao retorno = service.alugarFilme(usuario, filmes);

        boolean ehSegunda = DataUtils.verificarDiaSemana(retorno.getDataRetorno(), Calendar.MONDAY);
        Assert.assertTrue(ehSegunda);
    }
}
