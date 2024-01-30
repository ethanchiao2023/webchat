package com.justvastness.webchat.config.logback;


import ch.qos.logback.classic.PatternLayout;

/**
 * logback属性添加
 *
 * @author wangdong
 * @date 2020/7/31 8:27 下午
 **/
public class BHPatternLayout extends PatternLayout {

    static {
        DEFAULT_CONVERTER_MAP.put("ip", IPConverter.class.getName());
    }
}
