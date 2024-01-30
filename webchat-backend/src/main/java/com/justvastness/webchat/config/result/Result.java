package com.justvastness.webchat.config.result;

import com.justvastness.webchat.config.enums.AuthResultEnum;
import com.justvastness.webchat.config.enums.BaseResultEnum;
import com.justvastness.webchat.config.enums.GlobalResultEnum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * Result
 * 统一返回参数
 *
 * @author WangDong
 * @version 1.0.0
 * @date 2020/6/1 3:39 下午
 **/
@Data
@Api(tags = "统一返回参数")
public class Result<T> implements Serializable {

    private static final long serialVersionUID = -8031438847417769246L;

    /**
     * 返回代码
     */
    @ApiModelProperty(value = "状态码", example = "00000", required = true)
    private String code;

    /**
     * 返回处理消息
     */
    @ApiModelProperty(value = "处理消息", example = "处理成功!!!", required = true)
    private String message;

    /**
     * 返回数据对象 data
     */
    @ApiModelProperty(value = "返回数据对象", example = "Object")
    private T result;

    public Result() {
        this.code = GlobalResultEnum.GLOBAL_SUCCESS.getCode();
        this.message = GlobalResultEnum.GLOBAL_SUCCESS.getMessage();
        this.result = null;
    }

    public static <T> Result<T> success() {
        return new Result<>();
    }

    public static <T> Result<T> success(T t) {
        Result<T> r = new Result<>();
        r.setResult(t);
        return r;
    }

    public static <T> Result<T> success(String code, String message, T t) {
        Result<T> r = new Result<>();
        r.setCode(code);
        r.setMessage(message);
        r.setResult(t);
        return r;
    }

    public static <T> Result<T> fail(AuthResultEnum enums) {
        return fail(enums.getCode(), enums.getMessage());
    }

    public static <T> Result<T> fail(GlobalResultEnum enums) {
        return fail(enums.getCode(), enums.getMessage());
    }

    public static <T> Result<T> fail(BaseResultEnum enums) {
        return fail(enums.getCode(), enums.getMessage());
    }

    public static <T> Result<T> fail(String code, String msg) {
        return fail(code, msg, null);
    }

    public static <T> Result<T> fail(String msg) {
        return fail(GlobalResultEnum.GLOBAL_FAIL.getCode(), msg, null);
    }

    public static <T> Result<T> fail() {
        return fail(GlobalResultEnum.GLOBAL_FAIL.getCode(), GlobalResultEnum.GLOBAL_FAIL.getMessage(), null);
    }

    public static <T> Result<T> fail(String code, String msg, T t) {
        Result<T> r = new Result<>();
        r.setCode(code);
        r.setMessage(msg);
        r.setResult(t);
        return r;
    }
}
