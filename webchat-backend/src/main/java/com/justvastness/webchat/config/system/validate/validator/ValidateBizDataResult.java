package com.justvastness.webchat.config.system.validate.validator;

/**
 * <p>
 * 校验业务数据结果信息接口
 * </p>
 * @author Chen JianGuo
 * @version 1.0.0
 * @date 2021/10/21 16:28
 **/
public interface ValidateBizDataResult {
    /**
     * <p>
     * 获取校验结果信息
     * </p>
     * @return: java.lang.String
     * @author: Chen JianGuo
     * @date: 17:00 2021/10/21
     **/
    String getMessage();

    /**
     * <p>
     * 获取校验结果编码
     * </p>
     * @return: java.lang.String
     * @author: Chen JianGuo
     * @date: 17:01 2021/10/21
     **/
    String getCode();
}
