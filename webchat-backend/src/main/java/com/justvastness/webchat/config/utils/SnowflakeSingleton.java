package com.justvastness.webchat.config.utils;

import cn.hutool.core.lang.Snowflake;

public class SnowflakeSingleton {
    private static volatile Snowflake snowflakeInstance = null;

    public static Snowflake getSnowflake() {
        if (snowflakeInstance == null) {
            synchronized(Snowflake.class) {
                if (snowflakeInstance == null) {
                    snowflakeInstance = new Snowflake();
                }
            }
        }
        return snowflakeInstance;
    }
}
