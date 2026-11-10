package br.com.motorfuzzy.explicabilidade;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ResultadoDetalhadoTest {

    @Test
    void deveFiltrarRegrasAtivadas() {

        AtivacaoRegra regra1 = new AtivacaoRegra("Regra 1", "risco", "alto", 0.8);

        AtivacaoRegra regra2 = new AtivacaoRegra("Regra 2", "risco", "baixo", 0.0);

        ResultadoDetalhado resultado = new ResultadoDetalhado(
                75.0,
                "risco",
                "DefuzzificacaoCentroide",
                List.of(regra1, regra2)
        );

        assertEquals(2, resultado.regras().size());
        assertEquals(1, resultado.regrasAtivadas().size());
        assertEquals(regra1, resultado.regrasAtivadas().get(0));
    }

    @Test
    void deveImpedirAlteracaoDaListaDeRegras() {

        ResultadoDetalhado resultado = new ResultadoDetalhado(
                75.0,
                "risco",
                "DefuzzificacaoCentroide",
                List.of()
        );

        assertThrows(
                UnsupportedOperationException.class,
                () -> resultado.regras().add(
                        new AtivacaoRegra(
                                "Regra", "risco", "alto", 0.5
                        )
                )
        );
    }
}