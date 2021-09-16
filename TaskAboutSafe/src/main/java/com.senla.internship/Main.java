package com.senla.internship;

import com.senla.internship.model.Item;
import java.util.List;

import static com.senla.internship.dao.ItemDao.getCountLinesInFile;
import static com.senla.internship.dao.ItemDao.getLinesFromFile;
import static com.senla.internship.service.ItemService.*;
import static com.senla.internship.service.SafeService.fillInSafe;
import static com.senla.internship.util.Utils.getMatrix;
import static com.senla.internship.util.Utils.printMatrix;

public class Main {

    public static void main(String[] args) {
        String filename = args[0];
        int count = getCountLinesInFile(filename) - 1;
        String[] str = getLinesFromFile(filename);
        List<Item> items = getItemsFromString(str);
        assert str != null;
        int weight = Integer.parseInt(str[0]);
        items.forEach(System.out::println);
        for(int i = 0; i<= weight; i++) {
            String format = String.format("%1$-3d", i);
            System.out.print(" " + format + " ");
        }
        System.out.println();
        int [][]m = getMatrix(getPrices(items), getVolumes(items), weight, count);
        printMatrix(m, count, weight);
        fillInSafe(items, m, count, weight);
    }
}