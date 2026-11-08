package br.com.motorfuzzy.inferencia;

import java.util.Collection;

public class AgregacaoMaximo {

    public double calcular(Collection<Double> graus) {

        if (graus == null || graus.isEmpty()) {
            return 0.0;
        }

        double maximo = 0.0;

        for (Double grau : graus) {

            if (grau == null || !Double.isFinite(grau) || grau < 0 || grau > 1) {
                throw new IllegalArgumentException("Os graus devem estar entre 0 e 1.");
            }
            maximo = Math.max(maximo, grau);
        }

        return maximo;
    }
}