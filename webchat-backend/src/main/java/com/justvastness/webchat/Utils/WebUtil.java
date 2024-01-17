package com.justvastness.webchat.Utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class WebUtil {


    private static volatile ObjectMapper mapper = null;
    // ObjectMapper的实例只需要一个，使用双重校验锁的单例模式，提供一个方法返回 ObjectMapper 实例
    // 需要把消息中的日期转换为 年-月-日 时:分:秒 的形式
    private static ObjectMapper getMapper() {
        if (mapper == null) {
            synchronized (WebUtil.class) {
                if (mapper == null) {
                    mapper = new ObjectMapper();
                    DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    mapper.setDateFormat(format);
                }
            }
        }
        return mapper;
    }

    // 序列化：将java对象转化为json字符串
    public static String write(Object o) {
        try {
            return getMapper().writeValueAsString(o);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("序列化为json字符串出错", e);
        }
    }

    // 反序列化：将json字符串转化为java对象，这里使用重载提供两种反序列化操作，一是将json字符串反序列化为java对象，二是将输入流中json字符串反序列化为java对象
    public static  <T> T read(InputStream inputStream, Class<T> tClass) {
        try {
            return getMapper().readValue(inputStream, tClass);
        } catch (IOException e) {
            throw new RuntimeException("反序列化为java对象出错", e);
        }
    }

    public static <T> T read(String json, Class<T> tClass) {
        try {
            return getMapper().readValue(json, tClass);
        } catch (IOException e) {
            throw new RuntimeException("反序列化为java对象出错", e);
        }
    }
}
