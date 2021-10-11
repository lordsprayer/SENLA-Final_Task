package com.senla.internship;

import com.senla.internship.dao.Repository;
import com.senla.internship.model.Item;
import java.util.List;

import static com.senla.internship.service.ItemService.*;
import static com.senla.internship.service.SafeService.fillInSafe;
import static com.senla.internship.util.Utils.*;

public class Main {

    public static void main(String[] args) {
        String filename = args[0];
        List<String> strings = getInfoFromFile(filename);
        Repository repository = new Repository(strings);
        int weight = repository.getWeight();
        List<Item> items = repository.getItems();
        int count = items.size();
        items.forEach(System.out::println);
        for(int i = 0; i<= weight; i++) {
            String format = String.format("%1$-3d", i);
            System.out.print(" " + format + " ");
        }
        System.out.println();
        int [][]matrix = getMatrix(getPrices(items), getVolumes(items), weight, count);
        printMatrix(matrix, count, weight);
        fillInSafe(items, matrix, weight);
    }
}