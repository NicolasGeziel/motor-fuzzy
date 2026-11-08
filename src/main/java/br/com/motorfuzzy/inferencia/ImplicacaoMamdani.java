package br.com.motorfuzzy.inferencia;

import br.com.motorfuzzy.conjuntos.ConjuntoFuzzy;

public class ImplicacaoMamdani {

    public double calcular(double ativacao, ConjuntoFuzzy conjuntoSaida, double valor) {

        if (!Double.isFinite(ativacao) || ativacao < 0 || ativacao > 1) {
            throw new IllegalArgumentException("A ativação deve estar entre 0 e 1.");
        }

        if (conjuntoSaida == null) {
            throw new IllegalArgumentException("O conjunto de saída não pode ser nulo.");
        }

        double pertinencia = conjuntoSaida.calcularPertinencia(valor);

        return Math.min(ativacao, pertinencia);
    }
}