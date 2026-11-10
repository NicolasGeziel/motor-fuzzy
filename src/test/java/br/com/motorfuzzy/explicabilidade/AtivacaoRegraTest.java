package br.com.motorfuzzy.explicabilidade;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AtivacaoRegraTest {

    @Test
    void deveIdentificarRegraAtivada() {

        AtivacaoRegra regra = new AtivacaoRegra(
                "SE temperatura É alta ENTÃO risco É alto",
                "risco",
                "alto",
                0.7
        );

        assertTrue(regra.foiAtivada());
    }

    @Test
    void deveIdentificarRegraNaoAtivada() {

        AtivacaoRegra regra = new AtivacaoRegra(
                "SE temperatura É alta ENTÃO risco É alto",
                "risco",
                "alto",
                0.0
        );

        assertFalse(regra.foiAtivada());
    }

    @Test
    void deveRejeitarAtivacaoInvalida() {

        assertThrows(
                IllegalArgumentException.class,
                () -> new AtivacaoRegra(
                        "Regra de teste",
                        "risco",
                        "alto",
                        1.5
                )
        );
    }
}