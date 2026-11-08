package br.com.motorfuzzy.defuzzificacao;

import br.com.motorfuzzy.conjuntos.ConjuntoFuzzy;
import br.com.motorfuzzy.funcoes.FuncaoTriangular;
import br.com.motorfuzzy.inferencia.ResultadoInferencia;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DefuzzificacaoCentroideTest {

    @Test
    void deveCalcularCentroideDeTrianguloSimetrico() {

        ResultadoInferencia resultado = new ResultadoInferencia();

        ConjuntoFuzzy conjunto = new ConjuntoFuzzy("medio", new FuncaoTriangular(0, 50, 100));

        resultado.adicionarContribuicao(conjunto, 1.0);

        DefuzzificacaoCentroide centroide = new DefuzzificacaoCentroide(1000);

        double valor = centroide.calcular(resultado, 0, 100);

        assertEquals(50.0, valor, 0.001);
    }

    @Test
    void deveCalcularCentroideComConjuntoRecortado() {

        ResultadoInferencia resultado = new ResultadoInferencia();

        ConjuntoFuzzy conjunto = new ConjuntoFuzzy("alto", new FuncaoTriangular(50, 75, 100));

        resultado.adicionarContribuicao(conjunto, 0.6);

        DefuzzificacaoCentroide centroide = new DefuzzificacaoCentroide(1000);

        double valor = centroide.calcular(resultado, 0, 100);

        assertEquals(75.0, valor, 0.001);
    }

    @Test
    void deveRejeitarIntervaloInvalido() {

        ResultadoInferencia resultado = new ResultadoInferencia();

        DefuzzificacaoCentroide centroide = new DefuzzificacaoCentroide();

        assertThrows(IllegalArgumentException.class, () -> centroide.calcular(resultado, 100, 0));
    }

    @Test
    void deveRejeitarNumeroDeDivisoesInvalido() {

        assertThrows(IllegalArgumentException.class, () -> new DefuzzificacaoCentroide(0));
    }

    @Test
    void deveRejeitarResultadoSemArea() {

        ResultadoInferencia resultado = new ResultadoInferencia();

        DefuzzificacaoCentroide centroide = new DefuzzificacaoCentroide();

        assertThrows(IllegalStateException.class, () -> centroide.calcular(resultado, 0, 100));
    }

    @Test
    void deveCalcularCentroideDeTrianguloAssimetrico() {

        ResultadoInferencia resultado = new ResultadoInferencia();

        ConjuntoFuzzy conjunto = new ConjuntoFuzzy("assimetrico", new FuncaoTriangular(0, 20, 90));

        resultado.adicionarContribuicao(conjunto, 1.0);

        DefuzzificacaoCentroide centroide = new DefuzzificacaoCentroide(1000);

        double valor = centroide.calcular(resultado, 0, 100);

        assertEquals(110.0 / 3.0, valor, 0.01);
    }
}