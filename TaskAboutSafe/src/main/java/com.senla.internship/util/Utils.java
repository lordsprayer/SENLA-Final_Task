package com.senla.internship.util;

import com.senla.internship.model.Item;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    public static List<Integer> getIndexes (int[][] matrix, int n, int weight) {
        List<Integer> indexes = new ArrayList<>();
        int max = matrix[n][weight];
        for (int i = 0; i <=n; i++) {
            for (int j = 0; j <= weight; j++) {
                if (matrix[i][j] == max) {
                    indexes.add(i);
                    indexes.add(j);
                    return indexes;
                }
            }
        }
        return null;
    }

    public static List<String> getInfoFromFile(String filename) {
        List<String> lines = new ArrayList<>();
        try {
            FileReader fr = new FileReader(filename);
            BufferedReader reader = new BufferedReader(fr);
            String line = reader.readLine();
            while (line != null) {
                lines.add(line);
                line = reader.readLine();
            }
        } catch (IOException e) {
            System.out.println("Error with read file");
        }
        return lines;
    }

    public static List<Item> getItemsFromStrings(List<String> strings) {
        List<Item> items = new ArrayList<>();
        String[] subStr;
        String delimiter = ", ";
        for(int i = 1; i< Objects.requireNonNull(strings).size(); i++) {
            Item item = new Item();
            subStr = strings.get(i).split(delimiter);
            item.setName(subStr[0]);
            item.setVolume(Integer.parseInt(subStr[1]));
            item.setPrice(Integer.parseInt(subStr[2]));
            items.add(item);
        }
        return items;
    }
}
