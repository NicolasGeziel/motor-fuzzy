package br.com.motorfuzzy.inferencia;

import br.com.motorfuzzy.conjuntos.ConjuntoFuzzy;
import br.com.motorfuzzy.funcoes.FuncaoTriangular;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ImplicacaoMamdaniTest {

    @Test
    void deveLimitarPertinenciaPelaAtivacao() {

        ConjuntoFuzzy alto = new ConjuntoFuzzy(
                "alto",
                new FuncaoTriangular(50, 75, 100)
        );

        ImplicacaoMamdani implicacao = new ImplicacaoMamdani();

        assertEquals(0.6, implicacao.calcular(0.6, alto, 75));
        assertEquals(0.4, implicacao.calcular(0.6, alto, 60));
    }

    @Test
    void deveRejeitarAtivacaoInvalida() {

        ConjuntoFuzzy alto = new ConjuntoFuzzy(
                "alto",
                new FuncaoTriangular(50, 75, 100)
        );

        ImplicacaoMamdani implicacao = new ImplicacaoMamdani();

        assertThrows(
                IllegalArgumentException.class,
                () -> implicacao.calcular(1.5, alto, 75)
        );
    }
}