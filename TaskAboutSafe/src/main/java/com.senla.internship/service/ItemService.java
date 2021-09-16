package com.senla.internship.service;

import com.senla.internship.model.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ItemService {

    public static List<Item> getItemsFromString(String[] str) {
        List<Item> items = new ArrayList<>();
        String[] subStr;
        String delimiter = ", ";
        for(int i = 1; i< Objects.requireNonNull(str).length; i++) {
            Item item = new Item();
            subStr = str[i].split(delimiter);
            item.setName(subStr[0]);
            item.setVolume(Integer.parseInt(subStr[1]));
            item.setPrice(Integer.parseInt(subStr[2]));
            items.add(item);
        }
        return items;
    }

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

    public static List<Item> getItemList(List<Item> itemList, int[][] m, int n, int W) {
        int max = 0;
        int index = 0;
        int fullWeight = 0;

        for (int i = 0; i <=n; i++) {
            for (int j = 0; j <= W; j++) {
                if (m[i][j] > max) {
                    max = m[i][j];
                    index = i;
                    fullWeight = j;
                }
            }
        }

        List<Item> items = new ArrayList<>();
        Item item1 = itemList.get(index - 1);
        items.add(item1);
        fullWeight -= item1.getVolume();
        max -= item1.getPrice();
        if (max == 0) {
            return items;
        } else {
            while (true) {
                //max = 0;
                for (int i = 0; i < n + 1; i++) {
                    if (m[i][fullWeight] == max) {
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
