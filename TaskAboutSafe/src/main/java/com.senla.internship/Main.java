package com.senla.internship;

import com.senla.internship.dao.Repository;
import com.senla.internship.model.Safe;
import com.senla.internship.service.SafeService;

public class Main {

    public static void main(String[] args) {
        SafeService safeService = new SafeService();
        String fileName = args[0];
        Repository repository = new Repository(fileName);
        Safe safe = new Safe(repository.getVolume());
        repository.printItems();
        safeService.fillInSafe(safe, repository);
    }
}