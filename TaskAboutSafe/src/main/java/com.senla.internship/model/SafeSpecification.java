package com.senla.internship.model;

import java.util.ArrayList;
import java.util.List;

public class SafeSpecification {

    private int price;
    private int volume;
    private List<Item> items;

    public SafeSpecification() {
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItem(Item item) {
        if(items == null) {
            items = new ArrayList<>();
        }
        items.add(item);
    }
}
