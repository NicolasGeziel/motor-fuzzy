package br.com.motorfuzzy.regras;

import java.util.Map;
import java.util.Objects;

public class CondicaoOu implements CondicaoFuzzy {

    private final CondicaoFuzzy esquerda;
    private final CondicaoFuzzy direita;

    public CondicaoOu(CondicaoFuzzy esquerda, CondicaoFuzzy direita) {
        this.esquerda = Objects.requireNonNull(
                esquerda, "A condição esquerda não pode ser nula."
        );

        this.direita = Objects.requireNonNull(
                direita, "A condição direita não pode ser nula."
        );
    }

    @Override
    public double avaliar(Map<String, Double> entradas) {

        double grauEsquerda = esquerda.avaliar(entradas);
        double grauDireita = direita.avaliar(entradas);

        return Math.max(grauEsquerda, grauDireita);
    }
}