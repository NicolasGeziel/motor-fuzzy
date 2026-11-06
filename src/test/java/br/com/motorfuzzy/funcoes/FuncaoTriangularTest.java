package br.com.motorfuzzy.funcoes;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FuncaoTriangularTest {
    @Test
    void deveRetornarZeroForaDoConjunto() {
        FuncaoTriangular funcaoTriangular = new FuncaoTriangular(0,5,10);
        assertEquals(0.0, funcaoTriangular.calcular(-1));
        assertEquals(0.0,funcaoTriangular.calcular(10));
    }
    @Test
    void deveRetornarUmNoPico() {
        FuncaoTriangular funcaoTriangular = new FuncaoTriangular(0, 5, 10);
        assertEquals(1.0,funcaoTriangular.calcular(5));
    }
    @Test
    void deveCalcularSubidaCorretamente() {
        FuncaoTriangular funcao = new FuncaoTriangular(0, 5, 10);

        assertEquals(0.5, funcao.calcular(2.5));
    }

    @Test
    void deveCalcularDescidaCorretamente() {
        FuncaoTriangular funcao = new FuncaoTriangular(0, 5, 10);

        assertEquals(0.5, funcao.calcular(7.5));
    }

    @Test
    void deveRecusarValoresInvalidos(){
        assertThrows(IllegalArgumentException.class,()->new FuncaoTriangular(5,2,10));
    }
}
