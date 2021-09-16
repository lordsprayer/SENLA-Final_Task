package com.senla.internship.model;

public class Item {
    private String name;
    private int volume;
    private int price;

    public Item(String name, int volume, int price) {
        this.name = name;
        this.volume = volume;
        this.price = price;
    }

    public Item() {
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Item{" +
                "name='" + name + '\'' +
                ", volume=" + volume +
                ", price=" + price +
                "}\n";
    }
}
