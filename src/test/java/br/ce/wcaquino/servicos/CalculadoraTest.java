package br.ce.wcaquino.servicos;

import br.ce.wcaquino.exception.NaoPodeDividirPorZeroException;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CalculadoraTest {

    private Calculadora calculadora;

    @Before
    public void setup(){
        calculadora = new Calculadora();
    }

    @Test
    public void deveSomarDoisValores(){
        int a = 5;
        int b = 3;

        int resultado = calculadora.somar(a,b);

        Assert.assertEquals(8, resultado);
    }

    @Test
    public void deveSubtrarirDoisValores(){
        int a = 8;
        int b = 3;

        int resultado = calculadora.subtracao(a,b);

        Assert.assertEquals(5, resultado);
    }

    @Test
    public void deveDividirDoisValores() throws NaoPodeDividirPorZeroException {
        double a = 4;
        double b = 2;

        Double resultado = calculadora.dividir(a, b);

        Assert.assertThat(resultado, CoreMatchers.is(2.0));
    }

    @Test(expected = NaoPodeDividirPorZeroException.class)
    public void deveLancarExececaoDividirPorZero() throws NaoPodeDividirPorZeroException {
        double a = 10;
        double b = 0;

        calculadora.dividir(a,b);

    }
}
