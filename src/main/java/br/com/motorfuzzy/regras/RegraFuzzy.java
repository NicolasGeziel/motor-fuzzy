package br.com.motorfuzzy.regras;

import java.util.Map;
import java.util.Objects;

public class RegraFuzzy {

    private final CondicaoFuzzy antecedente;
    private final String variavelSaida;
    private final String conjuntoSaida;

    public RegraFuzzy(CondicaoFuzzy antecedente, String variavelSaida, String conjuntoSaida) {

        this.antecedente = Objects.requireNonNull(antecedente, "O antecedente não pode ser nulo.");

        if (variavelSaida == null || variavelSaida.isBlank()) {
            throw new IllegalArgumentException("A variável de saída não pode ser vazia.");
        }

        if (conjuntoSaida == null || conjuntoSaida.isBlank()) {
            throw new IllegalArgumentException("O conjunto de saída não pode ser vazio.");
        }

        this.variavelSaida = variavelSaida;
        this.conjuntoSaida = conjuntoSaida;
    }

    public double avaliar(Map<String, Double> entradas) {
        return antecedente.avaliar(entradas);
    }

    public String getVariavelSaida() {
        return variavelSaida;
    }

    public String getConjuntoSaida() {
        return conjuntoSaida;
    }

    public String descrever() {
        return "SE " + antecedente.descrever() + " ENTÃO " + variavelSaida + " É " + conjuntoSaida;
    }
}