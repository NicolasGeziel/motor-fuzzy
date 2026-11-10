package br.com.motorfuzzy.nucleo;

import br.com.motorfuzzy.explicabilidade.ResultadoDetalhado;
import br.com.motorfuzzy.funcoes.FuncaoTriangular;
import br.com.motorfuzzy.inferencia.ResultadoInferencia;
import br.com.motorfuzzy.regras.CondicaoE;
import br.com.motorfuzzy.regras.CondicaoSimples;
import br.com.motorfuzzy.regras.RegraFuzzy;
import br.com.motorfuzzy.variaveis.VariavelFuzzy;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class MotorFuzzyTest {

    @Test
    void deveExecutarInferenciaMamdani() {

        VariavelFuzzy temperatura = new VariavelFuzzy("temperatura");
        temperatura.adicionarConjunto("alta", new FuncaoTriangular(20, 40, 60));

        VariavelFuzzy umidade = new VariavelFuzzy("umidade");
        umidade.adicionarConjunto("baixa", new FuncaoTriangular(0, 20, 40));

        VariavelFuzzy risco = new VariavelFuzzy("risco");
        risco.adicionarConjunto("alto", new FuncaoTriangular(50, 75, 100));

        MotorFuzzy motor = new MotorFuzzy();
        motor.adicionarEntrada(temperatura);
        motor.adicionarEntrada(umidade);
        motor.adicionarSaida(risco);

        RegraFuzzy regra = new RegraFuzzy(
                new CondicaoE(
                        new CondicaoSimples("temperatura", "alta"),
                        new CondicaoSimples("umidade", "baixa")
                ),
                "risco",
                "alto"
        );

        motor.adicionarRegra(regra);

        ResultadoInferencia resultado = motor.inferir(
                Map.of(
                        "temperatura", 30.0,
                        "umidade", 25.0
                ),
                "risco"
        );

        assertEquals(0.5, resultado.calcularPertinencia(75));
        assertEquals(0.0, resultado.calcularPertinencia(20));
    }

    @Test
    void deveExecutarInferenciaEDefuzzificacao() {

        VariavelFuzzy temperatura = new VariavelFuzzy("temperatura");

        temperatura.adicionarConjunto("alta", new FuncaoTriangular(20, 40, 60));

        VariavelFuzzy risco = new VariavelFuzzy("risco");

        risco.adicionarConjunto("alto", new FuncaoTriangular(50, 75, 100));

        MotorFuzzy motor = new MotorFuzzy();

        motor.adicionarEntrada(temperatura);
        motor.adicionarSaida(risco);

        motor.adicionarRegra(
                new RegraFuzzy(
                        new CondicaoSimples("temperatura", "alta"),
                        "risco",
                        "alto"
                )
        );

        double resultado = motor.calcular(
                Map.of("temperatura", 30.0),
                "risco",
                0,
                100
        );

        assertEquals(75.0, resultado, 0.001);
    }

    @Test
    void deveRejeitarVariavelDeSaidaInexistente() {
        MotorFuzzy motor = new MotorFuzzy();

        assertThrows(
                IllegalArgumentException.class,
                () -> motor.inferir(Map.of(), "risco")
        );
    }

    @Test
    void deveRejeitarEntradaAusente() {

        MotorFuzzy motor = new MotorFuzzy();

        VariavelFuzzy temperatura = new VariavelFuzzy("temperatura");
        VariavelFuzzy risco = new VariavelFuzzy("risco");

        motor.adicionarEntrada(temperatura);
        motor.adicionarSaida(risco);

        assertThrows(
                IllegalArgumentException.class,
                () -> motor.inferir(Map.of(), "risco")
        );
    }

    @Test
    void deveCalcularResultadoComExplicabilidade() {

        VariavelFuzzy temperatura = new VariavelFuzzy("temperatura");

        temperatura.adicionarConjunto(
                "alta",
                new FuncaoTriangular(20, 40, 60)
        );

        VariavelFuzzy risco = new VariavelFuzzy("risco");

        risco.adicionarConjunto(
                "alto",
                new FuncaoTriangular(50, 75, 100)
        );

        MotorFuzzy motor = new MotorFuzzy();

        motor.adicionarEntrada(temperatura);
        motor.adicionarSaida(risco);

        motor.adicionarRegra(
                new RegraFuzzy(
                        new CondicaoSimples("temperatura", "alta"),
                        "risco",
                        "alto"
                )
        );

        ResultadoDetalhado resultado = motor.calcularDetalhado(
                Map.of("temperatura", 30.0),
                "risco",
                0,
                100
        );

        assertEquals(75.0, resultado.valorNumerico(), 0.001);

        assertEquals("risco", resultado.variavelSaida());

        assertEquals(
                "DefuzzificacaoCentroide",
                resultado.metodoDefuzzificacao()
        );

        assertEquals(1, resultado.regras().size());

        assertEquals(
                0.5,
                resultado.regras().get(0).forcaAtivacao()
        );

        assertEquals(
                "SE temperatura É alta ENTÃO risco É alto",
                resultado.regras().get(0).descricao()
        );
    }
}