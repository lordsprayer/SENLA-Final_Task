package com.senla.internship.util;

import com.senla.internship.dao.Repository;
import com.senla.internship.model.Item;

import java.util.ArrayList;
import java.util.List;

import static com.senla.internship.service.ItemService.getPrices;
import static com.senla.internship.service.ItemService.getVolumes;

public class Utils {

    public static int[][] getMatrix(Repository repository) {
        List<Item> items = repository.getItems();
        int safeWeight = repository.getWeight();
        int itemsCount = items.size();
        int[] itemsWeight = getVolumes(items);
        int[] itemsPrice = getPrices(items);
        int[][] matrix = new int[itemsCount+1][safeWeight+1];
        for(int j=0; j<= safeWeight; j++)  {
            matrix[0][j] = 0;
        }
        for(int i = 1; i <= itemsCount; i++) {
            for (int j = 0; j <= safeWeight; j++) {
                if (itemsWeight[i - 1] > j) {
                    matrix[i][j] = matrix[i - 1][j];
                } else {
                    matrix[i][j] = Math.max(matrix[i - 1][j], matrix[i - 1][j - itemsWeight[i - 1]] + itemsPrice[i - 1]);
                }
            }
        }
        return matrix;
    }

    public static void printMatrixHeader(int columnsNumber) {
        for(int i = 0; i<= columnsNumber; i++) {
            String format = String.format("%1$-3d", i);
            System.out.print(" " + format + " ");
        }
        System.out.println();
    }


    public static void printMatrix(int[][] matrix) {
        int columnsNumber = matrix[0].length;
        printMatrixHeader(columnsNumber - 1);
        for (int[] rowsNumber : matrix) {
            for (int j = 0; j < columnsNumber; j++) {
                String str = String.format("%1$-3d", rowsNumber[j]);
                System.out.print(" " + str + " ");
            }
            System.out.println();
        }
    }

    public static List<Integer> getIndexes (int[][] matrix) {
        List<Integer> indexes = new ArrayList<>();
        int max = matrix[matrix.length - 1][matrix[0].length - 1];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == max) {
                    indexes.add(i);
                    indexes.add(j);
                    return indexes;
                }
            }
        }
        return new ArrayList<>(2);
    }
}
