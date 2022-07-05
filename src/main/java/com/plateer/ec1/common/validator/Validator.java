package com.plateer.ec1.common.validator;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;

@Component
public class Validator {
    public static BiPredicate<LocalDate, LocalDate> isPeriodValid = (date1, date2)
            -> LocalDate.now().plusDays(1).isAfter(date1) && LocalDate.now().isBefore(date2.plusDays(1));

    public static BiFunction<Integer, Double, Double> isCompareValid = (pram1, pram2)
            -> {
        if (pram1 > pram2) {
            return pram2;
        } else {
            return Double.valueOf(pram1);
        }
    };
}
