package com.senla.courses.api;

import java.time.LocalDate;

public interface IPriceDynamics {
    Double getAvgCost();
    LocalDate getCurrentDate();
}
