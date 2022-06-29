package com.plateer.ec1;

import com.plateer.ec1.claim.mapper.ClaimMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.function.BiPredicate;
import java.util.function.Predicate;

class Ec1ApplicationTests {


    @Test
    void test() {
        LocalDate strtDt = LocalDate.of(2022, 06, 27);
        LocalDate endDt = LocalDate.of(2022, 06, 29);

        BiPredicate<LocalDate, LocalDate> predicate = (date1, date2) -> LocalDate.now().plusDays(1).isAfter(date1) && LocalDate.now().isBefore(date2.plusDays(1));
        Predicate<LocalDate> predicate2 = (date) -> LocalDate.now().plusDays(1).isAfter(date);
        //현재날짜가 param데이터보다 크다
        Predicate<LocalDate> predicate3= (date) -> LocalDate.now().isBefore(date.plusDays(1));
        //현재날짜가 param보다 작다

        boolean a = predicate2.test(strtDt);
        boolean b = predicate3.test(endDt);
        boolean base = predicate.test(strtDt, endDt);
        System.out.println(a);
        System.out.println(b);
        System.out.println(base);
    }

    @Test
    void test2() {
        LocalDateTime test = LocalDateTime.of(2022, 06, 29, 12, 00);
        System.out.println(LocalDateTime.now().isBefore(test));
    }
}
