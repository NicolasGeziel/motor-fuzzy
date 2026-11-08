package br.com.motorfuzzy.regras;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class RegraFuzzyTest {

    @Test
    void deveCalcularForcaDeAtivacao() {

        CondicaoFuzzy antecedente = new CondicaoE(
                new CondicaoSimples("temperatura", "alta"),
                new CondicaoSimples("umidade", "baixa")
        );

        RegraFuzzy regra = new RegraFuzzy(
                antecedente,
                "riscoIncendio",
                "alto"
        );

        double ativacao = regra.avaliar(
                Map.of(
                        "temperatura.alta", 0.8,
                        "umidade.baixa", 0.6
                )
        );

        assertEquals(0.6, ativacao);
    }

    @Test
    void deveArmazenarConsequencia() {

        RegraFuzzy regra = new RegraFuzzy(
                new CondicaoSimples("temperatura", "alta"),
                "riscoIncendio",
                "alto"
        );

        assertEquals("riscoIncendio", regra.getVariavelSaida());
        assertEquals("alto", regra.getConjuntoSaida());
    }

    @Test
    void deveRejeitarAntecedenteNulo() {

        assertThrows(
                NullPointerException.class,
                () -> new RegraFuzzy(null, "riscoIncendio", "alto")
        );
    }
}