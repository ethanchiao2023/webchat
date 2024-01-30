package com.justvastness.webchat.config.system.validate.annotation;


import com.justvastness.webchat.config.system.validate.validator.IdCardValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * 身份证号码校验注解
 * 支持18位、15位和港澳台的10位
 *
 * @author wangdong
 * @date 2021/3/9 10:12 上午
 **/
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = {IdCardValidator.class})
public @interface IdCardValid {

    boolean required() default true;

    String message() default "请输入正确的身份证号格式 !!!";

    String key() default "";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
