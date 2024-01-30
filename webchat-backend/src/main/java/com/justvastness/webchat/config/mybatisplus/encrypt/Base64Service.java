package com.justvastness.webchat.config.mybatisplus.encrypt;

import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.symmetric.AES;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

/**
 * Base64Service
 *
 * @author wangdong
 * @version 1.0.0
 * @date 2021/10/13 4:53 下午
 **/
@Component
public class Base64Service {


    private static final AES AES = SecureUtil.aes("test".getBytes(StandardCharsets.UTF_8));

    /**
     * 加密
     *
     * @return java.lang.String
     * @author wangdong
     * @date 2021/10/13 4:57 下午
     **/
    public static String encrypt(String data) {
        return AES.encryptBase64(data, StandardCharsets.UTF_8);
    }

    /**
     * 解密
     *
     * @return java.lang.String
     * @author wangdong
     * @date 2021/10/13 4:57 下午
     **/
    public static String decrypt(String data) {
        return AES.decryptStr(data, StandardCharsets.UTF_8);
    }
}
