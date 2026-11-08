package br.com.motorfuzzy.regras;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class CondicoesFuzzyTest {

    private final Map<String, Double> entradas = Map.of(
            "temperatura.alta", 0.8,
            "umidade.baixa", 0.6
    );

    @Test
    void deveAvaliarCondicaoSimples() {
        CondicaoFuzzy condicao =
                new CondicaoSimples("temperatura", "alta");

        assertEquals(0.8, condicao.avaliar(entradas));
    }

    @Test
    void deveAvaliarOperadorE() {
        CondicaoFuzzy condicao = new CondicaoE(
                new CondicaoSimples("temperatura", "alta"),
                new CondicaoSimples("umidade", "baixa")
        );

        assertEquals(0.6, condicao.avaliar(entradas));
    }

    @Test
    void deveAvaliarOperadorOu() {
        CondicaoFuzzy condicao = new CondicaoOu(
                new CondicaoSimples("temperatura", "alta"),
                new CondicaoSimples("umidade", "baixa")
        );

        assertEquals(0.8, condicao.avaliar(entradas));
    }

    @Test
    void deveAvaliarOperadorNao() {
        CondicaoFuzzy condicao = new CondicaoNao(
                new CondicaoSimples("temperatura", "alta")
        );

        assertEquals(0.2, condicao.avaliar(entradas), 0.000001);
    }

    @Test
    void deveAvaliarCondicoesCompostas() {
        CondicaoFuzzy condicao = new CondicaoE(
                new CondicaoOu(
                        new CondicaoSimples("temperatura", "alta"),
                        new CondicaoSimples("umidade", "baixa")
                ),
                new CondicaoNao(
                        new CondicaoSimples("umidade", "baixa")
                )
        );

        // min(max(0.8, 0.6), 1 - 0.6) = 0.4
        assertEquals(0.4, condicao.avaliar(entradas), 0.000001);
    }

    @Test
    void deveRejeitarEntradaInexistente() {
        CondicaoFuzzy condicao =
                new CondicaoSimples("vento", "forte");

        assertThrows(
                IllegalArgumentException.class,
                () -> condicao.avaliar(entradas)
        );
    }

    @Test
    void deveRejeitarGrauInvalido() {
        CondicaoFuzzy condicao =
                new CondicaoSimples("temperatura", "alta");

        assertThrows(
                IllegalArgumentException.class,
                () -> condicao.avaliar(
                        Map.of("temperatura.alta", 1.5)
                )
        );
    }
}