package br.com.motorfuzzy.conjuntos;

import br.com.motorfuzzy.funcoes.FuncaoTrapezoidal;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConjuntoFuzzyTest {

    @Test
    void deveCriarConjuntoFuzzy() {

        ConjuntoFuzzy conjunto = new ConjuntoFuzzy(
                "baixa",
                new FuncaoTrapezoidal(0, 0, 10, 20)
        );

        assertEquals("baixa", conjunto.getNome());
    }

    @Test
    void deveCalcularPertinencia() {

        ConjuntoFuzzy conjunto = new ConjuntoFuzzy(
                "baixa",
                new FuncaoTrapezoidal(0, 0, 10, 20)
        );

        assertEquals(0.5, conjunto.calcularPertinencia(15));
    }

    @Test
    void deveRejeitarNomeVazio() {

        assertThrows(
                IllegalArgumentException.class,
                () -> new ConjuntoFuzzy(
                        "",
                        new FuncaoTrapezoidal(0, 0, 10, 20)
                )
        );
    }

    @Test
    void deveRejeitarFuncaoNula() {

        assertThrows(
                IllegalArgumentException.class,
                () -> new ConjuntoFuzzy("baixa", null)
        );
    }
}