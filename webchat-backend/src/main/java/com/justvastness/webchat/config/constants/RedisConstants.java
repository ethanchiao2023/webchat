package com.justvastness.webchat.config.constants;

/**
 * Redis Key 常量
 *
 * @author wangdong
 * @version 1.0.0
 * @date 2021/9/24 4:20 下午
 **/
public class RedisConstants {

    /**
     * dict
     */
    public static final String BASE_DICT_ALL = "bgs:base:dict:all";
    /**
     * Token 白名单
     */
    public static final String USER_TOKEN_WHITE_LIST = "bgs:user:token:whiteList";
    /**
     * URL 对应 角色缓存
     */
    public static final String USER_PERMISSION_ALL = "bgs:user:permission:all";

    public static String getKey(String prefix, Object suffix) {
        return prefix + ":" + suffix;
    }
}
