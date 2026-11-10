package br.com.motorfuzzy.explicabilidade;

import java.util.List;
import java.util.Objects;

public record ResultadoDetalhado(double valorNumerico, String variavelSaida, String metodoDefuzzificacao, List<AtivacaoRegra> regras) {

    public ResultadoDetalhado {

        if (!Double.isFinite(valorNumerico)) {
            throw new IllegalArgumentException("O resultado numérico deve ser finito.");
        }

        if (variavelSaida == null || variavelSaida.isBlank()) {
            throw new IllegalArgumentException("A variável de saída não pode ser vazia.");
        }

        if (metodoDefuzzificacao == null || metodoDefuzzificacao.isBlank()) {
            throw new IllegalArgumentException("O método de defuzzificação não pode ser vazio.");
        }

        Objects.requireNonNull(regras, "A lista de regras não pode ser nula.");

        regras = List.copyOf(regras);
    }

    public List<AtivacaoRegra> regrasAtivadas() {
        return regras.stream().filter(AtivacaoRegra::foiAtivada).toList();
    }
}