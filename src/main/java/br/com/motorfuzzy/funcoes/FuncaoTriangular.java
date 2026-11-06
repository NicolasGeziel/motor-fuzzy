package br.com.motorfuzzy.funcoes;

public class FuncaoTriangular implements FuncaoPertinencia {

    private final double a;
    private final double b;
    private final double c;

    public FuncaoTriangular(double a, double b, double c) {

        if (!Double.isFinite(a) || !Double.isFinite(b) || !Double.isFinite(c)) {
            throw new IllegalArgumentException("Os parâmetros devem ser números finitos.");
        }

        if (!(a <= b && b <= c)) {
            throw new IllegalArgumentException("Os parâmetros devem respeitar: a <= b <= c.");
        }

        this.a = a;
        this.b = b;
        this.c = c;
    }

    @Override
    public double calcular(double valor) {

        if (!Double.isFinite(valor)) {
            throw new IllegalArgumentException("O valor deve ser um número finito.");
        }

        if (valor < a || valor > c) {
            return 0.0;
        }

        if (valor == b) {
            return 1.0;
        }

        if (valor < b) {
            return (valor - a) / (b - a);
        }

        return (c - valor) / (c - b);
    }
}