package com.senla.internship.service;

import com.senla.internship.model.Item;
import com.senla.internship.model.MatrixCharacteristic;
import com.senla.internship.model.Safe;

import java.util.ArrayList;
import java.util.List;


public class SafeService {

    public void fillInSafe(Safe safe, List<Item> sourceItems) {
        int [][]matrix = getMatrix(sourceItems, safe.getVolume());
        List<Item> safeItems = getSafeItems(matrix, sourceItems);
        safe.setItems(safeItems);
        System.out.println(safe);
        System.out.printf("Total volume %d, total price %d", safe.getUsedVolume(), safe.getItemsPrice());
    }

    private int[][] getMatrix(List<Item> items, int safeVolume) {
        int itemsCount = items.size();
        int[] itemsVolume = items.stream().mapToInt(Item::getVolume).toArray();
        int[] itemsPrice = items.stream().mapToInt(Item::getPrice).toArray();
        int[][] matrix = new int[itemsCount+1][safeVolume+1];
        for(int j=0; j<= safeVolume; j++)  {
            matrix[0][j] = 0;
        }
        for(int i = 1; i <= itemsCount; i++) {
            for (int j = 0; j <= safeVolume; j++) {
                if (itemsVolume[i - 1] > j) {
                    matrix[i][j] = matrix[i - 1][j];
                } else {
                    matrix[i][j] = Math.max(matrix[i - 1][j], matrix[i - 1][j - itemsVolume[i - 1]] + itemsPrice[i - 1]);
                }
            }
        }
        printMatrix(matrix);
        return matrix;
    }


    private void printMatrixHeader(int columnsNumber) {
        for(int i = 0; i<= columnsNumber; i++) {
            String format = String.format("%1$-3d", i);
            System.out.print(" " + format + " ");
        }
        System.out.println();
    }


    private void printMatrix(int[][] matrix) {
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

    private MatrixCharacteristic getCharacteristic(int[][] matrix) {
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

    private void addItemToSafeItems(List<Item> sourceItems, MatrixCharacteristic characteristic, List<Item> safeItems) {
        Item item = sourceItems.get(characteristic.getRow() - 1);
        safeItems.add(item);
        characteristic.setColumn(characteristic.getColumn() - item.getVolume());
        characteristic.setValue(characteristic.getValue() - item.getPrice());
    }

    private List<Item> getSafeItems(int[][] matrix, List<Item> sourceItems) {
        List<Item> safeItems= new ArrayList<>();
        MatrixCharacteristic characteristic = getCharacteristic(matrix);
        addItemToSafeItems(sourceItems, characteristic, safeItems);
        if (characteristic.getValue() == 0 && characteristic.getColumn() == 0) {
            return safeItems;
        } else {
            while (true) {
                for (int i = 0; i <= matrix.length; i++) {
                    if (matrix[i][characteristic.getColumn()] == characteristic.getValue()) {
                        characteristic.setRow(i);
                        break;
                    }
                }
                addItemToSafeItems(sourceItems, characteristic, safeItems);
                if (characteristic.getValue() == 0 && characteristic.getColumn() == 0) {
                    return safeItems;
                }
            }
        }
    }
}
