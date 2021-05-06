package br.ce.wcaquino.br.ce.wcaquino.matcher;

import br.ce.wcaquino.utils.DataUtils;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

import java.util.Date;

public class DataDiferencaDiaMatcher extends TypeSafeMatcher<Date> {


    private Integer quantidadeDias;

    public DataDiferencaDiaMatcher(Integer quantidadeDias) {
        this.quantidadeDias = quantidadeDias;
    }

    @Override
    protected boolean matchesSafely(Date data) {
        return DataUtils.isMesmaData(data, DataUtils.obterDataComDiferencaDias(quantidadeDias));
    }

    @Override
    public void describeTo(Description description) {

    }
}
