package com.justvastness.webchat.config.system.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * BgsSysProperties
 *
 * @author wangdong
 * @version 1.0.0
 * @date 2021/10/23 10:36 上午
 **/
@Data
@Configuration
@ConfigurationProperties(prefix = "sys")
public class BgsSysProperties {

    private String domain = "";

    private FileProperties file;

    private UserProperties user;

    private MqProperties mq;

}
