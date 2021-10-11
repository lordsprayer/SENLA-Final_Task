package com.senla.internship.service;

import com.senla.internship.model.Item;
import com.senla.internship.model.Safe;

import java.util.List;

import static com.senla.internship.service.ItemService.getItemList;

public class SafeService {

    public static int getRealVolume(Safe safe) {
        List<Item> items = safe.getItems();
        return items.stream()
                .mapToInt(Item::getVolume)
                .sum();
    }

    public static int getPrice(Safe safe) {
        List<Item> items = safe.getItems();
        return items.stream()
                .mapToInt(Item::getPrice)
                .sum();
    }

    public static Safe fillInSafe(List<Item> items, int[][] matrix, int weight) {
        List<Item> itemList = getItemList(items, matrix, weight);
        Safe safe = new Safe(weight);
        safe.setItems(itemList);
        int totalPrice = getPrice(safe);
        int totalVolume = getRealVolume(safe);
        System.out.println(safe);
        System.out.printf("Total volume %d, total price %d", totalVolume, totalPrice);
        return safe;
    }
}
