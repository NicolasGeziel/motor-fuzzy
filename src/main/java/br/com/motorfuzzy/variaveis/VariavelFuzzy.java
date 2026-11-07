package br.com.motorfuzzy.variaveis;

import br.com.motorfuzzy.conjuntos.ConjuntoFuzzy;
import br.com.motorfuzzy.funcoes.FuncaoPertinencia;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class VariavelFuzzy {

    private final String nome;

    private final Map<String, ConjuntoFuzzy> conjuntos = new LinkedHashMap<>();

    public VariavelFuzzy(String nome) {

        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("O nome da variável não pode ser vazio.");
        }

        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void adicionarConjunto(String nome, FuncaoPertinencia funcao) {

        ConjuntoFuzzy conjunto = new ConjuntoFuzzy(nome, funcao);

        adicionarConjunto(conjunto);
    }

    public void adicionarConjunto(ConjuntoFuzzy conjunto) {

        if (conjunto == null) {
            throw new IllegalArgumentException("O conjunto não pode ser nulo.");
        }

        if (conjuntos.containsKey(conjunto.getNome())) {
            throw new IllegalArgumentException(
                    "Já existe um conjunto com o nome: " + conjunto.getNome()
            );
        }

        conjuntos.put(conjunto.getNome(), conjunto);
    }

    public Map<String, Double> fuzzificar(double valor) {

        Map<String, Double> resultados = new LinkedHashMap<>();

        for (ConjuntoFuzzy conjunto : conjuntos.values()) {

            double grau = conjunto.calcularPertinencia(valor);

            resultados.put(conjunto.getNome(), grau);
        }

        return Collections.unmodifiableMap(resultados);
    }

    public Map<String, ConjuntoFuzzy> getConjuntos() {
        return Collections.unmodifiableMap(conjuntos);
    }
}