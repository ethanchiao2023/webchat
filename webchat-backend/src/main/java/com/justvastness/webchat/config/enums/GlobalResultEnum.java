package com.justvastness.webchat.config.enums;


import com.justvastness.webchat.config.i18n.I18nUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum GlobalResultEnum {
    /**
     * SUCCESS
     */
    GLOBAL_SUCCESS("00000"),
    /**
     * FAIL
     */
    GLOBAL_FAIL("00001"),
    /**
     * 不支持该请求方法
     */
    GLOBAL_REQUEST_METHOD_NOT_SUPPORTED("00002"),
    /**
     * 请求参数错误
     */
    GLOBAL_REQUEST_PARAM_ERROR("00003"),
    /**
     * 应用程序错误
     */
    GLOBAL_APPLICATION_EXCEPTION("00004"),

    ;

    /**
     * 编码
     */
    private final String code;


    public String getMessage() {
        return I18nUtil.message(this.toString());
    }
}
