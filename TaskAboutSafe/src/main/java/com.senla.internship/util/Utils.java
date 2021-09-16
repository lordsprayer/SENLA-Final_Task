package com.senla.internship.util;

public class Utils {

    public static int[][] getMatrix(int[]v, int[]w, int W, int n) {
        int[][] m = new int[n+1][W+1];
        for(int j=0; j<= W; j++)  {
            m[0][j] = 0;
        }
        for(int i = 1; i <= n; i++)
            for (int j = 0; j <= W; j++) {
                if (w[i-1] > j) {
                    m[i][j] = m[i-1][j];
                } else {
                    m[i][j] = Math.max(m[i-1][j], m[i-1][j-w[i-1]] + v[i-1]);
                }
            }
        return m;
    }

    public static void printMatrix(int[][] m, int n, int W) {
        for(int i=0; i<=n; i++) {
            for(int j=0; j<=W; j++) {
                String str = String.format("%1$-3d", m[i][j]);
                System.out.print(" " + str + " ");
            }
            System.out.println();
        }
    }
}
