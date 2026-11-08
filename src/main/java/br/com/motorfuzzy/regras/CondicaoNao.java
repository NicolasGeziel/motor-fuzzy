package br.com.motorfuzzy.regras;

import java.util.Map;
import java.util.Objects;

public class CondicaoNao implements CondicaoFuzzy {

    private final CondicaoFuzzy condicao;

    public CondicaoNao(CondicaoFuzzy condicao) {
        this.condicao = Objects.requireNonNull(
                condicao, "A condição não pode ser nula."
        );
    }

    @Override
    public double avaliar(Map<String, Double> entradas) {

        return 1.0 - condicao.avaliar(entradas);
    }
}