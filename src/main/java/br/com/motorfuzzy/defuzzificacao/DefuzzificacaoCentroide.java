package br.com.motorfuzzy.defuzzificacao;

import br.com.motorfuzzy.inferencia.ResultadoInferencia;

public class DefuzzificacaoCentroide implements MetodoDefuzzificacao {

    private final int divisoes;

    public DefuzzificacaoCentroide() {
        this(1000);
    }

    public DefuzzificacaoCentroide(int divisoes) {

        if (divisoes < 1) {
            throw new IllegalArgumentException("O número de divisões deve ser maior que zero.");
        }

        this.divisoes = divisoes;
    }

    @Override
    public double calcular(ResultadoInferencia resultado, double minimo, double maximo) {

        if (resultado == null) {
            throw new IllegalArgumentException("O resultado da inferência não pode ser nulo.");
        }

        if (!Double.isFinite(minimo) || !Double.isFinite(maximo) || minimo >= maximo) {
            throw new IllegalArgumentException("O intervalo de saída deve ser válido.");
        }

        double passo = (maximo - minimo) / divisoes;

        if (!Double.isFinite(passo) || passo <= 0) {
            throw new IllegalArgumentException("O intervalo é incompatível com a resolução escolhida.");
        }

        double numerador = 0.0;
        double denominador = 0.0;

        for (int i = 0; i <= divisoes; i++) {

            double valor = minimo + i * passo;

            double pertinencia = resultado.calcularPertinencia(valor);

            double peso = (i == 0 || i == divisoes) ? 0.5 : 1.0;

            numerador += peso * valor * pertinencia;
            denominador += peso * pertinencia;
        }

        if (denominador == 0.0) {
            throw new IllegalStateException("Não foi possível defuzzificar: " + "a área agregada é zero.");
        }

        return numerador / denominador;
    }
}