package com.justvastness.webchat.config.system.validate.annotation;

import com.justvastness.webchat.config.system.validate.validator.PhoneValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * 手机号校验注解
 * 是否为座机号码+手机号码（中国）+手机号码（香港）+手机号码（台湾）+手机号码（澳门）
 *
 * @author wangdong
 * @date 2021/3/9 10:12 上午
 **/
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = {PhoneValidator.class})
public @interface PhoneValid {

    boolean required() default true;

    String message() default "请输入正确的手机号码格式 !!!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
