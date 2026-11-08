package br.com.motorfuzzy.defuzzificacao;

import br.com.motorfuzzy.inferencia.ResultadoInferencia;

public interface MetodoDefuzzificacao {

    double calcular(
            ResultadoInferencia resultado,
            double minimo,
            double maximo
    );
}