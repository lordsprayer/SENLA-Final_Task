package com.senla.internship.service;

import com.senla.internship.dao.Repository;
import com.senla.internship.model.Item;
import com.senla.internship.model.MatrixCharacteristic;

import java.util.List;

public class MatrixService {

    public int[][] getMatrix(Repository repository) {
        List<Item> items = repository.getItems();
        int safeWeight = repository.getVolume();
        int itemsCount = items.size();
        int[] itemsWeight = items.stream().mapToInt(Item::getVolume).toArray();
        int[] itemsPrice = items.stream().mapToInt(Item::getPrice).toArray();
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

    private void printMatrixHeader(int columnsNumber) {
        for(int i = 0; i<= columnsNumber; i++) {
            String format = String.format("%1$-3d", i);
            System.out.print(" " + format + " ");
        }
        System.out.println();
    }


    public void printMatrix(int[][] matrix) {
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

    public MatrixCharacteristic getCharacteristic(int[][] matrix) {
        int max = matrix[matrix.length - 1][matrix[0].length - 1];
        MatrixCharacteristic characteristic = new MatrixCharacteristic(max);
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == max) {
                    characteristic.setRow(i);
                    characteristic.setColumn(j);
                    return characteristic;
                }
            }
        }
        return characteristic;
    }
}
