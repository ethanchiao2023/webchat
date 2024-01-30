package com.justvastness.webchat.config.system.validate.validator;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.justvastness.webchat.config.enums.GlobalResultEnum;
import com.justvastness.webchat.config.exception.GlobalException;
import com.justvastness.webchat.config.system.validate.annotation.PatternValid;
import lombok.extern.slf4j.Slf4j;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;
import java.util.regex.PatternSyntaxException;

/**
 * @author wangdong
 * @version 1.0.0
 * @date 2021/3/16 15:08
 **/
@Slf4j
public class PatternValidator implements ConstraintValidator<PatternValid, Object> {

    private boolean required;

    private java.util.regex.Pattern pattern;

    @Override
    public void initialize(PatternValid patternValid) {
        this.required = patternValid.required();
        try {
            this.pattern = java.util.regex.Pattern.compile(patternValid.regexp());
        } catch (PatternSyntaxException var8) {
            throw new GlobalException(GlobalResultEnum.GLOBAL_FAIL);
        }
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (!required) {
            if (value instanceof String) {
                if (StrUtil.isBlank((String) value)) {
                    return true;
                }
            }
            if (Objects.isNull(value)) {
                return true;
            }
        }
        if (Objects.isNull(value)) {
            return false;
        }
        if (Objects.isNull(pattern) || ObjectUtil.isNull(value)) {
            return false;
        }
        if (value instanceof CharSequence) {
            return StrUtil.isNotBlank((CharSequence) value) && pattern.matcher((CharSequence) value).matches();
        } else {
            String var = StrUtil.toString(value);
            return StrUtil.isNotBlank(var) && pattern.matcher(var).matches();
        }
    }
}
