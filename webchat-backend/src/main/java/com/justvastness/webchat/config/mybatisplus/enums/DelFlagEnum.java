package com.justvastness.webchat.config.mybatisplus.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author wangdong
 * @version 1.0.0
 * @date 2020/12/6 15:50
 **/
@Getter
@AllArgsConstructor
public enum DelFlagEnum {

    /**
     * 删除标记（0：正常；1：删除;）
     */
    NOT_DELETED(0, "未删除"),
    DELETED(1, "已删除");

    @EnumValue
    private final int code;
    private final String desc;

    // 无参构造函数
    DelFlagEnum() {
        this.code = 0;
        this.desc = "";
    }
}
