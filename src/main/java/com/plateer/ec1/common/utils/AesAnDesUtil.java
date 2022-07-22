package com.plateer.ec1.common.utils;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Component
public class AesAnDesUtil {
    private static final Charset ENCODING_TYPE = StandardCharsets.UTF_8;
    private static final String INSTANCE_TYPE = "AES/CBC/PKCS5Padding";

    public static String encodeSha(String planeText) {
        String hex = null;
        try {
            MessageDigest msg = MessageDigest.getInstance("SHA-512");
            msg.update(planeText.getBytes());

            hex = String.format("%128x", new BigInteger(1, msg.digest()));

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return hex;
    }

    public static String encryptAes(String str, String secretKey, String iv)  {
        String encryptedStr = str;
        SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getBytes(), "AES");
        IvParameterSpec ivParameterSpec = new IvParameterSpec(iv.getBytes(ENCODING_TYPE));
        try {
            Cipher cipher = Cipher.getInstance(INSTANCE_TYPE);
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);

            byte[] encrypted = cipher.doFinal(str.getBytes(ENCODING_TYPE));
            encryptedStr = new String(org.apache.tomcat.util.codec.binary.Base64.encodeBase64(encrypted));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return encryptedStr;
    }

    public static String decryptAes(String str, String secretKey, String iv) {
        String decodedStr = str;
        SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getBytes(), "AES");
        IvParameterSpec ivParameterSpec = new IvParameterSpec(iv.getBytes(ENCODING_TYPE));

        try {
            Cipher cipher = Cipher.getInstance(INSTANCE_TYPE);
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);
            byte[] decoded = Base64.decodeBase64(str.getBytes(ENCODING_TYPE));
            decodedStr = new String(cipher.doFinal(decoded), ENCODING_TYPE);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return decodedStr;
    }
}
