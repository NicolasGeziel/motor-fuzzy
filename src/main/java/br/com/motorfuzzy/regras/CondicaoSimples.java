package br.com.motorfuzzy.regras;

import java.util.Map;

public class CondicaoSimples implements CondicaoFuzzy {

    private final String variavel;
    private final String conjunto;

    public CondicaoSimples(String variavel, String conjunto) {

        if (variavel == null || variavel.isBlank()) {
            throw new IllegalArgumentException(
                    "O nome da variável não pode ser vazio."
            );
        }

        if (conjunto == null || conjunto.isBlank()) {
            throw new IllegalArgumentException(
                    "O nome do conjunto não pode ser vazio."
            );
        }

        this.variavel = variavel;
        this.conjunto = conjunto;
    }

    @Override
    public double avaliar(Map<String, Double> entradas) {

        if (entradas == null) {
            throw new IllegalArgumentException(
                    "As entradas não podem ser nulas."
            );
        }

        String chave = variavel + "." + conjunto;

        Double grau = entradas.get(chave);

        if (grau == null) {
            throw new IllegalArgumentException(
                    "Grau de pertinência não encontrado: " + chave
            );
        }

        if (!Double.isFinite(grau) || grau < 0 || grau > 1) {
            throw new IllegalArgumentException(
                    "O grau de pertinência deve estar entre 0 e 1."
            );
        }

        return grau;
    }
}