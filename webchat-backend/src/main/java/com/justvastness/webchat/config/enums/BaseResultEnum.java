package com.justvastness.webchat.config.enums;

import com.justvastness.webchat.config.i18n.I18nUtil;
import com.justvastness.webchat.config.system.validate.validator.ValidateBizDataResult;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * AuthResultEnum
 *
 * @author wangdong
 * @version 1.0.0
 * @date 2021/9/27 3:46 下午
 **/
@Getter
@AllArgsConstructor
public enum BaseResultEnum implements ValidateBizDataResult {

    /**
     * 文件操作失败
     */
    FILE_IO_ERROR("B0001"),
    /**
     * 文件格式不正确
     */
    FILE_FORMAT_ERROR("B0001"),

    /**
     * 文件内容为空
     */
    FILE_CONTEXT_EMPTY("B0001"),

    /**
     * 字典类型代码已经存在
     */
    BASE_DICT_TYPE_CODE_ALREADY_EXISTS("B0001"),
    /**
     * 请先删除该类型下字典值
     */
    BASE_DICT_TYPE_EXISTS_DICT("B0001"),
    /**
     * 字典类型不存在
     */
    BASE_DICT_TYPE_NOT_EXISTS("B0001"),
    /**
     * 字典代码已经存在
     */
    BASE_DICT_CODE_ALREADY_EXISTS("B0001"),



    /**
     * 定时任务已存在相同函数
     */
    BASE_JOB_EXISTENT_ERROR("B0001"),
    /**
     * 定时任务信息不存在
     */
    BASE_JOB_NON_EXISTENT_ERROR("B0001"),
    /**
     * 获取表达式触发器失败
     */
    BASE_JOB_GET_CRON_TRIGGER_ERROR("B0001"),
    /**
     * 创建定时任务失败
     */
    BASE_JOB_CREATE_ERROR("B0001"),
    /**
     * 修改定时任务失败
     */
    BASE_JOB_UPDATE_ERROR("B0001"),
    /**
     * 运行定时任务失败
     */
    BASE_JOB_RUN_ERROR("B0001"),
    /**
     * 暂停任务失败
     */
    BASE_JOB_PAUSE_ERROR("B0001"),
    /**
     * 恢复任务失败
     */
    BASE_JOB_RESUME_ERROR("B0001"),
    /**
     * 删除任务失败
     */
    BASE_JOB_DELETE_ERROR("B0001"),

    ;


    private final String code;

    @Override
    public String getMessage() {
        return I18nUtil.message(this.toString());
    }
}
