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

    @Override
    public String toString() {
        return "Vault{\n" +
                "items: \n" + items +
                " volume=" + volume +
                "}\n";
    }
}