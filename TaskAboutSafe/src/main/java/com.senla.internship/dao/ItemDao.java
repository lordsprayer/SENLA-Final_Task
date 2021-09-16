package com.senla.internship.dao;

import java.io.FileReader;
import java.io.IOException;

public class ItemDao {

    public static int getCountLinesInFile(String file) {
        int count = 0;
        int symbol;
        try(FileReader fileReader = new FileReader(file)) {
            do {
                symbol = fileReader.read();
                if((char)symbol == '\n') {
                    count++;
                }
            } while (fileReader.ready());
        } catch (IOException e) {
            System.out.println("Error with read file");
        }
        return count;
    }

    public static String[] getLinesFromFile(String file) {
        int count = getCountLinesInFile(file);
        String[] lines = new String[count];
        StringBuilder s;
        int symbol;
        int i;
        if(count <= 0) {
            return null;
        }
        try(FileReader fileReader = new FileReader(file)) {
            s = new StringBuilder();
            i=0;
            do {
                symbol= fileReader.read();
                if((char)symbol == '\n') {
                    s = new StringBuilder(s.substring(0, s.length() - 1));
                    lines[i] = s.toString();
                    s = new StringBuilder();
                    i++;
                } else {
                    s.append((char) symbol);
                }
            } while (fileReader.ready());
        } catch (IOException e) {
            System.out.println("Error with read file");
        }
        return lines;
    }
}
