package com.senla.internship.model;

import java.util.List;

public class Safe {

    private List<Item> items;
    private final int volume;

    public Safe(int volume) {
        this.volume = volume;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public int getVolume() {
        return volume;
    }

    public int getUsedVolume() {
        return items.stream().mapToInt(Item::getVolume).sum();
    }

    public int getItemsPrice() {
        return items.stream().mapToInt(Item::getPrice).sum();
    }


    @Override
    public String toString() {
        return "Vault{\n" +
                "items: \n" + items +
                " volume=" + volume +
                "}\n";
    }
}