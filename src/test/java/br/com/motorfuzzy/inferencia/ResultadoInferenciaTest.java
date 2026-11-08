package br.com.motorfuzzy.inferencia;

import br.com.motorfuzzy.conjuntos.ConjuntoFuzzy;
import br.com.motorfuzzy.funcoes.FuncaoTriangular;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ResultadoInferenciaTest {

    @Test
    void deveAgregarContribuicoesDeRegras() {

        ConjuntoFuzzy medio = new ConjuntoFuzzy(
                "medio",
                new FuncaoTriangular(0, 50, 100)
        );

        ResultadoInferencia resultado = new ResultadoInferencia();

        resultado.adicionarContribuicao(medio, 0.3);
        resultado.adicionarContribuicao(medio, 0.7);

        assertEquals(0.7, resultado.calcularPertinencia(50));
    }

    @Test
    void deveRetornarZeroSemContribuicoes() {

        ResultadoInferencia resultado = new ResultadoInferencia();

        assertEquals(0.0, resultado.calcularPertinencia(50));
    }
}