package com.senla.internship.model;

import java.util.List;

public class Safe {

    private List<Item> items;
    private final int volume;

    public Safe(int volume) {
        this.volume = volume;
    }

    public void setItems(SafeSpecification specification) {
        this.items = specification.getItems();
    }

    @Override
    public String toString() {
        return "Vault{\n" +
                "items: \n" + items +
                " volume=" + volume +
                "}\n";
    }
}