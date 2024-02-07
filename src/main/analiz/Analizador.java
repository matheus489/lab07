 package analiz;

import java.util.Arrays;

public class Analizador {
    public int[] acha_sequencia(double[] probs, int[] valores) {
        int n = probs.length;
        double[][] dp = new double[n + 1][n + 1];

        for (int i = 0; i <= n; i++) {
            Arrays.fill(dp[i], -1);
        }

        dp[0][0] = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                if (dp[i][j] >= 0) {
                    dp[i + 1][j] = Math.max(dp[i + 1][j], dp[i][j]);
                    double p = probs[i];
                    dp[i + 1][j + 1] = Math.max(dp[i + 1][j + 1], dp[i][j] + p * valores[i]);
                }
            }
        }

        int[] resposta = new int[n];
        int j = n;
        for (int i = n - 1; i >= 0; i--) {
            if (dp[n][i + 1] > dp[n][i]) {
                resposta[i] = 1;
                j--;
            }
        }

        int[] sequencia = new int[n];
        int pos = 0;
        for (int i = 0; i < n; i++) {
            if (resposta[i] == 1) {
                sequencia[i] = pos++;
            } else {
                for (int k = 0; k < n; k++) {
                    if (resposta[k] == 0) {
                        sequencia[k] = pos++;
                    }
                }
                break;
            }
        }
        return sequencia;
    }}