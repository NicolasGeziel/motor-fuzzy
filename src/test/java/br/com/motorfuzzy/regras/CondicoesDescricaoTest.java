package br.com.motorfuzzy.regras;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CondicoesDescricaoTest {

    @Test
    void deveDescreverCondicaoSimples() {

        CondicaoFuzzy condicao = new CondicaoSimples("temperatura", "alta");

        assertEquals("temperatura É alta", condicao.descrever());
    }

    @Test
    void deveDescreverCondicaoComposta() {

        CondicaoFuzzy condicao = new CondicaoE(
                new CondicaoSimples("temperatura", "alta"),
                new CondicaoNao(
                        new CondicaoSimples("umidade", "alta")
                )
        );

        assertEquals("(temperatura É alta E NÃO (umidade É alta))", condicao.descrever());
    }

    @Test
    void deveDescreverRegraCompleta() {

        RegraFuzzy regra = new RegraFuzzy(
                new CondicaoSimples("temperatura", "alta"),
                "risco",
                "alto"
        );

        assertEquals("SE temperatura É alta ENTÃO risco É alto", regra.descrever());
    }
}