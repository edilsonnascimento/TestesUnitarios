package br.ce.wcaquino.servicos;

import br.ce.wcaquino.exception.NaoPodeDividirPorZeroException;

public class Calculadora {
    
    public int somar(int a, int b) {
        return a + b;
    }

    public int subtracao(int a, int b) {
        return a - b;
    }

    public Double dividir(double a, double b) throws NaoPodeDividirPorZeroException {

        if( b== 0.0) throw  new NaoPodeDividirPorZeroException();

        return a / b;
    }
}
