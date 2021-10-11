package com.senla.internship.dao;

import com.senla.internship.model.Item;

import java.util.List;

import static com.senla.internship.util.Utils.getItemsFromStrings;

public class Repository {

    private final int weight;
    private final List<Item> items;

    public Repository(List<String> strings) {
        weight = Integer.parseInt(strings.get(0));
        items = getItemsFromStrings(strings);
    }

    public int getWeight() {
        return weight;
    }

    public List<Item> getItems() {
        return items;
    }

}
