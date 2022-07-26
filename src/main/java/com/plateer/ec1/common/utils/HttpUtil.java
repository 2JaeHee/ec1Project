package com.plateer.ec1.common.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.plateer.ec1.payment.vo.inicis.InicisApproveRes;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Component
public class HttpUtil {

    public static <T> HttpEntity<MultiValueMap<String, String>> httpEntityMultiValueMap(T parameter) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        httpHeaders.setAccept(Collections.singletonList(MediaType.ALL));

        ObjectMapper reqMapper = new ObjectMapper();
        MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>();
        Map<String, String> map = reqMapper.convertValue(parameter, new TypeReference<Map<String, String>>() {});
        multiValueMap.setAll(map);

        return new HttpEntity<>(multiValueMap, httpHeaders);
    }

}
