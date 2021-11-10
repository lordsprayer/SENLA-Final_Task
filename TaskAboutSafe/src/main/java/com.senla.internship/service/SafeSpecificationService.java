package com.senla.internship.service;

import com.senla.internship.dao.Repository;
import com.senla.internship.model.Item;
import com.senla.internship.model.MatrixCharacteristic;
import com.senla.internship.model.SafeSpecification;

import java.util.List;


public class SafeSpecificationService {

    private final MatrixService matrixService = new MatrixService();

    private void addItemToSpecification(List<Item> itemList, MatrixCharacteristic characteristic, SafeSpecification specification) {
        int index = characteristic.getRow();
        Item item = itemList.get(index - 1);
        specification.setItem(item);
        characteristic.setColumn(characteristic.getColumn() - item.getVolume());
        characteristic.setValue(characteristic.getValue() - item.getPrice());
    }

    public SafeSpecification getSafeSpecification(int[][] matrix, Repository repository) {
        List<Item> itemList = repository.getItems();
        MatrixCharacteristic characteristic = matrixService.getCharacteristic(matrix);
        SafeSpecification specification= new SafeSpecification();
        specification.setPrice(characteristic.getValue());
        specification.setVolume(characteristic.getColumn());
        addItemToSpecification(itemList, characteristic, specification);
        if (characteristic.getValue() == 0 && characteristic.getColumn() == 0) {
            return specification;
        } else {
            while (true) {
                for (int i = 0; i <= matrix.length; i++) {
                    if (matrix[i][characteristic.getColumn()] == characteristic.getValue()) {
                        characteristic.setRow(i);
                        break;
                    }
                }
                addItemToSpecification(itemList, characteristic, specification);
                if (characteristic.getValue() == 0 && characteristic.getColumn() == 0) {
                    return specification;
                }
            }
        }
    }
}
