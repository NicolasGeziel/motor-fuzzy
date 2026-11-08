package br.com.motorfuzzy.inferencia;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AgregacaoMaximoTest {

    @Test
    void deveRetornarMaiorGrau() {

        AgregacaoMaximo agregacao = new AgregacaoMaximo();

        double resultado = agregacao.calcular(List.of(0.3, 0.7, 0.5));

        assertEquals(0.7, resultado);
    }

    @Test
    void deveRetornarZeroSemContribuicoes() {

        AgregacaoMaximo agregacao = new AgregacaoMaximo();

        assertEquals(0.0, agregacao.calcular(List.of()));
    }

    @Test
    void deveRejeitarGrauInvalido() {

        AgregacaoMaximo agregacao = new AgregacaoMaximo();

        assertThrows(
                IllegalArgumentException.class,
                () -> agregacao.calcular(List.of(0.5, 1.2))
        );
    }
}