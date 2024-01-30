package com.justvastness.webchat.config.system.validate.annotation;

import com.justvastness.webchat.config.system.validate.validator.PatternValidator;
import com.justvastness.webchat.config.system.validate.validator.PatternValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * @author wangdong
 * @version 1.0.0
 * @date 2021/3/16 15:07
 **/
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = {PatternValidator.class})
public @interface PatternValid {

    String regexp();

    boolean required() default true;

    String message() default "";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
