package com.justvastness.webchat.config.system.properties;

import lombok.Data;

/**
 * BgsSysProperties
 *
 * @author wangdong
 * @version 1.0.0
 * @date 2021/10/23 10:36 上午
 **/
@Data
public class MqProperties {

    /**
     * 消费失败重试次数
     */
    private Integer retryTimesWhenReceiveFailed;

}
