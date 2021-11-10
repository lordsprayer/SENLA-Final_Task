package com.senla.internship;

import com.senla.internship.dao.Repository;

import static com.senla.internship.service.ItemService.printItems;
import static com.senla.internship.service.SafeService.fillInSafe;
import static com.senla.internship.util.Utils.getMatrix;
import static com.senla.internship.util.Utils.printMatrix;

public class Main {

    public static void main(String[] args) {
        String fileName = args[0];
        Repository repository = new Repository(fileName);
        printItems(repository);
        int [][]matrix = getMatrix(repository);
        printMatrix(matrix);
        fillInSafe(matrix, repository);
    }
}