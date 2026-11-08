package br.com.motorfuzzy.inferencia;

import br.com.motorfuzzy.conjuntos.ConjuntoFuzzy;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ResultadoInferencia {

    private final List<ContribuicaoRegra> contribuicoes;
    private final ImplicacaoMamdani implicacao;
    private final AgregacaoMaximo agregacao;

    public ResultadoInferencia() {
        this.contribuicoes = new ArrayList<>();
        this.implicacao = new ImplicacaoMamdani();
        this.agregacao = new AgregacaoMaximo();
    }

    public void adicionarContribuicao(ConjuntoFuzzy conjunto, double ativacao) {

        Objects.requireNonNull(conjunto, "O conjunto não pode ser nulo.");

        if (!Double.isFinite(ativacao) || ativacao < 0 || ativacao > 1) {
            throw new IllegalArgumentException("A ativação deve estar entre 0 e 1.");
        }

        contribuicoes.add(new ContribuicaoRegra(conjunto, ativacao));
    }

    public double calcularPertinencia(double valor) {

        List<Double> graus = new ArrayList<>();

        for (ContribuicaoRegra contribuicao : contribuicoes) {

            double grau = implicacao.calcular(
                    contribuicao.ativacao(),
                    contribuicao.conjunto(),
                    valor
            );

            graus.add(grau);
        }

        return agregacao.calcular(graus);
    }

    private record ContribuicaoRegra(ConjuntoFuzzy conjunto, double ativacao) {}
}