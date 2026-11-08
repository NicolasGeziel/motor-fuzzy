package br.com.motorfuzzy.nucleo;

import br.com.motorfuzzy.conjuntos.ConjuntoFuzzy;
import br.com.motorfuzzy.defuzzificacao.DefuzzificacaoCentroide;
import br.com.motorfuzzy.defuzzificacao.MetodoDefuzzificacao;
import br.com.motorfuzzy.inferencia.ResultadoInferencia;
import br.com.motorfuzzy.regras.RegraFuzzy;
import br.com.motorfuzzy.variaveis.VariavelFuzzy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MotorFuzzy {

    private final Map<String, VariavelFuzzy> variaveisEntrada;
    private final Map<String, VariavelFuzzy> variaveisSaida;
    private final List<RegraFuzzy> regras;

    public MotorFuzzy() {
        variaveisEntrada = new HashMap<>();
        variaveisSaida = new HashMap<>();
        regras = new ArrayList<>();
    }

    public void adicionarEntrada(VariavelFuzzy variavel) {

        if (variavel == null) {
            throw new IllegalArgumentException("A variável não pode ser nula.");
        }

        if (variaveisEntrada.containsKey(variavel.getNome()) || variaveisSaida.containsKey(variavel.getNome())) {
            throw new IllegalArgumentException("Variável já cadastrada: " + variavel.getNome());
        }

        variaveisEntrada.put(variavel.getNome(), variavel);
    }

    public void adicionarSaida(VariavelFuzzy variavel) {

        if (variavel == null) {
            throw new IllegalArgumentException("A variável não pode ser nula.");
        }

        if (variaveisSaida.containsKey(variavel.getNome()) || variaveisEntrada.containsKey(variavel.getNome())) {
            throw new IllegalArgumentException("Variável já cadastrada: " + variavel.getNome());
        }

        variaveisSaida.put(variavel.getNome(), variavel);
    }

    public void adicionarRegra(RegraFuzzy regra) {

        if (regra == null) {
            throw new IllegalArgumentException("A regra não pode ser nula.");
        }

        regras.add(regra);
    }

    public ResultadoInferencia inferir(Map<String, Double> valoresEntrada, String nomeSaida) {

        if (valoresEntrada == null) {
            throw new IllegalArgumentException("Os valores de entrada não podem ser nulos.");
        }

        VariavelFuzzy variavelSaida = variaveisSaida.get(nomeSaida);

        if (variavelSaida == null) {
            throw new IllegalArgumentException("Variável de saída não encontrada: " + nomeSaida);
        }

        Map<String, Double> grausFuzzificados = new HashMap<>();

        for (VariavelFuzzy variavel : variaveisEntrada.values()) {

            Double valor = valoresEntrada.get(variavel.getNome());

            if (valor == null || !Double.isFinite(valor)) {
                throw new IllegalArgumentException("Valor de entrada ausente ou inválido: " + variavel.getNome());
            }

            Map<String, Double> graus = variavel.fuzzificar(valor);

            for (Map.Entry<String, Double> entrada : graus.entrySet()) {
                String chave = variavel.getNome() + "." + entrada.getKey();
                grausFuzzificados.put(chave, entrada.getValue());
            }
        }

        ResultadoInferencia resultado = new ResultadoInferencia();

        for (RegraFuzzy regra : regras) {

            if (!regra.getVariavelSaida().equals(nomeSaida)) {
                continue;
            }

            double ativacao = regra.avaliar(grausFuzzificados);

            ConjuntoFuzzy conjuntoSaida = variavelSaida.obterConjunto(regra.getConjuntoSaida());

            resultado.adicionarContribuicao(conjuntoSaida, ativacao);
        }

        return resultado;
    }

    public double calcular(Map<String, Double> valoresEntrada, String nomeSaida, double minimo, double maximo) {

        return calcular(valoresEntrada, nomeSaida, minimo, maximo, new DefuzzificacaoCentroide());
    }

    public double calcular(Map<String, Double> valoresEntrada, String nomeSaida, double minimo, double maximo, MetodoDefuzzificacao metodo) {

        if (metodo == null) {
            throw new IllegalArgumentException("O método de defuzzificação não pode ser nulo.");
        }

        ResultadoInferencia resultado = inferir(valoresEntrada, nomeSaida);

        return metodo.calcular(resultado, minimo, maximo);
    }
}