package com.justvastness.webchat.config.exception;

import lombok.extern.slf4j.Slf4j;
import com.justvastness.webchat.config.enums.GlobalResultEnum;
import com.justvastness.webchat.config.i18n.I18nUtil;
import com.justvastness.webchat.config.result.Result;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

/**
 * 异常处理器
 *
 * @author wangdong
 * @version 1.0.0
 * @date 2020/7/27 20:17
 **/
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    private GlobalExceptionHandler() {
    }

    /**
     * Valid 参数校验异常拦截
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result<String> methodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error(e.getMessage(), e);
        return Result.fail(GlobalResultEnum.GLOBAL_REQUEST_PARAM_ERROR.getCode(), getError(e.getBindingResult().getAllErrors()));
    }

    @SuppressWarnings("all")
    private String getError(List<ObjectError> allErrors) {
        StringBuilder message = new StringBuilder();
        for (ObjectError error : allErrors) {
            try {
//                final String objectName = error.getObjectName().toUpperCase(Locale.ROOT);
                final String errorKey = error.getDefaultMessage().replace("{","").replace("}","");
//                final DefaultMessageSourceResolvable argument = (DefaultMessageSourceResolvable) error.getArguments()[0];
//                final String field = argument.getDefaultMessage().toUpperCase(Locale.ROOT);
//                String key = objectName + "_" + field;
                message.append(I18nUtil.message(errorKey)).append(" & ");
            } catch (Exception e) {
                message.append(GlobalResultEnum.GLOBAL_REQUEST_PARAM_ERROR.getMessage()).append(" & ");
            }
        }
        return message.substring(0, message.length() - 3);
    }

    /**
     * 处理自定义异常
     */
    @ExceptionHandler(AuthException.class)
    public Result<String> authException(AuthException e) {
        log.error("AuthException -> [{}]", e.getMessage(), e);
        return Result.fail(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(BizException.class)
    public Result<String> bizException(BizException e) {
        log.error("BizException -> [{}]", e.getMessage(), e);
        return Result.fail(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(GlobalException.class)
    public Result<String> globalException(GlobalException e) {
        log.error("GlobalException -> [{}]", e.getMessage(), e);
        return Result.fail(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public Result<String> httpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        log.error(e.getMessage(), e);
        return Result.fail(GlobalResultEnum.GLOBAL_REQUEST_METHOD_NOT_SUPPORTED.getCode(), GlobalResultEnum.GLOBAL_REQUEST_METHOD_NOT_SUPPORTED.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public Result<String> exception(Exception e) {
        log.error(e.getMessage(), e);
        return Result.fail(GlobalResultEnum.GLOBAL_FAIL.getCode(), GlobalResultEnum.GLOBAL_FAIL.getMessage());
    }
}
