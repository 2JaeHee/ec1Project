package com.plateer.ec1.common.validator;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.function.BiPredicate;

@Component
public class DateValidator {
    private static final int STANDARD_DAY = 1;

    public static BiPredicate<LocalDate, LocalDate> isPeriodValid = (date1, date2)
            -> LocalDate.now().plusDays(STANDARD_DAY).isAfter(date1) && LocalDate.now().isBefore(date2.plusDays(STANDARD_DAY));
}
