package com.senla.internship;

import com.senla.internship.dao.Repository;
import com.senla.internship.model.Safe;
import com.senla.internship.service.SafeService;

public class Main {

    public static void main(String[] args) {
        String fileName = args[0];
        Repository repository = new Repository(fileName);
        repository.printItems();
        Safe safe = new Safe(repository.getVolume());
        SafeService safeService = new SafeService();
        safeService.fillInSafe(safe, repository.getItems());
    }
}