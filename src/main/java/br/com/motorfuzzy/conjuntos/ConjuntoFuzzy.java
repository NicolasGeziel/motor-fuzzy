package br.com.motorfuzzy.conjuntos;

import br.com.motorfuzzy.funcoes.FuncaoPertinencia;

public class ConjuntoFuzzy {
    private String nome;
    private FuncaoPertinencia funcaoPertinencia;

    public ConjuntoFuzzy(String nome, FuncaoPertinencia funcaoPertinencia){
        if(nome == null || nome.isBlank()){
            throw new IllegalArgumentException("Nome invalido!");
        }
        if(funcaoPertinencia == null){
            throw  new IllegalArgumentException("Funcção de pertinencia!");
        }
        this.nome = nome;
        this.funcaoPertinencia = funcaoPertinencia;
    }

    public String getNome(){
        return nome;
    }

    public double calcularPertinencia(double valor){
        return funcaoPertinencia.calcular(valor);
    }
}
