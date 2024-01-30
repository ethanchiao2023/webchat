package com.justvastness.webchat.config.exception;

import com.justvastness.webchat.config.enums.BaseResultEnum;
import com.justvastness.webchat.config.system.validate.validator.ValidateBizDataResult;
import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * 认证异常类
 *
 * @author wangdong
 * @date 2020/7/27 8:16 下午
 **/
@Getter
@EqualsAndHashCode(callSuper = true)
public class BizException extends RuntimeException {

    private final String code;

    public BizException(BaseResultEnum enums) {
        super(enums.getMessage());
        this.code = enums.getCode();
    }

    public BizException(Enum<? extends ValidateBizDataResult> enums) {
        super(((ValidateBizDataResult)enums).getMessage());
        this.code = ((ValidateBizDataResult)enums).getCode();
    }

    public BizException(String code,String message) {
        super(message);
        this.code = code;
    }
}
