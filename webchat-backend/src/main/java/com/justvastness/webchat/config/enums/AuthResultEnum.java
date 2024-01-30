package com.justvastness.webchat.config.enums;

import com.justvastness.webchat.config.i18n.I18nUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * AuthResultEnum
 *
 * @author wangdong
 * @version 1.0.0
 * @date 2021/9/27 3:46 下午
 **/
@Getter
@AllArgsConstructor
public enum AuthResultEnum {

    /**
     * 账号信息不存在
     */
    LOGIN_NOT_USER("L0401"),
    /**
     * 尚未登入
     */
    LOGIN_NOT_LOGIN("L0401"),
    /**
     * 该账户已被禁用
     */
    LOGIN_USER_ACCOUNT_DISABLE("L0401"),
    /**
     * 用户名或密码错误
     */
    LOGIN_USERNAME_OR_PASSWORD_ERROR("L0401"),
    /**
     * 输入密码次数超限
     */
    LOGIN_INPUT_PASSWORD_EXCEED_LIMIT("L0401"),
    /**
     * token无效或已过期
     */
    LOGIN_TOKEN_INVALID_OR_EXPIRED("L0401"),
    /**
     * 访问未授权
     */
    LOGIN_USER_ACCESS_UNAUTHORIZED("L0403"),

    ;


    private final String code;

    public String getMessage() {
        return I18nUtil.message(this.toString());
    }
}
