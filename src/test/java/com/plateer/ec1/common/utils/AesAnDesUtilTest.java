package com.plateer.ec1.common.utils;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AesAnDesUtilTest {

    @Test
    @DisplayName("μνΈν")
    void encodeTest() {
        String pw = "12345";
        String s = AesAnDesUtil.encodeSha(pw);

    }
}