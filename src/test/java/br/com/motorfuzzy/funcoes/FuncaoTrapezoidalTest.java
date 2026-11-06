package br.com.motorfuzzy.funcoes;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FuncaoTrapezoidalTest {

    @Test
    void deveRetornarZeroForaDoConjunto() {
        FuncaoTrapezoidal funcao = new FuncaoTrapezoidal(0, 2, 6, 8);

        assertEquals(0.0, funcao.calcular(-1));
        assertEquals(0.0, funcao.calcular(9));
    }

    @Test
    void deveRetornarUmNoNucleo() {
        FuncaoTrapezoidal funcao = new FuncaoTrapezoidal(0, 2, 6, 8);

        assertEquals(1.0, funcao.calcular(4));
    }

    @Test
    void deveCalcularSubidaCorretamente() {
        FuncaoTrapezoidal funcao = new FuncaoTrapezoidal(0, 2, 6, 8);

        assertEquals(0.5, funcao.calcular(1));
    }

    @Test
    void deveCalcularDescidaCorretamente() {
        FuncaoTrapezoidal funcao = new FuncaoTrapezoidal(0, 2, 6, 8);

        assertEquals(0.5, funcao.calcular(7));
    }

    @Test
    void deveRejeitarParametrosInvalidos() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new FuncaoTrapezoidal(5, 2, 6, 8)
        );
    }
}
