package com.justvastness.webchat.config.exception;

import com.justvastness.webchat.config.enums.GlobalResultEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 自定义全局异常类
 *
 * @author wangdong
 * @date 2020/7/27 8:16 下午
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class GlobalException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private final String code;

    public GlobalException(GlobalResultEnum globalResultEnum) {
        super(globalResultEnum.getMessage());
        this.code = globalResultEnum.getCode();
    }

}
