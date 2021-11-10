package com.senla.internship.dao;

import com.senla.internship.model.Item;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Repository {

    private final int weight;
    private final List<Item> items;

    public Repository(String fileName) {
        weight = getWeightFromStrings(fileName);
        items = getItemsFromStrings(fileName);
    }

    public int getWeight() {
        return weight;
    }

    public List<Item> getItems() {
        return items;
    }

    private List<String> getInfoFromFile(String fileName) {
        List<String> lines = new ArrayList<>();
        try {
            FileReader fr = new FileReader(fileName);
            BufferedReader reader = new BufferedReader(fr);
            String line = reader.readLine();
            while (line != null) {
                lines.add(line);
                line = reader.readLine();
            }
        } catch (IOException e) {
            System.out.println("Error with read file");
        }
        return lines;
    }

    private int getWeightFromStrings(String fileName)  {
        return Integer.parseInt(getInfoFromFile(fileName).get(0));
    }

    private List<Item> getItemsFromStrings(String filename) {
        List<String> strings = getInfoFromFile(filename);
        List<Item> items = new ArrayList<>();
        String[] subStr;
        String delimiter = ", ";
        for(int i = 1; i< strings.size(); i++) {
            Item item = new Item();
            subStr = strings.get(i).split(delimiter);
            item.setName(subStr[0]);
            item.setVolume(Integer.parseInt(subStr[1]));
            item.setPrice(Integer.parseInt(subStr[2]));
            items.add(item);
        }
        return items;
    }

}
