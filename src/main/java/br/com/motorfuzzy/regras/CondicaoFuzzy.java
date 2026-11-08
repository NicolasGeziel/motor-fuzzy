package br.com.motorfuzzy.regras;

import java.util.Map;

public interface CondicaoFuzzy {
    double avaliar(Map<String, Double> entradas);
}