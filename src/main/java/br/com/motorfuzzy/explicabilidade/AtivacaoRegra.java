package br.com.motorfuzzy.explicabilidade;

public record AtivacaoRegra(String descricao, String variavelSaida, String conjuntoSaida, double forcaAtivacao) {

    public AtivacaoRegra {

        if (descricao == null || descricao.isBlank()) {
            throw new IllegalArgumentException("A descrição não pode ser vazia.");
        }

        if (variavelSaida == null || variavelSaida.isBlank()) {
            throw new IllegalArgumentException("A variável de saída não pode ser vazia.");
        }

        if (conjuntoSaida == null || conjuntoSaida.isBlank()) {
            throw new IllegalArgumentException("O conjunto de saída não pode ser vazio.");
        }

        if (!Double.isFinite(forcaAtivacao) || forcaAtivacao < 0 || forcaAtivacao > 1) {
            throw new IllegalArgumentException("A força de ativação deve estar entre 0 e 1.");
        }
    }

    public boolean foiAtivada() {
        return forcaAtivacao > 0;
    }
}