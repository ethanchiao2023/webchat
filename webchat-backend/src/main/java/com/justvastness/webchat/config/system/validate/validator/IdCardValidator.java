package com.justvastness.webchat.config.system.validate.validator;

import cn.hutool.core.util.IdcardUtil;
import cn.hutool.core.util.StrUtil;
import com.justvastness.webchat.config.system.validate.annotation.IdCardValid;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 手机号校验
 *
 * @author wangdong
 * @date 2021/3/9 10:13 上午
 **/
public class IdCardValidator implements ConstraintValidator<IdCardValid, String> {

    private boolean required;

    /**
     * 初始化方法， 在执行isValid 方法前，会先执行此方法
     */
    @Override
    public void initialize(IdCardValid idCardValid) {
        this.required = idCardValid.required();
    }

    /**
     * 校验的具体逻辑实现
     */
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return StrUtil.isNotBlank(value) && IdcardUtil.isValidCard(value);
    }
}
