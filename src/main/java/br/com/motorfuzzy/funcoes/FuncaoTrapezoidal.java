package br.com.motorfuzzy.funcoes;

public class FuncaoTrapezoidal implements FuncaoPertinencia {

    private final double a;
    private final double b;
    private final double c;
    private final double d;

    public FuncaoTrapezoidal(double a, double b, double c, double d) {

        if (!Double.isFinite(a) || !Double.isFinite(b)
                || !Double.isFinite(c) || !Double.isFinite(d)) {
            throw new IllegalArgumentException("Os parâmetros devem ser números finitos.");
        }

        if (!(a <= b && b <= c && c <= d)) {
            throw new IllegalArgumentException("Os parâmetros devem respeitar: a <= b <= c <= d.");
        }

        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }

    @Override
    public double calcular(double valor) {

        if (!Double.isFinite(valor)) {
            throw new IllegalArgumentException("O valor deve ser um número finito.");
        }

        if (valor < a || valor > d) {
            return 0.0;
        }

        if (valor >= b && valor <= c) {
            return 1.0;
        }

        if (valor < b) {
            return (valor - a) / (b - a);
        }

        return (d - valor) / (d - c);
    }
}