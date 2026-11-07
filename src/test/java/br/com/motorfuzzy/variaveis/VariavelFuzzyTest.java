package br.com.motorfuzzy.variaveis;

import br.com.motorfuzzy.funcoes.FuncaoTrapezoidal;
import br.com.motorfuzzy.funcoes.FuncaoTriangular;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class VariavelFuzzyTest {

    @Test
    void deveAdicionarConjuntos() {

        VariavelFuzzy temperatura = new VariavelFuzzy("temperatura");

        temperatura.adicionarConjunto(
                "baixa",
                new FuncaoTrapezoidal(0, 0, 10, 20)
        );

        temperatura.adicionarConjunto(
                "media",
                new FuncaoTriangular(15, 25, 35)
        );

        assertEquals(2, temperatura.getConjuntos().size());
    }

    @Test
    void deveFuzzificarValorCorretamente() {

        VariavelFuzzy temperatura = new VariavelFuzzy("temperatura");

        temperatura.adicionarConjunto(
                "baixa",
                new FuncaoTrapezoidal(0, 0, 10, 20)
        );

        temperatura.adicionarConjunto(
                "media",
                new FuncaoTriangular(15, 25, 35)
        );

        temperatura.adicionarConjunto(
                "alta",
                new FuncaoTrapezoidal(30, 40, 50, 50)
        );

        Map<String, Double> resultado = temperatura.fuzzificar(25);

        assertEquals(0.0, resultado.get("baixa"));
        assertEquals(1.0, resultado.get("media"));
        assertEquals(0.0, resultado.get("alta"));
    }

    @Test
    void deveRejeitarConjuntosDuplicados() {

        VariavelFuzzy temperatura = new VariavelFuzzy("temperatura");

        temperatura.adicionarConjunto(
                "baixa",
                new FuncaoTrapezoidal(0, 0, 10, 20)
        );

        assertThrows(
                IllegalArgumentException.class,
                () -> temperatura.adicionarConjunto(
                        "baixa",
                        new FuncaoTriangular(0, 5, 10)
                )
        );
    }

    @Test
    void deveRejeitarNomeDeVariavelVazio() {

        assertThrows(
                IllegalArgumentException.class,
                () -> new VariavelFuzzy("")
        );
    }

    @Test
    void deveImpedirAlteracaoDiretaDosResultados() {

        VariavelFuzzy temperatura = new VariavelFuzzy("temperatura");

        temperatura.adicionarConjunto(
                "baixa",
                new FuncaoTrapezoidal(0, 0, 10, 20)
        );

        Map<String, Double> resultado = temperatura.fuzzificar(10);

        assertThrows(
                UnsupportedOperationException.class,
                () -> resultado.put("alta", 1.0)
        );
    }
}