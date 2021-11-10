package com.senla.internship.service;

import com.senla.internship.dao.Repository;
import com.senla.internship.model.Safe;
import com.senla.internship.model.SafeSpecification;


public class SafeService {

    public SafeSpecificationService safeSpecificationService = new SafeSpecificationService();

    public void fillInSafe(Safe safe, Repository repository) {
        MatrixService matrixService = new MatrixService();
        int [][]matrix = matrixService.getMatrix(repository);
        matrixService.printMatrix(matrix);
        SafeSpecification specification = safeSpecificationService.getSafeSpecification(matrix, repository);
        safe.setItems(specification);
        System.out.println(safe);
        System.out.printf("Total volume %d, total price %d", specification.getVolume(), specification.getPrice());
    }
}
