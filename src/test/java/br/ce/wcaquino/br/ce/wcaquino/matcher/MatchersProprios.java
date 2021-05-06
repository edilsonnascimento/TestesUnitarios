package br.ce.wcaquino.br.ce.wcaquino.matcher;

import java.time.LocalDate;
import java.util.Calendar;

public class MatchersProprios {

    public static DiaSemanaMatcher caiEm(Integer diaSemana){
        return new DiaSemanaMatcher(diaSemana);
    }

    public static DiaSemanaMatcher caiNumaSegunda(){
        return new DiaSemanaMatcher(Calendar.MONDAY);
    }

    public static DataDiferencaDiaMatcher ehHojeComDiferencaDias(Integer quantidadeDias){
        return new DataDiferencaDiaMatcher(quantidadeDias);
    }

    public static DataDiferencaDiaMatcher ehHoje(){
        return new DataDiferencaDiaMatcher(0);
    }
}
