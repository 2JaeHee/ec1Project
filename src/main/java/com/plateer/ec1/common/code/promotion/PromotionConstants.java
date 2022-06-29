package com.plateer.ec1.common.code.promotion;

import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;

@Component
public class PromotionConstants {
    public static final String ADMIN = "admin";

    public static final DateTimeFormatter YYYYMMDD = DateTimeFormatter.ofPattern("yyyyMMdd");
}
