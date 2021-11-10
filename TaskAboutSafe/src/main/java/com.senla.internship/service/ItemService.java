package com.senla.internship.service;

import com.senla.internship.dao.Repository;
import com.senla.internship.model.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.senla.internship.util.Utils.getIndexes;

public class ItemService {

    public static List<Item> createItems(int count) {
        List<Item> items = new ArrayList<>();
        int n = count;
        while(count > 0) {
            Item item = new Item(
                    "item " + (n-count +1), 1 + (int) (Math.random()*10), 1 + (int) (Math.random()*10));
            items.add(item);
            count --;
        }
        return items;
    }

    public static void printItems(Repository repository) {
        repository.getItems().forEach(System.out::println);
    }

    public static int[] getVolumes(List<Item> items) {
        return items.stream()
                .mapToInt(Item::getVolume)
                .toArray();
    }

    public static int[] getPrices(List<Item> items) {
        return items.stream()
                .mapToInt(Item::getPrice)
                .toArray();
    }

    public static List<Item> getItemList(int[][] matrix, Repository repository) {
        List<Item> itemList = repository.getItems();
        int count = itemList.size();
        int max = matrix[matrix.length - 1][matrix[0].length - 1];
        List<Integer> indexes = getIndexes(matrix);
        int index = indexes.get(0);
        int fullWeight = indexes.get(1);
        List<Item> items = new ArrayList<>();
        Item item1 = itemList.get(index - 1);
        items.add(item1);
        fullWeight -= item1.getVolume();
        max -= item1.getPrice();
        if (max == 0 && fullWeight == 0) {
            return items;
        } else {
            while (true) {
                for (int i = 0; i <= count; i++) {
                    if (matrix[i][fullWeight] == max) {
                        index = i;
                        break;
                    }
                }
                Item item2 = itemList.get(index - 1);
                items.add(item2);
                fullWeight -= item2.getVolume();
                max -= item2.getPrice();
                if (max == 0) {
                    return items;
                }
            }
        }
    }
}
