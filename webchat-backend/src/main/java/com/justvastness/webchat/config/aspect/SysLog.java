package com.justvastness.webchat.config.aspect;

import java.lang.annotation.*;

/**
 * SysLog
 *
 * @author wangdong
 * @version 1.0.0
 * @date 2021/10/13 9:54 上午
 **/
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SysLog {

    String type() default "DEFAULT";

    /**
     * 接口描述
     **/
    String value();

    /**
     * 是否保存请求的参数
     */
    boolean isSaveRequestData() default true;

    /**
     * 是否保存响应的参数
     */
    boolean isSaveResponseData() default true;
}
