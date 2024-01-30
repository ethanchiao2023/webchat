package com.justvastness.webchat.config.exception;

import com.justvastness.webchat.config.enums.AuthResultEnum;
import com.justvastness.webchat.config.i18n.I18nUtil;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.security.core.AuthenticationException;

/**
 * 认证异常类
 *
 * @author wangdong
 * @date 2020/7/27 8:16 下午
 **/
@Getter
@EqualsAndHashCode(callSuper = true)
public class AuthException extends AuthenticationException {

    private final String code;

    public AuthException(AuthResultEnum authResultEnum) {
        super(I18nUtil.message(authResultEnum.getMessage()));
        this.code = authResultEnum.getCode();
    }
}
